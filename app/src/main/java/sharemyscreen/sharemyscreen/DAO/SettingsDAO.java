package sharemyscreen.sharemyscreen.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cleme_000 on 03/11/2015.
 */
public class SettingsDAO extends DAOBase {
    public static final String TABLE_NAME = "settings";
    public static final String KEY = "key";
    public static final String VALUE = "value";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " TEXT, " + VALUE + " TEXT);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public SettingsDAO(Context pContext) {
        super(pContext);
    }

    public long add(String key, String value) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY, key);
        contentValues.put(VALUE, value);

        return _mDb.insert(TABLE_NAME, null, contentValues);
    }

    public void delete(String key) {

        _mDb.delete(TABLE_NAME, KEY + " = ?", new String[]{key});
    }

    public void modify(String key, String value) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY, key);
        contentValues.put(VALUE, value);
        _mDb.update(TABLE_NAME, contentValues, KEY + " = ?", new String[]{key});
    }

    public String select(String key) {

        String value = null;

        Cursor c = _mDb.rawQuery("select * from " + TABLE_NAME + " WHERE KEY = ?" , new String[] {key});

        if (c.moveToFirst()) {
            value = c.getString(1);
        }

        c.close();
        return value;
    }


    public void setDB(SQLiteDatabase db) {
        this._mDb = db;
    }
}