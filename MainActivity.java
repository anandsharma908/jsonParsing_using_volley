package com.example.anand.volleylistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static String TAG = MainActivity.class.getSimpleName();
	private Button btnMakeArrayRequest;
	private EditText ETCOde;
	private EditText ETName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnMakeArrayRequest = (Button) findViewById(R.id.btnArrayRequest);
		ETCOde = (EditText)findViewById(R.id.ETCode);
		ETCOde.setEnabled(false);
		ETName = (EditText)findViewById(R.id.ETName);
		ETName.setEnabled(false);
		btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MainActivityList.class);
				startActivity(intent);
			}
		});
		if (getIntent() != null){
			if (getIntent().hasExtra("Code")) {
				ETCOde.setText(getIntent().getStringExtra("Code"), TextView.BufferType.EDITABLE);
			}
			if (getIntent().hasExtra("Name")) {
				ETName.setText(getIntent().getStringExtra("Name"), TextView.BufferType.EDITABLE);
			}
		}
	}
}
