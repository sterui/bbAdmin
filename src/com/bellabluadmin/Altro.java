package com.bellabluadmin;






import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.support.v7.app.ActionBarActivity;



import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class Altro extends ActionBarActivity {

	 
	 
	
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_altro);
		StrictMode.enableDefaults();
		
		
		Button b1 = (Button) findViewById(R.id.button1);
		Button b2 = (Button) findViewById(R.id.button2);
		Button b3 = (Button) findViewById(R.id.button3);
		final Intent intent = new Intent(this, PDF.class);
		
		
		 b2.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	
	            	setBlocco();
	            	Toast.makeText(Altro.this, "BLOCCO PERMANENTE IMPOSTATO!!!", Toast.LENGTH_LONG).show();
	               
	            }
	        });
		 
		 b1.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	
	            	setSblocco();
	            	Toast.makeText(Altro.this, "SBLOCCO PERMANENTE IMPOSTATO!!!", Toast.LENGTH_LONG).show();
	               
	            }
	        });
		 
		 b3.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	
	            	startActivity(intent);
	            	
	            	/*createPDF();
	            	if (createPDF()) {
	                    Toast.makeText(getApplicationContext(),
	                            "test" + ".pdf created", Toast.LENGTH_SHORT)
	                            .show();
	                } else {
	                    Toast.makeText(getApplicationContext(), "I/O error",
	                            Toast.LENGTH_SHORT).show();
	                }*/
	               
	            }
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.altro, menu);
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
	
	public void setBlocco()
	{
		 try{
             HttpClient httpclient = new DefaultHttpClient();
             HttpPost httppost = new HttpPost("http://bellablu.dx.am/setBlocco.php");
            
             httpclient.execute(httppost);
             
           
          }
          catch(Exception e){
       	   Log.e("log_tag2", "Error converting result "+e.toString());
              System.out.println("Error in http connection "+e.toString());
        }
	}
	
	public void setSblocco()
	{
		 try{
             HttpClient httpclient = new DefaultHttpClient();
             HttpPost httppost = new HttpPost("http://bellablu.dx.am/setSblocco.php");
            
             httpclient.execute(httppost);
             
           
          }
          catch(Exception e){
       	   Log.e("log_tag2", "Error converting result "+e.toString());
              System.out.println("Error in http connection "+e.toString());
        }
		
	}
	
	
/*	@SuppressLint("SdCardPath")
	public boolean createPDF()
    {
		 try {
	            String fpath = "/sdcard/" + "test" + ".pdf";
	            File file = new File(fpath);
	            // If file does not exists, then create it
	            if (!file.exists()) {
	                file.createNewFile();
	            }

	            // step 1
	            Document document = new Document();
	            // step 2
	            PdfWriter.getInstance(document,
	                    new FileOutputStream(file.getAbsoluteFile()));
	            // step 3
	            document.open();
	            // step 4
	            document.add(new Paragraph("Hello World!"));
	            document.add(new Paragraph("Hello World2!"));
	            // step 5
	            document.close();

	            Log.d("Suceess", "Sucess");
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        } catch (DocumentException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return false;
	            
	        }
		 
		 
		 
		 }*/
		 
		 
		 
}
