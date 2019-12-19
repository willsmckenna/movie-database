package shop.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

abstract class UIBuilderObj<T> implements Builder {
	
	protected List<UIPair<String, T>> _menu;
	
	public UIBuilderObj() {
	    _menu = new ArrayList<UIPair<String, T>>();
	  }
	
	public UIElement<T> toUIElement(String heading){
		if (null == heading)
		      throw new IllegalArgumentException();
		 if (_menu.size() < 1)
			 throw new IllegalStateException();
		 UIPair<String, T>[] array = new UIPair[_menu.size()];
		 for (int i = 0; i < _menu.size(); i++)
		    array[i] = (UIPair<String, T>) (_menu.get(i));
		 
		return new UIElementObj<T>(heading, array);
	}
	
}