package com.francis.paywavee

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.francis.paywavee.common.snackbar.SnackbarManager
import com.francis.paywavee.ui.screens.accountsList.AccountsListScreen
import com.francis.paywavee.ui.screens.add_edit.AddEditScreen
import com.francis.paywavee.ui.screens.login.LoginScreen
import com.francis.paywavee.ui.screens.settings.SettingsScreen
import com.francis.paywavee.ui.screens.sign_up.SignUpScreen
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

    composable(PAY_ACCOUNTS) { AccountsListScreen(openScreen = { route -> appState.navigate(route) }) }

    composable(
        route = "$ADD_EDIT$ITEM_ID_ARG",
        arguments = listOf(navArgument(ITEM_ID) { defaultValue = ITEM_DEFAULT_ID })
    ) {
        AddEditScreen(
            popUpScreen = { appState.popUp() },
            itemId = it.arguments?.getString(ITEM_ID) ?: ITEM_DEFAULT_ID
        )
    }
}