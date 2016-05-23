package az.nar.mobileapp.nar.utils;

import java.util.List;

public abstract class ListGroup extends SimpleElement{

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = -1338932676450481616L;
	private List<? extends SimpleElement> children;
	
	/**
	 * @return the children
	 */
	public List<? extends SimpleElement> getChildren() {
		return children;
	}
	
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<? extends SimpleElement> children) {
		this.children = children;
	}
	
}
