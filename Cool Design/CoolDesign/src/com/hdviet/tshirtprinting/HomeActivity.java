package com.hdviet.tshirtprinting;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class HomeActivity extends MActivity
{
	class SwipeGestureDetector extends SimpleOnGestureListener
	{
		@Override
		public boolean onFling( MotionEvent e1, MotionEvent e2, float velocityX, float velocityY )
		{
			try
			{
				// right to left swipe
				if ( e1.getX( ) - e2.getX( ) > SWIPE_MIN_DISTANCE && Math.abs( velocityX ) > SWIPE_THRESHOLD_VELOCITY )
				{
					mViewFlipper.setInAnimation( AnimationUtils.loadAnimation( mContext, R.anim.left_in ) );
					mViewFlipper.setOutAnimation( AnimationUtils.loadAnimation( mContext, R.anim.left_out ) );
					// controlling animation
					mViewFlipper.getInAnimation( ).setAnimationListener( mAnimationListener );
					mViewFlipper.showNext( );
					return true;
				}
				else if ( e2.getX( ) - e1.getX( ) > SWIPE_MIN_DISTANCE
						&& Math.abs( velocityX ) > SWIPE_THRESHOLD_VELOCITY )
				{
					mViewFlipper.setInAnimation( AnimationUtils.loadAnimation( mContext, R.anim.right_in ) );
					mViewFlipper.setOutAnimation( AnimationUtils.loadAnimation( mContext, R.anim.right_out ) );
					// controlling animation
					mViewFlipper.getInAnimation( ).setAnimationListener( mAnimationListener );
					mViewFlipper.showPrevious( );
					return true;
				}
				
			}
			catch ( Exception e )
			{
				e.printStackTrace( );
			}
			
			return false;
		}
	}
	
	private static final int		SWIPE_MIN_DISTANCE			= 120;
	private static final int		SWIPE_THRESHOLD_VELOCITY	= 200;
	
	protected static final long		timeDuration				= 5000;
	
	Animation[]						m_animation					= new Animation[ 2 ];
	
	int[]							animation_id				=
																{ R.anim.fade_in, R.anim.fade_out };
	
	private ViewFlipper				mViewFlipper;
	private final GestureDetector	detector					= new GestureDetector( new SwipeGestureDetector( ) );
	private Context					mContext;
	
	private AnimationListener		mAnimationListener;
	
	int								gallery_grid_Images[]		=
																{ R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3,
																R.drawable.pic_4
																};
	Handler							handler						= new Handler( );
	private Runnable				runnable;
	
	private int						k							= 0;
	
	Timer							timer;
	
	public void callAsynchronousTask( long time )
	{
		final Handler handler = new Handler( );
		timer = new Timer( );
		TimerTask doAsynchronousTask = new TimerTask( )
		{
			@Override
			public void run( )
			{
				handler.post( new Runnable( )
				{
					@Override
					public void run( )
					{
						try
						{
							// HomeActivity.this.next( );
						}
						catch ( Exception e )
						{
						}
					}
				} );
			}
		};
		timer.schedule( doAsynchronousTask, 0, time ); // execute in every time ms
	}
	
	// private void fadeIn( )
	// {
	// this.mViewFlipper.setAnimation( this.animation[ 0 ] );
	// this.mViewFlipper.startAnimation( this.animation[ 0 ] );
	// }
	//
	// private void fadeOut( )
	// {
	// this.mViewFlipper.setAnimation( this.animation[ 1 ] );
	// this.mViewFlipper.startAnimation( this.animation[ 1 ] );
	// }
	
	// private void next( )
	// {
	// // nextanimation( );
	// if ( k == 0 )
	// {
	// fadeIn( );
	// k = 1;
	// return;
	// }
	// if ( k == 1 )
	// {
	// fadeOut( );
	// k = 2;
	// return;
	// }
	// if ( k == 2 )
	// {
	// fadeIn( );
	// this.mViewFlipper.showNext( ); // 2
	// k = 3;
	// return;
	// }
	// if ( k == 3 )
	// {
	// fadeOut( );
	// k = 4;
	// return;
	// }
	// if ( k == 4 )
	// {
	// fadeIn( );
	// this.mViewFlipper.showNext( ); // 3
	// k = 5;
	// return;
	// }
	// if ( k == 5 )
	// {
	// fadeOut( );
	// k = 6;
	// return;
	// }
	//
	// if ( k == 6 )
	// {
	// fadeIn( );
	// this.mViewFlipper.showNext( ); // 4
	// k = 7;
	// return;
	// }
	// if ( k == 7 )
	// {
	// fadeOut( );
	// k = 8;
	// return;
	// }
	//
	// if ( k == 8 )
	// {
	// fadeIn( );
	// this.mViewFlipper.showNext( );
	// k = 1;
	// return;
	// }
	// Log.d( "k", "k = " + k );
	//
	// }
	//
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.home_activity );
		
		mContext = this;
		mViewFlipper = ( ViewFlipper ) this.findViewById( R.id.flipper );
		
		for ( int i = 0; i < gallery_grid_Images.length; i++ )
		{
			// This will create dynamic image view and add them to ViewFlipper
			setFlipperImage( gallery_grid_Images[ i ], i );
		}
		
		for ( int k = 0; k < 2; k++ )
		{
			m_animation[ k ] = AnimationUtils.loadAnimation( this, animation_id[ k ] );
		}
		
		m_animation[ 0 ].setAnimationListener( new AnimationListener( )
		{
			
			@Override
			public void onAnimationEnd( Animation animation )
			{
				HomeActivity.this.m_animation[ 1 ].setAnimationListener( new AnimationListener( )
				{
					
					@Override
					public void onAnimationEnd( Animation animation )
					{
						mViewFlipper.showNext( );
						mViewFlipper.startAnimation( m_animation[ 0 ] );
					}
					
					@Override
					public void onAnimationRepeat( Animation animation )
					{
						
					}
					
					@Override
					public void onAnimationStart( Animation animation )
					{
						
					}
				} );
				mViewFlipper.startAnimation( m_animation[ 1 ] );
			}
			
			@Override
			public void onAnimationRepeat( Animation animation )
			{
				
			}
			
			@Override
			public void onAnimationStart( Animation animation )
			{
				
			}
		} );
		mViewFlipper.startAnimation( m_animation[ 0 ] );
		
		// callAsynchronousTask( HomeActivity.timeDuration );
		
		// animation listener
		mAnimationListener = new Animation.AnimationListener( )
		{
			@Override
			public void onAnimationEnd( Animation animation )
			{
				// TODO animation stopped event
			}
			
			@Override
			public void onAnimationRepeat( Animation animation )
			{
			}
			
			@Override
			public void onAnimationStart( Animation animation )
			{
				// animation started event
			}
		};
		
		Button contact = ( Button ) findViewById( R.id.contact );
		contact.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				// Intent t = new Intent( HomeActivity.this, ContactsActivity.class );
				// startActivity( t );
				Intent email = new Intent( Intent.ACTION_SEND );
				email.putExtra( Intent.EXTRA_EMAIL, new String[ ]
				{ "info@tshirtprinting.co.za" }
						);
				email.putExtra( Intent.EXTRA_SUBJECT, "subject" );
				email.putExtra( Intent.EXTRA_TEXT, "message" );
				email.setType( "message/rfc822" );
				startActivity( Intent.createChooser( email, "Choose an Email client :" ) );
			}
		} );
		
		Button service = ( Button ) findViewById( R.id.service );
		service.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				Intent t = new Intent( HomeActivity.this, ServicesActivity.class );
				startActivity( t );
			}
		} );
		
		Button portfolio = ( Button ) findViewById( R.id.portfolio );
		portfolio.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				Intent t = new Intent( HomeActivity.this, ListImageActivity.class );
				startActivity( t );
			}
		} );
		
		Button website = ( Button ) findViewById( R.id.website );
		website.setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				openBrowser( HomeActivity.this, "http://www.tshirtprinting.co.za" );
			}
		} );
	}
	
	private void setFlipperImage( int res, int k )
	{
		Log.i( "Set Filpper Called", res + "" );
		ImageView image = new ImageView( getApplicationContext( ) );
		image.setBackgroundResource( res );
		mViewFlipper.addView( image );
		
	}
}
