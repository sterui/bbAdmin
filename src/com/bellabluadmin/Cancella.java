package com.bellabluadmin;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Cancella extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cancella);
		StrictMode.enableDefaults();
		Button b1 = (Button) findViewById(R.id.button1);
		Button b2 = (Button) findViewById(R.id.button2);
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cancella.this);
		 
        // Setting Dialog Title
        alertDialog.setTitle("Cancella");
 
        // Setting Dialog Message
        alertDialog.setMessage("Operazione irreversibile. Sei sicuro?");
 
       
 
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
 
            // Write your code here to invoke YES event
            Toast.makeText(getApplicationContext(), "Cancellato", Toast.LENGTH_SHORT).show();
            cancella();
            }
        });
 
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            // Write your code here to invoke NO event
            Toast.makeText(getApplicationContext(), "Operazione annullata", Toast.LENGTH_SHORT).show();
            dialog.cancel();
            }
        });
		
		 b1.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	//Toast.makeText(getApplicationContext(),"Excel Creato", 
         	       //     Toast.LENGTH_LONG).show();
	            	//DownloadDatabase(DownloadUrl);
	            	alertDialog.show();
	               
	            }
	        });
		 
		 b2.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	finish();
	            	
	               
	            }
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cancella, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void cancella()
	{
		 try{
             HttpClient httpclient = new DefaultHttpClient();
             HttpPost httppost = new HttpPost("http://bellablu.dx.am/cancella.php");
            
             httpclient.execute(httppost);
             
           
          }
          catch(Exception e){
       	   Log.e("log_tag2", "Error converting result "+e.toString());
              System.out.println("Error in http connection "+e.toString());
        }
	}
}
