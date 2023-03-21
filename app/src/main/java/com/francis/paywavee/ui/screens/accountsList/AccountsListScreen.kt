package com.francis.paywavee.ui.screens.accountsList

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.francis.paywavee.common.composable.ActionToolbar
import com.francis.paywavee.common.util.smallSpacer
import com.francis.paywavee.common.util.toolbarActions
import com.francis.paywavee.R.string as AppText
import com.francis.paywavee.R.drawable as AppIcon


@OptIn(ExperimentalLifecycleComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalMaterialApi
fun AccountsListScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AccountsListScreenViewModel = hiltViewModel()
) {

    val itemList = viewModel
        .items
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
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            ActionToolbar(
                title = AppText.app_name,
                modifier = Modifier.toolbarActions(),
                endActionIcon = AppIcon.ic_settings,
                endAction = { viewModel.onSettingsClick(openScreen) }
            )

            Spacer(modifier = Modifier.smallSpacer())

            LazyColumn{
                items(itemList.value, key = {it.id}){ listItem ->
                    AccountListItem(
                        item = listItem,
                        modifier = Modifier
                            .clickable {
                                viewModel.onItemClick(openScreen, listItem)
                            }
                    )
                }
            }
        }
    }
}
