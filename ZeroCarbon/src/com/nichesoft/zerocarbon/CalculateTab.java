package com.nichesoft.zerocarbon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;

@SuppressLint("SetJavaScriptEnabled")
public class CalculateTab extends Activity {
	/** Called when the activity is first created. */
	
	protected WebView calculateWebView ;
  

	public void init(Bundle savedInstanceState){
		
		
		
		setContentView(R.layout.calculate);
		calculateWebView = (WebView) findViewById(R.id.calculateWebView);

		
//		calculateWebView.setWebChromeClient(new WebChromeClient());
		
		
		WebSettings webSettings = calculateWebView.getSettings();
		
		 // Enable Javascript for interaction
	    webSettings.setJavaScriptEnabled(true);
	    

	    // Make the zoom controls visible
	    //webSettings.setBuiltInZoomControls(true);

	    // Allow for touching selecting/deselecting data series
	    calculateWebView.requestFocusFromTouch();

	   
	    // Set the client
	    calculateWebView.setWebViewClient(new WebViewClient());
	    calculateWebView.setWebChromeClient(new CallcaulateWebViewClient());  
	    
	    
		// Display 
		calculateWebView.setPadding(0, 0, 0, 0);
		calculateWebView.setVerticalScrollBarEnabled(false);
		calculateWebView.setHorizontalScrollBarEnabled(false);

		
		calculateWebView.getSettings().setLoadWithOverviewMode(true);
		calculateWebView.getSettings().setUseWideViewPort(false);
	    
		
		// Network 
		//webSettings.setAppCacheMaxSize( 5 * 1024 * 1024 ); // 5MB
//		webSettings.setAppCachePath( getApplicationContext().getCacheDir().getAbsolutePath() );
//		webSettings.setAllowFileAccess( true );
//		webSettings.setAppCacheEnabled( true );


//		webSettings.setCacheMode( WebSettings.LOAD_DEFAULT ); // load online by default
//
//		if ( !isNetworkAvailable() ) { // loading offline
//			webSettings.setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
//		}
		
		 if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
			 webSettings.setAllowUniversalAccessFromFileURLs(true);
			 webSettings.setAllowFileAccessFromFileURLs(true);
		 }
		 
	
		// Load
//		calculateWebView.addJavascriptInterface(this, "toaster");
//		calculateWebView.loadUrl("file:///android_asset/calculate.html");

	 
		    
		if (savedInstanceState != null) {
			calculateWebView.restoreState(savedInstanceState);
        } else {    
        	calculateWebView.loadUrl(getResources().getString(R.string.url_calculate));
        }
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init(savedInstanceState);

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		init(null);
		
	}
	 @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        calculateWebView.saveState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        calculateWebView.stopLoading();
    }
    
	
//	private boolean isNetworkAvailable() {
//	    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService( CONNECTIVITY_SERVICE );
//	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//	    return activeNetworkInfo != null;
//	}


	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if( calculateWebView.canGoBack() ){
                	calculateWebView.goBack();
                
                }else{
                    //finish();
                	
                	new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.exit_title)
                    .setMessage(R.string.exit_message)
                    .setPositiveButton(R.string.exit_yes, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //Stop the activity
                            finish();    
                        }

                    })
                    .setNegativeButton(R.string.exit_no, null)
                    .show();

                    return true;
                    
                }
            	
                return true ;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
	
	private class CallcaulateWebViewClient extends WebChromeClient {
		
		@Override
		public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
			
			final AlertDialog.Builder alert = new AlertDialog.Builder(CalculateTab.this);    
			try{
				
				String title = message.split(":")[0].trim() ;
				//String unit =  message.split(":")[1].trim() ;
				
				/** disble image on title **/
				 // get input stream
			    /*InputStream ims = getAssets().open(unit);
			    // load image as Drawable
			    Drawable d = Drawable.createFromStream(ims, null);
				
				final ImageView image = new ImageView(CalculateTab.this) ;
				image.setImageDrawable(d);
				
				LinearLayout linearImage = new LinearLayout(CalculateTab.this);
//				linearImage.setScaleY(20);
				linearImage.setOrientation(0); //0 is for Horizontal orientation
				linearImage.setHorizontalGravity(Gravity.LEFT); 
				linearImage.addView(image);
				*/
				
				final EditText input = new EditText(CalculateTab.this);
				input.setText(defaultValue);
				input.setInputType(InputType.TYPE_CLASS_NUMBER);
			    
				
				
				
				LinearLayout lila1= new LinearLayout(CalculateTab.this);
				lila1.setOrientation(1); //1 is for vertical orientation
				lila1.setHorizontalGravity(Gravity.LEFT); 
//				lila1.addView(linearImage);
			    lila1.addView(input);
			    alert.setView(lila1);
					    
//			    alert.setIcon(R.drawable.icon2);
		        alert.setTitle(title);
			
		        alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {             
		        	public void onClick(DialogInterface dialog, int whichButton) {              
		               
		            	Log.d(MainActivity.ZC,"JSResult:"+input.getText().toString())  ;
		            	int inputValue = 0 ;
		            	try{
		    			   inputValue = Integer.parseInt(input.getText().toString()) ;
		            	}catch(Exception e){}
		            	if(inputValue <= 0 ){
		            		result.confirm();
		            	}else{
		            		result.confirm(""+inputValue);
		            	}
		             }                     
		        });     
		        
		        alert.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {                           
		            public void onClick(DialogInterface dialog, int whichButton) {          
		                dialog.cancel();    
		            }     
		        });         
		        
		        alert.create() ;
		        alert.show() ;
		        
			}catch(Exception e){
				Log.d(MainActivity.ZC,"Exception Error:"+e.getMessage()) ;
				e.printStackTrace();
			}
		
	        return true ; 
	    } 

	}
	

}