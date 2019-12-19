package shop.ui;

final class FormObj extends UIElementObj<UIFormTest> implements Form{

	public FormObj(String heading, UIPair<String, UIFormTest>[] uiElement) {
		super(heading, uiElement);
		_heading = heading;
		_uiElement = uiElement;
	}

	public boolean checkInput(int i, String input){
		 if (null == _uiElement[i])
		      return true;
		 UIFormTest theTest = _uiElement[i].getSecond();
		 return theTest.run(input);
	}

}
