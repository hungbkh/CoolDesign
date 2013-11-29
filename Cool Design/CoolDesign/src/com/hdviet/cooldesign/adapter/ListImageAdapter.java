package com.hdviet.cooldesign.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.hdviet.cooldesign.R;

public class ListImageAdapter extends ArrayAdapter< ListImageItem >
{
	class VietHolder
	{
		ImageView	image;
	}
	
	Context					context;
	List< ListImageItem >	listImage;
	
	public ListImageAdapter( final Activity context, final List< ListImageItem > listImage )
	{
		super( context, R.layout.list_image_item, listImage );
		this.context = context;
		this.listImage = listImage;
		Log.d( "", "create adapter" );
		
	}
	
	@Override
	public View getView( int position, View convertView, ViewGroup parent )
	{
		Log.d( "", "yyyyyy" );
		
		View rowView = convertView;
		if ( rowView == null )
		{
			final LayoutInflater inflater = ( ( Activity ) this.context ).getLayoutInflater( );
			rowView = inflater.inflate( R.layout.list_image_item, null );
			
			final VietHolder viewHolder = new VietHolder( );
			
			viewHolder.image = ( ImageView ) rowView.findViewById( R.id.imageView1 );
			
			rowView.setTag( viewHolder );
		}
		
		final VietHolder holder = ( VietHolder ) rowView.getTag( );
		ListImageItem item = listImage.get( position );
		
		Log.d( "", item.getNameImage( ) + "" );
		holder.image.setImageResource( item.getNameImage( ) );
		
		return rowView;
	}
}
