package com.kehtolaulu.subcast.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.helpers.*
import com.kehtolaulu.subcast.presentation.extensions.showToast
import com.kehtolaulu.subcast.presentation.feature.player.activity.PlayerActivity
import java.io.IOException
import java.util.LinkedList
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class PlayerService : Service() {
    private var playedEpisodesIndexes = LinkedList<Int>().apply { add(0) }
    private var toPlay: List<Episode> = ArrayList()
    private var prepared = false
    private var activeEpisode: Episode? = null
    private var isPlaying: Boolean? = false

    override fun onBind(intent: Intent) = mBinder
    private val mBinder = PlayerServiceBinder()
    private val mediaPlayer by lazy {
        MediaPlayer().apply {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
            setAudioAttributes(audioAttributes)
        }
    }

    override fun onUnbind(intent: Intent): Boolean {
        mediaPlayer.stop()
        mediaPlayer.release()
        return false
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when (intent.action) {
            ACTION_PLAY_PAUSE -> playOrPause()
            ACTION_NEXT -> next()
            ACTION_PREVIOUS -> previous()
        }
        return START_NOT_STICKY
    }

    private fun next() {
        val nextIndex = playedEpisodesIndexes.last() + 1
        if (nextIndex < toPlay.size)
            playOrPauseEpisode(toPlay[nextIndex])
    }

    private fun previous() {
        var previousIndex = 0
        try {
            previousIndex = playedEpisodesIndexes.removeLast()
            playOrPauseEpisode(toPlay[playedEpisodesIndexes.last()], addToHistory = false)
        } catch (e: NoSuchElementException) {
            showToast("No previous elements")
            playedEpisodesIndexes.add(previousIndex)
        }
    }

    private fun stopForegroundService() {
        mediaPlayer.stop()
        mediaPlayer.release()
        stopForeground(true)
        stopSelf()
    }


    private fun playOrPauseEpisode(episode: Episode?, addToHistory: Boolean = true) {
        try {
            if (episode == null) {
                playOrPause()
            } else {
                if (prepared) {
                    prepared = false
                    mediaPlayer.reset()
                }
                if (episode.path == null) {
                    mediaPlayer.setDataSource(episode.url)
                } else {
                    mediaPlayer.setDataSource(episode.path)
                }
                activeEpisode = episode
                if (addToHistory)
                    playedEpisodesIndexes.add(toPlay.indexOf(episode))
                playOrPause()
            }
        } catch (e: IOException) {
            showToast(FILE_NOT_FOUND)
            activeEpisode = null
            prepared = false
            mediaPlayer.reset()
        }
    }

    private fun playOrPause() {
        if (activeEpisode != null) {
            with(mediaPlayer) {
                if (isPlaying) {
                    pause()
                } else {
                    if (!prepared) {
                        prepare()
                        prepared = true
                    }
                    start()
                }
            }
            updateIsPlaying()
            setupNotification()
        } else {
            showToast(FILE_NOT_FOUND)
        }
    }

    private fun setupNotification() {
        val largeIconBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background)

        val playOrPauseButton =
            if (isPlaying == true)
                android.R.drawable.ic_media_pause
            else android.R.drawable.ic_media_play

        var usesChronometer = false
        var ongoing = false
        var howLong = 0L
        var showWhen = false
        if (isPlaying == true) {
            howLong = System.currentTimeMillis() - mediaPlayer.currentPosition
            usesChronometer = true
            ongoing = true
            showWhen = true
        }

        createNotificationChannel(this)
        val notification = NotificationCompat.Builder(this,
            CHANNEL_ID
        ).apply {
            setWhen(howLong)
            setShowWhen(showWhen)
            setContentTitle(activeEpisode?.name)
            setOngoing(ongoing)
            setContentIntent(getContentIntent())
            setUsesChronometer(usesChronometer)
            setLargeIcon(largeIconBitmap)
            priority = if (isOreoPlus()) NotificationManager.IMPORTANCE_HIGH else NotificationCompat.PRIORITY_HIGH
            setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0, 1, 2)
                    .setShowCancelButton(true)
            )
            addAction(android.R.drawable.ic_media_previous, "Previous", getActionIntent(ACTION_PREVIOUS))
            addAction(playOrPauseButton, "Play / Pause", getActionIntent(ACTION_PLAY_PAUSE))
            addAction(android.R.drawable.ic_media_next, "Next", getActionIntent(ACTION_NEXT))
        }
        startForeground(1, notification.build())

        if (isPlaying != true) {
            stopForeground(false)
        }
    }

    private fun createNotificationChannel(context: Context) {
        if (isOreoPlus()) {
            val name = context.getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = getString(R.string.channel_description)
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun getContentIntent(): PendingIntent {
        val contentIntent = Intent(this, PlayerActivity::class.java)
        return PendingIntent.getActivity(this, 0, contentIntent, 0)
    }

    private fun getActionIntent(action: String): PendingIntent {
        val intent = Intent(this, PlayerService::class.java)
        intent.action = action
        return PendingIntent.getService(applicationContext, 0, intent, 0)
    }


    private fun updateIsPlaying() {
        isPlaying = mediaPlayer.isPlaying
    }

    private fun seekTo(seconds: Int) = mediaPlayer.seekTo(seconds * 1000)

    private fun getCurrentPosition() =
        if (prepared) mediaPlayer.currentPosition / 1000 else 0

    private fun playFrom(episode: Episode, time: Int, addToHistory: Boolean = true) {
        playOrPauseEpisode(episode)
        seekTo(time)
    }

    inner class PlayerServiceBinder : Binder() {
        fun isPlaying() = this@PlayerService.isPlaying
        fun next() = this@PlayerService.next()
        fun previous() = this@PlayerService.previous()
        fun getCurrentPosition() = this@PlayerService.getCurrentPosition()
        fun seekTo(seconds: Int) = this@PlayerService.seekTo(seconds)
        fun playOrPauseEpisode(episode: Episode? = null) = this@PlayerService.playOrPauseEpisode(episode)
        fun stopService() = this@PlayerService.stopForegroundService()
        fun getActiveEpisode() = this@PlayerService.activeEpisode
        fun setEpisodeList(episodes: List<Episode>) {
            this@PlayerService.toPlay = episodes
        }

        fun playFrom(episode: Episode, time: Int) = this@PlayerService.playFrom(episode, time)
    }

    companion object {
        private const val CHANNEL_ID = "MUSIC_PLAYER_CHANNEL_ID"
    }
}
