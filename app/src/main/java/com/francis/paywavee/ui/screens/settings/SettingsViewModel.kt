package com.francis.paywavee.ui.screens.settings

import com.francis.paywavee.LOGIN_SCREEN
import com.francis.paywavee.SIGN_UP_SCREEN
import com.francis.paywavee.SPLASH_SCREEN
import com.francis.paywavee.model.service.services.AccountService
import com.francis.paywavee.model.service.services.LogService
import com.francis.paywavee.ui.screens.PayWaveViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
) : PayWaveViewModel(logService) {

    // Check if the account is anonymous or not in order to update state
    val uiState = accountService.currentUser.map {
        SettingsUiState(it.isAnonymous)
    }

    fun onLoginClick(openScreen: (String) -> Unit) = openScreen(LOGIN_SCREEN)

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)

    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.signOut()
            restartApp(SPLASH_SCREEN)
        }
    }

    fun onDeleteMyAccountClick(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.deleteAccount()
            restartApp(SPLASH_SCREEN)
        }
    }
}