package com.francis.paywavee

import android.content.res.Resources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.compose.*
import com.francis.paywavee.common.snackbar.SnackbarManager
import com.francis.paywavee.ui.screens.accountsList.AccountsListScreen
import com.francis.paywavee.ui.screens.add_edit.AddEditScreen
import com.francis.paywavee.ui.screens.accountsList.custom_dialog.CustomDialog
import com.francis.paywavee.ui.screens.login.LoginScreen
import com.francis.paywavee.ui.screens.settings.SettingsScreen
import com.francis.paywavee.ui.screens.sign_up.SignUpScreen
import com.francis.paywavee.ui.screens.spending.SpendingScreen
import com.francis.paywavee.ui.screens.splash.SplashScreen
import com.francis.paywavee.ui.theme.PayWaveeTheme
import kotlinx.coroutines.CoroutineScope


@Composable
@ExperimentalMaterialApi
fun PayWaveApp() {
    PayWaveeTheme {
        Surface(color = MaterialTheme.colors.background) {

            val appState = rememberAppState()

            // place the layout foundation of the screen
            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                        }
                    )
                },
                bottomBar = {
                            BottomNavBar(
                                items = listOf(
                                    BottomNavItem(
                                        name = "Pay",
                                        route = PAY_ACCOUNTS,
                                        icon = painterResource(id = R.drawable.ic_account)
                                    ),
                                    BottomNavItem(
                                        name = "Add",
                                        route = "$ADD_EDIT$ITEM_ID_ARG",
                                        icon = painterResource(id = R.drawable.ic_add_edit)
                                    ),
                                    BottomNavItem(
                                        name = "Spending",
                                        route = SPENDING_SCREEN,
                                        icon = painterResource(id = R.drawable.ic_spendings)
                                    ),
                                ),
                                navController = appState.navController,
                                onItemClick = {
                                    appState.navController.navigate(it.route)
                                }
                            )
                },
                scaffoldState = appState.scaffoldState

            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    payWaveGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
        PayWaveAppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@ExperimentalMaterialApi
fun NavGraphBuilder.payWaveGraph(appState: PayWaveAppState) {
    
    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SETTINGS_SCREEN) {
        SettingsScreen(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(LOGIN_SCREEN) {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(PAY_ACCOUNTS) {
        AccountsListScreen(openScreen = { route -> appState.navigate(route) }){
            appState.navController
                .navigate(PAY_DIALOG + "?category=${it.category}"+"?paybill=${it.payBill}"+"?phone=${it.phoneNumber}")
        }
    }


    composable(
        route = "$ADD_EDIT$ITEM_ID_ARG",
        arguments = listOf(navArgument(ITEM_ID) { defaultValue = ITEM_DEFAULT_ID })
    ) {
        AddEditScreen(
            popUpScreen = { appState.popUp() },
            itemId = it.arguments?.getString(ITEM_ID) ?: ITEM_DEFAULT_ID
        )
    }

    composable(SPENDING_SCREEN){
        SpendingScreen()
    }

    dialog(
        "$PAY_DIALOG?category={category}?paybill={paybill}?phone={phone}", arguments = listOf(
            navArgument("category"){
                type = NavType.StringType },
            navArgument("paybill"){
                type = NavType.StringType },
            navArgument("phone"){
                type = NavType.StringType }
        )){ navBackStackEntry ->
        val category =   navBackStackEntry.arguments?.getString("category")!!
        val paybill =   navBackStackEntry.arguments?.getString("paybill")!!
        val phone =   navBackStackEntry.arguments?.getString("phone")!!
        CustomDialog(
            category = category,
            paybill = paybill,
            phone = phone ,
            onDismiss = { appState.navController.popBackStack()})
    }
}

@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach{ item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = {onItemClick(item)},
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.name,
                        )
                        if (selected){
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}
