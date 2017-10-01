package truongbs.fet.hut.doubledrawerlayouttutorial;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {

	// Khai bao cac bien can thiet
	private DrawerLayout mDrawerLayout;
	private ListView mleftList, mrightList;
	private ActionBarDrawerToggle mDrawerToggle;
	private ArrayList<ItemNavigation> arrLeft, arrRight;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Add du lieu vao array
		initArrayForListViewDrawer();

		//

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// Link den code
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mleftList = (ListView) findViewById(R.id.left_drawer);
		mrightList = (ListView) findViewById(R.id.right_drawer);

		// bat su kien click cua list ben trai
		mleftList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				
				switch (pos) {
				case 0:
					// Chuyen sang activity settings
					Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
					startActivity(i);
					Toast.makeText(MainActivity.this, "Ban da tap chon Settings",
							Toast.LENGTH_LONG).show();
					break;
				default:
					break;
				}
				
					
				Log.d("debug", "click left " + pos);
				mleftList.setItemChecked(pos, true);
				// Dong Drawer khi click xong
				mDrawerLayout.closeDrawer(mleftList);

			}
		});

		// bat su kien click cua list ben phai
		mrightList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				Log.d("debug", "click right " + pos);
				mleftList.setItemChecked(pos, true);
				// Dong Drawer khi click xong
				mDrawerLayout.closeDrawer(mrightList);

			}
		});
		// Set Adapter cho list ben trai
		
		ItemAdapter adapterLeft =  new ItemAdapter(this, arrLeft);
		mleftList.setAdapter(adapterLeft);
		
		// Set Adapter cho list ben phai
		ItemAdapter adapterRight =  new ItemAdapter(this, arrRight);
		mrightList.setAdapter(adapterRight);
		
		
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		mDrawerToggle = new ActionBarDrawerToggle(this, /* Activity chua Actionbar */
		mDrawerLayout, /* DrawerLayout de lien ket den ActionBar */
		R.drawable.ic_drawer, /* Hinh anh Drawer */
		R.string.drawer_open, /* Chuoi mo ta hanh dong mo Drawer "open drawer" */
		R.string.drawer_close /* Chuoi mo ta hanh dong dong Drawer "close drawer" */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle("Close DrawerLayout");
				Log.d("debug", "onDrawerClosed");
				invalidateOptionsMenu(); // tao lai menu sau khi options menu da duoc thay doi
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Open DrawerLayout");
				Log.d("debug", "onDrawerOpened");
				invalidateOptionsMenu();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}

	// Them du lieu vao array
	private void initArrayForListViewDrawer() {
		// TODO Auto-generated method stub
		arrLeft = new ArrayList<ItemNavigation>();
		arrRight = new ArrayList<ItemNavigation>();
		
		ItemNavigation ItemNavigation1 = new ItemNavigation(
				R.drawable.ic_action_settings, "Settings");
		arrLeft.add(ItemNavigation1);
		ItemNavigation ItemNavigation2 = new ItemNavigation(
				R.drawable.ic_action_new, "Add new");
		arrLeft.add(ItemNavigation2);
		ItemNavigation ItemNavigation3 = new ItemNavigation(
				R.drawable.ic_action_favorite, "Favorite");
		arrLeft.add(ItemNavigation3);
		ItemNavigation ItemNavigation4 = new ItemNavigation(
				R.drawable.ic_action_collection, "Foder");
		arrLeft.add(ItemNavigation4);
		ItemNavigation ItemNavigation5 = new ItemNavigation(
				R.drawable.ic_action_about, "About");
		arrLeft.add(ItemNavigation5);

		ItemNavigation ItemNavigation11 = new ItemNavigation(
				R.drawable.ic_action_settings, "Settings");
		arrRight.add(ItemNavigation11);

	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		Log.d("debug", "onPostCreate");
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		Log.d("debug", "onConfigurationChanged");
	}

	/*
	 * Khi goi invalidateOptionsMenu() thi chung ta phai override lai phuong
	 * thuc nay
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		Log.d("debug", "onPrepareOptionsMenu");
		// Neu drawer ben trai dang mo thi an cac item cua actionbar
		boolean drawerleftOpen = mDrawerLayout.isDrawerOpen(mleftList);
		menu.findItem(R.id.action_search).setVisible(!drawerleftOpen);
		menu.findItem(R.id.action_st).setVisible(!drawerleftOpen);
		// Neu drawer ben phai dang mo thi an cac item cua actionbar
		boolean drawerrightOpen = mDrawerLayout.isDrawerOpen(mrightList);
		menu.findItem(R.id.action_search).setVisible(!drawerrightOpen);
		getActionBar().setDisplayHomeAsUpEnabled(!drawerrightOpen);
		getActionBar().setHomeButtonEnabled(!drawerrightOpen);

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_bar, menu);
		return true;
	}

	// Bat su kien click tren actionbar
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		switch (id) {
		case android.R.id.home:
			Log.d("debug", "click home");
			boolean drawerleftOpen = mDrawerLayout.isDrawerOpen(mleftList);
			if (!drawerleftOpen) {
				mDrawerLayout.openDrawer(Gravity.START);
			} else {
				mDrawerLayout.closeDrawer(mleftList);
			}

			break;
		case R.id.action_st:
			Log.d("debug", "click setting");
			boolean drawerrightOpen = mDrawerLayout.isDrawerOpen(mrightList);
			if (!drawerrightOpen) {
				mDrawerLayout.openDrawer(Gravity.END);
			} else {
				mDrawerLayout.closeDrawer(mrightList);
			}
			break;

		case R.id.action_search:
			Log.d("debug", "click Search");
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
