/**
 * 
 */
package com.engg.solution;

import java.io.IOException;
import java.util.ArrayList;

import com.engg.solution.DataBaseHelper;
import com.engg.solution.db.QuestionBase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author Badrinath
 * 
 */
public class Questions extends Activity {

	ArrayList<String> names = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Setting Questions View
		setContentView(R.layout.questions);

		names = new ArrayList<String>();

		//names.add(0, "List Mobile Platforms ?");
		try {
			DataBaseHelper db= new DataBaseHelper(Questions.this);
			db.opendatabase();
			
			//Cursor cur =db.myDataBase.rawQuery("SELECT find.findno FROM main.find",null);
			
			//Cursor cur =db.myDataBase.rawQuery("SELECT data.idx FROM main.data",null);
			
			Cursor cur =db.myDataBase.rawQuery("SELECT data.idx,data.qustn,data.ans FROM main.data",null);
			
			while(cur.moveToNext()){
				//System.out.println(cur.getInt(0));
				int questno=cur.getInt(0);
				String questn=cur.getString(1);
				names.add(0,questn);
			}
			cur.close();
			db.close();
  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		ListView ls = (ListView) findViewById(R.id.questionsview);

		ls.setAdapter(new MyListAdapter(Questions.this, R.id.questionsview,
				names));
		
		ls.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {

				//Toast.makeText(Questions.this, names.get(position), Toast.LENGTH_LONG).show();
				Intent answer= new Intent(Questions.this,Answers.class);
				answer.putExtra("question", names.get(0));
				startActivity(answer);
			}
		});

	}

	class MyListAdapter extends ArrayAdapter<String> {

		private ArrayList<String> mList;
		private Context mContext;

		public MyListAdapter(Context context, int textViewResourceId,
				ArrayList<String> list) {
			super(context, textViewResourceId, list);
			this.mList = list;
			this.mContext = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			try {
				if (view == null) {
					LayoutInflater vi = (LayoutInflater) mContext
							.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					view = vi.inflate(R.layout.questionlist, null); 
				}

				TextView name = ((TextView) view.findViewById(R.id.questions));

				if (position == 0) {
					name.setText(names.get(position));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return view;
		}
	}
}




//db.myDataBase.rawQuery("SELECT test.testno FROM main.test",null);
// db.myDataBase.query("main."+"questiondata", new String[]{"question"}, null, null, null,null,null);
	 