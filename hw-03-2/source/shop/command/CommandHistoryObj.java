package shop.command;
import java.util.Stack;

final class CommandHistoryObj implements CommandHistory {
  Stack<UndoableCommand> _undoStack = new Stack<UndoableCommand>();
  Stack<UndoableCommand> _redoStack = new Stack<UndoableCommand>();
  RerunnableCommand _undoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_undoStack.empty();
        if (result) {
          _undoStack.get(_undoStack.size() - 1).undo();
          _redoStack.add(_undoStack.get(_undoStack.size() - 1));
          _undoStack.pop();
         
        }
        return result;
      }
    };
  RerunnableCommand _redoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_redoStack.empty();
        if (result) {
          _undoStack.add(_redoStack.get(_redoStack.size() - 1));
          _redoStack.get(_redoStack.size() - 1).redo();
          _redoStack.pop();
        }
        return result;
      }
    };

  public void add(UndoableCommand cmd) {
    _undoStack.add(cmd);
    _redoStack.clear();
  }
  
  public RerunnableCommand getUndo() {
    return _undoCmd;
  }
  
  public RerunnableCommand getRedo() {
    return _redoCmd;
  }
  
  // For testing
  UndoableCommand topUndoCommand() {
    if (_undoStack.empty())
      return null;
    else
      return _undoStack.peek();
  }
  // For testing
  UndoableCommand topRedoCommand() {
    if (_redoStack.empty())
      return null;
    else
      return _redoStack.peek();
  }
}
