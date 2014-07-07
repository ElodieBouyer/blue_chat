package fr.project.bluechat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class User {
	private Database database;
	private SQLiteDatabase db;
	private String name = null;

	/**
	 * User Constructor.
	 * Create database if not exist.
	 * @param context Context's application.
	 */
	public User(Context context) {
		database = new Database(context);
	}

	/**
	 * Opens the database in writable, 
	 * and put its content into db.
	 */
	public void open() {
		db = database.getWritableDatabase();
	}

	/**
	 * Close access to the database db.
	 */
	public void close() {
		db.close();
	}


	/**
	 * Get the user's name.
	 * @return User's name.
	 */
	public String getName() {
		try {
			if( name != null ) return name;

			open();
			Cursor c = this.db.query(
					USER.TAB_USER,                  
					USER.ALL_COLUMNS,                
					null,
					null, null, null, null, null);

			if( c.getCount() == 0) {
				return null;
			}

			c.moveToFirst();
			name = c.getString(USER.NUM_COL_NAME);
			close();
			return name;

		}
		catch(Exception e) {
			Log.i("User.getName()" , e.toString());
		}
		return name;
	}

	/**
	 * Set the user's name.
	 * @param name
	 */
	public void setName(String newName) {
		if( newName == null) return;

		try {
			ContentValues values = new ContentValues();
			delete();
			name = newName;

			open();
			values.put(USER.COL_NAME, name);
			db.insert(USER.TAB_USER, null,values);
			close();
		}
		catch(Exception e) {
			Log.i("User.setName", e.toString());
		}
	}


	/**
	 * Delete name.
	 */
	private void delete() {
		if( name == null ) return;

		try {
			open();
			db.delete(USER.TAB_USER, USER.COL_NAME + " LIKE \"" + name + "\"" , null);
			close();
		}
		catch(Exception e) {
			Log.i("User.delete", e.toString());
		}
	}
}
