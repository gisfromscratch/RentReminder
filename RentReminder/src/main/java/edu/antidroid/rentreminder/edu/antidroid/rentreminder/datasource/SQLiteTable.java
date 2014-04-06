package edu.antidroid.rentreminder.edu.antidroid.rentreminder.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite table of a SQLiteWorkspace.
 */
abstract class SQLiteTable extends SQLiteOpenHelper {

    private final String tableName;

    SQLiteTable(Context context, String tableName, int workspaceVersion) {
        super(context, tableName, null, workspaceVersion);
        this.tableName = tableName;
    }

    public abstract String getCreateSqlCommand();
}
