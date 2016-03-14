package com.bellabluadmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Mese extends ActionBarActivity {
	
	private InputStream is;
	private String result;
	private String request="";
	private String mese="Gennaio";
	private int _mese;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mese);
		StrictMode.enableDefaults();
		@SuppressWarnings("unused")
		final TextView tw = (TextView) findViewById(R.id.textView1);
		final TextView tw2 = (TextView) findViewById(R.id.textView2);
		tw2.setMovementMethod(new ScrollingMovementMethod());
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
				
				_mese = checkMese(mese);
				sendRequest(_mese);
				
			            tw2.setText(request);
			            tw2.scrollTo(0, 0);
						request="";
				
				
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
				
			}
	    });
	    
	   
	    
		
	}
	
	public void sendRequest(int mese_)
	{
		HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://bellablu.dx.am/getMese.php");
        
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
            String sum= json_data.getString("SUM(punteggio)");
            JSONObject json_data2 = jArray.getJSONObject(1);
            String sum2= json_data2.getString("SUM(punteggio)");
            JSONObject json_data3 = jArray.getJSONObject(2);
            String sum3= json_data3.getString("SUM(punteggio)");
                   
                   
                   request+= "Il dipartimento cucina, nel mese di "+ mese + " ha ottenuto il seguente punteggio:  "+ sum+"!!\n\n";
                   request+= "Il dipartimento pulizia, nel mese di "+ mese + " ha ottenuto il seguente punteggio:  "+ sum2+"!!\n\n";
                   request+= "Il dipartimento servizio, nel mese di "+ mese + " ha ottenuto il seguente punteggio:  "+ sum3+"!!\n\n";
            
      }
      catch(JSONException e){
            Log.e("log_tag", "Error parsing data "+e.toString());
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
	}
