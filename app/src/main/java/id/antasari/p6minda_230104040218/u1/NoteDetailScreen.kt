@file:OptIn(ExperimentalMaterial3Api::class)

package id.antasari.p6minda_230104040218.ui

import android.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import id.antasari.p6minda_230104040218.data.DiaryEntry
import id.antasari.p6minda_230104040218.data.DiaryRepository
import id.antasari.p6minda_230104040218.data.MindaDatabase
import id.antasari.p6minda_230104040218.util.formatTimestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun NoteDetailScreen(
    entryId: Int,
    onBack: () -> Unit,
    onEntryDeleted: () -> Unit,
    onEdit: (Int) -> Unit
) {
    val context = LocalContext.current
    val db = remember { MindaDatabase.getInstance(context) }
    val repo = remember { DiaryRepository(db.diaryDao()) }
    val scope = rememberCoroutineScope()

    var entry by remember { mutableStateOf<DiaryEntry?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }

    LaunchedEffect(entryId) {
        if (entryId > 0) {
            withContext(Dispatchers.IO) {
                entry = repo.findEntry(entryId)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = entry?.title ?: "Loading...") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    // Tombol Edit
                    IconButton(onClick = { entry?.let { onEdit(it.id) } }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit Note")
                    }

                    // Overflow menu
                    Box {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(Icons.Filled.MoreVert, contentDescription = "More actions")
                        }

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Delete") },
                                onClick = {
                                    showMenu = false
                                    showDeleteDialog = true
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        // Main content
        if (entry != null) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Mood: ${entry!!.mood}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Created: ${formatTimestamp(entry!!.timestamp)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Divider(Modifier.padding(vertical = 12.dp))
                Text(
                    text = entry!!.content,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Entry not found or still loading...")
            }
        }

        // Konfirmasi Dialog Delete
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            scope.launch {
                                entry?.let {
                                    withContext(Dispatchers.IO) {
                                        repo.remove(it)
                                    }
                                    onEntryDeleted() // Balik ke Home
                                }
                                showDeleteDialog = false
                            }
                        }
                    ) {
                        Text("Delete", color = MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text("Cancel")
                    }
                },
                title = { Text("Confirm Deletion") },
                text = { Text("Are you sure you want to permanently delete this diary entry?")
                }
            )
        }
    }
}