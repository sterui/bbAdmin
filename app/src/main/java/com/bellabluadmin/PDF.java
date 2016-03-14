package com.bellabluadmin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

@SuppressLint("SdCardPath")
public class PDF extends ActionBarActivity {

	private InputStream is;
	private String result,punteggio,timestamp,dipartimento;
	private String request="";
	private String mese="Gennaio";
	private String nomeCam="vuoto";
	private int _mese;
	private boolean fail = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pdf);
		StrictMode.enableDefaults();
		Button b1 = (Button) findViewById(R.id.button1);
		Button b2 = (Button) findViewById(R.id.button2);
		@SuppressWarnings("unused")
		final TextView tw = (TextView) findViewById(R.id.textView1);
		final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	            android.R.layout.simple_spinner_item, getResources()
	                    .getStringArray(R.array.bellablu));//setting the country_array to spinner
	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    
	    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		       

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mese =  spinner.getSelectedItem().toString();
				
				
				
			          
				
				
				
			}
			

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
				
			}
	    });
	    b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	final ProgressDialog ringProgressDialog = ProgressDialog.show(PDF.this, "Please wait ...",	"Creazione PDF ...", true);
        		ringProgressDialog.setCancelable(true);
        		new Thread(new Runnable() {
        			@Override
        			public void run() {
        				try {
        					_mese = checkMese(mese);
        	            	
        	            	requestData(_mese);
        	            	
        	            	
        	                
        					Thread.sleep(10000);
        				} catch (Exception e) {

        				}
        				ringProgressDialog.dismiss();
        			}
        		}).start();
        		
        		if (!fail) {
                    
                } else {
                    Toast.makeText(getApplicationContext(), "I/O error",
                            Toast.LENGTH_SHORT).show();
            	
            	
            	
               
            }
            }});
	 
	 b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(Intent.ACTION_VIEW,
            	        Uri.parse("/sdcard/" + mese + ".pdf"));
            	intent.setType("application/pdf");
            	PackageManager pm = getPackageManager();
            	List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
            	if (activities.size() > 0) {
            	    startActivity(intent);
            	} else {
            		 Toast.makeText(getApplicationContext(), "PDF non trovato",
                             Toast.LENGTH_LONG).show();
            	}
               
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
	
	public int checkMese(String mese)
	{
		if(mese.equals("Gennaio"))
			return 1;
			else if(mese.equals("Febbraio"))
				return 2;
			else if(mese.equals("Marzo"))
				return 3;
			else if(mese.equals("Aprile"))
				return 4;
			else if(mese.equals("Maggio"))
				return 5;
			else if(mese.equals("Giugno"))
				return 6;
			else if(mese.equals("Luglio"))
				return 7;
			else if(mese.equals("Agosto"))
				return 8;
			else if(mese.equals("Settembre"))
				return 9;
			else if(mese.equals("Ottobre"))
				return 10;
			else if(mese.equals("Novembre"))
				return 11;
			else 
				return 12;
		
	
	}
		
	
	
	@Override
	public void onBackPressed()
	{
	    finish();  
	}
	public void createPDF()
    {
		 try {
	            String fpath = "/sdcard/" + mese + ".pdf";
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
	            document.add(new Paragraph("Risultati mese di "+mese +"\n\n\n"));
	            document.add(new Paragraph(request));
	            // step 5
	            document.close();

	            Log.d("Suceess", "Sucess");
	            fail =false;
	        } catch (IOException e) {
	            e.printStackTrace();
	            fail=true;
	        } catch (DocumentException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            fail=true;
	            
	        }
		 
		 
		 
		 }	
	
	public void requestData(int mese_){
		HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://bellablu.dx.am/pdfMese.php");
        
        JSONObject json = new JSONObject();

        try {
            // JSON data:
        
            json.put("mese", mese_);
           
           
            JSONArray postjson=new JSONArray();
            postjson.put(json);

            // Post the data:
            httppost.setHeader("json",json.toString());
            httppost.getParams().setParameter("jsonpost",postjson);
            // Execute HTTP Post Request
            //System.out.print(json);
            HttpResponse response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();
             is = entity.getContent();

           
        }
       
         catch (IOException e) {
            e.printStackTrace();
           
			Log.i("TAGSend", "Test Sql...");
        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
            }
            is.close();

            result=sb.toString();
      }catch(Exception e){
            Log.e("log_tag", "Error converting result "+e.toString());
      }

      //parse json data
        try{
            JSONArray jArray = new JSONArray(result);
           

            JSONObject json_data = jArray.getJSONObject(0);
            String sum= json_data.getString("COUNT(timestamp)");
            JSONObject json_data2 = jArray.getJSONObject(1);
            String sum2= json_data2.getString("COUNT(timestamp)");
            JSONObject json_data3 = jArray.getJSONObject(2);
            String sum3= json_data3.getString("COUNT(timestamp)");
            JSONObject json_data4 = jArray.getJSONObject(3);
            String sum4= json_data4.getString("COUNT(timestamp)");
            JSONObject json_data5 = jArray.getJSONObject(4);
            String sum5= json_data5.getString("COUNT(timestamp)");
            JSONObject json_data6 = jArray.getJSONObject(5);
            String sum6= json_data6.getString("COUNT(timestamp)");
         
           
                   
            request+="Totale voti mese di " +mese+": "+sum+".\n\n";      
            request+="Durante il mese di " +mese+", sono stati registrati "+sum2+" voti da 1 Stella.\n\n";
            request+="Durante il mese di " +mese+", sono stati registrati "+sum3+" voti da 2 Stelle.\n\n";
            request+="Durante il mese di " +mese+", sono stati registrati "+sum4+" voti da 3 Stelle.\n\n";
            request+="Durante il mese di " +mese+", sono stati registrati "+sum5+" voti da 4 Stelle.\n\n";
            request+="Durante il mese di " +mese+", sono stati registrati "+sum6+" voti da 5 Stelle.\n\n";
            request+="-------------------------------------------------------------------------------- \n\n";
           
            for(int i=6;i<jArray.length();i++){
                JSONObject json_data7 = jArray.getJSONObject(i);
                punteggio = json_data7.getString("punteggio");
                dipartimento = json_data7.getString("Dipartimento");
                timestamp = json_data7.getString("timestamp");
                nomeCam =  json_data7.getString("NomeCam");
                if(dipartimento.equals("food")) dipartimento="cucina";
                
                request+="Dipartimento: " +dipartimento+", punteggio: "+punteggio+", registrato alle: "+timestamp+ ". Cameriere selezionato: "+nomeCam+ "\n";
            
                createPDF();
      }}
      catch(JSONException e){
            Log.e("log_tag", "Error parsing data "+e.toString());
      }

	}
	
}
