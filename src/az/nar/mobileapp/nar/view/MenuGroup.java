package az.nar.mobileapp.nar.view;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import az.nar.mobileapp.nar.json.JSON;
import az.nar.mobileapp.nar.utils.ListGroup;

public class MenuGroup extends ListGroup {

    /**
     * generated serial version UID
     */
    private static final long serialVersionUID = 3030648748605526944L;

    public MenuGroup() {
    }

    public MenuGroup(long id, String name) {
	setID(id);
	setName(name);
    }

    public MenuGroup(JSONObject object) throws JSONException {
	setID(object.getLong(JSON.FLD_ID));
	setName(object.getString(JSON.FLD_NAME));

	List<MenuItem> children = new ArrayList<MenuItem>();
	JSONArray array = object.getJSONArray(JSON.FLD_CHILDREN);
	for (int i = 0; i < array.length(); i++) {
	    JSONObject child = array.getJSONObject(i);
	    MenuItem item = new MenuItem(child);
	    children.add(item);
	}
	setChildren(children);
    }
}
