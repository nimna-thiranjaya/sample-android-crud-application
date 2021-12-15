package com.example.samplecrud.Database;

import android.provider.BaseColumns;

import java.util.stream.BaseStream;

public final class UserMaster {
    public UserMaster() { }

    // create table in database
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
}
