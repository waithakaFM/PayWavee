package com.francis.paywavee.ui.screens.add_edit

// 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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.francis.paywavee.common.composable.*
import com.francis.paywavee.common.util.card
import com.francis.paywavee.common.util.fieldModifier
import com.francis.paywavee.common.util.spacer
import com.francis.paywavee.common.util.toolbarActions
import com.francis.paywavee.model.Category
import com.francis.paywavee.model.Item
import com.francis.paywavee.R.drawable as AppIcon
import com.francis.paywavee.R.string as AppText

@Composable
@ExperimentalMaterialApi
fun AddEditScreen(
    popUpScreen: () -> Unit,
    itemId: String,
    modifier: Modifier = Modifier,
    viewModel: AddEditViewModel = hiltViewModel()
){
    val item by viewModel.item

    LaunchedEffect(Unit){
        viewModel.initialize(itemId)
    }
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionSaveToolbar(
            title = AppText.add_edit,
            modifier = Modifier.toolbarActions(),
            endAction = {viewModel.onDoneClick(popUpScreen)}
        )

        Spacer(modifier = Modifier.spacer())
        CardSelectors(item = item, onCategoryChange = viewModel::onCategoryChange)

        Spacer(modifier = Modifier.spacer())

        val fieldModifier = Modifier.fieldModifier()
        BasicField(text = AppText.entity, value = item.entity,  viewModel::onEntityChange, fieldModifier)
        NumberField(text = AppText.payBill, value = item.payBill, viewModel::onPayBillChange, fieldModifier)
        BasicField(text = AppText.acc_no, value = item.accountNumber,  viewModel::onAccountNumberChange, fieldModifier)
        PhoneNumber(text = AppText.phone_no, value = item.phoneNumber,  viewModel::onPhoneNumberChange, fieldModifier)

        Spacer(modifier = Modifier.spacer())

    }
}

@Composable
@ExperimentalMaterialApi
private fun CardSelectors(
    item: Item,
    onCategoryChange: (String) -> Unit,
) {
    val categorySelection = Category.getByName(item.category).name
    CardSelector(
        label = AppText.category,
        options = Category.getOptions(),
        selection = categorySelection,
        modifier = Modifier.card()
    ) { newValue ->
        onCategoryChange(newValue)
    }
}