package Commands;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CommandHistory {
    private static final String FILE_PATH = "command_history.txt";

    public void save(String command) {
        // Simple file writing using FileWriter and PrintWriter
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, true); // 'true' means append to file
            PrintWriter printWriter = new PrintWriter(fileWriter);
            
            printWriter.println(command);
            
            printWriter.close();
            // System.out.println("Command saved to history."); // Optional logging
        } catch (IOException e) {
            System.out.println("Error saving history: " + e.getMessage());
        }
    }

    public void reset() {
        // Simple file clearing
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, false); // 'false' means overwrite/clear
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error resetting history: " + e.getMessage());
        }
    }
}
