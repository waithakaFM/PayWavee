package com.francis.paywavee.ui.screens.spending

import com.francis.paywavee.model.service.services.LogService
import com.francis.paywavee.model.service.services.StorageService
import com.francis.paywavee.ui.screens.PayWaveViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpendingViewModel @Inject constructor(
    logService: LogService,
    private val storageService: StorageService
):  PayWaveViewModel(logService){


    val utilities = storageService.queryTransaction("Utilities")
    val food = storageService.queryTransaction("Food")
    val rent = storageService.queryTransaction("Rent")
    val cloths = storageService.queryTransaction("Cloths")
    val entertainment = storageService.queryTransaction("Entertainment")
    val shopping = storageService.queryTransaction("Shopping")
    val others = storageService.queryTransaction("Others")

}