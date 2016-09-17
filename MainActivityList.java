package com.example.anand.volleylistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivityList extends Activity{

	public ListView list;
    public ArrayList<Model> doc = new ArrayList<Model>();
    public ListAdapter adapter;

	private String urlJsonArry = "http://api.androidhive.info/volley/person_array.json";
	private static String TAG = MainActivity.class.getSimpleName();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_list);
		makeJsonArrayRequest();
		list = (ListView) findViewById(R.id.list);

		adapter = new ListAdapter(this);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(MainActivityList.this, MainActivity.class);
				intent.putExtra("Code", doc.get(position).name);
				intent.putExtra("Name", doc.get(position).email);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
		});
	}

	private void makeJsonArrayRequest() {

		JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());

						try {
							for (int i = 0; i < response.length(); i++) {

								JSONObject person = (JSONObject) response
										.get(i);

								Model add=new Model();
								add.name = person.getString("name");
								add.email = person.getString("email");
								doc.add(add);

							}
							Toast.makeText(getApplicationContext(), "test : ",Toast.LENGTH_LONG).show();
							adapter.notifyDataSetChanged();

						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(getApplicationContext(),
									"Error: " + e.getMessage(),
									Toast.LENGTH_LONG).show();
						}
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				Toast.makeText(getApplicationContext(),
						error.getMessage(), Toast.LENGTH_SHORT).show();
			}
		});

		AppController.getInstance().addToRequestQueue(req);
	}

}
