package com.kehtolaulu.subcast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.extensions.toMinutesSecondsFormat
import com.kehtolaulu.subcast.utils.EpisodesDiffCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_episode.*

open class EpisodesAdapter : ListAdapter<Episode, EpisodesAdapter.EpisodeViewHolder>(EpisodesDiffCallback()) {

    var listItemClickListener: EpisodeOnClickListener? = null
    var episodesList: ArrayList<Episode>? = null

    init {
        episodesList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_episode, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
        }
    }

    override fun submitList(list: MutableList<Episode>?) {
        episodesList?.also {
            it.clear()
            if (list != null) {
                it.addAll(list)
            }
        }
        super.submitList(list)
    }

    fun getList(): ArrayList<Episode> {
        var list: ArrayList<Episode> = ArrayList()
        for (i in 0 until itemCount) {
            list.add(getItem(i))
        }
        return list
    }

    inner class EpisodeViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(episode: Episode) {
            txt_name.text = episode.name
            if (episode.progress != 0 && episode.progress != null) {
                tv_progress.text = episode.progress!!.toMinutesSecondsFormat()
            } else {
                tv_progress.visibility = View.INVISIBLE
            }

            btn_to_downloads.setOnClickListener {
                episode.let {
                    listItemClickListener?.download(episode)
                }
            }

            btn_to_later.setOnClickListener {
                episode.let {
                    listItemClickListener?.listenLater(episode)
                }
            }

            containerView.setOnClickListener {
                episode.let {
                    listItemClickListener?.onClick(it, episodesList)
                }
            }
        }
    }

    interface EpisodeOnClickListener {
        fun onClick(
            episode: Episode,
            episodesList: ArrayList<Episode>?
        )

        fun download(
            episode: Episode
        )

        fun listenLater(
            episode: Episode
        )
    }
}
