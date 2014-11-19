package eu.meiremans.winlife.app;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Nick on 16/11/2014.
 */
public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "WinLife";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}