package com.hdviet.tshirtprinting;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import android.content.Intent;
import android.os.Bundle;

public class ImageViewTouchActivity extends MActivity
{
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.image_touch );
		Intent t = getIntent( );
		int id = t.getIntExtra( "image", R.drawable.ic_launcher );
		
		ImageViewTouch im_image_2 = ( ImageViewTouch ) findViewById( R.id.im_image_2 );
		im_image_2.setImageResource( id );
	}
}
