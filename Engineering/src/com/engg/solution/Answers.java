/**
 * 
 */
package com.engg.solution;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Badrinath
 * 
 */
public class Answers extends Activity {

	Bundle questionBundle = null;
	String dispQuestion = null;
	Button showAnswer=null;
	TextView dispAnswer=null;
	OnClickListener hideAnswer =null;
	OnClickListener display=null;
	DataBaseHelper db=null;
	Cursor cur=null;
	String databaseAnswer=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showanswer);
		
		
		TextView questn=(TextView)findViewById(R.id.questn);
		
		if (this.getIntent().getExtras() != null) {
			questionBundle = this.getIntent().getExtras();
			dispQuestion = questionBundle.getString("question");
		}

		try {
			db= new DataBaseHelper(Answers.this);
			db.opendatabase();
			//cur =db.myDataBase.rawQuery("SELECT data.ans FROM main.data",null);
			
			cur=db.getAnswer(dispQuestion);
			
			while(cur.moveToNext()){
				//System.out.println("Question is "+cur.getString(0));
				databaseAnswer =cur.getString(0);
			}
			cur.close();
			db.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		questn.setText(dispQuestion);
		questn.setTextColor(Color.parseColor("#FFFFFF"));
		
		Button back=(Button)findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent questions= new Intent(Answers.this,Questions.class);
				startActivity(questions);
			}
		});
		
		dispAnswer= (TextView)findViewById(R.id.dispanswer);
		
		showAnswer=(Button)findViewById(R.id.showanswer);
		showAnswer.setId(10);
		showAnswer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showAnswer.setText("Hide Answer");
				//dispAnswer.setText("Answer\n\tAndroid\n\tIOS\n\tBlackBerry\n\tSymbian");
				dispAnswer.setText(databaseAnswer);
				v.setOnClickListener(hideAnswer);
			
				
				
				
			}
		});
		
		 display = new OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	showAnswer.setText("Hide Answer");
					//dispAnswer.setText("Answer\n\tAndroid\n\tIOS\n\tBlackBerry\n\tSymbian");
					dispAnswer.setText(databaseAnswer);
		        	v.setOnClickListener(hideAnswer);
		        }
		    };
		
		 hideAnswer = new OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	dispAnswer.setText("");
					showAnswer.setText("Show Answer");
					v.setOnClickListener(display);
		        }
		    };
		    
		    //closing operations 
		   /* cur.close();
		    db.close();*/
	}

	
	
}
