package fr.project.bluechat.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class Database extends SQLiteOpenHelper {
	private static final String DATABASE_NAME    = "bluechat.db";
	private static final int    DATABASE_VERSION = 1;

	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(USER.CREATE_TABLE_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + USER.TAB_USER);
		onCreate(db);
	}
}
