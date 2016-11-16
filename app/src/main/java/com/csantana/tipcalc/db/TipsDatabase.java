package com.csantana.tipcalc.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by CLsantana on 16/11/16.
 */

@Database(name = TipsDatabase.NAME, version = TipsDatabase.VERSION)
public class TipsDatabase {

    public static final String NAME = "Tips";
    public static final int VERSION = 1;
}
