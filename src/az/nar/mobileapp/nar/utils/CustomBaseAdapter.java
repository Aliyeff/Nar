package az.nar.mobileapp.nar.utils;

import java.util.List;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.widget.ListAdapter;

public abstract class CustomBaseAdapter implements ListAdapter {

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
	return mList == null ? 0: mList.size();
    }

    @Override
    public Object getItem(int position) {
	return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
	return ((SimpleElement) getItem(position)).getId();
    }

    @Override
    public boolean areAllItemsEnabled() {
	return true;
    }

    @Override
    public boolean isEnabled(int position) {
	return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
	// TODO Auto-generated method stub
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
	// TODO Auto-generated method stub
    }

    @Override
    public boolean hasStableIds() {
	return false;
    }

    @Override
    public int getItemViewType(int position) {
	return 0;
    }

    @Override
    public int getViewTypeCount() {
	return 1;
    }

    @Override
    public boolean isEmpty() {
	return getCount() == 0;
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
