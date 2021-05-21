package persistence;

import model.Camera;
import model.Light;
import model.MonitoringList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MonitoringList ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMonitoringList() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyMonitoringList.json");
        try {
            MonitoringList ml = reader.read();
            assertEquals(0, ml.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMonitoringList() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralMonitoringList.json");
        try {
            MonitoringList ml = reader.read();
            assertEquals(1, ml.getLength());
            assertEquals("kitchen", ml.getSectionAtPosition(0).getSectionName());
            List<Camera> listCamera = ml.getSectionAtPosition(0).getCameraList();
            assertEquals("Camera #0", listCamera.get(0).getIdentification());
            List<Light> listLight = ml.getSectionAtPosition(0).getLightList();
            assertEquals("Light #0", listLight.get(0).getLabel());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
