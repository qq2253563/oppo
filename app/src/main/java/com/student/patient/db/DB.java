package com.student.patient.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.student.patient.App;

@Database(version = 1,
        entities = {
         Sickness.class
        })
public abstract class DB extends RoomDatabase {

    private static final String DB_NAME = "sickness_info";

    private static DB sInstance = null;


    DB() {

    }

    public static DB getInstance() {
        if (sInstance == null) {
            synchronized (DB.class) {
                if (sInstance == null) {
                    sInstance = createDatabase();
                }
            }
        }
        return sInstance;
    }

    private static DB createDatabase() {
        return Room.databaseBuilder(App.Companion.getInstance(), DB.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public abstract SicknessDao getSicknessDao();
}
