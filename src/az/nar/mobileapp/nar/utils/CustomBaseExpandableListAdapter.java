package az.nar.mobileapp.nar.utils;

import java.util.List;

import android.view.LayoutInflater;
import android.widget.BaseExpandableListAdapter;

public abstract class CustomBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private List<? extends ListGroup> list;
    protected LayoutInflater inflater;

    public CustomBaseExpandableListAdapter(LayoutInflater inflater, List<? extends ListGroup> list) {
	this.list = list;
	this.inflater = inflater;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
	if (list != null) {
	    List<? extends SimpleElement> children = list.get(groupPosition).getChildren();

	    if (children != null) {
		return children.get(childPosition);
	    }
	}

	return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
	if (list != null) {
	    List<? extends SimpleElement> children = list.get(groupPosition).getChildren();

	    if (children != null) {
		return children.get(childPosition).getId();
	    }
	}

	return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
	if (list != null) {
	    List<? extends SimpleElement> children = list.get(groupPosition).getChildren();

	    if (children != null) {
		return children.size();
	    }
	}

	return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
	if (list != null) {
	    return list.get(groupPosition);
	}

	return null;
    }

    @Override
    public int getGroupCount() {
	if (list != null) {
	    return list.size();
	}

	return 0;
    }

    @Override
    public long getGroupId(int groupPosition) {
	if (list != null) {
	    return list.get(groupPosition).getId();
	}

	return 0;
    }

}
