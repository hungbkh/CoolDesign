package com.hdviet.cooldesign;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hdviet.cooldesign.help.MyClipboardManager;

public class ContactsActivity extends MActivity
{
	
	private PopupWindow	pwindo;
	
	MyClipboardManager	manager	= new MyClipboardManager( );
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.contacts_layout );
		
		LinearLayout tel_phone = ( LinearLayout ) findViewById( R.id.tel_phone );
		LinearLayout cell_phone2 = ( LinearLayout ) findViewById( R.id.cell_phone2 );
		LinearLayout email2 = ( LinearLayout ) findViewById( R.id.email2 );
		LinearLayout email1 = ( LinearLayout ) findViewById( R.id.email1 );
		LinearLayout map = ( LinearLayout ) findViewById( R.id.map );
		
		final TextView tv_tel_phone = ( TextView ) findViewById( R.id.tv_tel_phone );
		final TextView tv_cel_phone = ( TextView ) findViewById( R.id.tv_cel_phone );
		final TextView tv_email2 = ( TextView ) findViewById( R.id.tv_email2 );
		final TextView tv_email1 = ( TextView ) findViewById( R.id.tv_email1 );
		
		// final LinearLayout company_name = ( LinearLayout ) findViewById( R.id.company_name );
		// company_name.setOnClickListener( new OnClickListener( )
		// {
		//
		// @Override
		// public void onClick( View v )
		// {
		// showPopup( company_name, "2COOLDESIGN" );
		// }
		// } );
		//
		// final LinearLayout contact = ( LinearLayout ) findViewById( R.id.contact );
		// contact.setOnClickListener( new OnClickListener( )
		// {
		//
		// @Override
		// public void onClick( View v )
		// {
		// showPopup( company_name, "Guy Tasker" );
		// }
		// } );
		//
		// final LinearLayout fax = ( LinearLayout ) findViewById( R.id.fax );
		// fax.setOnClickListener( new OnClickListener( )
		// {
		//
		// @Override
		// public void onClick( View v )
		// {
		// showPopup( company_name, "086 524 8688" );
		// }
		// } );
		
		tel_phone.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				try
				{
					Intent callIntent = new Intent( Intent.ACTION_DIAL );
					callIntent.setData( Uri.parse( "tel:" + tv_tel_phone.getText( ).toString( ) ) );
					callIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
					startActivity( callIntent );
				}
				catch ( ActivityNotFoundException activityException )
				{
					Log.e( "Calling a Phone Number", "Call failed", activityException );
				}
			}
		} );
		
		cell_phone2.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				try
				{
					Intent callIntent = new Intent( Intent.ACTION_DIAL );
					callIntent.setData( Uri.parse( "tel:" + tv_cel_phone.getText( ).toString( ) ) );
					callIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
					startActivity( callIntent );
				}
				catch ( ActivityNotFoundException activityException )
				{
					Log.e( "Calling a Phone Number", "Call failed", activityException );
				}
			}
		} );
		
		email2.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				Intent email = new Intent( Intent.ACTION_SEND );
				email.putExtra( Intent.EXTRA_EMAIL, new String[ ]
				{ tv_email2.getText( ).toString( ) }
						);
				email.putExtra( Intent.EXTRA_SUBJECT, "subject" );
				email.putExtra( Intent.EXTRA_TEXT, "message" );
				email.setType( "message/rfc822" );
				startActivity( Intent.createChooser( email, "Choose an Email client :" ) );
			}
		} );
		
		email1.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				Intent email = new Intent( Intent.ACTION_SEND );
				email.putExtra( Intent.EXTRA_EMAIL, new String[ ]
				{ tv_email1.getText( ).toString( ) }
						);
				email.putExtra( Intent.EXTRA_SUBJECT, "subject" );
				email.putExtra( Intent.EXTRA_TEXT, "message" );
				email.setType( "message/rfc822" );
				startActivity( Intent.createChooser( email, "Choose an Email client :" ) );
			}
		} );
		
		map.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				Intent t = new Intent( ContactsActivity.this, MapActivity.class );
				startActivity( t );
			}
		} );
		
		LinearLayout share = ( LinearLayout ) findViewById( R.id.share );
		share.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				Intent email = new Intent( Intent.ACTION_SEND );
				email.putExtra( Intent.EXTRA_EMAIL, new String[ ]
				{ "" }
						);
				email.putExtra( Intent.EXTRA_SUBJECT, "Great app" );
				String body =
						"COMPANY NAME:       2COOLDESIGN" + "\n" +
								"CONTACT PERSON:     Guy Tasker" + "\n" +
								"TEL:                073 171 0962" + "\n" +
								"FAX:                086 524 8688" + "\n" +
								"CELL:               073 171 0962" + "\n" +
								"EMAIL:              guy@2cooldesign.co.za, info@2cooldesign.co.za" + "\n" +
								"ADD:                15 Julian Street, Risiville, Vereeniging, Gauteng, South Africa"
								+ "\n";
				
				email.putExtra( Intent.EXTRA_TEXT, body );
				email.setType( "message/rfc822" );
				startActivity( Intent.createChooser( email, "Choose an Email client :" ) );
			}
		} );
	}
	
	private void showPopup( LinearLayout view, final String text )
	{
		LayoutInflater inflater = ( LayoutInflater ) this
				.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View layout = inflater.inflate( R.layout.popup_window,
				( ViewGroup ) findViewById( R.id.popup_element ) );
		pwindo = new PopupWindow( layout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true );
		pwindo.showAsDropDown( view );
		
		Button btn_cancle = ( Button ) layout.findViewById( R.id.cancle );
		btn_cancle.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				pwindo.dismiss( );
			}
		} );
		
		Button copy = ( Button ) layout.findViewById( R.id.copy );
		copy.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				manager.copyToClipboard( ContactsActivity.this, text );
				pwindo.dismiss( );
			}
		} );
		
	}
	
}
