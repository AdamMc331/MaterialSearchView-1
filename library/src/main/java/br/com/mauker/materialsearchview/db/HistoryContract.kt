package br.com.mauker.materialsearchview.db

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns

/**
 * Created by mauker on 15/04/16.
 * Database contract. Contains the definition of my tables.
 */
object HistoryContract {

    val CONTENT_AUTHORITY = initAuthority()

    private val BASE_CONTENT_URI = Uri.parse("content://$CONTENT_AUTHORITY")

    const val PATH_HISTORY = "history"

    // ----- Authority setup ----- //

    private fun initAuthority(): String {
        var authority = "br.com.mauker.materialsearchview.defaultsearchhistorydatabase"

        try {
            val clazzLoader = HistoryContract::class.java.classLoader
            val clazz = clazzLoader?.loadClass("br.com.mauker.MsvAuthority")
            val field = clazz?.getDeclaredField("CONTENT_AUTHORITY")

            authority = field?.get(null).toString()

        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        return authority
    }

    // ----- Table definitions ----- //

    class HistoryEntry : BaseColumns {
        companion object {
            // Content provider stuff.
            val CONTENT_URI: Uri = BASE_CONTENT_URI.buildUpon().appendPath(PATH_HISTORY).build()

            val CONTENT_TYPE =
                    "vnd.android.cursor.dir/$CONTENT_URI/$PATH_HISTORY"

            val CONTENT_ITEM =
                    "vnd.android.cursor.item/$CONTENT_URI/$PATH_HISTORY"

            // Table definition stuff.
            const val TABLE_NAME = "SEARCH_HISTORY"

            const val COLUMN_QUERY = "query"
            const val COLUMN_INSERT_DATE = "insert_date"
            const val COLUMN_IS_HISTORY = "is_history"

            fun buildHistoryUri(id: Long): Uri {
                return ContentUris.withAppendedId(CONTENT_URI, id)
            }
        }
    }

    // ----- !Table definitions ----- //

}
