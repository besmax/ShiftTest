package max.bes.shifttest.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import max.bes.shifttest.core.ui.navigation.NavigationGraph
import max.bes.shifttest.core.ui.theme.ShiftTestTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShiftTestTheme {
                val navController = rememberNavController()

                Scaffold { paddingValues ->
                    Box(
                        modifier = Modifier.padding(paddingValues)
                    ) {

                        NavigationGraph(navController)
                    }
                }
            }
        }
    }
}

