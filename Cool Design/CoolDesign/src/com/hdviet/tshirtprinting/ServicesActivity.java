package com.hdviet.tshirtprinting;

import android.os.Bundle;

public class ServicesActivity extends MActivity
{
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.service_layout );
		
		// LinearLayout icon_contact = ( LinearLayout ) findViewById( R.id.icon_contact );
		// icon_contact.setOnClickListener( new OnClickListener( )
		// {
		//
		// @Override
		// public void onClick( View v )
		// {
		// Intent t = new Intent( ServicesActivity.this, ContactsActivity.class );
		// startActivity( t );
		// }
		// } );
	}
}
