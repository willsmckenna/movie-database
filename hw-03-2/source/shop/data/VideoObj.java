package shop.data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if object invariant violated.
   */
  VideoObj(String title, int year, String director) {
    _title = title;
    _director = director;
    _year = year;
  }

  public String director() {
    return _director;
  }

  public String title() {
    return _title;
  }

  public int year() {
    return _year;
  }

  public boolean equals(Object thatObject) {
	  if (this == thatObject) {
		  return true;
	  }
	  if (!(thatObject instanceof VideoObj)) {
		  return false;
	  }
	  VideoObj thatVideoObj = (VideoObj)thatObject;
	  
	  return _title.equals(thatVideoObj._title) 
			  && _director.equals(thatVideoObj._director) 
			  && _year == thatVideoObj._year;
  }

  private int hcode;
  public int hashCode() {
    if (hcode == 0) {
    	hcode = 17;
    	hcode = 37 * hcode + _title.hashCode();
    	hcode = 37 * hcode + _year;
    	hcode = 37 * hcode + _director.hashCode();
    }
    return hcode;
  }
  public int compareTo(Object thatObject) {
	  if (this == thatObject) {
			return 0;
		}
		VideoObj that = (VideoObj)thatObject;
	    int compTitle = _title.compareTo(that._title);
	    if (compTitle != 0) {
	    	return compTitle;
	    }
	    int compYear = Integer.compare(_year, that._year);
	    if (compYear != 0) {
	    	return compYear;
	    }
	    return _director.compareTo(that._director);
  }

  public String toString() {
	  return _title + " (" + _year + ") : " + _director;
  }
}
