package az.nar.mobileapp.nar;

import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import az.nar.mobileapp.nar.json.JSON;
import az.nar.mobileapp.nar.utils.CustomBaseAdapter;
import az.nar.mobileapp.nar.utils.SimpleElement;
import az.nar.mobileapp.nar.utils.main.GroupViewAdapter;
import az.nar.mobileapp.nar.view.MenuGroup;
import az.nar.mobileapp.nar.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity
	implements NavigationDrawerFragment.NavigationDrawerCallbacks, OnClickListener {

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
	public static final int SEC_NONE = -1;
	
	public static final int SEC_SERVICE_PASSWORD = 11;
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NO = "section_no";
	
	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static PlaceholderFragment newInstance(int sectionNo) {
	    PlaceholderFragment fragment = new PlaceholderFragment();
	    
	    Bundle args = new Bundle();
	    args.putInt(ARG_SECTION_NO, sectionNo);
	    fragment.setArguments(args);
	    
	    return fragment;
	}

	private int mSectionNo;

	public PlaceholderFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
	    super.onAttach(activity);
	    
	    mSectionNo = getArguments().getInt(ARG_SECTION_NO, SEC_NONE);
	    ((MainActivity) activity).onSectionAttached(mSectionNo);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    switch (mSectionNo) {
	    case 0:
	    case 1:
	    case 2:
		return createMainView(inflater, container);
		
	    case SEC_SERVICE_PASSWORD:
		return createServicePasswordView(inflater, container);
		
	    default:
		return null;
	    }
	}
	
	private View createMainView(LayoutInflater inflater, ViewGroup container) {
	    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
	    
	    List<MenuItem> items = null;
	    try {
		long sectionID = JSON.navigationMenu.getJSONObject(mSectionNo).getLong(JSON.FLD_ID);
		
		items = JSON.getMenuChildren(sectionID);
		ListAdapter adapter = new GroupViewAdapter(inflater, items, (MainActivity) getActivity());
		AdapterView<ListAdapter> mainView = (GridView) inflater.inflate(R.layout.body_menu, rootView, false);
		mainView.setAdapter(adapter);
		
		rootView.addView(mainView);
	    } catch (JSONException e) {
		Toast.makeText(getContext(), "Technical problem", Toast.LENGTH_LONG).show();
	    }
	    
	    return rootView;
	}
	
	private View createServicePasswordView(LayoutInflater inflater, ViewGroup container) {
	    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_service_password, container, false);
	    //TODO initialize view and implement actions
	    return rootView;
	}
    }

    /**
     * Fragment managing the behaviors, interactions and presentation of the
     * navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in
     * {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	case R.id.vLogo:
	    mNavigationDrawerFragment.toggleDrawer();
	    break;
	
	case R.id.btnMenuGroup:
	    int groupPosition = (Integer) v.getTag();
	    onNavigationDrawerItemSelected(groupPosition);
	    break;
	default:
	    break;
	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	if (!mNavigationDrawerFragment.isDrawerOpen()) {
	    // Only show items in the action bar relevant to this screen
	    // if the drawer is not showing. Otherwise, let the drawer
	    // decide what to show in the action bar.
	    getMenuInflater().inflate(R.menu.main, menu);
	    restoreActionBar();
	    return true;
	}
	return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNavigationDrawerItemSelected(int sectionNo) {
	// update the main content by replacing fragments
	FragmentManager fragmentManager = getSupportFragmentManager();
	fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(sectionNo)).commit();
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();
	if (id == R.id.action_exit) {
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

    public void onSectionAttached(int sectionNo) {
	LinearLayout llMenu = (LinearLayout) findViewById(R.id.llMenu);
	for (int i = 0; i < llMenu.getChildCount(); i++) {
	    llMenu.getChildAt(i).setSelected(i == sectionNo);
	}
    }

    public void restoreActionBar() {
	ActionBar actionBar = getSupportActionBar();
	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	actionBar.setDisplayShowTitleEnabled(true);
	actionBar.setTitle(mTitle);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	LinearLayout llMenu = (LinearLayout) findViewById(R.id.llMenu);
	try {
	    List<MenuGroup> groups = JSON.getMenuGroups();
	    for (int i = 0; i < groups.size(); i++) {
		MenuGroup group = groups.get(i);
		
		Button button = (Button) getLayoutInflater().inflate(R.layout.item_header_menu_button, llMenu, false);
		button.setText(group.getName());
		button.setTag(i);
		button.setOnClickListener(this);
		llMenu.addView(button);
	    }
	} catch (JSONException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
		.findFragmentById(R.id.navigation_drawer);
	mTitle = getTitle();

	// Set up the drawer.
	mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

	View v = findViewById(R.id.vLogo);
	v.setOnClickListener(this);
    }
}
