package com.kehtolaulu.subcast.ui

import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.entities.Episode
import kotlinx.android.extensions.LayoutContainer
import com.kehtolaulu.subcast.utils.DownloadDiffCallback
import kotlinx.android.synthetic.main.card_download.*

class DownloadsAdapter : ListAdapter<Episode, DownloadsAdapter.DownloadViewHolder>(DownloadDiffCallback()) {

    var listItemClickListener: ListItemClickListener? = null
    var episodesList: ArrayList<Episode>? = null

    init {
        episodesList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DownloadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_download, parent, false)
        return DownloadViewHolder(view)
    }

    override fun onBindViewHolder(holder: DownloadViewHolder, position: Int) {
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

    inner class DownloadViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(episode: Episode) {
            txt_name.text = episode.name
            txt_description.text = episode.description

            containerView.setOnClickListener {
                episode.let {
                    listItemClickListener?.onClick(it)
                }
            }
        }
    }

    interface ListItemClickListener {
        fun onClick(episode: Episode)
    }

}
