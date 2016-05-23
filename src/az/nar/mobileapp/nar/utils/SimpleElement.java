package az.nar.mobileapp.nar.utils;

import java.io.Serializable;

/**
 * @author Desk
 * 
 */
public abstract class SimpleElement implements Serializable {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = 6978455399347295211L;
	private long ID;
	protected String mName;

	/**
	 * It's not recommended for extended classes
	 */
	public SimpleElement() {
	}

	/**
	 * It's recommended using specific constructors for extended classes <br>
	 * 
	 * @param id
	 * @param name
	 */
	public SimpleElement(long id, String name) {
		setID(id);
		setName(name);
	}

	public long getId() {
		return ID;
	}

	public String getName() {
		return mName;
	}

	public void setID(long id) {
		ID = id;
	}

	public void setName(String name) {
		mName = name;
	}
}
