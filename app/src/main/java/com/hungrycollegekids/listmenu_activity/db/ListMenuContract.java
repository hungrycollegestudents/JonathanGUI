package com.hungrycollegekids.listmenu_activity.db;

import android.provider.BaseColumns;

/**
 * Created by Jonathan on 2/22/18.
 */

public class ListMenuContract {

    public static final String DB_NAME = "com.hungrycollegekids.listmenu_activity.db";
    public static final int DB_VERSION = 1;

    public class ListMenuEntry implements BaseColumns {
        public static final String TABLE = "Grocery Lists";
        public static final String COL_LIST_TITLE =  "title";
    }
}
