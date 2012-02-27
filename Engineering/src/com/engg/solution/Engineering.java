package com.engg.solution;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Engineering extends Activity {
	protected boolean _active = true;

	protected int _splashTime = 1800;

	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);

		// Loading of Splash Screen
		Thread splashThread = new Thread() {

			@Override
			public void run() {
				try {
					int waited = 0;
					while (_active && (waited < _splashTime)) {
						sleep(100);
						if (_active) {
							waited += 100;
						}
					}
					if (waited == 1800) {
						Intent tabs= new Intent(Engineering.this,Tabs.class);
						startActivity(tabs);
					}
				} catch (InterruptedException e) {
					// do nothing
				} finally {

				}
			}
		};
		splashThread.start();
	}
}