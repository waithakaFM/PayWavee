package com.francis.paywavee.ui.screens.accountsList

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import com.francis.paywavee.ADD_EDIT
import com.francis.paywavee.ITEM_ID
import com.francis.paywavee.R
import com.francis.paywavee.SETTINGS_SCREEN
import com.francis.paywavee.common.snackbar.SnackbarManager
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

    fun onSettingsClick(
        openScreen: (String) -> Unit
    ) = openScreen(SETTINGS_SCREEN)

    val items = storageService.items

    fun onCategoryChange(category: String)  = storageService.queryItem(category)



    fun onOptionActionClick(openScreen: (String) -> Unit, item: Item, action: String) {
        when(ItemDropDown.getByTitle(action)){
            ItemDropDown.Edit -> {openScreen("$ADD_EDIT?$ITEM_ID={${item.id}}")}
            ItemDropDown.Favorite -> { onItemCheckChange(item = item)}
            ItemDropDown.Delete -> onDeleteItemClick(item)
        }
    }


    private fun onItemCheckChange(item: Item){
        launchCatching {
            storageService
                .update(item.copy(completed = !item.completed))
        }
    }

    fun onAddClick(
        openScreen: (String) -> Unit
    ) = openScreen(ADD_EDIT)


    private fun onDeleteItemClick(item: Item){
        launchCatching {
            SnackbarManager.showMessage(R.string.delete_item)
            storageService.delete(item.id)
        }
    }
}