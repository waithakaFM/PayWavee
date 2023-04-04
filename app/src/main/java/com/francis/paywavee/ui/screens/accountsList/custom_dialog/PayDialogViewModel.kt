package com.francis.paywavee.ui.screens.accountsList.custom_dialog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.francis.paywavee.R
import com.francis.paywavee.common.snackbar.SnackbarManager
import com.francis.paywavee.model.Item
import com.francis.paywavee.model.Transaction
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
    private val storageService: StorageService,
    savedStateHandle: SavedStateHandle
):  PayWaveViewModel(logService) {
    var isButtonEnabled by mutableStateOf(false)

    val transaction = mutableStateOf(Transaction())

    init {
        val itemCategory = savedStateHandle.get<String>("category")!!
        transaction.value = transaction.value.copy(category = itemCategory)
    }

    fun onAmountChange(newValue: String){
        transaction.value = transaction.value.copy(amount = newValue.toInt())
    }


    fun isAmountEmpty(amount: String): Boolean{
        isButtonEnabled = amount.isNotEmpty()
        return isButtonEnabled
    }

    fun onConfirm(phoneNumber: String,amount: String) {
        launchCatching {
            val addTransaction = transaction.value.copy(
                amount = amount.toInt()
            )
            if (addTransaction.amount.toString().isNotEmpty()) {
                darajaDriver.performStkPush(
                    stkPushRequest = darajaSTK(phoneNumber, amount)
                )
                storageService.saveTransaction(addTransaction)
                SnackbarManager.showMessage(R.string.enter_pin)
            }
        }
    }
}
