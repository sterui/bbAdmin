package com.bellabluadmin;




import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Due extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_due);
		setTitle("Menu");
		StrictMode.enableDefaults();
		final Intent intent = new Intent(this, Admin.class);
		final Intent intent2 = new Intent(this, Cancella.class);
		setTitle("BellaBluAdmin");
		Button b1 = (Button) findViewById(R.id.button1);
		Button b2 = (Button) findViewById(R.id.button2);
		Button b3 = (Button) findViewById(R.id.button3);
		
		 b1.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	setControllo();
	            	Toast.makeText(getApplicationContext(),"Sbloccato", 
	        	            Toast.LENGTH_LONG).show();
	            	
	               
	            }
	        });
		 
		 b2.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	startActivity(intent);
	               
	            }
	        });
		 
		 b3.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	startActivity(intent2);
	               
	            }
	        });
	}
	 public void setControllo()
	    {
	    	 try{
	             HttpClient httpclient = new DefaultHttpClient();
	             HttpPost httppost = new HttpPost("http://bellablu.dx.am/controlloadmin.php");
	            
	             httpclient.execute(httppost);
	             
	           
	          }
	          catch(Exception e){
	       	   Log.e("log_tag2", "Error converting result "+e.toString());
	              System.out.println("Error in http connection "+e.toString());
	        }
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
