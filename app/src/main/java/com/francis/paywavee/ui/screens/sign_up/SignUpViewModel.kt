package com.francis.paywavee.ui.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import com.francis.paywavee.SETTINGS_SCREEN
import com.francis.paywavee.SIGN_UP_SCREEN
import com.francis.paywavee.common.snackbar.SnackbarManager
import com.francis.paywavee.common.util.isValidEmail
import com.francis.paywavee.common.util.isValidPassword
import com.francis.paywavee.common.util.passwordMatches
import com.francis.paywavee.model.service.services.AccountService
import com.francis.paywavee.model.service.services.LogService
import com.francis.paywavee.ui.screens.PayWaveViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.francis.paywavee.R.string as AppText
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
): PayWaveViewModel(logService) {

    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email

    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String){
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String){
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        launchCatching {
            // Linking the anonymous account with new
            accountService.linkAccount(email, password)
            openAndPopUp(SETTINGS_SCREEN, SIGN_UP_SCREEN)
        }
    }
}