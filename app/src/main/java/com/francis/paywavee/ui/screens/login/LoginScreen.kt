package com.francis.paywavee.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.francis.paywavee.common.composable.*
import com.francis.paywavee.common.util.basicButton
import com.francis.paywavee.common.util.fieldModifier
import com.francis.paywavee.common.util.textButton
import com.francis.paywavee.R.string as AppText

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    BasicToolbar(AppText.login_details)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(
            uiState.email,
            viewModel::onEmailChange,
            Modifier.fieldModifier()
        )

        PasswordField(
            uiState.password,
            viewModel::onPasswordChange,
            Modifier.fieldModifier()
        )

        BasicButton(
            AppText.sign_in,
            Modifier.basicButton()
        ) {
            viewModel.onSignInClick(openAndPopUp)
        }

        BasicTextButton(AppText.forgot_password, Modifier.textButton()) {
            viewModel.onForgotPasswordClick()
        }
    }
}