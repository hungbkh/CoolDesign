package com.hdviet.cooldesign;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity
{
	// An AsyncTask class for accessing the GeoCoding Web Service
	private class GeocoderTask extends AsyncTask< String, Void, List< Address >>
	{
		
		@Override
		protected List< Address > doInBackground( String... locationName )
		{
			// Creating an instance of Geocoder class
			Geocoder geocoder = new Geocoder( getBaseContext( ) );
			List< Address > addresses = null;
			
			try
			{
				// Getting a maximum of 3 Address that matches the input text
				addresses = geocoder.getFromLocationName( locationName[ 0 ], 3 );
			}
			catch ( IOException e )
			{
				e.printStackTrace( );
			}
			return addresses;
		}
		
		@Override
		protected void onPostExecute( List< Address > addresses )
		{
			
			if ( addresses == null || addresses.size( ) == 0 )
			{
				Toast.makeText( getBaseContext( ), "No Location found", Toast.LENGTH_SHORT ).show( );
			}
			
			// Clears all the existing markers on the map
			googleMap.clear( );
			
			// Adding Markers on Google Map for each matching address
			if ( addresses != null )
			{
				for ( int i = 0; i < addresses.size( ); i++ )
				{
					
					Address address = addresses.get( i );
					
					// Creating an instance of GeoPoint, to display in Google Map
					latLng = new LatLng( address.getLatitude( ), address.getLongitude( ) );
					
					String addressText = String.format( "%s, %s",
							address.getMaxAddressLineIndex( ) > 0 ? address.getAddressLine( 0 ) : "",
							address.getCountryName( ) );
					
					markerOptions = new MarkerOptions( );
					markerOptions.position( latLng );
					markerOptions.title( addressText );
					
					googleMap.addMarker( markerOptions );
					
					// Locate the first location
					if ( i == 0 )
						googleMap.animateCamera( CameraUpdateFactory.newLatLngZoom( latLng, 15 ) );
				}
			}
		}
	}
	
	private GoogleMap	googleMap;
	MarkerOptions		markerOptions;
	LatLng				latLng;
	
	@Override
	protected void onCreate( Bundle arg0 )
	{
		// TODO Auto-generated method stub
		super.onCreate( arg0 );
		this.requestWindowFeature( Window.FEATURE_NO_TITLE );
		setContentView( R.layout.activity_map );
		googleMap = ( ( SupportMapFragment ) getSupportFragmentManager( ).findFragmentById( R.id.map ) ).getMap( );
		googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom( new LatLng( 39.809734, -98.555620 ), 12 ) );
		
		googleMap.setMyLocationEnabled( true );
		// Getting reference to btn_find of the layout activity_main
		Button btn_find = ( Button ) findViewById( R.id.btn_find );
		
		if ( ConnectionDetector.isConnectingToInternet( MapActivity.this ) )
		{
			new GeocoderTask( ).execute( "15 Julian St, South Africa" );
		}
		else
		{
			MyOkDialog.showDialog( getString( R.string.a_connect_internet ), MapActivity.this );
		}
		
		// Defining button click event listener for the find button
		OnClickListener findClickListener = new OnClickListener( )
		{
			@Override
			public void onClick( View v )
			{
				if ( !ConnectionDetector.isConnectingToInternet( MapActivity.this ) )
				{
					MyOkDialog.showDialog( getString( R.string.a_connect_internet ), MapActivity.this );
					return;
				}
				
				// Getting reference to EditText to get the user input location
				EditText etLocation = ( EditText ) findViewById( R.id.et_location );
				
				// Getting user input location
				String location = etLocation.getText( ).toString( );
				
				if ( location != null && !location.equals( "" ) )
				{
					new GeocoderTask( ).execute( location );
				}
			}
		};
		
		// Setting button click event listener for the find button
		btn_find.setOnClickListener( findClickListener );
	}
	
}
