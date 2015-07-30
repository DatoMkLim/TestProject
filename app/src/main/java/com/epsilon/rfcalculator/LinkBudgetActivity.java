package com.epsilon.rfcalculator;

//import android.support.v7.app.ActionBarActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
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


public class LinkBudgetActivity extends Activity implements OnClickListener {

    EditText tPowerEdit = null;
    EditText tGainEdit = null;
    EditText rGainEdit = null;
    EditText fslEdit= null;
    EditText misc_lossEdit = null;
    EditText tFeederEdit = null;
    EditText rFeederEdit = null;
    Button calButton = null;
    Button clearButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_budget);

        tPowerEdit = (EditText)findViewById(R.id.editText1);
        tGainEdit = (EditText)findViewById(R.id.editText2);;
        rGainEdit = (EditText)findViewById(R.id.editText3);;
        fslEdit = (EditText)findViewById(R.id.editText4);;
        misc_lossEdit = (EditText)findViewById(R.id.editText5);;
        tFeederEdit = (EditText)findViewById(R.id.editText6);;
        rFeederEdit = (EditText)findViewById(R.id.editText7);;
        calButton = (Button)findViewById(R.id.button1);
        calButton.setOnClickListener(this);
        clearButton = (Button)findViewById(R.id.button2);
        clearButton.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_link_budget, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        String ptxStr = tPowerEdit.getText().toString();
        String gtxStr = tGainEdit.getText().toString();
        String grxStr = rGainEdit.getText().toString();
        String lfsStr = fslEdit.getText().toString();
        String mslStr = misc_lossEdit.getText().toString();
        String ltxStr = tFeederEdit.getText().toString();
        String lrxStr = rFeederEdit.getText().toString();
        double ptx = Double.parseDouble(!ptxStr.isEmpty() ? ptxStr : "0");
        double gtx = Double.parseDouble(!gtxStr.isEmpty() ? gtxStr : "0");
        double grx = Double.parseDouble(!grxStr.isEmpty() ? grxStr : "0");
        double lfs = Double.parseDouble(!lfsStr.isEmpty() ? lfsStr : "0");
        double msl = Double.parseDouble(!mslStr.isEmpty() ? mslStr : "0");
        double ltx = Double.parseDouble(!ltxStr.isEmpty() ? ltxStr : "0");
        double lrx = Double.parseDouble(!lrxStr.isEmpty() ? lrxStr : "0");
        if(v == calButton){
            double result = ptx + gtx - ltx - lfs - msl + grx - lrx;

            ((TextView)findViewById(R.id.textTransmissionPrd)).setText(Double.toString(result));
        } else
            if(v == clearButton){
                ((EditText)findViewById(R.id.editText1)).setText("");
                ((EditText)findViewById(R.id.editText2)).setText("");
                ((EditText)findViewById(R.id.editText3)).setText("");
                ((EditText)findViewById(R.id.editText4)).setText("");
                ((EditText)findViewById(R.id.editText5)).setText("");
                ((EditText)findViewById(R.id.editText6)).setText("");
                ((EditText)findViewById(R.id.editText7)).setText("");
            }
    }
}
