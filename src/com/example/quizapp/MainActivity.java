package com.example.quizapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button ff,dd,skip,Op1,Op2,Op3,Op4;
	TextView ques,score;
	String ans;
	int Inc;
	//String st1,st2,st3,st4,st5;
	private ArrayList<Sumerp> array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ff=(Button)findViewById(R.id.button1);
		dd=(Button)findViewById(R.id.button2);
		skip=(Button)findViewById(R.id.button3);
		Op1=(Button)findViewById(R.id.button4);
		Op2=(Button)findViewById(R.id.button5);
		Op3=(Button)findViewById(R.id.button6);
		Op4=(Button)findViewById(R.id.button7);
		ques=(TextView)findViewById(R.id.textView1);
		score=(TextView)findViewById(R.id.textView2);
		
DBAdapter dbObject=DBAdapter.getDBAdapter(getApplicationContext());
        
        if(!dbObject.checkDatabase())
        {
        	dbObject.createDatabase(getApplicationContext());
        }
        dbObject.openDatabase();
        array=dbObject.getData();
        update(0);
    }
        public void update(int i)
        {
        	if(array.size()>i)
        	{
        ques.setText(array.get(i).getQuestion());
        Op1.setText(array.get(i).getOption1());
        Op2.setText(array.get(i).getOption2());
        Op3.setText(array.get(i).getOption3());
        Op4.setText(array.get(i).getOption4());
        ans=array.get(i).getAnswer();
        }
        	else
        	{
        		Toast.makeText(getApplicationContext(), "END of questions",Toast.LENGTH_SHORT).show();
        		update(0);
        	}
        }
   /* public void fifty(View a)
    {
    	
    }
    public void dd(View a)
    {
    	
    }*/
    public void skip(View a)
    {
    	update(1);
    }
    public void option(View a)
    {
    	switch(a.getId())
    	{
    	case R.id.button4:
    	if(ans.equalsIgnoreCase(Op1.getText().toString()))
    	{
    		Toast.makeText(getApplicationContext(), "CORRECT answer",Toast.LENGTH_SHORT).show();
    		Inc+=1;
    		update(Inc);
    	}
    	else
    	{
    		Toast.makeText(getApplicationContext(), "WRONG answer",Toast.LENGTH_SHORT).show();
    		Toast.makeText(getApplicationContext(), "GAME over",Toast.LENGTH_SHORT).show();
    		update(0);
    	}
    	break;
    	case R.id.button5:
        	if(ans.equalsIgnoreCase(Op2.getText().toString()))
        	{
        		Toast.makeText(getApplicationContext(), "CORRECT answer",Toast.LENGTH_SHORT).show();
        		Inc+=1;
        		update(Inc);
        	}
        	else
        	{
        		Toast.makeText(getApplicationContext(), "WRONG answer",Toast.LENGTH_SHORT).show();
        		Toast.makeText(getApplicationContext(), "GAME over",Toast.LENGTH_SHORT).show();
        		update(0);
        	}
        	break;
    	case R.id.button6:
        	if(ans.equalsIgnoreCase(Op3.getText().toString()))
        	{
        		Toast.makeText(getApplicationContext(), "CORRECT answer",Toast.LENGTH_SHORT).show();
        		Inc+=1;
        		update(Inc);
        	}
        	else
        	{
        		Toast.makeText(getApplicationContext(), "WRONG answer",Toast.LENGTH_SHORT).show();
        		Toast.makeText(getApplicationContext(), "GAME over",Toast.LENGTH_SHORT).show();
        		update(0);
        	}
        	break;
    	case R.id.button7:
        	if(ans.equalsIgnoreCase(Op4.getText().toString()))
        	{
        		Toast.makeText(getApplicationContext(), "CORRECT answer",Toast.LENGTH_SHORT).show();
        		Inc+=1;
        		update(Inc);
        	}
        	else
        	{
        		Toast.makeText(getApplicationContext(), "WRONG answer",Toast.LENGTH_SHORT).show();
        		Toast.makeText(getApplicationContext(), "GAME over",Toast.LENGTH_SHORT).show();
        		update(0);
        	}
        	break;
    	}
    	
    }
    public void next(View a)
    {
    	Intent myIntent=new Intent(getApplicationContext(),SecActivity.class);
    	startActivity(myIntent);
    }

}
