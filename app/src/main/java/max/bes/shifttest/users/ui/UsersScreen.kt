package max.bes.shifttest.users.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import max.bes.shifttest.R
import max.bes.shifttest.core.ui.elements.Error
import max.bes.shifttest.core.ui.elements.Loading
import max.bes.shifttest.core.ui.theme.Blue
import max.bes.shifttest.core.ui.theme.CardBgColor
import max.bes.shifttest.users.domain.models.User
import max.bes.shifttest.users.presentation.UsersScreenState
import max.bes.shifttest.users.presentation.UsersViewModel

@Composable
fun UsersScreen(viewModel: UsersViewModel = hiltViewModel()) {

    val uiState by viewModel.screenState.observeAsState(initial = UsersScreenState.Loading)

    UsersScreenContent(
        uiState = uiState,
        onUserItemClick = { },
        refreshUsers = viewModel::updateUsersFromNetwork,
        onEmailClick = viewModel::sendEmail,
        onPhoneClick = viewModel::makePhoneCall,
        onAddressClick = { latitude, longitude -> viewModel.openMap(latitude, longitude) },
    )

}

@Composable
fun UsersScreenContent(
    uiState: UsersScreenState,
    onUserItemClick: (Int) -> Unit,
    refreshUsers: () -> Unit,
    onEmailClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
    onAddressClick: (String, String) -> Unit,

    ) {

    when (uiState) {
        is UsersScreenState.Loading -> Loading()
        is UsersScreenState.Error -> Error(error = uiState.error)
        is UsersScreenState.Content -> UserList(
            uiState.users,
            onUserItemClick,
            refreshUsers,
            onEmailClick,
            onPhoneClick,
            onAddressClick
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserList(
    users: List<User>,
    onUserItemClick: (Int) -> Unit,
    refreshUsers: () -> Unit,
    onEmailClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
    onAddressClick: (String, String) -> Unit,
) {
    val state = rememberPullToRefreshState()
    if (state.isRefreshing) {
        LaunchedEffect(true) {
            refreshUsers.invoke()
            delay(1500)
            state.endRefresh()
        }
    }

    Box(
        modifier = Modifier
            .nestedScroll(state.nestedScrollConnection),
        contentAlignment = Alignment.Center
    ) {

        PullToRefreshContainer(
            modifier = Modifier
                .align(Alignment.TopCenter),
            state = state,
            contentColor = Blue,
            containerColor = if (!state.isRefreshing) Color.Transparent
            else MaterialTheme.colorScheme.background
        )
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 12.dp)
        ) {
            items(
                items = users,
                key = { user -> user.id }
            ) { user ->
                if (!state.isRefreshing) {
                    UserListItem(
                        user,
                        onUserItemClick,
                        onEmailClick,
                        onPhoneClick,
                        onAddressClick
                    )
                }
            }
        }
    }
}

@Composable
fun UserListItem(
    user: User,
    onUserItemClick: (Int) -> Unit,
    onEmailClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
    onAddressClick: (String, String) -> Unit,
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = CardBgColor,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 12.dp, end = 20.dp)
            .clickable { onUserItemClick(user.id) },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            AsyncImage(
                model = user.pictureLarge,
                contentDescription = "${user.firstName} ${user.lastName} picture",
                error = painterResource(id = R.drawable.user_picture_placeholder),
                placeholder = painterResource(id = R.drawable.user_picture_placeholder),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(4.dp)),
            )
            Column {
                Text(
                    text = "${user.firstName} ${user.lastName}"
                )
                Text(
                    text = "${user.country} ${user.city}",
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { onAddressClick(user.latitude, user.longitude) }
                )
                Text(
                    text = user.phone,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { onPhoneClick(user.phone) }
                )

                Text(
                    text = user.email,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { onEmailClick(user.email) }
                )
            }

        }

    }

}