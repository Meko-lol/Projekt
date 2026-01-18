package MyFileManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyFileManager {

    private ObjectMapper objectMapper;

    public MyFileManager() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void saveToFile(String filePath, Object objectToSave) {
        try {
            String jsonString = objectMapper.writeValueAsString(objectToSave);
            Files.write(Paths.get(filePath), jsonString.getBytes());
        } catch (Exception e) {
            //TODO
        }
    }

    public <T> T loadFromFile(String filePath, Class<T> classOfObject) {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
            return objectMapper.readValue(jsonString, classOfObject);
        } catch (Exception e) {
            //TODO
            return null;
        }
    }
}
