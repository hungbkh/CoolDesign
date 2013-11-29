package com.hdviet.cooldesign;

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
	
	Animation[]						animation					= new Animation[ 2 ];
	
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
	
	private void next( )
	{
		nextanimation( );
		this.mViewFlipper.showNext( );
	}
	
	private void nextanimation( )
	{
		if ( this.mViewFlipper.getDisplayedChild( ) % 2 == 0 )
		{
			this.mViewFlipper.setAnimation( this.animation[ 0 ] );
			this.mViewFlipper.startAnimation( this.animation[ 0 ] );
			return;
		}
		this.mViewFlipper.setAnimation( this.animation[ 1 ] );
		this.mViewFlipper.startAnimation( this.animation[ 1 ] );
	}
	
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
			animation[ k ] = AnimationUtils.loadAnimation( this, animation_id[ k ] );
		}
		
		this.runnable = new Runnable( )
		{
			@Override
			public void run( )
			{
				HomeActivity.this.next( );
				HomeActivity.this.handler.postDelayed( HomeActivity.this.runnable,
						HomeActivity.this.timeDuration );
			}
		};
		this.handler.postDelayed( this.runnable, this.timeDuration );
		
		// mViewFlipper.setAutoStart( true );
		// mViewFlipper.setFlipInterval( 6000 );
		// mViewFlipper.startFlipping( );
		
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
				Intent t = new Intent( HomeActivity.this, ContactsActivity.class );
				startActivity( t );
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
				openBrowser( HomeActivity.this, "www.2cooldesign.co.za" );
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
