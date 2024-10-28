import java.util.ArrayList;

public class CommandParser {

    public String command;
    public ArrayList<String> commandArgumants;
    private ArrayList<String> Split(String s, char a) {
        ArrayList<String> array = new ArrayList<>();
        int idx = 0, len = s.length();
        String word = "";
        while(idx < len) {
            if(s.charAt(idx) == a) {
                array.add(word);
                word = "";
                idx++;
                continue;
            }
            word += s.charAt(idx);
            idx++;
        }
        if(!word.isEmpty()) {
            array.add(word);
        }
        return array;
    }

    public CommandParser(String command) {
        ArrayList<String> splittedCommand = Split(command, ' ');
        this.command = splittedCommand.get(0);
        commandArgumants = new ArrayList<>();
        for(int i = 1; i < splittedCommand.size();i ++) {
            commandArgumants.add(splittedCommand.get(i));
        }
    }

}
