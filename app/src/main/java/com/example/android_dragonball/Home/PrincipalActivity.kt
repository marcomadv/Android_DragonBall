package com.example.android_dragonball.Home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.android_dragonball.Fragment.HeroesList
import com.example.android_dragonball.Login.MainActivityViewModel
import com.example.android_dragonball.Models.Hero
import com.example.android_dragonball.View.MainActivity
import com.example.android_dragonball.databinding.ActivityPrincipalBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PrincipalActivity : AppCompatActivity() {

    companion object {
        val TOKEN = "TOKEN"

        fun lanzarActivity(context: Context, texto: String ) {
            val intent = Intent(context, PrincipalActivity::class.java)
            intent.putExtra(TOKEN, texto)
            context.startActivity(intent)
        }
    }

    private val viewModel: PrincipalActivityViewModel by viewModels()
    private lateinit var binding: ActivityPrincipalBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("TOKEN")
        viewModel.launchGetHeroes(token.toString())
        setObservers()
        }

    private fun setObservers(){
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.uiHomeState.collect { state ->
                when(state) {
                    is PrincipalActivityViewModel.HeroesState.Idle -> {}
                    is PrincipalActivityViewModel.HeroesState.Error -> ShowError(state.message)
                    is PrincipalActivityViewModel.HeroesState.Loading -> ShowLoading(true)
                    is PrincipalActivityViewModel.HeroesState.HeroesSuccess -> ShowHeroes(state.heroeList)
                }
            }
        }
    }

    private fun ShowError(message: String){
        ShowLoading(false)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun ShowLoading(show: Boolean){
        if (show)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }

    private fun ShowHeroes( heroList: List<Hero>){
        ShowLoading(false)
        fragment(HeroesList(heroList))
    }

    fun fragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.frameLayout.id, fragment)
            .commitNow()
    }
}



