package com.example.android_dragonball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.android_dragonball.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        binding.buttonLogin.setOnClickListener {viewModel.launchLogin(binding.editTextUser.toString(), binding.editTextPassword.toString())}
    }

    private fun setObservers(){
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.uiState.collect { state ->
                when(state) {
                    is MainActivityViewModel.State.Idle -> idle()
                    is MainActivityViewModel.State.Error -> showError(state.message)
                    is MainActivityViewModel.State.Loading -> showLoading(true)
                    is MainActivityViewModel.State.SuccessLogin -> showSuccessLogin()
                    is MainActivityViewModel.State.SuccessGetHeroes -> showSuccessGetHeroes(state.heroList)
                }
            }
        }
    }
}