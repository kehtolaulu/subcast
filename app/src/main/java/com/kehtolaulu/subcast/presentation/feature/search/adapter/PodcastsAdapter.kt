package com.kehtolaulu.subcast.presentation.feature.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.domain.feature.search.Podcast
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_podcast.*

open class PodcastsAdapter : ListAdapter<Podcast, PodcastsAdapter.PodcastViewHolder>(
    PodcastsDiffCallback()
) {

    var listItemClickListener: PodcastOnClickListener? = null
    var podcasts: ArrayList<Podcast>? = null

    init {
        podcasts = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PodcastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_podcast, parent, false)
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
            tv_name.text = podcast.name
            btn_subscribe.setOnClickListener {
                podcast.let {
                    listItemClickListener?.subscribe(it)
                }
            }

            containerView.setOnClickListener {
                podcast.let {
                    listItemClickListener?.onClick(it)
                }
            }
        }
    }

    interface PodcastOnClickListener {
        fun onClick(podcast: Podcast)
        fun subscribe(podcast: Podcast)
    }
}
