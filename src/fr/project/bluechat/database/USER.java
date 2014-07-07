package fr.project.bluechat.database;

class USER {
	static final String TAB_USER = "USER";

	static final String COL_NAME      = "USER_NAME";

	static final int NUM_COL_NAME      = 0;

	static final String[] ALL_COLUMNS = {COL_NAME };

	static final String CREATE_TABLE_USER = 
			"CREATE TABLE " 
					+ TAB_USER  + " (" 
					+ COL_NAME  + " TEXT NOT NULL UNIQUE);";
}
