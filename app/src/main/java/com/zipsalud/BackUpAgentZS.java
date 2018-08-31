/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/

package com.zipsalud;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.SharedPreferences;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.IOException;



public class BackUpAgentZS extends BackupAgentHelper {

    static final String PREFS = "prefsZS";
    static final String PREFS_BACKUP_KEY = "prefsZS";


    @Override
    public void onCreate() {
        SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(this, PREFS);
        addHelper(PREFS_BACKUP_KEY, helper);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("key_name1", true);
        editor.commit();
    }

    @Override
    public void onBackup(ParcelFileDescriptor oldState, BackupDataOutput data, ParcelFileDescriptor newState) throws IOException {
        super.onBackup(oldState, data, newState);
        Log.i("backup", "OnBackUp");
    }

    @Override
    public void onRestore(BackupDataInput data, int appVersionCode, ParcelFileDescriptor newState) throws IOException {
        super.onRestore(data, appVersionCode, newState);
        Log.i("restore", "onRestore");
    }

    @Override
    public void onRestoreFinished() {
        super.onRestoreFinished();
        Log.i("restore", "onRestoreFinished");
    }
}
