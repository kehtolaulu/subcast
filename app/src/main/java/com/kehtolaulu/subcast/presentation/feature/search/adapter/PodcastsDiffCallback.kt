package com.kehtolaulu.subcast.presentation.feature.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.kehtolaulu.subcast.domain.feature.search.Podcast

class PodcastsDiffCallback : DiffUtil.ItemCallback<Podcast>() {
    override fun areItemsTheSame(oldItem: Podcast, newItem: Podcast): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Podcast, newItem: Podcast): Boolean =
        oldItem == newItem
}
