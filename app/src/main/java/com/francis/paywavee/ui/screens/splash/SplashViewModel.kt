package com.francis.paywavee.ui.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.francis.paywavee.PAY_ACCOUNTS
import com.francis.paywavee.SPLASH_SCREEN
import com.francis.paywavee.model.service.services.AccountService
import com.francis.paywavee.model.service.services.ConfigurationService
import com.francis.paywavee.model.service.services.LogService
import com.francis.paywavee.ui.screens.PayWaveViewModel
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    configurationService: ConfigurationService,
    private val accountService: AccountService,
    logService: LogService
) : PayWaveViewModel(logService) {
    val showError = mutableStateOf(false)

    init {
        launchCatching { configurationService.fetchConfiguration() }
    }

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {

        showError.value = false
        if (accountService.hasUser) openAndPopUp(PAY_ACCOUNTS, SPLASH_SCREEN)
        else createAnonymousAccount(openAndPopUp)
    }

    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
        launchCatching(snackbar = false) {
            try {
                accountService.createAnonymousAccount()
            } catch (ex: FirebaseAuthException) {
                showError.value = true
                throw ex
            }
            openAndPopUp(PAY_ACCOUNTS, SPLASH_SCREEN)
        }
    }
}