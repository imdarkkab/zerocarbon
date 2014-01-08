package com.nichesoft.zerocarbon;

import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;


@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity implements OnTabChangeListener {
	/** Called when the activity is first created. */
	
	public static final String ZC = "ZC" ; 
	
	private TabHost tabHost ;
	//public static String CURRENT_TAB ;
	private TabSpec aboutTab ;
	private TabSpec exampleTab   ;
	private TabSpec calculatorTab  ;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);
		
//		Display display = getWindowManager().getDefaultDisplay();
//        int width = display.getWidth();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		

		/** TabHost will have Tabs */
		tabHost = (TabHost) findViewById(android.R.id.tabhost);

		/**
		 * TabSpec used to create a new tab. By using TabSpec only we can able
		 * to setContent to the tab. By using TabSpec setIndicator() we can set
		 * name to tab.
		 */

		/** tid1 is firstTabSpec Id. Its used to access outside. */
		aboutTab = tabHost.newTabSpec("about");
		exampleTab = tabHost.newTabSpec("example");
		calculatorTab = tabHost.newTabSpec("calculator");

		/** TabSpec setIndicator() is used to set name for the tab. */
		/** TabSpec setContent() is used to set content for a particular tab. */
		String tabAboutName = getResources().getString(R.string.tab_about) ;
		String tabExampleName = getResources().getString(R.string.tab_example) ;
		String tabCalculateName = getResources().getString(R.string.tab_calculate) ;
		
		
		tabAboutName = "" ;
		tabExampleName = "" ;
		tabCalculateName = "" ;
		
		aboutTab.setIndicator(tabAboutName,getResources().getDrawable(R.drawable.menu_about));
		aboutTab.setContent(new Intent(this, AboutTab.class));

		exampleTab.setIndicator(tabExampleName,getResources().getDrawable(R.drawable.menu_sample));
		exampleTab.setContent(new Intent(this, ExampleTab.class));
		
		calculatorTab.setIndicator(tabCalculateName,getResources().getDrawable(R.drawable.menu_cal));
		calculatorTab.setContent(new Intent(this, CalculateTab.class));

		/** Add tabSpec to the TabHost to display. */
		tabHost.addTab(aboutTab);
		tabHost.addTab(exampleTab);
		tabHost.addTab(calculatorTab);

	     
		setTabProperty(tabHost);
		//this.CURRENT_TAB = getResources().getString(R.string.tab_about)  ;
		tabHost.setOnTabChangedListener(this) ;
	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		Log.d(ZC, "tabId="+tabId) ;
		 //this.CURRENT_TAB = tabId ;
		setTabProperty(tabHost);
		
		
	}
	
	 public void setTabProperty(TabHost aTabHost) { 
		int heightValue = 70;
		 
		TabWidget tabWidget = aTabHost.getTabWidget() ;
		int tabCount =  tabWidget.getChildCount() ;
			
		aTabHost.setBackgroundColor(Color.parseColor("#FFFFFF"));
		
		 for(int i=0 ; i<  tabCount ; i++){ 
			 
			 View currentView = tabWidget.getChildAt(i);

			 
			 LinearLayout.LayoutParams currentLayout =(LinearLayout.LayoutParams) currentView.getLayoutParams();
			 if(i==0){
				currentLayout.setMargins(0, 0, 0, 2);
			 }else{
				currentLayout.setMargins(2, 0, 0, 2);
			 }
			 
			 aTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#95B839")); //unselected 
			 aTabHost.getTabWidget().getChildAt(i).getLayoutParams().height = (int) (heightValue * this.getResources().getDisplayMetrics().density);

			 
		 } 
		 //aTabHost.getCurrentTabView().setBackgroundColor(Color.parseColor("#81DAF5"));

		 
	}

}
