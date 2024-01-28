package max.bes.shifttest.core.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import max.bes.shifttest.R
import max.bes.shifttest.users.domain.models.ErrorType

@Composable
fun Error(error: ErrorType) {
    Column(
        modifier = Modifier
            .padding(top = 140.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(
                id = when (error) {
                    ErrorType.SERVER_ERROR -> R.string.error_server
                    ErrorType.NO_INTERNET -> R.string.error_no_internet
                    ErrorType.NO_CONTENT -> R.string.error_no_content
                }
            )
        )
    }
}