package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/5/3.
 */
public class sqlitehelp extends SQLiteOpenHelper {

    public sqlitehelp(Context context) {
        super(context, "news.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table feedback(id integer primary key AUTOINCREMENT,command varchar(20),qq varchar(20))";
        String sql1="create table collect(id integer primary key AUTOINCREMENT,title varchar(20),url varchar(20))";
        db.execSQL(sql);
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
