package az.nar.mobileapp.nar.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import az.nar.mobileapp.nar.view.MenuGroup;
import az.nar.mobileapp.nar.view.MenuItem;

public class JSON {

    public static final String FLD_ID = "id";
    public static final String FLD_NAME = "name";
    public static final String FLD_DESCRIPTION = "description";
    public static final String FLD_CHILD = "child";
    public static final String FLD_CHILDREN = "children";
    
    public static JSONArray navigationMenu;
    
    static {
	try {
	    navigationMenu = getMenu();
	} catch (JSONException e) {
	    e.printStackTrace();
	}
    } 

    public static List<MenuGroup> getMenuGroups() throws JSONException {
	List<MenuGroup> groups = new ArrayList<MenuGroup>();
	
	for (int i = 0; i < navigationMenu.length(); i++) {
	    JSONObject object = navigationMenu.getJSONObject(i);
	    MenuGroup group = new MenuGroup(object);
	    groups.add(group);
	}
	
	return groups;
    }
    
    public static List<MenuItem> getMenuChildren(long groupID) throws JSONException{
	
	List<MenuItem> items = new ArrayList<MenuItem>();
	
	JSONObject object = null;
	for (int i = 0; i < navigationMenu.length(); i++) {
	    object = navigationMenu.getJSONObject(i);
	    if (object.getLong(FLD_ID) == groupID) {
		break;
	    }
	}
	
	JSONArray array = object.getJSONArray(FLD_CHILDREN);
	
	for (int i = 0; i < array.length(); i++) {
	    JSONObject child = array.getJSONObject(i);
	    MenuItem item = new MenuItem(child);
	    items.add(item);
	}
	
	return items;
    }
    
    private static JSONArray getMenu() throws JSONException {
	JSONArray menu = new JSONArray();
	JSONObject menuGroup;
	JSONArray menuChildren;
	JSONObject menuItem;

	// User information
	menuGroup = new JSONObject();
	menuGroup.put(FLD_ID, 1);
	menuGroup.put(FLD_NAME, "User Information");
	menuChildren = new JSONArray();

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 11);
	menuItem.put(FLD_NAME, "Service Password");
	menuItem.put(FLD_DESCRIPTION, "Set your password and personal question.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 12);
	menuItem.put(FLD_NAME, "Subscriber Information");
	menuItem.put(FLD_DESCRIPTION, "See your subscription details.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 13);
	menuItem.put(FLD_NAME, "Account Information");
	menuItem.put(FLD_DESCRIPTION, "Check detailed information on your account.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 14);
	menuItem.put(FLD_NAME, "My Offerings");
	menuItem.put(FLD_DESCRIPTION, "Learn your current tariff, active campaigns and services.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 15);
	menuItem.put(FLD_NAME, "Change Language");
	menuItem.put(FLD_DESCRIPTION, "Choose IVR, SMS, USSD language.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 16);
	menuItem.put(FLD_NAME, "Account Status");
	menuItem.put(FLD_DESCRIPTION, "Find out the general status of your account.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 17);
	menuItem.put(FLD_NAME, "Transactions History");
	menuItem.put(FLD_DESCRIPTION, "Look through the status of the executed operations.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 18);
	menuItem.put(FLD_NAME, "Login History");
	menuItem.put(FLD_DESCRIPTION, "Go through the history of logon attempts.");
	menuChildren.put(menuItem);

	menuGroup.put(FLD_CHILDREN, menuChildren);
	menu.put(menuGroup);
	// -----------------------------------------

	// Operations
	menuGroup = new JSONObject();
	menuGroup.put(FLD_ID, 2);
	menuGroup.put(FLD_NAME, "Operations");
	menuChildren = new JSONArray();

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 21);
	menuItem.put(FLD_NAME, "Cancel Supplementary Offering");
	menuItem.put(FLD_DESCRIPTION, "Cancel the current supplementary offerings.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 22);
	menuItem.put(FLD_NAME, "Order Supplementary Offering");
	menuItem.put(FLD_DESCRIPTION, "Order supplementary offerings such as SMS and Internet packages, calls and balance services.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 23);
	menuItem.put(FLD_NAME, "Send SMS");
	menuItem.put(FLD_DESCRIPTION, "Send an SMS to other Nar subscribers from Web.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 24);
	menuItem.put(FLD_NAME, "Modify Primary Offering");
	menuItem.put(FLD_DESCRIPTION, "Change your tariff plan.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 25);
	menuItem.put(FLD_NAME, "Favorite Numbers");
	menuItem.put(FLD_DESCRIPTION, "Add, change or remove favorite numbers.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 26);
	menuItem.put(FLD_NAME, "Activate Scratch Card");
	menuItem.put(FLD_DESCRIPTION, "Top-up your or another Nar subscriber’s balance by a scratch card.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 27);
	menuItem.put(FLD_NAME, "Money Transfer");
	menuItem.put(FLD_DESCRIPTION, "Transfer balance to another Nar subscriber’s account.");
	menuChildren.put(menuItem);

	menuGroup.put(FLD_CHILDREN, menuChildren);
	menu.put(menuGroup);
	// -------------------------------

	// Payment
	menuGroup = new JSONObject();
	menuGroup.put(FLD_ID, 3);
	menuGroup.put(FLD_NAME, "Payment");
	menuChildren = new JSONArray();

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 31);
	menuItem.put(FLD_NAME, "Money Transfer History");
	menuItem.put(FLD_DESCRIPTION, "Get detailed information on money transfers.");
	menuChildren.put(menuItem);

	menuItem = new JSONObject();
	menuItem.put(FLD_ID, 32);
	menuItem.put(FLD_NAME, "Recharge History");
	menuItem.put(FLD_DESCRIPTION, "Learn more about the details of amounts recharged to your balance.");
	menuChildren.put(menuItem);

	menuGroup.put(FLD_CHILDREN, menuChildren);
	menu.put(menuGroup);
	// -------------------------------
	return menu;
    }
    
    public static List<MenuGroup> getMenuList() throws JSONException {
	List<MenuGroup> groups = new ArrayList<MenuGroup>();
	for (int i = 0; i < navigationMenu.length(); i++) {
	    JSONObject groupObject = navigationMenu.getJSONObject(i);
	    MenuGroup group = new MenuGroup(groupObject);
	    groups.add(group);
	}
	return groups;
    }
}
