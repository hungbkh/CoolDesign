package com.hdviet.tshirtprinting;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hdviet.tshirtprinting.adapter.ListImageAdapter;
import com.hdviet.tshirtprinting.adapter.ListImageItem;

public class ListImageActivity extends MActivity
{
	private int[]					listImageId	=
												{
												R.drawable.portfolio_pic1, R.drawable.portfolio_pic100,
												R.drawable.portfolio_pic2,
												R.drawable.portfolio_pic3, R.drawable.portfolio_pic4,
												R.drawable.portfolio_pic5,
												R.drawable.portfolio_pic6,
												R.drawable.portfolio_pic7, R.drawable.portfolio_pic8,
												R.drawable.portfolio_pic99
												};
	private ListImageAdapter		listImageAdapter;
	private ListView				listViewImage;
	private List< ListImageItem >	listImageItem;
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.list_image_activity );
		listViewImage = ( ListView ) findViewById( R.id.list_image );
		
		listImageItem = new ArrayList< ListImageItem >( );
		for ( int k = 0; k < listImageId.length; k++ )
		{
			Log.d( "", "init image" );
			ListImageItem imageItem = new ListImageItem( listImageId[ k ] );
			listImageItem.add( imageItem );
		}
		listImageAdapter = new ListImageAdapter( this, listImageItem );
		listViewImage.setAdapter( listImageAdapter );
		
		listViewImage.setOnItemClickListener( new OnItemClickListener( )
		{
			
			@Override
			public void onItemClick( AdapterView< ? > parent, View view, int position, long id )
			{
				Intent t = new Intent( ListImageActivity.this, ImageViewTouchActivity.class );
				t.putExtra( "image", listImageId[ position ] );
				startActivity( t );
			}
		} );
	}
}
