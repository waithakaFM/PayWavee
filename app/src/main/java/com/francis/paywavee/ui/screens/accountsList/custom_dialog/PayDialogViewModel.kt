package com.francis.paywavee.ui.screens.accountsList.custom_dialog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.francis.paywavee.R
import com.francis.paywavee.common.snackbar.SnackbarManager
import com.francis.paywavee.model.service.mpesa.darajaDriver
import com.francis.paywavee.model.service.mpesa.darajaSTK
import com.francis.paywavee.model.service.services.LogService
import com.francis.paywavee.model.service.services.StorageService
import com.francis.paywavee.ui.screens.PayWaveViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PayDialogViewModel@Inject constructor(
    logService: LogService,
    private val storageService: StorageService
):  PayWaveViewModel(logService) {
    var isButtonEnabled by mutableStateOf(false)

    fun isAmountEmpty(amount: String): Boolean{
        isButtonEnabled = amount.isNotEmpty()
        return isButtonEnabled
    }

    fun onConfirm(phoneNumber: String, amount: String) {
        launchCatching {
            if (amount.isNotEmpty()) {
                darajaDriver.performStkPush(
                    stkPushRequest = darajaSTK(phoneNumber, amount)
                )
                SnackbarManager.showMessage(R.string.enter_pin)
            }
        }
    }
}
