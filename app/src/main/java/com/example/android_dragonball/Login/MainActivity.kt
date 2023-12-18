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
        binding.buttonLogin?.setOnClickListener {
            lifecycleScope.launch{
                viewModel.launchLogin(binding.editTextUser.text.toString(), binding.editTextPassword.text.toString())
                viewModel.uiState.collect {
                    when(it){
                        is MainActivityViewModel.State.TokenSucces ->
                            startActivity(PrincipalActivity().launch(this@MainActivity, it.text))
                        is MainActivityViewModel.State.Error -> showError("Error en Login")
                        is MainActivityViewModel.State.Idle -> {}
                        is MainActivityViewModel.State.Loading -> showLoading(true)
                    }
                }
            }

        }
    }

    private fun showLoading(show: Boolean) {
        if (show)
            binding.progressBarLogin.visibility = View.VISIBLE
        else
            binding.progressBarLogin.visibility = View.GONE
    }

    private fun showError(message: String) {
        showLoading(false)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}