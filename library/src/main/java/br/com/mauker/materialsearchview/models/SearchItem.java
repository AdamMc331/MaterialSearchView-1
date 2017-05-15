package br.com.mauker.materialsearchview.models;

import android.content.ContentValues;
import android.database.Cursor;

import br.com.mauker.materialsearchview.R;
import br.com.mauker.materialsearchview.db.HistoryContract;

/**
 * Represents a row in the History table.
 *
 * Created by adam.mcneilly on 5/11/17.
 */
public class SearchItem {
    private String query;
    private boolean isHistory;
    private long insertDate;

    public SearchItem(Cursor cursor) {
        this.query = cursor.getString(cursor.getColumnIndex(HistoryContract.HistoryEntry.COLUMN_QUERY));
        this.insertDate = cursor.getLong(cursor.getColumnIndex(HistoryContract.HistoryEntry.COLUMN_INSERT_DATE));

        int historyInteger = cursor.getInt(cursor.getColumnIndex(HistoryContract.HistoryEntry.COLUMN_IS_HISTORY));
        this.isHistory = (historyInteger == 1);
    }

    public SearchItem(String query) {
        this(query, false);
    }

    public SearchItem(String query, boolean isHistory) {
        this(query, isHistory, System.currentTimeMillis());
    }

    public SearchItem(String query, boolean isHistory, long insertDate) {
        this.query = query;
        this.isHistory = isHistory;
        this.insertDate = insertDate;
    }

    public String getQuery() {
        return query;
    }

    public boolean isHistory() {
        return isHistory;
    }

    public long getInsertDate() {
        return insertDate;
    }

    public int getImageResource() {
        return (isHistory) ? R.drawable.ic_history_white : R.drawable.ic_action_search_white;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();

        values.put(HistoryContract.HistoryEntry.COLUMN_QUERY, query);
        values.put(HistoryContract.HistoryEntry.COLUMN_INSERT_DATE, System.currentTimeMillis());

        int historyInteger = (isHistory) ? 1 : 0;
        values.put(HistoryContract.HistoryEntry.COLUMN_IS_HISTORY, historyInteger);

        return values;
    }
}
