package com.epsilon.rfcalculator;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;

public class GraphActivity extends Activity {

	private int chart = 0;
	private double tPower;
	private double tGain;
	private double rGain;
	private double freq;
	private double tHeight;
	private double rHeight;
	private double wavelength;
	private double light;
	private double er;
	
	private GraphicalView mChart;
    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeries mCurrentSeries;
    private XYSeriesRenderer mCurrentRenderer;

    private void initChart() {
        mCurrentSeries = new XYSeries("Sample Data");
        mDataset.addSeries(mCurrentSeries);
        mCurrentRenderer = new XYSeriesRenderer();

        mCurrentRenderer.setPointStyle(PointStyle.CIRCLE);
        mCurrentRenderer.setFillPoints(true);

        mRenderer.setGridColor(Color.GRAY);
        mRenderer.setShowGrid(true);
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.BLACK);
        mRenderer.setPointSize(2);
        
        mRenderer.addSeriesRenderer(mCurrentRenderer);
        mRenderer.setLabelsColor(Color.YELLOW);
        mRenderer.setInScroll(true);
        mRenderer.setXTitle("T-x-Rx distance(m)");
        mRenderer.setYTitle("Received Power(W)");
        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setShowLegend(false);
        mRenderer.setXLabelsColor(Color.RED);
        mRenderer.setYLabelsColor(0, Color.RED);
        mRenderer.setZoomRate((float) 0.5);
    }

    private void addFreeSpaceData() {
        double rPower;
		double lamda = 300000000/freq;
		//mCurrentSeries.add(0, Double.MAX_VALUE);
		double d = 100;
		while(d<=1000) {
//			rPower = tPower * rGain * Math.pow(lamda / 4 * Math.PI * d, 2);
			rPower = tPower * tGain * rGain * Math.pow(lamda / (4 * Math.PI * d),2)/ light / 100;
			mCurrentSeries.add(d, rPower);
			d = d + 1;
		}
		/*
		for(int i=100;i<1000;i++) {
    		distance = i * 10;
			rPower = tPower * tGain * rGain * Math.pow(lamda / (4 * Math.PI * distance),2)/ loss / 100;
			mCurrentSeries.add(distance, rPower);
        }
        */
		//mCurrentSeries.add(Double.MAX_VALUE, 0);
    }	
    
    private void addTwoRayData() {
        double rPower;
	
		double lambda = wavelength; 
		double d = 1;
		double dl, dr, phi, s0, c0, gamma, p2r, p2r_dbm;
		while(d<=100) {
			rPower = tPower * rGain * tGain * Math.pow(lambda / (4 * Math.PI * d), 2);
			dl = Math.sqrt(Math.pow(d, 2) + Math.pow(tHeight - rHeight,2));
			dr = Math.sqrt(Math.pow(d, 2) + Math.pow(tHeight + rHeight,2));
			phi = 2*Math.PI*(dr-dl)/lambda;
			s0 = (tHeight+rHeight)/dr;
			c0 = d/dr;
			gamma = (s0-Math.sqrt(er-Math.pow(c0,2)))/(s0+Math.sqrt(er-Math.pow(c0,2)));
			p2r = rPower*(Math.pow((gamma*Math.cos(phi)+1),2)+Math.pow((gamma*Math.sin(phi)),2));
			mCurrentSeries.add(d, p2r);
			d = d + 0.1;
		}
		
/*
		for(int i=10;i<100;i++) {
    		distance = i * 1;
			rPower = tPower * tGain * rGain * Math.pow(tHeight * rHeight,2) / (Math.pow(distance,4) * loss);
			mCurrentSeries.add(distance, rPower);
        }
*/        
    }	

    private void addTwoRayData2() {
        double rPower;
	
		double lambda = wavelength; 
		double dl, dr, phi, s0, c0, gamma, p2r, p2r_dbm;
		double d = 1;
		while(d<=100) {
			rPower = tPower * rGain * tGain * Math.pow(lambda / (4 * Math.PI * d), 2);
			dl = Math.sqrt(Math.pow(d, 2) + Math.pow(tHeight - rHeight,2));
			dr = Math.sqrt(Math.pow(d, 2) + Math.pow(tHeight + rHeight,2));
			phi = 2*Math.PI*(dr-dl)/lambda;
			s0 = (tHeight+rHeight)/dr;
			c0 = d/dr;
			gamma = (s0-Math.sqrt(er-Math.pow(c0,2)))/(s0+Math.sqrt(er- Math.pow(c0, 2)));
			p2r = rPower*(Math.pow((gamma * Math.cos(phi) + 1), 2) + Math.pow((gamma * Math.sin(phi)), 2));
			p2r_dbm = 10*(Math.log(p2r*1000)/Math.log(10));
			mCurrentSeries.add(d, p2r_dbm);
			d = d + 0.1;
		}
		
/*
		for(int i=10;i<100;i++) {
    		distance = i * 1;
			rPower = tPower * tGain * rGain * Math.pow(tHeight * rHeight,2) / (Math.pow(distance,4) * loss);
			mCurrentSeries.add(distance, rPower);
        }
*/        
    }	

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graph);
		
		String model = getIntent().getStringExtra("model");
		if(model.equals("freeSpace")) {
			chart = 1;
			tPower = getIntent().getDoubleExtra("tp", 0.0);
			tGain = getIntent().getDoubleExtra("tg", 0.0);
			rGain = getIntent().getDoubleExtra("rg", 0.0);
			freq = getIntent().getDoubleExtra("f", 0.0);
			light = getIntent().getDoubleExtra("l", 0.0);
//			((ImageView)findViewById(R.id.imageView1)).setImageResource(R.drawable.freespace);
		}
		else if(model.equals("twoRay")) {
			chart = 2;
			tPower = getIntent().getDoubleExtra("tp", 0.0);
			tGain = getIntent().getDoubleExtra("tg", 0.0);
			rGain = getIntent().getDoubleExtra("rg", 0.0);
			tHeight = getIntent().getDoubleExtra("th", 0.0);
			rHeight = getIntent().getDoubleExtra("rh", 0.0);
			er = getIntent().getDoubleExtra("rp", 0.0);
			wavelength = getIntent().getDoubleExtra("w", 0.0);
//			((ImageView)findViewById(R.id.imageView1)).setImageResource(R.drawable.tworay);
		}
		else if(model.equals("twoRay2")) {
			chart = 3;
			tPower = getIntent().getDoubleExtra("tp", 0.0);
			tGain = getIntent().getDoubleExtra("tg", 0.0);
			rGain = getIntent().getDoubleExtra("rg", 0.0);
			tHeight = getIntent().getDoubleExtra("th", 0.0);
			rHeight = getIntent().getDoubleExtra("rh", 0.0);
			er = getIntent().getDoubleExtra("rp", 0.0);
			wavelength = getIntent().getDoubleExtra("w", 0.0);
//			((ImageView)findViewById(R.id.imageView1)).setImageResource(R.drawable.tworay);
		}
	}

	protected void onResume() {
        super.onResume();
        LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
        if (mChart == null) {
            initChart();
            switch(chart) {
            case 1: addFreeSpaceData(); mRenderer.setChartTitle("Free Space Propagation Model"); break;
            case 2: addTwoRayData(); mRenderer.setChartTitle("Two Ray Propagation Model"); break;
            case 3: addTwoRayData2(); mRenderer.setChartTitle("Two Ray Propagation Model"); break;
            }
            mChart = ChartFactory.getCubeLineChartView(this, mDataset, mRenderer, 0.3f);
            layout.addView(mChart);
        } else {
            mChart.repaint();
        }
    }	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graph, menu);
		return true;
	}

}
