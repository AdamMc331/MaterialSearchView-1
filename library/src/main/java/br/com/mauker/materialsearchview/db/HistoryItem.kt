package br.com.mauker.materialsearchview.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SEARCH_HISTORY")
data class HistoryItem(
        @PrimaryKey @ColumnInfo(name = "_id") val id: Long? = null,
        @ColumnInfo(name = "query") val query: String? = null,
        @ColumnInfo(name = "insert_date") val insertDate: Int? = null,
        @ColumnInfo(name = "is_history") val isHistory: Boolean? = null
)