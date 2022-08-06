package com.laioffer.tinnews;

import android.app.Application;

import androidx.room.Room;

import com.laioffer.tinnews.database.TinNewsDatabase;

public class TinNewsApplication extends Application {
    // db lifecycle follows app
    // cannot be in main activity, static field in main activity -> memory leak
    // static field will not be collected -> main activity always remains
    // as static reference remains, main activity will not be gc
    // because rotate will recreate activity
    private static TinNewsDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, TinNewsDatabase.class, "tinnews_db").build();
    }

    public static TinNewsDatabase getDatabase() {
        return database;
    }
}
