package com.example.android_dragonball.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_dragonball.ViewModels.PrincipalActivityViewModel
import com.example.android_dragonball.databinding.FragmentListBinding

class HeroesList: Fragment() {

    private val viewModel: PrincipalActivityViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.launchGetHeroes()
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        binding.recyclerView.adapter = HeroesAdapter(viewModel.heroList, viewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)

    }
}