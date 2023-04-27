package 디자인패턴.커맨드패턴;

public class RemoteLoader {
    public static void main(String[] args){
        CeilingFan ceilingFan = new CeilingFan();
        Command ceilingFanLowCommand = new CeilingFanLowCommand(ceilingFan);
        Command ceilingFanMediumCommand = new CeilingFanMediumCommand(ceilingFan);
        Command ceilingFanHighCommand = new CeilingFanHighCommand(ceilingFan);
        Command ceilingFanOffCommand = new CeilingFanOffCommand(ceilingFan);

        Command[] onCommands = { ceilingFanLowCommand, ceilingFanMediumCommand, ceilingFanHighCommand, new NoCommand() };
        Command[] offCommands = { ceilingFanOffCommand, ceilingFanOffCommand, ceilingFanOffCommand, new NoCommand() };

        RemoteController remoteController = new RemoteController(onCommands, offCommands);

        remoteController.on(0);
        remoteController.off(0);

        remoteController.off(1);
        remoteController.on(1);

        remoteController.undo();
    }
}
