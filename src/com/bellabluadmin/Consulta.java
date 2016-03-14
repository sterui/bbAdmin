package com.bellabluadmin;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Consulta extends ActionBarActivity {
	@SuppressWarnings("unused")
	private TextView tw;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inserisci);
		setTitle("Consulta");
		tw = (TextView) findViewById(R.id.textView1);
		final Intent intent = new Intent(this, Dipartimento.class);
		final Intent intent2 = new Intent(this, Giorno.class);
		final Intent intent3 = new Intent(this, Mese.class);
	
		Button b1 = (Button) findViewById(R.id.button1);
		Button b2 = (Button) findViewById(R.id.button2);
		Button b3 = (Button) findViewById(R.id.button3);
		
		
		
		b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startActivity(intent);
            	
               
            }
        });
	 
	 b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startActivity(intent3);
            	
               
            }
        });
	 b3.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
        	 startActivity(intent2);
         	
            
         }
     });
	}

	@Override
	 public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
      case android.R.id.home: 
          // API 5+ solution
          onBackPressed();
          return true;

      default:
          return super.onOptionsItemSelected(item);
      }
  }
}
