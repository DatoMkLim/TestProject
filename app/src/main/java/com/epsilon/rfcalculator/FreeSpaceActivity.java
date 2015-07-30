package com.epsilon.rfcalculator;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FreeSpaceActivity extends Activity implements OnClickListener {

	EditText tPowerEdit = null;
	EditText tGainEdit = null;
	EditText rGainEdit = null;
	EditText freqEdit = null;
	EditText distanceEdit = null;
	EditText lightEdit = null;
	Button calButton = null;
	Button graphButton = null;
    Button clearButton = null;
	Button plotButton = null;

	EditText radEdit = null;//testing
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_free_space);

		tPowerEdit = (EditText)findViewById(R.id.editText1);
		tGainEdit = (EditText)findViewById(R.id.editText2);
		rGainEdit = (EditText)findViewById(R.id.editText3);
		freqEdit = (EditText)findViewById(R.id.editText4);	
		distanceEdit = (EditText)findViewById(R.id.editText7);
		lightEdit = (EditText)findViewById(R.id.editText8);
		calButton = (Button)findViewById(R.id.button1);
		calButton.setOnClickListener(this);
		graphButton = (Button)findViewById(R.id.button2);
		graphButton.setOnClickListener(this);
        clearButton = (Button)findViewById(R.id.button3);
        clearButton.setOnClickListener(this);
		plotButton =(Button)findViewById(R.id.plot_button);
		plotButton.setOnClickListener(this);

		radEdit = (EditText)findViewById(R.id.editText28);//testing

	}

    private ActionBar getSupportActionBar() {
        return null;
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.free_space, menu);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String tPowerStr = tPowerEdit.getText().toString();
		String tGainStr = tGainEdit.getText().toString();
		String rGainStr = rGainEdit.getText().toString();
		String freqStr = freqEdit.getText().toString();
		String distanceStr = distanceEdit.getText().toString();
		String lightStr = lightEdit.getText().toString();
		double tPower = Double.parseDouble(!tPowerStr.isEmpty() ? tPowerStr : "0");
		double tGain = Double.parseDouble(!tGainStr.isEmpty() ? tGainStr : "0");
		double rGain = Double.parseDouble(!rGainStr.isEmpty() ? rGainStr : "0");
		double freq = Double.parseDouble(!freqStr.isEmpty() ? freqStr : "0");
		double distance = Double.parseDouble(!distanceStr.isEmpty() ? distanceStr : "0");
		double light = Double.parseDouble(!lightStr.isEmpty() ? lightStr : "0");

		String radStr = radEdit.getText().toString();//testing
		double radius = Double.parseDouble(!radStr.isEmpty() ? radStr : "0");//testing

		if(v == calButton) {
			double lamda = 300000000/freq;
			
			double result = tPower * tGain * rGain * Math.pow(lamda / (4 * Math.PI * distance),2)/ light / 100;
			//double radius = 5000;
			((TextView)findViewById(R.id.textFreeSpacePrw)).setText(Double.toString(result));
			((TextView)findViewById(R.id.textFreeSpacePrd)).setText(Double.toString(10 * Math.log10(result * 1000)));
		}
		else if(v == graphButton) {
			
			Intent intent = new Intent(this,GraphActivity.class);
			intent.putExtra("model","freeSpace");
			intent.putExtra("tp",tPower);
			intent.putExtra("tg",tGain);
			intent.putExtra("rg",rGain);
			intent.putExtra("f",freq);
			intent.putExtra("l",light);
			this.startActivity(intent);
		}
        else if(v == clearButton) {
			((EditText) findViewById(R.id.editText1)).setText("");
			((EditText) findViewById(R.id.editText2)).setText("");
			((EditText) findViewById(R.id.editText3)).setText("");
			((EditText) findViewById(R.id.editText4)).setText("");
			((EditText) findViewById(R.id.editText7)).setText("");
			((EditText) findViewById(R.id.editText8)).setText("");
			((EditText) findViewById(R.id.editText28)).setText("");
			((TextView) findViewById(R.id.textFreeSpacePrw)).setText("");
			((TextView) findViewById(R.id.textFreeSpacePrd)).setText("");
		}
		else if(v == plotButton) {
			//double radius = Double.parseDouble(radius.getText().toString());
			Intent intent = new Intent(this, MapActivity.class);
			Bundle b = new Bundle();
			b.putDouble("radius", radius);
			intent.putExtras(b);
			this.startActivity(intent);
		}
	}



}
