package com.epsilon.rfcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TransmissionActivity extends Activity implements OnClickListener {

	EditText tPowerEdit = null;
	EditText tGainEdit = null;
	EditText rGainEdit = null;
	EditText lamdaEdit = null;
	EditText tHeightEdit = null;
	EditText rHeightEdit = null;
	EditText distanceEdit = null;
	EditText relativeEdit = null;
	Button calButton = null;
	Button graphButton = null;
	Button graphButton2 = null;
    Button clearButton = null;
	Button plotButton = null;

	EditText radEdit = null;//testing


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transmission);
		
		tPowerEdit = (EditText)findViewById(R.id.editText1);
		tGainEdit = (EditText)findViewById(R.id.editText2);
		rGainEdit = (EditText)findViewById(R.id.editText3);
		lamdaEdit = (EditText)findViewById(R.id.editText4);	
		tHeightEdit = (EditText)findViewById(R.id.editText5);
		rHeightEdit = (EditText)findViewById(R.id.editText6);
		distanceEdit = (EditText)findViewById(R.id.editText7);
		relativeEdit = (EditText)findViewById(R.id.editText8);
		calButton = (Button)findViewById(R.id.button1);
		calButton.setOnClickListener(this);
		graphButton = (Button)findViewById(R.id.button2);
		graphButton.setOnClickListener(this);
		graphButton2 = (Button)findViewById(R.id.button3);
		graphButton2.setOnClickListener(this);
        clearButton = (Button)findViewById(R.id.button4);
        clearButton.setOnClickListener(this);
		plotButton =(Button)findViewById(R.id.plot_button);
		plotButton.setOnClickListener(this);

		radEdit = (EditText)findViewById(R.id.editText28);//testing
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.transmission, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		String tPowerStr = tPowerEdit.getText().toString();
		String tGainStr = tGainEdit.getText().toString();
		String rGainStr = rGainEdit.getText().toString();
		String lamdaStr = lamdaEdit.getText().toString();
		String tHeightStr = tHeightEdit.getText().toString();
		String rHeightStr = rHeightEdit.getText().toString();
		String distanceStr = distanceEdit.getText().toString();
		String relativeStr = relativeEdit.getText().toString();
		double tPower = Double.parseDouble(!tPowerStr.isEmpty() ? tPowerStr : "0");
		double tGain = Double.parseDouble(!tGainStr.isEmpty() ? tGainStr : "0");
		double rGain = Double.parseDouble(!rGainStr.isEmpty() ? rGainStr : "0");
		double wavelength = Double.parseDouble(!lamdaStr.isEmpty() ? lamdaStr : "0");
		double tHeight = Double.parseDouble(!tHeightStr.isEmpty() ? tHeightStr : "0");
		double rHeight = Double.parseDouble(!rHeightStr.isEmpty() ? rHeightStr : "0");
		double distance = Double.parseDouble(!distanceStr.isEmpty() ? distanceStr : "0");
		double relative = Double.parseDouble(!relativeStr.isEmpty() ? relativeStr : "0");

		String radStr = radEdit.getText().toString();//testing
		double radius = Double.parseDouble(!radStr.isEmpty() ? radStr : "0");//testing

		if(v == calButton) {
			//double result = tPower * tGain * rGain * Math.pow(tHeight * rHeight,2) / (Math.pow(distance,4));
			double result = tPower * rGain * tGain * Math.pow(wavelength / (4 * Math.PI * distance), 2);
			((TextView)findViewById(R.id.textTransmissionPrw)).setText(Double.toString(result));
			((TextView)findViewById(R.id.textTransmissionPrd)).setText(Double.toString(10 * Math.log10(result * 1000)));
//			((EditText)findViewById(R.id.editText9)).setText(Double.toString(result));
//			((EditText)findViewById(R.id.editText10)).setText(Double.toString(10 * Math.log10(result * 1000)));
		} else
        if(v == clearButton) {
            ((EditText)findViewById(R.id.editText1)).setText("");
            ((EditText)findViewById(R.id.editText2)).setText("");
            ((EditText)findViewById(R.id.editText3)).setText("");
            ((EditText)findViewById(R.id.editText4)).setText("");
            ((EditText)findViewById(R.id.editText5)).setText("");
            ((EditText)findViewById(R.id.editText6)).setText("");
            ((EditText)findViewById(R.id.editText7)).setText("");
            ((EditText)findViewById(R.id.editText8)).setText("");
			((EditText) findViewById(R.id.editText28)).setText("");
            ((TextView)findViewById(R.id.textTransmissionPrw)).setText("");
            ((TextView)findViewById(R.id.textTransmissionPrd)).setText("");
        }else
		if(v == graphButton) {
			Intent intent = new Intent(this,GraphActivity.class);
			intent.putExtra("model","twoRay");
			intent.putExtra("tp",tPower);
			intent.putExtra("tg",tGain);
			intent.putExtra("rg",rGain);
			intent.putExtra("th",tHeight);
			intent.putExtra("rh",rHeight);
			intent.putExtra("w",wavelength);
			intent.putExtra("rp",relative);
			this.startActivity(intent);
		} else
		if(v == graphButton2) {
			Intent intent = new Intent(this,GraphActivity.class);
			intent.putExtra("model","twoRay2");
			intent.putExtra("tp",tPower);
			intent.putExtra("tg",tGain);
			intent.putExtra("rg",rGain);
			intent.putExtra("th",tHeight);
			intent.putExtra("rh",rHeight);
			intent.putExtra("w",wavelength);
			intent.putExtra("rp",relative);
			this.startActivity(intent);
		}
		else if(v == plotButton) {
			Intent intent = new Intent(this, MapActivity.class);
			Bundle b = new Bundle();
			b.putDouble("radius", radius);
			intent.putExtras(b);
			this.startActivity(intent);
		}
			
	}
}
