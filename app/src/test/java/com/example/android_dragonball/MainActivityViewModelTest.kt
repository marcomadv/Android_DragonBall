package com.example.android_dragonball

import com.example.android_dragonball.Login.MainActivityViewModel
import org.junit.Test
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull

// Intento de testeo de estados en viewmodel

/*
class MainActivityViewModelTest {

    val viewModel = MainActivityViewModel()

    @Test
    suspend fun `comprobar login`() {
        viewModel.uiState.collect {
            var token: String = ""

            when (it) {
                is MainActivityViewModel.State.Idle -> {
                    assertNull(token.toString(), 0)
                }

                is MainActivityViewModel.State.Error -> {
                    assertNotNull(it.message)
                }

                is MainActivityViewModel.State.Loading -> {
                    assertNull(token.toString(), 0)
                }

                is MainActivityViewModel.State.SuccessLogin -> {
                    token = viewModel.token
                    assertNotNull(token)
                }
            }
        }
    }
}
*/