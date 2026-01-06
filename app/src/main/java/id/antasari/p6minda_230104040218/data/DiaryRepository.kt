package id.antasari.p6minda_230104040218.data

import androidx.room.Dao

class DiaryRepository (private val dao: DiaryDao) {

    suspend fun addEntry(
        title: String,
        content: String,
        mood: String
    ) {
        val entry = DiaryEntry(
            title = title,
            content = content,
            mood = mood,
            timestamp = System.currentTimeMillis()
        )
        dao.insert(entry)
    }
    // File: DiaryRepository.kt (Update)

// ... (kode sebelumnya)

    suspend fun findEntry(id: Int): DiaryEntry? = dao.getById(id)

    suspend fun allEntries(): List<DiaryEntry> = dao.getAll()
    suspend fun remove(entry: DiaryEntry)= dao.delete(entry)
    suspend fun edit(entry: DiaryEntry)= dao.update(entry)

}