package com.example.android_dragonball

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivityViewModel: ViewModel() {
    val BASE_URL = "https://dragonball.keepcoding.education/api/"
    private val _uiState = MutableStateFlow<State>(State.Idle())
    val uiState: StateFlow<State> = _uiState
    private var token = ""

    sealed class State {
        class Idle : State()
        class Error(val message: String) : State()
        class Loading: State()
        class SuccessLogin : State()
        class SuccessGetHeroes(val heroList: List<Hero>) : State()
    }

    fun launchLogin(username: String, password: String) {
        viewModelScope.launch (Dispatchers.IO) {
            _uiState.value = State.Loading()
            val client = OkHttpClient ()
            val url = "${BASE_URL}auth/login"
            val credentials = Credentials.basic(username, password)
            val formBody = FormBody.Builder()
                .build ()
            val request = Request.Builder()
                .url (url)
                .addHeader ("Authorization", credentials)
                .post(formBody)
                .build()
            val call = client.newCall (request)
            val response = call.execute()
            _uiState.value = if (response.isSuccessful)
                response.body?.let {
                    token = it.string()
                    State.SuccessLogin()
                } ?: State.Error("Empty Token")
            else
                State.Error(response.message)
        }
    }
    fun launchGetHeroes(){
        viewModelScope.launch (Dispatchers.IO) {
            _uiState.value = State.Loading()
            val client = OkHttpClient ()
            val url = "${BASE_URL}heros/all"
            val formBody = FormBody.Builder()
                .add("name", "")
                .build ()
            val request = Request.Builder()
                .url (url)
                .addHeader ("Authorization", "Bearer $token")
                .post(formBody)
                .build()
            val call = client.newCall (request)
            val response = call.execute()
            _uiState.value = if (response.isSuccessful)
                response.body?.let {
                    val heroesArray: Array<HeroDto> = Gson().fromJson(it.string(), Array<HeroDto>::class.java)
                    val heroList = heroesArray.map {
                        Hero(it.name,it.photo)
                    }
                    State.SuccessGetHeroes(heroList.toList())
                } ?: State.Error("Call Failed")
            else
                State.Error(response.message)
        }
    }

}