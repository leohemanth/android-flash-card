/**
 * 
 */
package com.engg.solution.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * @author Badrinath
 * 
 */
public class QuestionBase extends SQLiteOpenHelper {

	private static QuestionBase questionbase;

	//reference of database
	public SQLiteDatabase myDataBase;

	// Path Name
	private static String DB_PATH = "/data/data/com.engg.solution.db/databases/";

	// Database name
	private static String DB_NAME = "Questions.sqlite";

	// declare context
	private final Context context;

	public QuestionBase(Context context) {
		super(context, DB_NAME, null, 1);
		this.context = context;
		try {
			createDatabase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createDatabase() throws IOException {

		// check whether databse exists or not
		boolean dbExist = checkDB();
		 if(dbExist)
		    {
		        //System.out.println("Database exists");
		        opendatabase(); 
		    }
		    else
		    {
		        System.out.println("Database doesn't exist");
		        createDatabase();
		    }

		
		
		
		
		/*try {
			if (dbExist) {
				opendatabase();
				System.out.println("Database opened");
			} else {
				this.getReadableDatabase();
				try {
					copyDataBase();
				} catch (Exception e) {
					throw new Error("Error copying database");
				}
			}
		} catch (SQLException e) {
		}*/
	}
	
	public void opendatabase() throws SQLException
	{
	    //Open the database
	    String mypath = DB_PATH + DB_NAME;
	    myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);

	}

	/*public boolean checkDb() {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (Exception e) {

		}

		if (checkDB != null) {
			checkDB.close();
			return true;
		} else {
			return false;
		}
	}
*/
	
	
	private boolean checkDB() {
	    //SQLiteDatabase checkdb = null;
	    boolean checkdb = false;
	    try{
	        String myPath = DB_PATH + DB_NAME;
	        File dbfile = new File(myPath);
	        //checkdb = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
	        checkdb = dbfile.exists();
	    }
	    catch(Exception e){
	        System.out.println("Database doesn't exist");
	    }

	    return checkdb;
	}


	
	private void copyDataBase() throws IOException {
		// Open your local db as the input stream
		InputStream myInput = context.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
