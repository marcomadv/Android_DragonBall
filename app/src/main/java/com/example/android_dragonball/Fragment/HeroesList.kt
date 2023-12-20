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
import com.example.android_dragonball.Models.Hero
import com.example.android_dragonball.databinding.ActivityMainBinding
import com.example.android_dragonball.databinding.FragmentListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HeroesList(var heroeList: List<Hero>): Fragment() {

    init {
        heroeList = this.heroeList
    }

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
            val adapter = HeroesAdapter(heroeList, viewModel)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
    }
}