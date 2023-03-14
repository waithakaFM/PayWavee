package com.francis.paywavee.ui.screens.add_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.francis.paywavee.ITEM_DEFAULT_ID
import com.francis.paywavee.common.util.idFromParameter
import com.francis.paywavee.model.Item
import com.francis.paywavee.model.service.services.LogService
import com.francis.paywavee.model.service.services.StorageService
import com.francis.paywavee.ui.screens.PayWaveViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    logService: LogService,
    private val storageService: StorageService
):  PayWaveViewModel(logService){

    val item = mutableStateOf(Item())

    fun initialize(itemId: String){
        launchCatching {
            if (itemId != ITEM_DEFAULT_ID){
                item.value = storageService.getItem(itemId.idFromParameter()) ?: Item()
            }
        }
    }

    fun onCategoryChange(newValue: String) {
        item.value = item.value.copy(category = newValue)
    }

    fun onEntityChange(newValue: String) {
        item.value = item.value.copy(entity = newValue)
    }

    fun onPayBillChange(newValue: String){
        item.value = item.value.copy(payBill = newValue)
    }

    fun onAccountNumberChange(newValue: String) {
        item.value = item.value.copy(accountNumber = newValue)
    }

    fun onPhoneNumberChange(newValue: String){
        item.value = item.value.copy(phoneNumber = newValue)
    }

    fun onDoneClick( popUpScreen: () -> Unit){
        launchCatching {
            val editedItem = item.value
            if (editedItem.id.isBlank()){
                storageService.save(editedItem)
            }else{
                storageService.update(editedItem)
            }
            popUpScreen()
        }
    }

}