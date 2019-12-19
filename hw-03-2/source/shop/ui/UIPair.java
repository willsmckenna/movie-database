package shop.ui;

class UIPair<String, T> implements Pair<String, T> {
	private String _string;
	private T _t;
	
	public UIPair(String string, T t) {
		_string = string;
		_t= t;
	}
	
	public String getFirst() {
		return _string;
	}

	public T getSecond() {
		return _t;
	}
	
	public Pair<String, T> toUIPair(){
		return new UIPair<String, T>(_string, _t);
	}
	
	public java.lang.String toString() {
		return _string + " : " + _t;
	}
}
