package com.example.android_dragonball.Home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.android_dragonball.Fragment.HeroesList
import com.example.android_dragonball.View.MainActivity
import com.example.android_dragonball.databinding.ActivityPrincipalBinding
import kotlinx.coroutines.CoroutineScope

class PrincipalActivity : AppCompatActivity() {

    private val viewModel: PrincipalActivityViewModel by viewModels()
    private lateinit var binding: ActivityPrincipalBinding

    companion object {
        const val TOKEN = "TOKEN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("TOKEN")

        viewModel.launchGetHeroes(token.toString())
        fragment(HeroesList())
        }

    fun launch(context: Context, text: String): Intent {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(TOKEN, text)
        context.startActivity(intent)
        return intent
    }

    fun fragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.frameLayout.id, fragment)
            .commit()
    }
}