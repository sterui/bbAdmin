package com.bellabluadmin;



import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		final String user = "carmelo";
		final String psw = "220957";
		final Intent intent = new Intent(this, Due.class);
		final TextView tw = (TextView) findViewById(R.id.textView1);
		tw.setMovementMethod(new ScrollingMovementMethod());
		final EditText et1= (EditText) findViewById(R.id.editText1);
		final EditText et2= (EditText) findViewById(R.id.editText2);
		final Button but= (Button) findViewById(R.id.button1);
		
		but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(et1.getText().toString().equals(user) && et2.getText().toString().equals(psw))
            	startActivity(intent);
            	else
            		Toast.makeText(getApplicationContext(),"Login Failed", 
            	            Toast.LENGTH_LONG).show();
               
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
}
