package id.antasari.p6minda_230104040218.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// <- IMPORT PENTING
import id.antasari.p6minda_230104040218.data.DiaryEntry
import id.antasari.p6minda_230104040218.data.DiaryRepository
import id.antasari.p6minda_230104040218.data.MindaDatabase
import id.antasari.p6minda_230104040218.util.formatTimestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun TestRoomScreen() {
    val context = LocalContext.current
    // Mendapatkan instance Database dan Repository
    val db = remember { MindaDatabase.getInstance(context) }
    val repo = remember { DiaryRepository (db.diaryDao()) }
    // State untuk menyimpan daftar entry
    var entries by remember { mutableStateOf(listOf<DiaryEntry>()) }
    val scope = rememberCoroutineScope()

    // Fungsi untuk memuat ulang data dari DB
    fun loadEntries() {
        scope.launch {
            withContext(Dispatchers.IO) {
                entries = repo.allEntries()
            }
        }
    }

    // 1) Seed data sekali di awal (untuk demo praktikum)
    LaunchedEffect(true) {
        withContext(Dispatchers.IO) {
            if (repo.allEntries().isEmpty()) {
                // Entry 1
                repo.addEntry(
                    title = "My day 1",
                    content =
                        "What was the best thing about today and why?\n" +
                                "What were the challenges today and how did I overcome them?\n" +
                                "What was my emotional state today?",
                    mood = "Happy"
                )

                // Entry 2
                repo.addEntry(
                    title = "Gratitude journal",
                    content =
                        "What am I thankful for today?\n" +
                                "Who made my day better?",
                    mood = "Sad"
                )
            }
            loadEntries() // Muat data setelah seeding (jika ada)
        }
    }

    // UI
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Minda — Your mind, in one place.",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer (Modifier.height(12.dp))

        // Tombol untuk menambah dummy data lagi (KELANJUTAN DARI HAL. 10)
        Button(
            onClick = {
                scope.launch {
                    withContext(Dispatchers.IO) {
                        repo.addEntry(
                            title = "New entry at ${System.currentTimeMillis()}",
                            content = "Content for new entry",
                            mood = "Neutral"
                        )
                        loadEntries() // Muat ulang list setelah insert
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add dummy entry")
        }

        Spacer(Modifier.height(16.dp))

        // Daftar entry (KELANJUTAN DARI HAL. 10)
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(entries) { entry ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    //onClick = { /* TODO: Navigate to detail screen */ } // Nanti di Langkah 2
                    onClick = { /* Untuk saat ini, kosongkan saja */ }
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = entry.title,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Mood: ${entry.mood}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = formatTimestamp(entry.timestamp),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}