package br.com.mauker.materialsearchview.db

import android.test.AndroidTestCase
import androidx.room.Room
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class HistoryDatabaseTest : AndroidTestCase() {
    private lateinit var database: HistoryDatabase
    private lateinit var historyDAO: HistoryDAO

    @Before
    override fun setUp() {
        database = Room.inMemoryDatabaseBuilder(mContext, HistoryDatabase::class.java).allowMainThreadQueries().build()
        historyDAO = database.historyDAO()
    }

    @After
    override fun tearDown() {
        runBlocking {
            historyDAO.deleteAll()
            database.close()
        }
    }

    @Test
    fun testInsertReadItems() {
        // TODO: Implement
    }
}