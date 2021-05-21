package persistence;

import exception.NumberUsedException;
import model.Camera;
import model.Light;
import model.MonitoringList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MonitoringList ml = new MonitoringList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyMonitoringList() {
        try {
            MonitoringList ml = new MonitoringList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMonitoringList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMonitoringList.json");
            ml = reader.read();
            assertEquals(0, ml.getLength());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

//    private void checkSectionName(String name, MonitoringList ml) {
//        assertEquals(name, ml.getSectionAtPosition(0).getSectionName());
//    }

//    private void checkContainsCamera(List<Camera> list, MonitoringList ml) {
//        assertEquals(list, ml.getSectionAtPosition(0).getCameraList());
//    }

    @Test
    void testWriterGeneralMonitoringList() {
        try {
            MonitoringList ml = new MonitoringList();
            Section section = new Section("kitchen");
            Light light = new Light(0);
            Camera camera = new Camera(0);
            try {
                section.addCamera(camera);
            } catch (NumberUsedException e) {
                fail("Exception should not have been thrown");
            }
            try {
                section.addLight(light);
            } catch (NumberUsedException e) {
                fail("Exception should not have been thrown");
            }
            ml.addSection(section);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMonitoringList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMonitoringList.json");
            ml = reader.read();
            assertEquals(1, ml.getLength());
            assertEquals("kitchen", ml.getSectionAtPosition(0).getSectionName());
            // assert that each part of camera is the same
            List<Camera> listCamera = ml.getSectionAtPosition(0).getCameraList();
            assertEquals("Camera #0", listCamera.get(0).getIdentification());
            List<Light> listLight = ml.getSectionAtPosition(0).getLightList();
            assertEquals("Light #0", listLight.get(0).getLabel());
            // loop through this list with a for loop
            // assert that each name is what it should be, each status and etc. do the same for light, it should be ok


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }



}

