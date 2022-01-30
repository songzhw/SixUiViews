package ca.six.demo.sixuiviews;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import ca.six.views.views.tabsview.OnTabItemClickListener;
import ca.six.views.views.tabsview.TabsView;

public class MyTabsViewActivity extends Activity implements OnTabItemClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabs_view_demo);
		
		TabsView tabs = (TabsView) findViewById(R.id.tabsview_demo);
		tabs.setOnTabItemClickListener(this);
	}

	@Override
	public void onTabItemClick(int index) {
		Toast.makeText(this, "-- "+index, Toast.LENGTH_SHORT).show();
	}
}
