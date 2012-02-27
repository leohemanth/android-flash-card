package com.engg.solution;



import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class Tabs extends TabActivity{

	//Declaring TabHost
	public static TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		
		tabHost = getTabHost();

		Intent intent = new Intent().setClass(Tabs.this, Questions.class);
		TabHost.TabSpec spec = tabHost.newTabSpec("Questions")
				.setIndicator("Q&A").setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent().setClass(Tabs.this, Favourite.class);
		 spec = tabHost.newTabSpec("Favourite")
				.setIndicator("Favourite").setContent(intent);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		//setTabColor(tabHost);
	}

	/*public static void setTabColor(TabHost tabhost) {
		for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
			tabhost.getTabWidget().getChildAt(i)
					.setBackgroundColor(Color.BLACK);// setBackgroundResource(R.drawable.icon);
		}
		// tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundResource(R.drawable.tabbarbg);
	}*/
	
}
