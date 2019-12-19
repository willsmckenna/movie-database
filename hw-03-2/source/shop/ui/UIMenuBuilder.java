package shop.ui;

import java.util.ArrayList;
import java.util.List;

final class UIMenuBuilder extends UIBuilderObj<UIMenuAction> implements MenuBuilder{
	

  public void add(String prompt, UIMenuAction action) {
    if (null == action)
      throw new IllegalArgumentException();
    
    _menu.add(new UIPair<String, UIMenuAction>(prompt, action));
  }
} 
