package model;

import exception.NumberUsedException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents an area with possible security features
public class Section implements Writable {
    private String sectionID; //name of the section
    private List<Camera> cameras; // list of all cameras in the section
    private List<Light> lights; // list of all lights in the section

    // constructs a new section with a sectionID of id and an empty list of cameras and lights
    public Section(String id) {
        sectionID = id;
        cameras = new ArrayList<>();
        lights = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a camera c to list of cameras and returns a string that states a camera has been added if it hasn't
    // been added already, if already been added, throws NumberUsedException
    public String addCamera(Camera c) throws NumberUsedException {
        if (cameras.contains(c)) {
            throw new NumberUsedException();
        } else {
            cameras.add(c);
            return "added new camera to " + sectionID;
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a camera c to list of cameras and returns a string that states a camera has been added (for Json)
    public String addCameraJson(Camera c) {
        cameras.add(c);
        return "added new camera to " + sectionID;
    }


    //MODIFIES: this
    //EFFECTS: removes camera c from list of cameras if present and returns a string that states it has been removed;
    // otherwise states that a camera has not been found in the list
    public String removeCamera(String name) {
        for (Camera c : cameras) {
            if (c.getIdentification().equals(name)) {
                cameras.remove(c);
                return "Removed camera";
            }
        }
        return "Camera not found in list";
    }

    //MODIFIES: this
    //EFFECTS: if a light with given number has not been added yet,
    // adds a light to a list of lights and returns a string that confirms the light has been added,
    // otherwise throw NumberUsedException
    public String addLight(Light unit) throws NumberUsedException {
        if (lights.contains(unit)) {
            throw new NumberUsedException();
        } else {
            lights.add(unit);
            return "added new light to " + sectionID;
        }
    }


    //MODIFIES: this
    //EFFECTS: adds a light to a list of lights and returns a string that confirms the light has been added
    public String addLightJson(Light unit) {
        lights.add(unit);
        return "added new light to " + sectionID;
    }


    @Override
    public String toString() {
        //return "Section{" + "sectionID='" + sectionID + '\'' + '}';
        return sectionID;
    }



    //MODIFIES: this
    //EFFECTS: removes a light from the list of lights and returns a confirmation message if light was removed; ow
    // returns statement saying light was not found in list
    public String removeLight(String label) {
        for (Light l : lights) {
            if (l.getLabel().equals(label)) {
                lights.remove(l);
                return "Removed light";
            }
        }
        return "Light not found in list";
    }


    //EFFECTS: returns a list of Cameras and Lights that are in this section
    public List securityFeatures() {
        List features = new ArrayList();
        for (Camera c : cameras) {
            features.add(c.getIdentification());
        }
        for (Light l : lights) {
            features.add(l.getLabel());
        }
        return features;
    }

    public List getCameraList() {
        return cameras;
    }

    public List getLightList() {
        return lights;
    }

    public String getSectionName() {
        return sectionID;
    }


    //EFFECTS: takes section and returns it as a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("sectionID", sectionID);
        json.put("lights", lightsToJson());
        json.put("cameras", camerasToJson());
        return json;
    }

    // EFFECTS: returns lights in this section as a JSON array
    private JSONArray lightsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Light l : lights) {
            jsonArray.put(l.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns cameras in this section as a JSON array
    private JSONArray camerasToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Camera c : cameras) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
