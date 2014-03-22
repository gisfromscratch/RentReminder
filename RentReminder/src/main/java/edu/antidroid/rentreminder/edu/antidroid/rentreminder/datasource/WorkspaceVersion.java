package edu.antidroid.rentreminder.edu.antidroid.rentreminder.datasource;

/**
 * A specific workspace version number.
 */
public enum WorkspaceVersion {

    RentReminderFirstVersion(1);

    private final int versionNumber;

    private WorkspaceVersion(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public int getVersionNumber() {
        return versionNumber;
    }
}
