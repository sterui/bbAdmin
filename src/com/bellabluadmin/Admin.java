package com.bellabluadmin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;




import org.apache.http.util.ByteArrayBuffer;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Admin extends ActionBarActivity {

   


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		StrictMode.enableDefaults();
		final Intent intent2 = new Intent(this, Risultati.class);
		final Intent intent = new Intent(this, Voti.class);
		final Intent intent3 = new Intent(this, Consulta.class);
		final Intent intent4 = new Intent(this, Altro.class);
		Button b1 = (Button) findViewById(R.id.button1);
		Button b2 = (Button) findViewById(R.id.button2);
		Button b3 = (Button) findViewById(R.id.button3);
		Button b4 = (Button) findViewById(R.id.button4);
		
		 b1.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	startActivity(intent);
	            	
	               
	            }
	        });
		 
		 b2.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	startActivity(intent2);
	            	
	               
	            }
	        });
		 
		 b3.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	startActivity(intent3);
	            	
	               
	            }
	        });
		 
		 b4.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	startActivity(intent4);
	            	
	               
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
	

   

   // and the method is

public void DownloadDatabase(String DownloadUrl) {
   try {
       
       URL url = new URL(DownloadUrl);

       URLConnection ucon = url.openConnection();
       ucon.setReadTimeout(5000);
       ucon.setConnectTimeout(10000);

       InputStream is = ucon.getInputStream();
       BufferedInputStream bis = new BufferedInputStream(is, 1024 * 5);
       File file = new File(Environment.getExternalStorageDirectory().getPath() + "/bella/test.xls");

       if (file.exists())
       {
           file.delete();
       }
       file.createNewFile();

       ByteArrayBuffer baf = new ByteArrayBuffer(50);
       int current = 0;
       while ((current = bis.read()) != -1) {
          baf.append((byte) current);
       }
       FileOutputStream fos = new FileOutputStream(file);
       fos.write(baf.toByteArray());
       fos.close();

      
       bis.close();

   }
   catch (Exception e)
   {
       e.printStackTrace();
       
   
   
   }

}
}
