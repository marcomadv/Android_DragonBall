package com.example.android_dragonball.Fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_dragonball.Models.Hero
import com.example.android_dragonball.R
import com.example.android_dragonball.Home.PrincipalActivityViewModel
import com.example.android_dragonball.databinding.HeroeCellBinding

class HeroesAdapter(val heroesList: List<Hero>,val viewModel: PrincipalActivityViewModel): RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {
    class HeroesViewHolder(val binding: HeroeCellBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            binding.heroeName.text = hero.name
            binding.lifeBar.max = hero.maxLife
            binding.lifeBar.progress = hero.currentLife
            Glide
                .with(binding.root)
                .load(hero.photo)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.heroeImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return  HeroesViewHolder(
            HeroeCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return heroesList.size
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(heroesList[position])
    }
}