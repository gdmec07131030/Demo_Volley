package com.example.demo_volley;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.NetworkImageView;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    //TextView tv;
	NetworkImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		tv=(TextView) findViewById(R.id.textView);
//		RequestQueue queue=Volley.newRequestQueue(this);
//		StringRequest request = new StringRequest("http://www.baidu.com", new Listener<String>() {
//
//			@Override
//			public void onResponse(String response) {
//				// TODO Auto-generated method stub
//				tv.setText(response);
//			}
//		}, new ErrorListener(){
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				// TODO Auto-generated method stub
//				Log.i("info", error.getMessage());
//			}});
//		queue.add(request);
		imageView=(NetworkImageView) findViewById(R.id.imageView);
	}

	public void doClick(View v){
		String url="http://img3.imgtn.bdimg.com/it/u=3033376009,3249989634&fm=11&gp=0.jpg";
		RequestQueue requsestQueue =Volley.newRequestQueue(this);
		final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(5);
		ImageCache imageCache = new ImageCache() {
			
			@Override
			public void putBitmap(String url, Bitmap bitmap) {
				// TODO Auto-generated method stub
				lruCache.put(url, bitmap);
			}
			
			@Override
			public Bitmap getBitmap(String url) {
				// TODO Auto-generated method stub
				return lruCache.get(url);
			}
		};
		ImageLoader imageLoader = new ImageLoader(requsestQueue, imageCache);
		imageView.setImageUrl(url, imageLoader);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
