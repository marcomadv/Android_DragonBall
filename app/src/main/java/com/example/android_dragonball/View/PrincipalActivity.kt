package com.example.android_dragonball.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_dragonball.R
import com.example.android_dragonball.databinding.ActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}