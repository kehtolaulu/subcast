package com.kehtolaulu.subcast.utils

import androidx.recyclerview.widget.DiffUtil
import com.kehtolaulu.subcast.entities.Episode

class EpisodesDiffCallback : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean =
        oldItem == newItem


    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean =
        oldItem == newItem
}
