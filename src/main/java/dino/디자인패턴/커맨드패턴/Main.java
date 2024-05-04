package dino.디자인패턴.커맨드패턴;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Key aKey = new ALowerKey();
        Command aKeyCommand = new KeyCommand(aKey);

        Key bKey = new BLowerKey();
        Command bKeyCommand = new KeyCommand(bKey);

        Map<Character, Command> commandMap = new HashMap<>();
        commandMap.put('a', aKeyCommand);
        commandMap.put('b', bKeyCommand);


        Keyboard keyboard = new Keyboard(commandMap);

        keyboard.pushCommand('a');
        keyboard.pushCommand('b');
        keyboard.pushCommand('c');
    }
}
