package edu.antidroid.rentreminder.edu.antidroid.rentreminder.datasource;

import android.content.Context;

/**
 * Creates SQLite workspaces using a specific version number.
 */
public class SQLiteWorkspaceFactory {

    private final Context context;
    private final WorkspaceVersion workspaceVersion;

    public SQLiteWorkspaceFactory(Context context) {
        this.context = context;
        workspaceVersion = WorkspaceVersion.RentReminderFirstVersion;
    }

    public SQLiteWorkspace openWorkspace(String workspaceName) {
        return new SQLiteWorkspace(workspaceName, workspaceVersion);
    }
}
