package max.bes.shifttest.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import max.bes.shifttest.userdetails.ui.UserDetailsScreen
import max.bes.shifttest.users.ui.UsersScreen

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.UsersScreen.route) {
        composable(route = Screen.UsersScreen.route) {
            UsersScreen(navHostController)
        }

        composable(
            route = Screen.UserDetailsScreen.route,
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.IntType
                    nullable = false
                }
            )) {

            UserDetailsScreen()
        }
    }
}