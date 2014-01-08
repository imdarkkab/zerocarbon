package com.nichesoft.zerocarbon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ExampleTab extends Activity {
	/** Called when the activity is first created. */
	

	protected WebView exampleWebView ;
	
	public void init(){
		
		setContentView(R.layout.example);
		exampleWebView = (WebView) findViewById(R.id.exampleWebView);
//		exampleWebView.getSettings().setJavaScriptEnabled(true);
//		exampleWebView.loadUrl("file:///android_asset/example.html");
		WebSettings webSettings = exampleWebView.getSettings();
		
		webSettings.setJavaScriptEnabled(true);
		
	    
		exampleWebView.requestFocusFromTouch();

		   
	    // Set the client
		exampleWebView.setWebViewClient(new WebViewClient());
	    
		
		exampleWebView.setPadding(0, 0, 0, 0);
		exampleWebView.setVerticalScrollBarEnabled(false);
		exampleWebView.setHorizontalScrollBarEnabled(false);
//		WebSettings settings = aboutWebView.getSettings();
//		settings.setUseWideViewPort(true);
//		settings.setLoadWithOverviewMode(true);
		exampleWebView.setInitialScale(100);
		exampleWebView.getSettings().setLoadWithOverviewMode(true);
		exampleWebView.getSettings().setUseWideViewPort(true);
		
		/*aboutWebView.getSettings().setBuiltInZoomControls(true);
		aboutWebView.getSettings().setSupportZoom(true);*/
		exampleWebView.getSettings().setUseWideViewPort(false);
		
		exampleWebView.loadUrl(getResources().getString(R.string.url_sample)) ;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		init();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if( exampleWebView.canGoBack() ){
                	exampleWebView.goBack();
                
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
}