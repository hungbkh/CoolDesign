package com.hdviet.cooldesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class ServicesActivity extends MActivity
{
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.service_layout );
		
		LinearLayout icon_contact = ( LinearLayout ) findViewById( R.id.icon_contact );
		icon_contact.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				Intent t = new Intent( ServicesActivity.this, ContactsActivity.class );
				startActivity( t );
			}
		} );
	}
}
