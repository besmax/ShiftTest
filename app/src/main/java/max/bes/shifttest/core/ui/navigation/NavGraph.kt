package max.bes.shifttest.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import max.bes.shifttest.users.ui.UsersScreen

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.UsersScreen.route) {
        composable(route = Screen.UsersScreen.route) {
            UsersScreen()
        }
    }
}