package com.example.actionbar;

import java.util.ArrayList;
import java.util.List;
import com.example.actionbar.adapter.TabPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private ViewPager mViewPager;
	private List<Fragment> mFragmentList;
	private ActionBar mActionBar;
	private TabHost mTabHost;
	private final static String TAB1 = "TAB1";
	private final static String TAB2 = "TAB2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFragmentList = new ArrayList<Fragment>();
		mFragmentList.add(new TestFragment());
		mFragmentList.add(new TestFragment());
		mViewPager = (ViewPager) findViewById(R.id.viewPager);

		mViewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(),
				mFragmentList));
		mViewPager.setOnPageChangeListener(pagerListener);
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setDisplayShowTitleEnabled(false);

		View v = View.inflate(getApplication(), R.layout.tab_layout, null);
		mTabHost = (TabHost) v.findViewById(android.R.id.tabhost);
		
		mTabHost.setup();
		mTabHost.addTab(mTabHost.newTabSpec(TAB1)
				.setIndicator(getTabView("tab1")).setContent(R.id.tab1));
		mTabHost.addTab(mTabHost.newTabSpec(TAB2)
				.setIndicator(getTabView("tab2")).setContent(R.id.tab2));

		mTabHost.setCurrentTab(0);
		mTabHost.setOnTabChangedListener(mTabChangeListener);
		mActionBar.setCustomView(v);
		mActionBar.setDisplayShowCustomEnabled(true);
	}

	private View getTabView(String tabText) {
		View view = View.inflate(this, R.layout.tab_child, null);
		view.setBackgroundResource(R.drawable.tab_indicator_ab_bwsportv3);
		TextView text = (TextView) view.findViewById(R.id.text);
		text.setText(tabText);
		return view;
	}

	private ViewPager.OnPageChangeListener pagerListener = new ViewPager.OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			// mActionBar.setSelectedNavigationItem(arg0);
			mTabHost.setCurrentTab(arg0);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	};

	private TabHost.OnTabChangeListener mTabChangeListener = new TabHost.OnTabChangeListener() {

		@Override
		public void onTabChanged(String tabId) {

			if (TAB1.equals(tabId)) {
				mViewPager.setCurrentItem(0, true);
			} else if (TAB2.equals(tabId)) {
				mViewPager.setCurrentItem(1, true);
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
