package br.com.mauker.materialsearchview.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDAO {
    @Query("SELECT * FROM SEARCH_HISTORY WHERE `query` LIKE :query ORDER BY is_history DESC, `query`")
    suspend fun getHistoryForQuery(query: String): List<HistoryItem>

    @Insert
    suspend fun insert(historyItem: HistoryItem): Long

    @Query("DELETE FROM SEARCH_HISTORY")
    suspend fun deleteAll(): Int
}