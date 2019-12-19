package shop.ui;


class UIFormBuilder extends UIBuilderObj<UIFormTest> implements FormBuilder{
	
	public void add(String prompt, UIFormTest test) {
		
		_menu.add(new UIPair<String, UIFormTest>(prompt, test));
	}

}
