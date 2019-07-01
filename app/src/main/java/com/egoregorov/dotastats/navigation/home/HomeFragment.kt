package com.egoregorov.dotastats.navigation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.egoregorov.dotastats.R
import com.egoregorov.dotastats.models.DotaHeroSimple
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var adapter: HeroListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HeroListAdapter()
        val viewManager = LinearLayoutManager(context)
        homeFragmentHeroRecyclerView.layoutManager = viewManager
        homeFragmentHeroRecyclerView.adapter = adapter
        homeViewModel.heroesList.observe(this, Observer {
            setHeroesList(it)
        })
        homeViewModel.heroesList.value?.let {
            setHeroesList(it)
        } ?: run {
            homeFragmentLoadingSpinner.visibility = View.VISIBLE
            homeFragmentHeroRecyclerView.visibility = View.GONE
        }
    }

    fun setHeroesList(heroesList: List<DotaHeroSimple>) {
        adapter.listOfHeroes = heroesList
        homeFragmentLoadingSpinner.visibility = View.GONE
        homeFragmentHeroRecyclerView.visibility = View.VISIBLE
    }
}
