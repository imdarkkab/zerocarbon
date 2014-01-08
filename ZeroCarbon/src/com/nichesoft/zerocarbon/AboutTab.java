package com.nichesoft.zerocarbon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

public class AboutTab extends Activity {
	/** Called when the activity is first created. */
	protected WebView aboutWebView ;

	
	public void init(){
		
		setContentView(R.layout.about);
		aboutWebView = (WebView) findViewById(R.id.aboutWebView);
		aboutWebView.getSettings().setJavaScriptEnabled(true);

//		aboutWebView.loadUrl("file:///android_asset/about.html");
		aboutWebView.setPadding(0, 0, 0, 0);
		aboutWebView.setVerticalScrollBarEnabled(false);
		aboutWebView.setHorizontalScrollBarEnabled(false);
//		WebSettings settings = aboutWebView.getSettings();
//		settings.setUseWideViewPort(true);
//		settings.setLoadWithOverviewMode(true);
		aboutWebView.setInitialScale(100);
		aboutWebView.getSettings().setLoadWithOverviewMode(true);
		aboutWebView.getSettings().setUseWideViewPort(true);
		
		/*aboutWebView.getSettings().setBuiltInZoomControls(true);
		aboutWebView.getSettings().setSupportZoom(true);*/
		aboutWebView.getSettings().setUseWideViewPort(false);
		
		aboutWebView.loadUrl(getResources().getString(R.string.url_about)) ;
		
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
                if( aboutWebView.canGoBack() ){
                	aboutWebView.goBack();
                
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