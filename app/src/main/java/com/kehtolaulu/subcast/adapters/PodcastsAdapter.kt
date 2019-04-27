package com.kehtolaulu.subcast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.entities.Podcast
import com.kehtolaulu.subcast.utils.SearchDiffCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_download.*

class PodcastsAdapter : ListAdapter<Podcast, PodcastsAdapter.PodcastViewHolder>(SearchDiffCallback()) {

    var listItemClickListener: PodcastOnClickListener? = null
    var podcasts: ArrayList<Podcast>? = null

    init {
        podcasts = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PodcastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_search, parent, false)
        return PodcastViewHolder(view)
    }

    override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
        }
    }

    override fun submitList(list: MutableList<Podcast>?) {
        podcasts?.also {
            it.clear()
            it.addAll(list as ArrayList)
        }
        super.submitList(podcasts)
    }

    fun getList(): ArrayList<Podcast> {
        var list: ArrayList<Podcast> = ArrayList()
        for (i in 0 until itemCount) {
            list.add(getItem(i))
        }
        return list
    }

    inner class PodcastViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(podcast: Podcast) {
            txt_name.text = podcast.name

            containerView.setOnClickListener {
                podcast.let {
                    listItemClickListener?.onClick(it)
                }
            }
        }
    }

    interface PodcastOnClickListener {
        fun onClick(podcast: Podcast)
    }
}
