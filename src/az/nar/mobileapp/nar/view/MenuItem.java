package az.nar.mobileapp.nar.view;

import org.json.JSONException;
import org.json.JSONObject;

import az.nar.mobileapp.nar.json.JSON;
import az.nar.mobileapp.nar.utils.SimpleElement;

public class MenuItem extends SimpleElement {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = 689396353637688830L;

	public MenuItem() {
	}
	
	public MenuItem(long id, String name) {
		setID(id);
		setName(name);
	}
	

	public MenuItem(JSONObject object) throws JSONException {
		setID(object.getLong(JSON.FLD_ID));
		setName(object.getString(JSON.FLD_NAME));
	}
}
