package shop.main;

import java.util.Comparator;
import java.util.Iterator;

import shop.command.Command;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Record;
import shop.data.Video;
import shop.ui.Form;
import shop.ui.FormBuilder;
import shop.ui.UI;
import shop.ui.UIElement;
import shop.ui.UIFactory;
import shop.ui.UIFormTest;

public enum StartStates{
	
	Default {
		@Override
		public void run() {
			_ui.displayError("doh!");
		}
	}, AddRemove {
		@Override
		public void run() {
			String[] result1 = _ui.processForm(_getVideoForm);
	        Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

	        FormBuilder form = UIFactory.createNewFormBuilder();
	        form.add("Number of copies to add/remove", _numberTest);
	        UIElement<UIFormTest> o = form.toUIElement("");
	        String[] result2 = _ui.processForm(UIFactory.convertToForm(o));
	                                            
	        Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
	        if (! c.run()) {
	          _ui.displayError("Command failed");
	        }
		}
	}, Checkin {
		@Override
		public void run() {
			String[] result3 = _ui.processForm(_getVideoForm);
	        Video v2 = Data.newVideo(result3[0], Integer.parseInt(result3[1]), result3[2]);
	          
	        Command c2 = Data.newInCmd(_inventory, v2);
	        
	        if (! c2.run()) {
	        	  _ui.displayError("Command failed");
	        	  } 
		}
	}, Checkout {
		@Override
		public void run() {
			String[] result4 = _ui.processForm(_getVideoForm);
	        Video v3 = Data.newVideo(result4[0], Integer.parseInt(result4[1]), result4[2]);
	            
	        Command c3 = Data.newOutCmd(_inventory, v3);
	        if (! c3.run()) {
	         _ui.displayError("Command failed");
	        }
			
		}
	}, Print {
		@Override
		public void run() {
			_ui.displayMessage(_inventory.toString());
		}
	}, 
	Clear {
		@Override
		public void run() {
			if (!Data.newClearCmd(_inventory).run()) 
	            _ui.displayError("Command failed");
		}
	}, Undo {
		@Override
		public void run() {
			if (!Data.newUndoCmd(_inventory).run()) 
	            _ui.displayError("Command failed");	
		}
	}, Redo {
		@Override
		public void run() {
			 if (!Data.newRedoCmd(_inventory).run()) 
		            _ui.displayError("Command failed");
		}
	}, PrintTopTen {
		@Override
		public void run() {
			String topTen = "";
	        Iterator topTenItr =  _inventory.iterator(new Comparator<Record>() {
	        	 public int compare(Record r1, Record r2) {
	        		 return r2.numRentals() - r1.numRentals();
	        	 }
	         });
	         int i = 1;
	         while (topTenItr.hasNext() && i <= 10 ) {
	        	  topTen += "Number " + i + " most rented movie: " + topTenItr.next().toString() + "\n";
	        	  i++;
	         }
	         _ui.displayMessage(topTen);
			
		}
	}, Exit {
		@Override
		public void run() {
		}
	}, InitializeContents {
		@Override
		public void run() {
			Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
	        Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
	        Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
	        Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
	        Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
	        Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
	        Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
	        Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
	        Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
	        Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
	        Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
	        Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
	        Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
	        Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
	        Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
	        Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
	        Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
	        Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
	        Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
	        Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
	        Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
	        Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
	        Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
	        Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
	        Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
	        Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();	
		}
	};
	
	private static UI _ui = UIFactory.ui();
	private static UIFormTest _yearTest;
	private static UIFormTest _numberTest;
	private static UIFormTest _stringTest;
	private static Inventory _inventory = Data.newInventory();
	private static Form _getVideoForm;
	
	StartStates(){	
		
		UIFormTest _yearTest = new UIFormTest() {
	        public boolean run(String input) {
	          try {
	            int i = Integer.parseInt(input);
	            return i > 1800 && i < 5000;
	          } catch (NumberFormatException e) {
	            return false;
	          }
	        }
	      };
	    UIFormTest _numberTest = new UIFormTest() {
	        public boolean run(String input) {
	          try {
	            Integer.parseInt(input);
	            return true;
	          } catch (NumberFormatException e) {
	            return false;
	          }
	        }
	      };
	    UIFormTest _stringTest = new UIFormTest() {
	        public boolean run(String input) {
	          return ! "".equals(input.trim());
	        }
	      };
	}
	
	public abstract void run();
	
	static {
	    FormBuilder f = UIFactory.createNewFormBuilder();
		f.add("Title", _stringTest);
		f.add("Year", _yearTest);
		f.add("Director", _stringTest);
		_getVideoForm = UIFactory.convertToForm(f.toUIElement("Enter Video"));   
	    }
	
}
