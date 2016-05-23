package az.nar.mobileapp.nar.utils;

import java.util.List;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public abstract class CustomBaseAdapter extends BaseAdapter {

    protected List<?> mList;
    protected LayoutInflater mInflater;

    private boolean colored;

    public CustomBaseAdapter(LayoutInflater inflater, List<? extends SimpleElement> list) {
	mList = list;
	mInflater = inflater;
	setColored(true);
    }

    public CustomBaseAdapter(LayoutInflater inflater, List<? extends SimpleElement> list, boolean isColored) {
	mList = list;
	mInflater = inflater;
	setColored(isColored);
    }

    @Override
    public int getCount() {
	if (mList != null) {
	    return mList.size();
	}

	return 0;
    }

    @Override
    public Object getItem(int position) {
	return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
	return ((SimpleElement) getItem(position)).getId();
    }

    public int getPosition(long id) {
	int count = getCount();

	for (int i = 0; i < count; i++) {
	    if (getItemId(i) == id) {
		return i;
	    }
	}

	return 0;
    }

    public void setList(List<? extends SimpleElement> list) {
	mList = list;
    }

    public boolean isColored() {
	return colored;
    }

    public void setColored(boolean colored) {
	this.colored = colored;
    }
}
