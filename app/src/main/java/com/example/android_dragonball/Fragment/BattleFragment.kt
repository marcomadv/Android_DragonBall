package com.example.android_dragonball.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.android_dragonball.Home.PrincipalActivity
import com.example.android_dragonball.Home.PrincipalActivityViewModel
import com.example.android_dragonball.Models.Hero
import com.example.android_dragonball.R
import com.example.android_dragonball.databinding.FragmentFightBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.security.Principal

class BattleFragment(var hero: Hero): Fragment() {
    private val viewModel: PrincipalActivityViewModel by viewModels()
    private lateinit var binding: FragmentFightBinding
    init {
        hero = this.hero
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFightBinding.inflate(inflater)
        adapterHero(hero)
        return binding.root
    }

    private fun adapterHero(hero: Hero) {
        binding.NamePlayer1.text = hero.name
        binding.lifeBar1.max = hero.maxLife
        binding.lifeBar1.progress = hero.currentLife
        Glide
            .with(binding.root)
            .load(hero.photo)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.ImagePlayer1)
    }
}