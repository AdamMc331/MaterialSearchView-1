package br.com.mauker.materialsearchview.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HistoryDatabaseTest {
    private lateinit var database: HistoryDatabase
    private lateinit var historyDAO: HistoryDAO

    @Before
    fun setUp() {
        val mContext = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(mContext, HistoryDatabase::class.java).allowMainThreadQueries().build()
        historyDAO = database.historyDAO()
    }

    @After
    fun tearDown() {
        runBlocking {
            historyDAO.deleteAll()
            database.close()
        }
    }

    @Test
    fun testInsertReadItem() {
        runBlocking {
            val testQuery = "Adam"
            val testItem = HistoryItem(query = testQuery)

            val newId = historyDAO.insert(testItem)

            val expected = testItem.copy(id = newId)
            val results = historyDAO.getHistoryForQuery(testQuery)
            assertEquals(expected, results.first())
        }
    }
}