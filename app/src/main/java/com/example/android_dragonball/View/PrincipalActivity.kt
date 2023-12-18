package com.example.android_dragonball.View

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.android_dragonball.R
import com.example.android_dragonball.ViewModels.PrincipalActivityViewModel
import com.example.android_dragonball.databinding.ActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {

    private val viewModel: PrincipalActivityViewModel by viewModels()
    private lateinit var binding: ActivityPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        private const val TOKEN = ""
        fun launch(context: Context, text: String) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(TOKEN, text)
            context.startActivity(intent)
        }
    }
}