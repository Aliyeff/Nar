package az.nar.mobileapp.nar.utils.main;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;
import az.nar.mobileapp.nar.R;
import az.nar.mobileapp.nar.utils.CustomBaseExpandableListAdapter;
import az.nar.mobileapp.nar.utils.ListGroup;
import az.nar.mobileapp.nar.view.MenuGroup;
import az.nar.mobileapp.nar.view.MenuItem;

public class MenuAdapter extends CustomBaseExpandableListAdapter{

    public MenuAdapter(LayoutInflater inflater, List<? extends ListGroup> list) {
	super(inflater, list);
    }

    @Override
    public boolean hasStableIds() {
	return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
	if (convertView == null) {
	    convertView = mInflater.inflate(R.layout.item_group, parent, false);
	}

	MenuGroup group = (MenuGroup) getGroup(groupPosition);
	CheckedTextView ctv = (CheckedTextView) convertView;
	ctv.setText(group.getName());
	ctv.setChecked(isExpanded);
	ctv.setTag(groupPosition);
	
	return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
	ViewGroup parent) {

	MenuItem child = (MenuItem) getChild(groupPosition, childPosition);

	if (convertView == null) {
	    convertView = mInflater.inflate(R.layout.item_child, parent, false);
	}

	((TextView) convertView).setText(child.getName());
	convertView.setTag(child);
	
	return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
	return true;
    }
}
