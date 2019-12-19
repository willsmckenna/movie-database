package shop.ui;

import java.util.function.Function;

class UIElementObj<T> implements UIElement<T> {
	protected String _heading;
	protected UIPair<String, T>[] _uiElement;
	
	public UIElementObj(String heading, UIPair<String, T>[] uiElement) {
		_heading = heading;
	    _uiElement = uiElement;
	}
	
	public int size() {
		return _uiElement.length;
	}

	public String getHeading() {
		return _heading;
	}

	public String getPrompt(int i) {
		return  _uiElement[i].getFirst();
	}
	
	public UIPair<String, T>[] getPairArr() {
		return _uiElement;
	}
	
}
