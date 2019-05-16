package com.kehtolaulu.subcast.presentation.feature.details.adapter

import androidx.recyclerview.widget.DiffUtil
import com.kehtolaulu.subcast.domain.feature.details.Episode

class EpisodesDiffCallback : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean =
        oldItem == newItem


    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean =
        oldItem == newItem
}
