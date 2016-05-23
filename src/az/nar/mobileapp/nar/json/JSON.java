package az.nar.mobileapp.nar.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON {

	public static final String FLD_ID = "id";
	public static final String FLD_NAME = "name";
	public static final String FLD_CHILD = "child";
	public static final String FLD_CHILDREN = "children";
	
	public static JSONArray getMenu() throws JSONException {
		JSONArray menu = new JSONArray();
		JSONObject menuGroup;
		JSONArray menuChildren;
		JSONObject menuItem;
		
		//User information
		menuGroup = new JSONObject();
		menuGroup.put(FLD_ID, 1);
		menuGroup.put(FLD_NAME, "User Information");
		menuChildren = new JSONArray();
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 11);
		menuItem.put(FLD_NAME, "Service Password");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 12);
		menuItem.put(FLD_NAME, "Subscriber Information");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 13);
		menuItem.put(FLD_NAME, "Account Information");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 14);
		menuItem.put(FLD_NAME, "My Offerings");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 15);
		menuItem.put(FLD_NAME, "Change Language");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 16);
		menuItem.put(FLD_NAME, "Account Status");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 17);
		menuItem.put(FLD_NAME, "Transactions History");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 18);
		menuItem.put(FLD_NAME, "Login History");
		menuChildren.put(menuItem);
		
		menuGroup.put(FLD_CHILDREN, menuChildren);
		menu.put(menuGroup);
		//-----------------------------------------
		
		//Operations
		menuGroup = new JSONObject();
		menuGroup.put(FLD_ID, 2);
		menuGroup.put(FLD_NAME, "Operations");
		menuChildren = new JSONArray();
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 21);
		menuItem.put(FLD_NAME, "Cancel Supplementary Offering");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 22);
		menuItem.put(FLD_NAME, "Order Supplementary Offering");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 23);
		menuItem.put(FLD_NAME, "Send SMS");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 24);
		menuItem.put(FLD_NAME, "Modify Primary Offering");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 25);
		menuItem.put(FLD_NAME, "Favorite Numbers");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 26);
		menuItem.put(FLD_NAME, "Activate Scratch Card");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 27);
		menuItem.put(FLD_NAME, "Money Transfer");
		menuChildren.put(menuItem);
		
		menuGroup.put(FLD_CHILDREN, menuChildren);
		menu.put(menuGroup);
		//-------------------------------
		
		//Payment
		menuGroup = new JSONObject();
		menuGroup.put(FLD_ID, 3);
		menuGroup.put(FLD_NAME, "Payment");
		menuChildren = new JSONArray();
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 31);
		menuItem.put(FLD_NAME, "Money Transfer History");
		menuChildren.put(menuItem);
		
		menuItem = new JSONObject();
		menuItem.put(FLD_ID, 32);
		menuItem.put(FLD_NAME, "Recharge History");
		menuChildren.put(menuItem);
		
		menuGroup.put(FLD_CHILDREN, menuChildren);
		menu.put(menuGroup);
		//-------------------------------
		return menu;
	}
}
