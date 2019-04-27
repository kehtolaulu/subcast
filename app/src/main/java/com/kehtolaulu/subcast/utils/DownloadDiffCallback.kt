package com.kehtolaulu.subcast.utils

import androidx.recyclerview.widget.DiffUtil
import com.kehtolaulu.subcast.entities.Episode

class DownloadDiffCallback : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(p0: Episode, p1: Episode): Boolean =
        p0 == p1


    override fun areContentsTheSame(p0: Episode, p1: Episode): Boolean =
        p0 == p1
}
