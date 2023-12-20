package com.example.android_dragonball.Fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android_dragonball.Home.PrincipalActivityViewModel
import com.example.android_dragonball.databinding.FragmentFightBinding

class BattleFragment: Fragment() {
    private val viewModel: PrincipalActivityViewModel by viewModels()
    private lateinit var binding: FragmentFightBinding
}