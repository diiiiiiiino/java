package 디자인패턴.커맨드패턴;

public class RemoteController {
    Command[] onCommands;
    Command[] offCommands;

    Command undoCommand;

    public RemoteController(Command[] onCommands, Command[] offCommands) {
        this.onCommands = onCommands;
        this.offCommands = offCommands;
        undoCommand = new NoCommand();
    }

    void on(int index){
        if(index < 0 || index >= onCommands.length) return;
        onCommands[index].execute();
        undoCommand = onCommands[index];
    }

    void off(int index){
        if(index < 0 || index >= offCommands.length) return;
        offCommands[index].execute();
        undoCommand = offCommands[index];
    }

    void undo(){
        undoCommand.undo();
    }
}
