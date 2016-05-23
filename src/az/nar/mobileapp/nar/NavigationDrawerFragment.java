package az.nar.mobileapp.nar;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
import az.nar.mobileapp.nar.json.JSON;
import az.nar.mobileapp.nar.view.MenuGroup;
import az.nar.mobileapp.nar.view.MenuItem;

/**
 * Fragment used for managing interactions for and presentation of a navigation
 * drawer. See the <a href=
 * "https://developer.android.com/design/patterns/navigation-drawer.html#Interaction"
 * > design guidelines</a> for a complete explanation of the behaviors
 * implemented here.
 */
public class NavigationDrawerFragment extends Fragment implements OnGroupExpandListener {

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_SECTION_ID = "selected_navigation_drawer_id";

    /**
     * Per the design guidelines, you should show the drawer on launch until the
     * user manually expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ExpandableListView mDrawerListView;
    private View mFragmentContainerView;

    private long mCurrentSelectedSectionID = 0;
    private int mLastExpandedPosition = -1;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	// Read in the flag indicating whether or not the user has demonstrated
	// awareness of the
	// drawer. See PREF_USER_LEARNED_DRAWER for details.
	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
	mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

	if (savedInstanceState != null) {
	    mCurrentSelectedSectionID = savedInstanceState.getLong(STATE_SELECTED_SECTION_ID);
	    mFromSavedInstanceState = true;
	}

	// Select either the default item (0) or the last selected item.
	selectItem(mCurrentSelectedSectionID);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);
	// Indicate that this fragment would like to influence the set of
	// actions in the action bar.
	setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	mDrawerListView = (ExpandableListView) inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
	// mDrawerListView
	// .setOnItemClickListener(new AdapterView.OnItemClickListener() {
	// @Override
	// public void onItemClick(AdapterView<?> parent, View view,
	// int position, long id) {
	// selectItem(position);
	// }
	// });
	mDrawerListView.setGroupIndicator(null);
	mDrawerListView.setOnGroupExpandListener(this);
	try {
	    loadMenu(inflater);
	} catch (JSONException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// mDrawerListView.setAdapter();
	// new ArrayAdapter<String>(getActivity().getApplicationContext(),
	// android.R.layout.simple_list_item_1,
	// android.R.id.text1, new String[] {
	// getString(R.string.title_section1),
	// getString(R.string.title_section2),
	// getString(R.string.title_section3), }));
//	mDrawerListView.setItemChecked(mCurrentSelectedSectionID, true);
	return mDrawerListView;
    }

    public boolean isDrawerOpen() {
	return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public void toggleDrawer() {
	if (mDrawerLayout != null) {
	    if (isDrawerOpen()) {
		mDrawerLayout.closeDrawer(mFragmentContainerView);
	    } else {
		mDrawerLayout.openDrawer(mFragmentContainerView);
	    }
	}
    }

    /**
     * Users of this fragment must call this method to set up the navigation
     * drawer interactions.
     * 
     * @param fragmentId
     *            The android:id of this fragment in its activity's layout.
     * @param drawerLayout
     *            The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
	mFragmentContainerView = getActivity().findViewById(fragmentId);
	mDrawerLayout = drawerLayout;

	// set a custom shadow that overlays the main content when the drawer
	// opens
	mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
	// set up the drawer's list view with items and click listener

	ActionBar actionBar = getActionBar();
	actionBar.setDisplayHomeAsUpEnabled(true);
	actionBar.setHomeButtonEnabled(true);

	// ActionBarDrawerToggle ties together the the proper interactions
	// between the navigation drawer and the action bar app icon.
	mDrawerToggle = new ActionBarDrawerToggle(
	    getActivity(), /* host Activity */
	    mDrawerLayout, /* DrawerLayout object */
	    // R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret
	    // */ deprecated at v7
	    R.string.navigation_drawer_open, /*
					      * "open drawer" description for
					      * accessibility
					      */
	    R.string.navigation_drawer_close /*
					      * "close drawer" description for
					      * accessibility
					      */
	) {
	    @Override
	    public void onDrawerClosed(View drawerView) {
		super.onDrawerClosed(drawerView);
		if (!isAdded()) {
		    return;
		}

		getActivity().supportInvalidateOptionsMenu(); // calls
							      // onPrepareOptionsMenu()
	    }

	    @Override
	    public void onDrawerOpened(View drawerView) {
		super.onDrawerOpened(drawerView);
		if (!isAdded()) {
		    return;
		}

		if (!mUserLearnedDrawer) {
		    // The user manually opened the drawer; store this flag to
		    // prevent auto-showing
		    // the navigation drawer automatically in the future.
		    mUserLearnedDrawer = true;
		    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
		    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).commit();
		}

		getActivity().supportInvalidateOptionsMenu(); // calls
							      // onPrepareOptionsMenu()
	    }
	};

	// If the user hasn't 'learned' about the drawer, open it to introduce
	// them to the drawer,
	// per the navigation drawer design guidelines.
	if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
	    mDrawerLayout.openDrawer(mFragmentContainerView);
	}

	// Defer code dependent on restoration of previous instance state.
	mDrawerLayout.post(new Runnable() {
	    @Override
	    public void run() {
		mDrawerToggle.syncState();
	    }
	});

	mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void selectItem(long sectionId) {
	mCurrentSelectedSectionID = sectionId;
//	if (mDrawerListView != null) {
//	    mDrawerListView.setItemChecked(sectionId, true);
//	}
	if (mDrawerLayout != null) {
	    mDrawerLayout.closeDrawer(mFragmentContainerView);
	}
	if (mCallbacks != null) {
	    mCallbacks.onNavigationDrawerItemSelected(sectionId);
	}
    }

    private void loadMenu(LayoutInflater inflater) throws JSONException {
	List<MenuGroup> groups = new ArrayList<MenuGroup>();
	JSONArray groupsArray = JSON.getMenu();
	for (int i = 0; i < groupsArray.length(); i++) {
	    JSONObject groupObject = groupsArray.getJSONObject(i);
	    MenuGroup group = new MenuGroup(groupObject);
	    groups.add(group);
	}

	MenuAdapter adapter = new MenuAdapter(inflater, groups);
	mDrawerListView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
	super.onAttach(activity);
	try {
	    mCallbacks = (NavigationDrawerCallbacks) activity;
	} catch (ClassCastException e) {
	    throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
	}
    }

    @Override
    public void onDetach() {
	super.onDetach();
	mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	outState.putLong(STATE_SELECTED_SECTION_ID, mCurrentSelectedSectionID);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
	super.onConfigurationChanged(newConfig);
	// Forward the new configuration the drawer toggle component.
	mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	// If the drawer is open, show the global app actions in the action bar.
	// See also
	// showGlobalContextActionBar, which controls the top-left area of the
	// action bar.
	if (mDrawerLayout != null && isDrawerOpen()) {
	    inflater.inflate(R.menu.global, menu);
	    // showGlobalContextActionBar();
	}
	super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
	if (mDrawerToggle.onOptionsItemSelected(item)) {
	    return true;
	}

	if (item.getItemId() == R.id.action_language) {
	    Toast.makeText(getActivity(), "Example action.", Toast.LENGTH_SHORT).show();
	    return true;
	}

	return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to
     * show the global app 'context', rather than just what's in the current
     * screen.
     */
    private void showGlobalContextActionBar() {
	ActionBar actionBar = getActionBar();
	actionBar.setDisplayShowTitleEnabled(true);
	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	actionBar.setTitle(R.string.app_name);
    }

    private ActionBar getActionBar() {
	return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    /**
     * Callbacks interface that all activities using this fragment must
     * implement.
     */
    public static interface NavigationDrawerCallbacks {
	/**
	 * Called when an item in the navigation drawer is selected.
	 */
	void onNavigationDrawerItemSelected(long sectionId);
    }

    @Override
    public void onGroupExpand(int groupPosition) {
	if (mLastExpandedPosition != -1 && groupPosition != mLastExpandedPosition) {
	    mDrawerListView.collapseGroup(mLastExpandedPosition);
	    mDrawerListView.setItemChecked(mLastExpandedPosition, false);
	}

	mDrawerListView.setItemChecked(groupPosition, true);
	mLastExpandedPosition = groupPosition;

	// selectItem(groupPosition);
    }
}
