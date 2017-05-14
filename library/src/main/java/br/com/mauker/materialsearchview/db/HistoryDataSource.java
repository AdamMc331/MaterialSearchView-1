package br.com.mauker.materialsearchview.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

import br.com.mauker.materialsearchview.models.HistoryItem;

/**
 * Datasource for connecting to the database that can replace the {@link HistoryProvider}
 *
 * Created by adam.mcneilly on 5/11/17.
 */
public class HistoryDataSource {

    /**
     * The SQLiteOpenHelper that creates the database.
     */
    private HistoryDbHelper openHelper;

    /**
     * The database reference to query.
     */
    private SQLiteDatabase database;

    public HistoryDataSource(Context context) {
        openHelper = new HistoryDbHelper(context);
    }

    /**
     * Opens a connection to the database.
     * @throws SQLiteException Thrown if an error occurs when creating the connection.
     */
    public void open() throws SQLiteException {
        database = openHelper.getWritableDatabase();
    }

    /**
     * Closes the connection to the database.
     */
    public void close() {
        openHelper.close();
    }

    /**
     * Queries the database for records from the History table.
     * @param projection The columns to be returned.
     * @param selection The selection condition for the rows to return.
     * @param selectionArgs Arguments for the selection condition.
     * @param sortOrder The order to return the results in.
     * @return A query of rows from the history table with the above parameters.
     */
    public Cursor getHistoryCursor(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return database.query(
                HistoryContract.HistoryEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    /**
     * Retrieves a list of search items that are history items.
     * @param limit The number of searches to return.
     * @return A list of search items up to size "limit".
     */
    public List<HistoryItem> getHistory(int limit) {
        List<HistoryItem> results = new ArrayList<>();

        Cursor cursor = getHistoryCursor(
                null,
                HistoryContract.HistoryEntry.COLUMN_IS_HISTORY + " = ?",
                new String[]{"1"},
                HistoryContract.HistoryEntry.COLUMN_INSERT_DATE + " DESC LIMIT " + limit
        );

        while (cursor.moveToNext()) {
            results.add(new HistoryItem(cursor));
        }

        cursor.close();

        return results;
    }

    /**
     * Inserts an item into the history table.
     * @param item The item to be inserted.
     * @return The identifier of the row that was created.
     */
    public long insertHistoryItem(HistoryItem item) {
        return database.insert(HistoryContract.HistoryEntry.TABLE_NAME, null, item.getContentValues());
    }
}
