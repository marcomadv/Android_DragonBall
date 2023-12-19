package com.example.android_dragonball.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_dragonball.Home.PrincipalActivityViewModel
import com.example.android_dragonball.databinding.ActivityMainBinding
import com.example.android_dragonball.databinding.FragmentListBinding
import kotlinx.coroutines.launch

class HeroesList: Fragment() {

    private val viewModel: PrincipalActivityViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            val listaHeroes = viewModel.heroList
            val adapter = HeroesAdapter(listaHeroes, viewModel)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        }
    }
/*
    private fun configureRecyclerView() {
        binding.recyclerView.adapter = HeroesAdapter(viewModel.heroList, viewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)

    }*/
}