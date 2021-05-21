package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

//represents a light with a status and a brightness level
public class Light implements Writable {
    private String label; // identification of the light
    private Boolean status; // whether the light is on or off; true = on, false = off
    private int brightness; // one of five levels of brightness, with 1 being the dimmest, and 5 being the brightest
    private static final int MAXIMUM_BRIGHTNESS = 5;
    private static final int MINIMUM_BRIGHTNESS = 1;
    private static final int DEFAULT_BRIGHTNESS = 3;
    private static final boolean DEFAULT_STATE = false;

    //REQUIRES: id cannot be negative
    //EFFECTS: constructs a Light that is off and has a brightness level of 3 with a name with given id number
    public Light(int id) {
        label = "Light #" + id;
        status = DEFAULT_STATE;
        brightness = DEFAULT_BRIGHTNESS;
    }

    //overloaded constructor
    public Light(String id) {
        label = id;
        status = DEFAULT_STATE;
        brightness = DEFAULT_BRIGHTNESS;
    }

    public String getLabel() {
        return label;
    }

    public Boolean getStatus() {
        return status;
    }

    public int getBrightness() {
        return brightness;
    }

    //MODIFIES: this
    //EFFECTS: turns on light if previously off, turns off light if already on
    public void powerSwitch() {
        if (status == false) {
            status = true;
        } else {
            status = false;
        }
    }

    //REQUIRES: id number has not been used already
    //MODIFIES: this
    //EFFECTS: changes id number in label
    public void changeLabel(int id) {
        label = "Light #" + id;
    }

    //REQUIRES: Light has to be on
    //MODIFIES: this
    //EFFECTS: sets the brightness level of the light from a scale of MINIMUM_BRIGHTNESS to MAXIMUM_BRIGHTNESS
    // and returns the new level as a string
    public String setBrightness(int level) {
        brightness = level;
        return "brightness level set to level " + level;
    }

    //REQUIRES: Light has to be on
    //MODIFIES: this
    //EFFECTS: increases the brightness level by 1 (unless at max.)
    // and returns a statement with the current brightness level
    public String increaseBrightness() {
        if (brightness == MAXIMUM_BRIGHTNESS) {
            return "brightness level already at maximum";
        } else {
            brightness = brightness + 1;
            return "brightness level increased to " + brightness;
        }
    }

    //REQUIRES: Light has to be on
    //MODIFIES: this
    //EFFECTS: decreases the brightness level by 1 (unless at min.)
    // and returns a statement with the current brightness level
    public String decreaseBrightness() {
        if (brightness == MINIMUM_BRIGHTNESS) {
            return "brightness level already at lowest";
        } else {
            brightness = brightness - 1;
            return "brightness level decreased to " + brightness;
        }
    }


    //EFFECTS: takes light and converts it to json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("label", label);
        json.put("status", status);
        json.put("brightness", brightness);
        return json;
    }

    //had to change the formatting for checkstyle
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Light light = (Light) o;
        return Objects.equals(label, light.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

}
