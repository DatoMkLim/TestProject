package com.epsilon.rfcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LossActivity extends Activity implements OnClickListener {

	EditText lamdaEdit = null;
	EditText distanceEdit = null;
	Button calButton = null;
    Button clearButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loss);
	
		lamdaEdit = (EditText)findViewById(R.id.editText1);
		distanceEdit = (EditText)findViewById(R.id.editText2);
		calButton = (Button)findViewById(R.id.button2);
		calButton.setOnClickListener(this);
        clearButton = (Button)findViewById(R.id.button1);
        clearButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loss, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == calButton) {
			double lamda = Double.parseDouble(lamdaEdit.getText().toString());
			double distance = Double.parseDouble(distanceEdit.getText().toString());
			
			double result = 20 * Math.log10((4 * Math.PI * distance) / lamda);
			
			((TextView)findViewById(R.id.textView4)).setText(Double.toString(result));
		}
        else if(v == clearButton) {
            ((EditText)findViewById(R.id.editText1)).setText("");
            ((EditText)findViewById(R.id.editText2)).setText("");
            ((TextView)findViewById(R.id.textView4)).setText("");
        }
	}

}
