package com.francis.paywavee.ui.screens.accountsList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.francis.paywavee.ADD_EDIT
import com.francis.paywavee.ITEM_ID
import com.francis.paywavee.SETTINGS_SCREEN
import com.francis.paywavee.model.Item
import com.francis.paywavee.model.service.services.ConfigurationService
import com.francis.paywavee.model.service.services.LogService
import com.francis.paywavee.model.service.services.StorageService
import com.francis.paywavee.ui.screens.PayWaveViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountsListScreenViewModel @Inject constructor(
    logService: LogService,
    private val storageService: StorageService,
    private val configurationService: ConfigurationService
) : PayWaveViewModel(logService) {

    val items = storageService.items

    fun onSettingsClick(
        openScreen: (String) -> Unit
    ) = openScreen(SETTINGS_SCREEN)

    fun onClickPayBtn(){

    }

    fun onItemCheckChange(item: Item){
        launchCatching {
            storageService
                .update(item.copy(completed = !item.completed))
        }
    }

    fun onItemClick(
        openScreen: (String) -> Unit,
        item: Item
    ) = openScreen("$ADD_EDIT?$ITEM_ID={${item.id}}")

    fun onAddClick(
        openScreen: (String) -> Unit
    ) = openScreen(ADD_EDIT)


    var isDialogShown by mutableStateOf(false)
        private set

    fun onPayClick(){
        isDialogShown = true
    }

    fun onDismissDialog(){
        isDialogShown = false
    }

    private fun onDeleteTaskClick(item: Item){
        launchCatching { storageService.delete(item.id) }
    }
}