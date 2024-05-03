package main.java.디자인패턴.커맨드패턴;

import java.util.HashMap;
import java.util.Map;

public class Keyboard {
    private Map<Character, Command> commandMap = new HashMap<>();

    public Keyboard(Map<Character, Command> map){
        commandMap.putAll(map);
    }

    public void pushCommand(Character key){
        Command command = commandMap.getOrDefault(key, new NoCommand());
        command.execute();
    }
}
