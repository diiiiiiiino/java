package main.java.디자인패턴.커맨드패턴;

public class KeyCommand implements Command{
    private Key key;

    public KeyCommand(Key aKey) {
        this.key = aKey;
    }

    @Override
    public void execute() {
        key.push();
    }
}
