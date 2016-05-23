package az.nar.mobileapp.nar;

import java.util.List;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import az.nar.mobileapp.nar.utils.CustomBaseAdapter;
import az.nar.mobileapp.nar.utils.SimpleElement;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity
	implements NavigationDrawerFragment.NavigationDrawerCallbacks, OnClickListener {

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
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
		.findFragmentById(R.id.navigation_drawer);
	mTitle = getTitle();

	// Set up the drawer.
	mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

	View v = findViewById(R.id.vLogo);
	v.setOnClickListener(this);
    }

    @Override
    public void onNavigationDrawerItemSelected(long sectionId) {
	// update the main content by replacing fragments
	FragmentManager fragmentManager = getSupportFragmentManager();
	fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(sectionId)).commit();
    }

    public void onSectionAttached(long id) {
	switch ((int) id) {
	case 1:
	    mTitle = getString(R.string.title_section1);
	    break;
	case 2:
	    mTitle = getString(R.string.title_section2);
	    break;
	case 3:
	    mTitle = getString(R.string.title_section3);
	    break;
	}
    }

    public void restoreActionBar() {
	ActionBar actionBar = getSupportActionBar();
	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	actionBar.setDisplayShowTitleEnabled(true);
	actionBar.setTitle(mTitle);
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
    public boolean onOptionsItemSelected(MenuItem item) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();
	if (id == R.id.action_exit) {
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_ID = "section_number";
	
	private ViewGroup mRootView;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static PlaceholderFragment newInstance(long sectionId) {
	    PlaceholderFragment fragment = new PlaceholderFragment();
	    Bundle args = new Bundle();
	    args.putLong(ARG_SECTION_ID, sectionId);
	    fragment.setArguments(args);
	    return fragment;
	}

	public PlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
	    return mRootView;
	}

	@Override
	public void onAttach(Activity activity) {
	    super.onAttach(activity);
	    long sectionID = getArguments().getLong(ARG_SECTION_ID);
	    
	    if (sectionID < 10) {
		
	    }
	    
	    ((MainActivity) activity).onSectionAttached(sectionID);
	}
    }

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	case R.id.vLogo:
	    mNavigationDrawerFragment.toggleDrawer();
	    break;

	default:
	    break;
	}
    }
    
    private class GroupViewAdapter extends CustomBaseAdapter {

	public GroupViewAdapter(LayoutInflater inflater, List<? extends SimpleElement> list) {
	    super(inflater, list);
	}

	public GroupViewAdapter(LayoutInflater inflater, List<? extends SimpleElement> list, boolean isColored) {
	    super(inflater, list, isColored);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    
	    if (convertView == null) {
//		TODO prepare menu item main view
	    }
	    return null;
	}
	
    }
}
