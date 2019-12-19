package shop.ui;

public class UIFactory {
  private UIFactory() {}
  
  static private UI _UI = new PopupUI();
  //static private UI _UI = new TextUI();
  
  static public UI ui () {
    return _UI;
  }
  
  public static MenuBuilder createNewMenuBuilder() {
		return new UIMenuBuilder();
	}
	
  public static FormBuilder createNewFormBuilder() {
		UIFormBuilder formBuilder = new UIFormBuilder();
		return formBuilder;
	}
  
  
  public static Menu convertToMenu(UIElement<UIMenuAction> o) {
	  return new MenuObj(o.getHeading(), o.getPairArr());
  }
  
  public static Form convertToForm(UIElement<UIFormTest> o) {
	  return new FormObj(o.getHeading(), o.getPairArr());
  }
	
}
