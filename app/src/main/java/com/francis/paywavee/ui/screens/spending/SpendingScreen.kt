package com.francis.paywavee.ui.screens.spending

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SpendingScreen(
    spendingViewModel: SpendingViewModel = hiltViewModel(),
    popUpScreen: () -> Unit
){

    val utilities = spendingViewModel.utilities.collectAsState(initial = emptyList()).value.sumOf { it.amount}
    val food = spendingViewModel.food.collectAsState(initial = emptyList()).value.sumOf { it.amount}
    val rent = spendingViewModel.rent.collectAsState(initial = emptyList()).value.sumOf { it.amount }
    val cloths = spendingViewModel.cloths.collectAsState(initial = emptyList()).value.sumOf { it.amount}
    val entertainment = spendingViewModel.entertainment.collectAsState(initial = emptyList()).value.sumOf { it.amount}
    val shopping = spendingViewModel.shopping.collectAsState(initial = emptyList()).value.sumOf { it.amount}
    val others = spendingViewModel.others.collectAsState(initial = emptyList()).value.sumOf { it.amount }

    val chartDetails = listOf(utilities,food,rent,cloths,entertainment,shopping,others)
    val total = chartDetails.sum()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Monthly Spending")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        spendingViewModel.onBackClick(popUpScreen)
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // Preview with sample data
            PieChart(
                data = mapOf(
                    Pair("utilities", utilities),
                    Pair("food", food),
                    Pair("rent", rent),
                    Pair("cloths", cloths),
                    Pair("entertainment", entertainment),
                    Pair("others", others),
                    Pair("shopping", shopping),
                ),
                total = total
            )

        }
    }


}



