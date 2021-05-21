package persistence;

import exception.NumberUsedException;
import model.MonitoringList;
import model.Section;
import model.Light;
import model.Camera;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.stream.Stream;

import org.json.*;

// structure of class based off of JsonSerializationDemo example given

// Represents a reader that reads monitoringlist from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads monitoringlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MonitoringList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMonitoringList(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: parses monitoringlist from JSON object and returns it
    private MonitoringList parseMonitoringList(JSONObject jsonObject) {
        MonitoringList ml = new MonitoringList();
        addSections(ml, jsonObject);
        return ml;
    }

    //MODIFIES: ml
    // EFFECTS: parses sections from JSON object and adds them to monitoringlist
    private void addSections(MonitoringList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("sections");
        for (Object json : jsonArray) {
            JSONObject nextSection = (JSONObject) json;
            addSection(ml, nextSection);
        }
    }

    //MODIFIES: ml
    //EFFECTS: parses light and camera within section from JSON object and adds it to section in ml
    private void addSection(MonitoringList ml, JSONObject jsonObject) {
        String id = jsonObject.getString("sectionID");
        JSONArray jsonArrayLights = jsonObject.getJSONArray("lights");
        JSONArray jsonArrayCameras = jsonObject.getJSONArray("cameras"); //ask if i do the right thing here
        Section section = new Section(id);

        addCameraToSection(jsonArrayCameras, section);

        for (Object jsonLight : jsonArrayLights) {
            JSONObject nextLight = (JSONObject) jsonLight;
            String label = nextLight.getString("label");
            Light light = new Light(label);
            section.addLightJson(light);
        }
        ml.addSection(section);
    }

    //EFFECTS: takes objects from JSONArray and turns them into cameras and adds them to section
    private void addCameraToSection(JSONArray jsonArrayCameras, Section section) {
        for (Object jsonCamera : jsonArrayCameras) {
            JSONObject nextCamera = (JSONObject) jsonCamera;
            String identification = nextCamera.getString("identification");
            Camera camera = new Camera(identification);
            section.addCameraJson(camera);
        }
    }




}
