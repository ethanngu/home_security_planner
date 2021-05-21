package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

//represents a camera with a status and a position
public class Camera implements Writable {
    private String identification; //name of Camera
    private Boolean status; // whether the Camera is on or off; false = off, true = on
    private String position; // direction the Camera is facing - one of "right", "center", "left"
    private static final Boolean DEFAULT_POWER = false;
    private static final String DEFAULT_POSITION = "center";

    //REQUIRES: num cannot be negative
    //EFFECTS: Constructs a Camera that is OFF and facing CENTER
    public Camera(int num) {
        identification = "Camera #" + num;
        status = DEFAULT_POWER;
        position = DEFAULT_POSITION;
    }

    //Overloaded constructor
    public Camera(String num) {
        identification = num;
        status = DEFAULT_POWER;
        position = DEFAULT_POSITION;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getPosition() {
        return position;
    }

    public String getIdentification() {
        return identification;
    }

    //REQUIRES: number hasn't been used yet
    //MODIFIES: this
    //EFFECTS: changes the name of the camera
    public void changeName(int num) {
        identification = "Camera #" + num;
    }


    //REQUIRES: camera to be off
    //MODIFIES: this
    //EFFECTS: turns on the camera - changes status to true
    public void powerOn() {
        status = true;
    }

    //REQUIRES: camera to be on
    //MODIFIES: this
    //EFFECTS: turns off the camera - changes status to false
    public void powerOff() {
        status = false;
    }

    //MODIFIES: this
    //EFFECTS: sends camera to the next position on the right; if already at right, stays right
    public void rotateRight() {
        if (position == "left") {
            position = "center";
        } else {
            position = "right";
        }
    }

    //MODIFIES: this
    //EFFECTS: sends camera to the next position on the left; if already at left, stays left
    public void rotateLeft() {
        if (position == "right") {
            position = "center";
        } else {
            position = "left";
        }
    }


    //EFFECTS: takes camera and returns it as a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("identification", identification);
        json.put("status", status);
        json.put("position", position);
        return json;
    }

    @Override // had to make formatting changes here
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Camera camera = (Camera) o;
        return Objects.equals(identification, camera.identification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identification);
    }
}
