package az.nar.mobileapp.nar.utils.main;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import az.nar.mobileapp.nar.NavigationDrawerFragment.NavigationDrawerCallbacks;
import az.nar.mobileapp.nar.R;
import az.nar.mobileapp.nar.utils.CustomBaseAdapter;
import az.nar.mobileapp.nar.utils.SimpleElement;
import az.nar.mobileapp.nar.view.MenuItem;

public class GroupViewAdapter extends CustomBaseAdapter implements OnClickListener{

    	private NavigationDrawerCallbacks mCallbacks;
    	
	public GroupViewAdapter(LayoutInflater inflater, List<? extends SimpleElement> list, NavigationDrawerCallbacks callbacks) {
	    super(inflater, list);
	    
	    mCallbacks = callbacks;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    
	    if (convertView == null) {
		convertView = mInflater.inflate(R.layout.item_menu_child_on_main_view, parent, false);
	    }
	    
	    MenuItem item = (MenuItem) getItem(position);
	    
	    TextView tvMenuTitle = (TextView) convertView.findViewById(R.id.tvMenuTitle);
	    tvMenuTitle.setText(item.getName() + " >>");
	    convertView.setTag(item);
	    convertView.setOnClickListener(this);
	    
	    TextView tvMenuDescription = (TextView) convertView.findViewById(R.id.tvMenuDescription);
	    tvMenuDescription.setText(item.getDescription());
	    
	    return convertView;
	}

	@Override
	public void onClick(View v) {
	    MenuItem item = (MenuItem) v.getTag();
	    mCallbacks.onNavigationDrawerItemSelected((int) item.getId());
	}
}