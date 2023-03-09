package com.francis.paywavee.ui.screens.accountsList

import com.francis.paywavee.SETTINGS_SCREEN
import com.francis.paywavee.model.service.services.LogService
import com.francis.paywavee.ui.screens.PayWaveViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountsListScreenViewModel @Inject constructor(
    logService: LogService,
) : PayWaveViewModel(logService) {

    fun onSettingsClick(
        openScreen: (String) -> Unit
    ) = openScreen(SETTINGS_SCREEN)
}