package com.example.quizapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper {
	static String name = "quiz.sqlite";
	static String path = "";
	static ArrayList<Sumerp> a;
	static SQLiteDatabase sdb;

	@Override
	public void onCreate(SQLiteDatabase db) { 
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	private DBAdapter(Context v) {
		super(v, name, null, 1);
		path = "/data/data/" + v.getApplicationContext().getPackageName()
				+ "/databases";
	}

	public boolean checkDatabase() {
		SQLiteDatabase db = null;
		try {
			db = SQLiteDatabase.openDatabase(path + "/" + name, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (Exception e) {

		}
		if (db == null) {
			
			return false;
		} else {
			db.close();
			return true;
		}
	}

	public static synchronized DBAdapter getDBAdapter(Context v) {
		return (new DBAdapter(v));
	}

	public void createDatabase(Context v) {
		this.getReadableDatabase();
		try {
			InputStream myInput = v.getAssets().open(name);
		    // Path to the just created empty db
		String outFileName = path +"/"+ name;
		    // Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
		    // transfer bytes from the inputfile to the outputfile
		byte[] bytes = new byte[1024];
		int length;
		while ((length = myInput.read(bytes)) > 0) {
			myOutput.write(bytes, 0, length);
		}
		    // Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
			
		/*	
			
			
			
			InputStream is = v.getAssets().open("quiz.sqlite");
			// System.out.println(is.available());
			System.out.println(new File(path + "/" + name).getAbsolutePath());
			FileOutputStream fos = new FileOutputStream(path + "/" + name);
			int num = 0;
			while ((num = is.read()) > 0) {
				fos.write((byte) num);
			}
			fos.close();
			is.close();*/
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void openDatabase() {
		try {
			sdb = SQLiteDatabase.openDatabase(path + "/" + name, null,
					SQLiteDatabase.OPEN_READWRITE);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
public void  insertDB(String que, String opt1,String opt2,String opt3,String opt4,String ans)
{
	ContentValues cv= new ContentValues();
	cv.put("Question",que);
	cv.put("Option1",opt1);
	cv.put("Option2",opt2);
	cv.put("Option3",opt3);
	cv.put("Option4",opt4);
	cv.put("Answer",ans);
	sdb.insert("sp", null, cv);	
}
	public ArrayList<Sumerp> getData() {
		Cursor c1 = sdb.rawQuery("select * from sp", null);
		a = new ArrayList<Sumerp>();
		while (c1.moveToNext()) {
			Sumerp q1 = new Sumerp();
			q1.setQuestion(c1.getString(0));
			q1.setOption1(c1.getString(1));
			q1.setOption2(c1.getString(2));
			q1.setOption3(c1.getString(3));
			q1.setOption4(c1.getString(4));
			q1.setAnswer(c1.getString(5));
			
			//q1.setAnswer(c1.getString(5));
			a.add(q1);
		}
		return a; 
	}
}
