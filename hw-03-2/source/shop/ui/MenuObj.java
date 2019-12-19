package shop.ui;

final class MenuObj extends UIElementObj<UIMenuAction> implements Menu{


	public MenuObj(String heading, UIPair<String, UIMenuAction>[] uiElement) {
		super(heading, uiElement);
		_heading = heading;
		_uiElement = uiElement;
		
	}

	public void runAction(int i) {
		 _uiElement[i].getSecond().run();
	}
}
