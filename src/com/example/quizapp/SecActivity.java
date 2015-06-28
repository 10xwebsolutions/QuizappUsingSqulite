package com.example.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecActivity extends Activity {
	EditText ques, o1, o2, o3, o4, ans;
	Button sub;
	DBAdapter dbObject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sec);
		ques = (EditText) findViewById(R.id.editText1);
		o1 = (EditText) findViewById(R.id.editText2);
		o2 = (EditText) findViewById(R.id.editText3);
		o3 = (EditText) findViewById(R.id.editText4);
		o4 = (EditText) findViewById(R.id.editText5);
		ans = (EditText) findViewById(R.id.editText6);

		dbObject = DBAdapter.getDBAdapter(getApplicationContext());

		if (!dbObject.checkDatabase()) {
			dbObject.createDatabase(getApplicationContext());
		}
		dbObject.openDatabase();
	}

	public void Submit(View a) {
		dbObject.insertDB(ques.getText().toString(), o1.getText().toString(),
				o2.getText().toString(), o3.getText().toString(), o4.getText()
						.toString(), ans.getText().toString());
	}
}
