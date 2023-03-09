package com.francis.paywavee.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francis.paywavee.common.snackbar.SnackbarManager
import com.francis.paywavee.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.francis.paywavee.model.service.services.LogService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


open class PayWaveViewModel(
    private val logService: LogService
    ) : ViewModel() {

    fun launchCatching(
        snackbar: Boolean = true,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(
        CoroutineExceptionHandler { _, throwable ->
            if (snackbar) {
                SnackbarManager.showMessage(throwable.toSnackbarMessage())
            }
            logService.logNonFatalCrash(throwable)
        },
        block = block
    )
}