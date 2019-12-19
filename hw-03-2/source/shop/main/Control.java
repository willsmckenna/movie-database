package shop.main;

import shop.ui.Menu;
import shop.ui.MenuBuilder;
import shop.ui.UI;
import shop.ui.UIElement;
import shop.ui.UIError;
import shop.ui.UIFactory;
import shop.ui.UIMenuAction;

import java.util.HashMap;

import shop.data.Inventory;

class Control {
  private Menu[] _menus;
  ControlStates _state;
  HashMap<ControlStates, Integer> numStates = new HashMap<ControlStates, Integer>();
  private Inventory _inventory;
  private UI _ui;
  
  Control(Inventory inventory, UI ui) {
    _inventory = inventory;
    _ui = ui;
    _menus = new Menu[3];
    _state = ControlStates.Start;
    numStates.put(ControlStates.Exited, 0);numStates.put(ControlStates.Exit, 1);
    numStates.put(ControlStates.Start, 2);
    addSTART(ControlStates.Start);
    addEXIT(ControlStates.Exit);
  }
  
  void run() {
    try {
      while (_state != ControlStates.Exited) {
        _ui.processMenu(_menus[numStates.get(_state)]);
      }
    } catch (UIError e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(ControlStates state) {
	  
    MenuBuilder m = UIFactory.createNewMenuBuilder();
    m.add("Default",  new UIMenuAction() {
		public void run() {
			StartStates.Default.run();
		}}
    );
    m.add("Add/Remove copies of a video", new UIMenuAction() {
		public void run() {
			StartStates.AddRemove.run();
		}});
    m.add("Check in a video", new UIMenuAction() {
		public void run() {
			StartStates.Checkin.run();
		}});
    m.add("Check out a video", new UIMenuAction() {
		public void run() {
			StartStates.Checkout.run();;
		}});
    m.add("Print the inventory", new UIMenuAction() {
		public void run() {
			StartStates.Print.run();
		}});
    m.add("Clear the inventory", new UIMenuAction() {
		public void run() {
			StartStates.Clear.run();
		}});
    m.add("Undo", new UIMenuAction() {
		public void run() {
			StartStates.Undo.run();
		}});
    m.add("Redo", new UIMenuAction() {
		public void run() {
			StartStates.Redo.run();
		}});
    m.add("Print top ten all time rentals in order", new UIMenuAction() {
		public void run() {
			StartStates.PrintTopTen.run();
		}});
    m.add("Exit", new UIMenuAction() {
		public void run() {
			StartStates.Exit.run();
			_state = ControlStates.Exit;
		}});
    m.add("Initialize with bogus contents", new UIMenuAction() {
		public void run() {
			StartStates.InitializeContents.run();
		}});
    
    UIElement<UIMenuAction> o = m.toUIElement("Bob's Video");
    _menus[numStates.get(state)] = UIFactory.convertToMenu(o);
  }
  
  private void addEXIT(ControlStates state) {
	MenuBuilder m = UIFactory.createNewMenuBuilder();
    m.add("Default", new UIMenuAction() {
    	public void run() {
    		_ui.displayError("doh!");
    	}
    });
    m.add("Yes", new UIMenuAction() {
    	public void run() {
    		_state = ControlStates.Exited;
    	}
    });
    m.add("No", new UIMenuAction() {
    	public void run() {
    		_state = ControlStates.Start;
    	}
    });
    
    UIElement<UIMenuAction> o = m.toUIElement("Are you sure you want to exit?");
    _menus[numStates.get(state)] = UIFactory.convertToMenu(o);
  }
}
