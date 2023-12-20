package com.example.android_dragonball.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_dragonball.Login.MainActivityViewModel
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

    private val _uiHomeState = MutableStateFlow<HeroesState>(HeroesState.Idle())
    val uiHomeState: StateFlow<HeroesState> = _uiHomeState

    sealed class HeroesState {
        class HeroesSuccess(val heroeList: List<Hero>): HeroesState()
        class Error(val message: String): HeroesState()
        class Loading(): HeroesState()
        class Idle(): HeroesState()

    }
    fun launchGetHeroes(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiHomeState.value = HeroesState.Loading()
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
            _uiHomeState.value = if(response.isSuccessful)
                response.body?.let {
                    val heroesArray: Array<HeroDto> =
                        Gson().fromJson(it.string(), Array<HeroDto>::class.java)
                    val heroList = heroesArray.toList().map {
                        Hero(it.id, it.name, it.photo)
                    }
                    HeroesState.HeroesSuccess(heroList)
                } ?: HeroesState.Error("Call Failed")
            else
                HeroesState.Error(response.message)
        }
    }

    fun damageLife(hero: Hero) {
        hero.currentLife = hero.currentLife - (20..50).random()
    }

    fun cure(hero: Hero) {
        if (hero.currentLife in 70..100) {
            hero.currentLife = 100
        } else {
            hero.currentLife = hero.currentLife + 30
        }
    }
}