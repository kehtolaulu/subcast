package com.kehtolaulu.subcast.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.adapters.EpisodesAdapter
import com.kehtolaulu.subcast.di.components.DaggerDetailsComponent
import com.kehtolaulu.subcast.di.modules.DetailsModule
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.entities.Podcast
import com.kehtolaulu.subcast.helpers.ARG_PODCAST_ID
import com.kehtolaulu.subcast.presentation.feature.details.presenter.DetailsPresenter
import com.kehtolaulu.subcast.presentation.feature.details.view.DetailsView
import kotlinx.android.synthetic.main.fragment_details.view.*
import javax.inject.Inject

class DetailsFragment : MvpAppCompatFragment(), DetailsView {

    private var adapter: EpisodesAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    private fun initDagger() {
        DaggerDetailsComponent.builder()
            .appComponent(MyApplication.appComponent)
            .detailsModule(DetailsModule())
            .build()
            .inject(this)
    }

    override fun showPodcast(podcast: Podcast) {
        thisPodcast = podcast
        presenter.getPodcastEpisodes(podcast)
    }

    override fun updateAdapter(podcast: Podcast) {
        presenter.updateAdapter(podcast)
    }

    override fun submitListIntoAdapter(list: List<Episode>) {
        addPodcastId(list)
        adapter?.submitList(list as MutableList<Episode>)
    }

    private fun addPodcastId(list: List<Episode>) {
        list.map{
            it.podcastId = thisPodcast?.id
        }
    }

    override fun addElementsToAdapter(list: List<Episode>) {
        val episodes: ArrayList<Episode> = ArrayList()
        adapter?.getList()?.let { episodes.addAll(it) }
        episodes.addAll(episodes)
        adapter?.submitList(episodes)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun providePresenter(): DetailsPresenter = presenter

    var thisPodcast: Podcast? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        adapter = EpisodesAdapter()
        val rv = view.recycler_details
        rv.adapter = adapter
        adapter?.listItemClickListener = activity as MainActivity
        rv.layoutManager = LinearLayoutManager(activity)
        presenter.getPodcast(arguments?.getInt(ARG_PODCAST_ID))

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(podcast: Podcast) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PODCAST_ID, podcast.id)
                }
            }
    }
}
