package com.epsilon.rfcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PowerActivity extends Activity implements OnClickListener {
	
	Button convertButton = null;
	Button clearButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_power);
		
		convertButton = (Button)findViewById(R.id.button1);
		clearButton = (Button)findViewById(R.id.button2);
		
		convertButton.setOnClickListener(this);
		clearButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.power, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0 == convertButton) {
			String wattStr = ((EditText)findViewById(R.id.editText1)).getText().toString();
			String mWattStr = ((EditText)findViewById(R.id.editText2)).getText().toString();
			String dbmStr = ((EditText)findViewById(R.id.editText3)).getText().toString();
			String dbWStr = ((EditText)findViewById(R.id.editText4)).getText().toString();
			
			if(wattStr != null && !wattStr.isEmpty()) {
				double watts = Double.parseDouble(wattStr);
				((EditText)findViewById(R.id.editText2)).setText(Double.toString(watts * 1000));
//				((EditText)findViewById(R.id.editText2)).setText(String.format("%f", watts * 1000));
				((EditText)findViewById(R.id.editText3)).setText(Double.toString(10 * Math.log10(watts * 1000)));
				((EditText)findViewById(R.id.editText4)).setText(Double.toString(10 * Math.log10(watts)));
			}
			else if(mWattStr != null && !mWattStr.isEmpty()) {
				double watts = Double.parseDouble(mWattStr)/1000;
				((EditText)findViewById(R.id.editText1)).setText(Double.toString(watts));
//				((EditText)findViewById(R.id.editText1)).setText(String.format("%f", watts));
				((EditText)findViewById(R.id.editText3)).setText(Double.toString(10 * Math.log10(watts * 1000)));
				((EditText)findViewById(R.id.editText4)).setText(Double.toString(10 * Math.log10(watts)));
			}
			else if(dbmStr != null && !dbmStr.isEmpty()) {
				double watts = Math.pow(10,Double.parseDouble(dbmStr)/10)/1000;
				((EditText)findViewById(R.id.editText1)).setText(Double.toString(watts));
				((EditText)findViewById(R.id.editText2)).setText(Double.toString(watts * 1000));
//				((EditText)findViewById(R.id.editText1)).setText(String.format("%f", watts));
//				((EditText)findViewById(R.id.editText2)).setText(String.format("%f", watts * 1000));
				((EditText)findViewById(R.id.editText4)).setText(Double.toString(10 * Math.log10(watts)));
			}
			else if(dbWStr != null && !dbWStr.isEmpty()) {
				double watts = Math.pow(10,Double.parseDouble(dbWStr)/10);
				((EditText)findViewById(R.id.editText1)).setText(Double.toString(watts));
				((EditText)findViewById(R.id.editText2)).setText(Double.toString(watts * 1000));
//				((EditText)findViewById(R.id.editText1)).setText(String.format("%f", watts));
//				((EditText)findViewById(R.id.editText2)).setText(String.format("%f", watts * 1000));
				((EditText)findViewById(R.id.editText3)).setText(Double.toString(10 * Math.log10(watts * 1000)));
			}
		}
		else if(arg0 == clearButton) {
			((EditText)findViewById(R.id.editText1)).setText("");
			((EditText)findViewById(R.id.editText2)).setText("");
			((EditText)findViewById(R.id.editText3)).setText("");
			((EditText)findViewById(R.id.editText4)).setText("");
		}
	}

}
