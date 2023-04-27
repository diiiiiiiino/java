package 디자인패턴.커맨드패턴;

public class CeilingFanMediumCommand implements Command {
    CeilingFan ceilingFan;
    CeilingFan.Level prevLevel;

    public CeilingFanMediumCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevLevel = ceilingFan.getLevel();
        ceilingFan.medium();
    }

    @Override
    public void undo() {
        if(prevLevel == CeilingFan.Level.HIGH){
            ceilingFan.high();
        } else if(prevLevel == CeilingFan.Level.MEDIUM){
            ceilingFan.medium();
        } else if(prevLevel == CeilingFan.Level.LOW){
            ceilingFan.low();
        } else if(prevLevel == CeilingFan.Level.OFF){
            ceilingFan.off();
        }
    }
}
