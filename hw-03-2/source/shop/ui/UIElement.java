package shop.ui;

public interface UIElement<T> {
	public int size();
	public String getHeading();
	public String getPrompt(int i);
	public UIPair<String, T>[] getPairArr();	
}
 