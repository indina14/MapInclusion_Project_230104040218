package id.antasari.p6minda_230104040218

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.R
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import id.antasari.p6minda_230104040218.ui.TestRoomScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MindaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Sementara, kita tampilkan TestRoomScreen
                    // untuk memverifikasi Room ORM sudah bekerja.
                    TestRoomScreen()

                    // Nanti, kode ini akan diganti dengan AppNavHost:
                    // AppNavHost()
                }
            }
        }
    }
}