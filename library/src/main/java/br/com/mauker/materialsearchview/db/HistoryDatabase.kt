package br.com.mauker.materialsearchview.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(HistoryItem::class)], version = 5)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDAO(): HistoryDAO

    companion object {
        private var INSTANCE: HistoryDatabase? = null

        fun getInMemoryDatabase(context: Context): HistoryDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context,
                        HistoryDatabase::class.java, "SearchHistory.db")
                        .build()
            }

            return INSTANCE!!
        }
    }
}