package com.example.android_dragonball

import com.example.android_dragonball.Home.PrincipalActivityViewModel
import com.example.android_dragonball.Models.Hero
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PrincipialActivityViewModelTest {

    val viewModel = PrincipalActivityViewModel()
    val hero1 = Hero("1", "Marco", "https://cdn.alfabetajuega.com/alfabetajuega/2020/06/Roshi.jpg?width=300", 40)
    val hero2 = Hero("2", "Carlos","https://cdn.alfabetajuega.com/alfabetajuega/2020/12/goku1.jpg?width=300", 70)
    @Test
    fun `comprobar funciones de vida`() {
        viewModel.damageLife(hero1)
        assertFalse( hero1.currentLife == 40)
        viewModel.cure(hero2)
        assertTrue(hero2.currentLife == 100)

    }
}