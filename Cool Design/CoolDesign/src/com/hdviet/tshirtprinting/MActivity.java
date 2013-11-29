package com.hdviet.tshirtprinting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

public class MActivity extends Activity
{
	AlertDialog.Builder			builder;
	
	AlertDialog					alert	= null;
	private static final String	HTTPS	= "https://";
	private static final String	HTTP	= "http://";
	
	public static void openBrowser( final Context context, String url )
	{
		
		if ( !url.startsWith( HTTP ) && !url.startsWith( HTTPS ) )
		{
			url = HTTP + url;
		}
		
		Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
		context.startActivity( Intent.createChooser( intent, "Choose browser" ) );
	}
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		this.requestWindowFeature( Window.FEATURE_NO_TITLE );
		builder = new AlertDialog.Builder(
				this );
	}
	
	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		MenuInflater menuInflater = getMenuInflater( );
		menuInflater.inflate( R.menu.main, menu );
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item )
	{
		switch ( item.getItemId( ) )
		{
			case R.id.share:
				Intent sharingIntent = new Intent( Intent.ACTION_SEND );
				sharingIntent.setType( "text/plain" );
				String playStoreLink = "https://play.google.com/store/apps/details?id=" +
						getPackageName( );
				String yourShareText = "Install this app " + playStoreLink;
				sharingIntent
						.putExtra(
								android.content.Intent.EXTRA_TEXT,
								yourShareText );
				
				startActivity( Intent.createChooser( sharingIntent, "Share using" ) );
				return true;
			case R.id.design:
				builder.setMessage( "designed by: appbusiness.co.za\ndesigned by: tshirtprinting.co.za" );
				builder.setTitle( "About" );
				builder.setPositiveButton( "Ok", new DialogInterface.OnClickListener( )
				{
					
					@Override
					public void onClick( DialogInterface dialog, int which )
					{
						if ( alert != null )
							alert.dismiss( );
					}
				} );
				alert = builder.create( );
				alert.show( );
				return true;
			case R.id.getapp:
				openBrowser( this, "http://www.appbusiness.co.za" );
				return true;
				
			default:
				return super.onOptionsItemSelected( item );
				
		}
		
	}
}
