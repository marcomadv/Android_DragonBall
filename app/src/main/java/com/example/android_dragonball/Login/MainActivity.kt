package com.example.android_dragonball.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.android_dragonball.Home.PrincipalActivity
import com.example.android_dragonball.Models.Hero
import com.example.android_dragonball.Login.MainActivityViewModel
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
        binding.buttonLogin.setOnClickListener {
            viewModel.launchLogin(binding.editTextUser.text.toString(), binding.editTextPassword.text.toString())}

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

    private fun showSuccessGetHeroes(heroList: List<Hero>) {
    }

    private fun showSuccessLogin() {
        showLoading(false)
        Toast.makeText(this,"Login exitoso", Toast.LENGTH_LONG).show()
        startActivity(PrincipalActivity().launch(this, viewModel.token))
    }

    private fun idle(){
        //default status
    }

    private fun showLoading(show: Boolean) {
        if (show)
            binding.progressBarLogin.visibility = View.VISIBLE
        else
            binding.progressBarLogin.visibility = View.GONE
    }

    private fun showError(message: String) {
        showLoading(false)
        Toast.makeText(this, message, Toast.LENGTH_LONG)
    }
}