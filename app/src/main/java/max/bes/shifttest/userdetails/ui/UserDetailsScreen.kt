package max.bes.shifttest.userdetails.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import max.bes.shifttest.R
import max.bes.shifttest.core.ui.elements.Loading
import max.bes.shifttest.core.ui.theme.Blue
import max.bes.shifttest.userdetails.presentation.UserDetailsScreenState
import max.bes.shifttest.userdetails.presentation.UserDetailsViewModel
import max.bes.shifttest.users.domain.models.User

@Composable
fun UserDetailsScreen(viewModel: UserDetailsViewModel = hiltViewModel()) {
    val uiState by viewModel.screenState.observeAsState(initial = UserDetailsScreenState.Loading)

    UserDetailsScreenContent(
        uiState = uiState,
        onEmailClick = viewModel::sendEmail,
        onPhoneClick = viewModel::makePhoneCall,
        onAddressClick = { latitude, longitude -> viewModel.openMap(latitude, longitude) },
        onErrorRefresh = { }
    )
}

@Composable
fun UserDetailsScreenContent(
    uiState: UserDetailsScreenState,
    onEmailClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
    onAddressClick: (String, String) -> Unit,
    onErrorRefresh: () -> Unit
) {

    when (uiState) {
        is UserDetailsScreenState.Loading -> Loading()
        is UserDetailsScreenState.Error -> max.bes.shifttest.core.ui.elements.Error(
            uiState.error,
            onErrorRefresh
        )

        is UserDetailsScreenState.Content -> UserInfo(
            user = uiState.user,
            onEmailClick = onEmailClick,
            onPhoneClick = onPhoneClick,
            onAddressClick = onAddressClick
        )
    }
}

@Composable
fun UserInfo(
    user: User,
    onEmailClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
    onAddressClick: (String, String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = user.pictureLarge,
            contentDescription = "${user.firstName} ${user.lastName} picture",
            error = painterResource(id = R.drawable.user_picture_placeholder),
            placeholder = painterResource(id = R.drawable.user_picture_placeholder),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${user.firstName} ${user.lastName}",
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${user.country}, ${user.city}",
            fontSize = 16.sp,
            color = Blue,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier
                .clickable { onAddressClick(user.latitude, user.longitude) }
                .padding(start = 4.dp)
        )

        TextItemInfoAsLink(stringResource(id = R.string.phone), user.phone, onPhoneClick)
        TextItemInfoAsLink(stringResource(id = R.string.email), user.email, onEmailClick)

    }

}

@Composable
fun TextItemInfoAsLink(title: String, text: String, onClick: (String) -> Unit) {
    Spacer(modifier = Modifier.height(4.dp))

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = Blue,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.clickable { onClick(text) }
        )
    }

}