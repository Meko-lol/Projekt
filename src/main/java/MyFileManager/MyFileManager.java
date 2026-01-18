package MyFileManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyFileManager {

    public MyFileManager() {
    }

    public void saveToFile(String filePath, Object objectToSave) {
    }

    public <T> T loadFromFile(String filePath, Class<T> classOfObject) {
        return null;
    }
}
