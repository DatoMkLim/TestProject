package com.epsilon.rfcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

public class WavelengthActivity extends Activity implements OnClickListener,OnItemSelectedListener,OnCheckedChangeListener {

	EditText freqEdit = null;
	Button calButton = null;
    Button clearButton = null;
	Spinner materialSpinner = null;
	RadioGroup unitGroup = null;
	
	double velocity = 300000000;
	double conversionFactor = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wavelength);
		
		freqEdit = (EditText)findViewById(R.id.editText1);
		unitGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		unitGroup.setOnCheckedChangeListener(this);
		materialSpinner = (Spinner)findViewById(R.id.spinner1);
		materialSpinner.setOnItemSelectedListener(this);
		calButton = (Button)findViewById(R.id.button2);
		calButton.setOnClickListener(this);
        clearButton = (Button)findViewById(R.id.button1);
        clearButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wavelength, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == calButton) {
			String freqStr = freqEdit.getText().toString();
			
			if(freqStr != null && !freqStr.isEmpty()) {
				double lamda = velocity / (Double.parseDouble(freqStr) * conversionFactor);
				((TextView)findViewById(R.id.textView4)).setText(String.format("%.3f", lamda * 1000));
				((TextView)findViewById(R.id.textView6)).setText(String.format("%.3f", lamda * 100));
				((TextView)findViewById(R.id.textView8)).setText(String.format("%.3f", lamda));
				((TextView)findViewById(R.id.textView10)).setText(String.format("%.3f", 100 * lamda/2.54));
				((TextView)findViewById(R.id.textView12)).setText(String.format("%.3f", 100 * lamda/(2.54 * 12)));

				//((TextView)findViewById(R.id.textView4)).setText(Double.toString(lamda * 1000));
				//((TextView)findViewById(R.id.textView6)).setText(Double.toString(lamda * 100));
				//((TextView)findViewById(R.id.textView8)).setText(Double.toString(lamda));
				//((TextView)findViewById(R.id.textView10)).setText(Double.toString(100 * lamda/2.54));
				//((TextView)findViewById(R.id.textView12)).setText(Double.toString(100 * lamda/(2.54 * 12)));
			}
		} else
        if(v == clearButton) {
            ((EditText) findViewById(R.id.editText1)).setText("");
            ((TextView)findViewById(R.id.textView4)).setText("");
            ((TextView)findViewById(R.id.textView6)).setText("");
            ((TextView)findViewById(R.id.textView8)).setText("");
            ((TextView)findViewById(R.id.textView10)).setText("");
            ((TextView)findViewById(R.id.textView12)).setText("");
        }
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		switch(arg2) {
			case 0: velocity = 300000000;break;
			case 1: velocity = 299912126;break;
			case 2: velocity = 225056264;break;
			case 3: velocity = 124018189;break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch(checkedId) {
			case R.id.radioButton1 : conversionFactor = 1L;break;
			case R.id.radioButton2 : conversionFactor = 1000L;break;
			case R.id.radioButton3 : conversionFactor = 1000000L;break;
			case R.id.radioButton4 : conversionFactor = 1000000000L;break;
			case R.id.radioButton5 : conversionFactor = 1000000000000L;break;
		}
	}
}