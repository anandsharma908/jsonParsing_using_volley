package com.example.anand.volleylistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	MainActivityList main;
	
	ListAdapter(MainActivityList main)
	{
		this.main = main;
	}
	
	@Override
	public int getCount() {
		return  main.doc.size();
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	static class ViewHolderItem {
		TextView name;
		TextView email;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolderItem holder = new ViewHolderItem();
		if (convertView == null) {
		 LayoutInflater inflater = (LayoutInflater) main.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.list_item, null);
	        
	        holder.name = (TextView) convertView.findViewById(R.id.name);
	        holder.email = (TextView) convertView.findViewById(R.id.code);
	        
	        convertView.setTag(holder);
		}
		else
		{
			 holder = (ViewHolderItem) convertView.getTag();
		}
	        
		
		holder.name.setText(this.main.doc.get(position).name);
		holder.email.setText(this.main.doc.get(position).email);
		
		return convertView;
	}

}
