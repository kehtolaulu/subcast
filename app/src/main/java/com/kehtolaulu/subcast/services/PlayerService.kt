package com.kehtolaulu.subcast.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import com.kehtolaulu.subcast.constants.ACTION_NEXT
import com.kehtolaulu.subcast.constants.ACTION_PLAY_PAUSE
import com.kehtolaulu.subcast.constants.ACTION_PREVIOUS
import com.kehtolaulu.subcast.entities.Episode
import java.io.IOException
import java.util.*

class PlayerService : Service() {
    override fun onBind(intent: Intent) = mBinder
    private val mBinder = PlayerServiceBinder()
    private lateinit var mediaPlayer: MediaPlayer

    override fun onUnbind(intent: Intent): Boolean {
        mediaPlayer.stop()
        mediaPlayer.release()
        return false
    }

    inner class PlayerServiceBinder : Binder() {
        fun isPlaying() = this@PlayerService.isPlaying
        fun next() = this@PlayerService.next()
        fun previous() = this@PlayerService.previous()
        fun getCurrentPosition() = this@PlayerService.getCurrentPosition()
        fun seekTo(seconds: Int) = this@PlayerService.seekTo(seconds)
        fun playOrPauseSong(episode: Episode? = null) = this@PlayerService.playOrPauseEpisode(episode)
        fun stopService() = this@PlayerService.stopForegroundService()
        fun getActiveEpisode() = this@PlayerService.activeEpisode
        fun setSongsList(episodes: List<Episode>) {
            this@PlayerService.toPlay = episodes
        }
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
//            showToast("No previous elements")
            playedEpisodesIndexes.add(previousIndex)
        }
    }

    private fun stopForegroundService() {
        mediaPlayer.stop()
        mediaPlayer.release()
        stopForeground(true)
        stopSelf()
    }

    private var playedEpisodesIndexes = LinkedList<Int>().apply { add(0) }
    private var toPlay: List<Episode> = ArrayList()
    private var prepared = false
    private var activeEpisode: Episode? = null
    private var isPlaying: Boolean? = false

    private fun playOrPauseEpisode(episode: Episode?, addToHistory: Boolean = true) {
        try {
            if (episode == null)
                playOrPause()
            else {
                if (prepared) {
                    prepared = false
                    mediaPlayer.reset()
                }
                mediaPlayer.setDataSource(this, Uri.parse(episode.path))
                activeEpisode = episode
                if (addToHistory)
                    playedEpisodesIndexes.add(toPlay.indexOf(episode))
                playOrPause()
            }
        } catch (e: IOException) {
//            showToast(FILE_NOT_FOUND)
            activeEpisode = null
            prepared = false
            mediaPlayer.reset()
        }
    }

    private fun playOrPause() {
        if (activeEpisode != null) {
            with(mediaPlayer) {
                if (isPlaying)
                    pause()
                else {
                    if (!prepared) {
                        prepare()
                        prepared = true
                    }
                    start()
                }
            }
            updateIsPlaying()
//            setupNotification()
        } else {
//            showToast(FILE_NOT_FOUND)
        }
    }

    private fun updateIsPlaying() {
        isPlaying = mediaPlayer.isPlaying
    }

    private fun seekTo(seconds: Int) = mediaPlayer.seekTo(seconds * 1000)

    private fun getCurrentPosition() =
        if (prepared) mediaPlayer.currentPosition / 1000 else 0


}
