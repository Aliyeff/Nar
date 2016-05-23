package az.nar.mobileapp.nar.view;

import org.json.JSONException;
import org.json.JSONObject;

import static az.nar.mobileapp.nar.json.JSON.*;
import az.nar.mobileapp.nar.utils.SimpleElement;

public class MenuItem extends SimpleElement {

    /**
     * generated serial version UID
     */
    private static final long serialVersionUID = 689396353637688830L;

    private String description;
    
    public MenuItem() {
    }

    public MenuItem(long id, String name) {
	setID(id);
	setName(name);
    }
    
    public MenuItem(long id, String name, String description) {
	setID(id);
	setName(name);
	setDescription(description);
    }

    public MenuItem(JSONObject object) throws JSONException {
	setID(object.getLong(FLD_ID));
	setName(object.getString(FLD_NAME));
	setDescription(object.getString(FLD_DESCRIPTION));
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
}
