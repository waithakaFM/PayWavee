package com.francis.paywavee.ui.screens.accountsList

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.francis.paywavee.common.composable.ActionToolbar
import com.francis.paywavee.common.util.smallSpacer
import com.francis.paywavee.common.util.toolbarActions
import com.francis.paywavee.model.Item
import com.francis.paywavee.ui.theme.ButtonBlue
import com.francis.paywavee.ui.theme.DarkerButtonBlue
import com.francis.paywavee.ui.theme.TextWhite
import com.francis.paywavee.R.string as AppText
import com.francis.paywavee.R.drawable as AppIcon


@OptIn(ExperimentalLifecycleComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalMaterialApi
fun AccountsListScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AccountsListScreenViewModel = hiltViewModel(),
    onPayClick: (Item) -> Unit
) {

    val categoryValue = remember {
        mutableStateOf("All")
    }

    val itemList = viewModel
        .items
        .collectAsStateWithLifecycle(emptyList())

    val queryList = viewModel
        .onCategoryChange(categoryValue.value)
        .collectAsStateWithLifecycle(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onAddClick(openScreen) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            ActionToolbar(
                title = AppText.app_name,
                modifier = Modifier.toolbarActions(),
                endActionIcon = AppIcon.ic_settings,
                endAction = { viewModel.onSettingsClick(openScreen) }
            )
            // Scrollable row
            ChipSection(
                chips = CategorySearch.getOptions(),
                onChipClick = { categorySelected ->
                    categoryValue.value = categorySelected
                }
            )
            Spacer(modifier = Modifier.smallSpacer())
            Items(
                items =if(categoryValue.value == "All") itemList.value else queryList.value,
                onItemClick = {
                    viewModel.onItemClick(openScreen, it)
                },
                onPayClick = {
                    onPayClick(it)
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Items(
    items: List<Item>,
    modifier: Modifier = Modifier,
    onItemClick: (Item) -> Unit,
    onPayClick: (Item) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        items(items, key = { it.id }) { listItem ->
            AccountListItem(
                item = listItem,
                modifier = modifier
                    .clickable {
                        onItemClick(listItem)
                    },
                onPayClick = {
                    onPayClick(listItem)
                }
            )
        }
    }
}

@Composable
fun ChipSection(
    chips: List<String>,
    onChipClick: (String) -> Unit
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        itemsIndexed(chips) { index, categories ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp, bottom = 15.dp)
                    .clickable {
                        onChipClick(categories)
                        selectedChipIndex = index
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == index) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(text = categories, color = TextWhite)
            }
        }
        items(chips.size) {
        }
    }
}

