package id.antasari.p6minda_230104040218.data

import androidx.room.*

@Dao
interface DiaryDao {
    @Insert
    suspend fun insert(entry: DiaryEntry)

    @Update
    suspend fun update (entry: DiaryEntry)

    @Delete
    suspend fun delete(entry: DiaryEntry)
// ... (kode sebelumnya)

    @Query("SELECT * FROM diary_entries WHERE id = :id")
    suspend fun getById(id: Int): DiaryEntry?
    fun getAll(): List<DiaryEntry>
}