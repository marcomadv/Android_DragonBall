package com.example.android_dragonball.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_dragonball.Models.Hero
import com.example.android_dragonball.Models.HeroDto
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class PrincipalActivityViewModel: ViewModel() {
    val BASE_URL = "https://dragonball.keepcoding.education/api/"
    private val _uiState = MutableStateFlow<MainActivityViewModel.State>(MainActivityViewModel.State.Idle())
    val uiState: StateFlow<MainActivityViewModel.State> = _uiState
    var token = ""

    fun launchGetHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = MainActivityViewModel.State.Loading()
            val client = OkHttpClient()
            val url = "${BASE_URL}heros/all"
            val formBody = FormBody.Builder()
                .add("name", "")
                .build()
            val request = Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer $token")
                .post(formBody)
                .build()
            val call = client.newCall(request)
            val response = call.execute()
            _uiState.value = if (response.isSuccessful)
                response.body?.let {
                    val heroesArray: Array<HeroDto> =
                        Gson().fromJson(it.string(), Array<HeroDto>::class.java)
                    val heroList = heroesArray.map {
                        Hero(it.id, it.name, it.photo)
                    }
                    MainActivityViewModel.State.SuccessGetHeroes(heroList.toList())
                } ?: MainActivityViewModel.State.Error("Call Failed")
            else
                MainActivityViewModel.State.Error(response.message)
        }
    }
}