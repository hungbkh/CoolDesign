package com.hdviet.tshirtprinting;
// package com.hdviet.cooldesign;
//
// import java.io.IOException;
// import java.util.ArrayList;
//
// import android.app.Activity;
// import android.content.Context;
// import android.content.Intent;
// import android.content.res.AssetManager;
// import android.graphics.Bitmap;
// import android.graphics.BitmapFactory;
// import android.net.Uri;
// import android.os.Bundle;
// import android.os.Handler;
// import android.view.Menu;
// import android.view.MotionEvent;
// import android.view.View;
// import android.view.animation.Animation;
// import android.view.animation.AnimationUtils;
// import android.widget.Button;
// import android.widget.ImageView;
// import android.widget.LinearLayout;
// import android.widget.ViewFlipper;
//
// public class MainActivity
// extends Activity
// implements View.OnClickListener
// {
// static int i = 1;
// Animation[] animation;
// int[] animation_id =
// { 2130968578, 2130968579 };
// AssetManager asset;
// Bitmap[] bmp;
// Button btnContact;
// Button btnGoLeft;
// Button btnGoRight;
// Button btnPortfolio;
// Button btnService;
// Button btnWebsite;
// Context context;
// private float fromPosition;
// Handler handler = new Handler( );
// String[] image;
// ArrayList< String > imglist = new ArrayList( );
// String[] list;
// private Context mContext;
// private int mDstHeight;
// private int mDstWidth;
// ViewFlipper mViewFlipper;
// Runnable runnable;
// int timeDuration = 5000;
//
// private void next( )
// {
// nextanimation( );
// this.mViewFlipper.showNext( );
// }
//
// private void nextanimation( )
// {
// if ( this.mViewFlipper.getDisplayedChild( ) % 2 == 0 )
// {
// this.mViewFlipper.setAnimation( this.animation[ 0 ] );
// this.mViewFlipper.startAnimation( this.animation[ 0 ] );
// return;
// }
// this.mViewFlipper.setAnimation( this.animation[ 1 ] );
// this.mViewFlipper.startAnimation( this.animation[ 1 ] );
// }
//
// @Override
// public void onClick( View paramView )
// {
// switch ( paramView.getId( ) )
// {
// case 2131296257:
// case 2131296258:
// default:
// return;
// case 2131296259:
// System.out.println( i + "........" );
// }
// try
// {
// this.list = getAssets( ).list( "" );
// label89: i = 1 + i;
// return;
// startActivity( new Intent( this.context, ServiceActivity.class ) );
// finish( );
// return;
// Intent localIntent = new Intent( "android.intent.action.VIEW" );
// localIntent.setData( Uri.parse( "http://www.2cooldesign.co.za/" ) );
// startActivity( localIntent );
// return;
// startActivity( new Intent( this.context, Contactus.class ) );
// finish( );
// return;
// startActivity( new Intent( this.context, ImageListActivity.class ) );
// finish( );
// return;
// }
// catch ( IOException localIOException )
// {
// break label89;
// }
// }
//
// @Override
// protected void onCreate( Bundle paramBundle )
// {
// super.onCreate( paramBundle );
// setContentView( 2130903040 );
// this.context = this;
// this.btnGoLeft = ( ( Button ) findViewById( 2131296257 ) );
// this.btnGoRight = ( ( Button ) findViewById( 2131296259 ) );
// this.btnService = ( ( Button ) findViewById( 2131296260 ) );
// this.btnPortfolio = ( ( Button ) findViewById( 2131296261 ) );
// this.btnWebsite = ( ( Button ) findViewById( 2131296262 ) );
// this.btnContact = ( ( Button ) findViewById( 2131296263 ) );
// this.btnGoLeft.setOnClickListener( this );
// this.btnGoRight.setOnClickListener( this );
// this.btnService.setOnClickListener( this );
// this.btnPortfolio.setOnClickListener( this );
// this.btnWebsite.setOnClickListener( this );
// this.btnContact.setOnClickListener( this );
// this.mContext = this;
// this.mViewFlipper = ( ( ViewFlipper ) findViewById( 2131296258 ) );
// this.mDstWidth = getResources( ).getDimensionPixelSize( 2131034114 );
// this.mDstHeight = getResources( ).getDimensionPixelSize( 2131034115 );
// this.asset = this.mContext.getAssets( );
// try
// {
// this.image = this.asset.list( "homeslider" );
// this.bmp = new Bitmap[ this.image.length ];
// j = 0;
// if ( j >= this.bmp.length )
// {
// this.animation = new Animation[ 2 ];
// k = 0;
// if ( k < this.animation.length )
// {
// break label382;
// }
// this.runnable = new Runnable( )
// {
// @Override
// public void run( )
// {
// MainActivity.this.next( );
// MainActivity.this.handler.postDelayed( MainActivity.this.runnable,
// MainActivity.this.timeDuration );
// }
// };
// this.handler.postDelayed( this.runnable, this.timeDuration );
// this.mViewFlipper.setOnTouchListener( new View.OnTouchListener( )
// {
// @Override
// public boolean onTouch( View paramAnonymousView, MotionEvent paramAnonymousMotionEvent )
// {
// MainActivity.this.handler.removeCallbacks( MainActivity.this.runnable );
// switch ( paramAnonymousMotionEvent.getAction( ) )
// {
// }
// for ( ;; )
// {
// MainActivity.this.handler.postDelayed( MainActivity.this.runnable,
// MainActivity.this.timeDuration );
// return true;
// MainActivity.this.fromPosition = paramAnonymousMotionEvent.getX( );
// continue;
// float f = paramAnonymousMotionEvent.getX( );
// if ( MainActivity.this.fromPosition > f + 20.0F )
// {
// MainActivity.this.next( );
// }
// else if ( MainActivity.this.fromPosition < f - 20.0F )
// {
// MainActivity.this.previous( );
// }
// }
// }
// } );
// }
// }
// catch ( IOException localIOException1 )
// {
// for ( ;; )
// {
// int j;
// int k;
// localIOException1.printStackTrace( );
// continue;
// try
// {
// this.bmp[ j ] = BitmapFactory.decodeStream( this.asset.open( "homeslider/" + this.image[ j ] ) );
// setFlipperImage( this.bmp[ j ] );
// j++;
// }
// catch ( IOException localIOException2 )
// {
// for ( ;; )
// {
// localIOException2.printStackTrace( );
// }
// }
// label382: this.animation[ k ] = AnimationUtils.loadAnimation( this.mContext, this.animation_id[ k ] );
// k++;
// }
// }
// }
//
// @Override
// public boolean onCreateOptionsMenu( Menu paramMenu )
// {
// getMenuInflater( ).inflate( 2131230720, paramMenu );
// return true;
// }
//
// @Override
// protected void onDestroy( )
// {
// super.onDestroy( );
// System.gc( );
// Runtime.getRuntime( ).gc( );
// }
//
// private void openurl( )
// {
// startActivity( new Intent( "android.intent.action.VIEW", Uri.parse( "http://www.google.com" ) ) );
// }
//
// private void previous( )
// {
// nextanimation( );
// this.mViewFlipper.showPrevious( );
// }
//
// private void setFlipperImage( Bitmap paramBitmap )
// {
// LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams( -1, -1 );
// Bitmap localBitmap = ScalingUtilities.createScaledBitmap( paramBitmap, this.mDstWidth, this.mDstHeight,
// ScalingUtilities.ScalingLogic.FIT );
// paramBitmap.recycle( );
// ImageView localImageView = new ImageView( this );
// localImageView.setScaleType( ImageView.ScaleType.FIT_XY );
// localImageView.setImageBitmap( localBitmap );
// localImageView.setLayoutParams( localLayoutParams );
// this.mViewFlipper.addView( localImageView );
// }
// }
