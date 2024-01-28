package max.bes.shifttest.core.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class Screen(
    val route: String,
    @StringRes val titleResId: Int? = null,
    @DrawableRes val iconResId: Int? = null
) {
    object UsersScreen : Screen("usersScreen")
    object UserDetailsScreen : Screen("userDetailsScreen/{user}")
}
