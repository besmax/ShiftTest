package max.bes.shifttest.core.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import max.bes.shifttest.core.ui.theme.Blue

@Composable
fun Loading() {
    Column(
        modifier = Modifier
            .padding(top = 140.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(44.dp),
            color = Blue,
        )
    }
}