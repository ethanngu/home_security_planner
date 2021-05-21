package model;

import exception.NumberUsedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SectionTest {
    private Section test;

    @BeforeEach
    void runBefore() {
        test = new Section("entrance");
    }

    @Test
    void testAddCamera() {
        Camera c1 = new Camera(0);
        try {
            assertEquals("added new camera to entrance", test.addCamera(c1));
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
        assertTrue(test.getCameraList().contains(c1));
    }

    @Test
    void testAddCameraDuplicate() {
        Camera c1 = new Camera(0);
        Camera c2 = new Camera(0);
        try {
            test.addCamera(c1);
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
        try {
            test.addCamera(c2);
            fail("Exception should have been thrown");
        } catch (NumberUsedException e) {
            //expected
        }
    }

    @Test
    void testRemoveCameraInList() {
        Camera c1 = new Camera(0);
        try {
            test.addCamera(c1);
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals("Removed camera", test.removeCamera("Camera #0")); //TODO: if camera id is decided - update this
        assertFalse(test.getCameraList().contains(c1));
    }

    @Test
    void testRemoveCameraNotInList(){
        Camera c1 = new Camera(0);
        assertEquals("Camera not found in list", test.removeCamera("Camera #0"));
    }

    @Test
    void testRemoveCameraWhenListMore(){
        Camera c1 = new Camera(0);
        Camera c2 = new Camera(1);
        try {
            test.addCamera(c1);
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
        try {
            test.addCamera(c2);
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals("Removed camera", test.removeCamera("Camera #1"));
        assertFalse(test.getCameraList().contains(c2));
        assertTrue(test.getCameraList().contains(c1));
    }

    @Test
    void testAddLight(){
        Light l1 = new Light(0);
        try {
            assertEquals("added new light to entrance", test.addLight(l1));
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
        assertTrue(test.getLightList().contains(l1));
    }

    @Test
    void testAddLightExceptionNotThrown() {
        Light l1 = new Light(0);
        try {
            test.addLight(l1);
            // pass
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testAddLightDuplicateExceptionThrown() {
        Light l1 = new Light(0);
        Light l2 = new Light(0);
        try {
            test.addLight(l1);
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
        try {
            test.addLight(l2);
            fail("Exception should have been thrown");
        } catch (NumberUsedException e) {
            //pass
        }

    }

    @Test
    void testRemoveLightInList() {
        Light l1 = new Light(0);
        try {
            test.addLight(l1);
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals("Removed light", test.removeLight("Light #0")); //TODO: if light id is decided - update this
        assertFalse(test.getLightList().contains(l1));
    }

    @Test
    void testRemoveLightNotInList() {
        Light l1 = new Light(0);
        assertEquals("Light not found in list", test.removeLight("Light #0"));
    }

    @Test
    void testRemoveLightWhenListMore(){
        Light l1 = new Light(0);
        Light l2 = new Light(1);
        try {
            test.addLight(l1);
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
        try {
            test.addLight(l2);
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals("Removed light", test.removeLight("Light #1"));
        assertFalse(test.getLightList().contains(l2));
        assertTrue(test.getLightList().contains(l1));
    }



    @Test
    void testSecurityFeatures(){
        Light l1 = new Light(0);
        Camera c1 = new Camera(0);
        try {
            test.addLight(l1);
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
        try {
            test.addCamera(c1);
        } catch (NumberUsedException e) {
            fail("Exception should not have been thrown");
        }
        List proto = new ArrayList();
        proto.add("Camera #0");
        proto.add("Light #0");
        assertEquals(2, test.securityFeatures().size());
        assertEquals(proto, test.securityFeatures());
    }

    @Test
    void testSecurityFeaturesEmpty(){
        List proto = new ArrayList();
        assertEquals(proto, test.securityFeatures());
        assertEquals(0, test.securityFeatures().size());
    }

    @Test
    void testToString() {
        assertEquals(test.toString(), "entrance");
    }

    @Test
    void testAddCameraJson() {
        Camera c1 = new Camera(0);
        test.addCameraJson(c1);
        assertTrue(test.getCameraList().contains(c1));
    }

    @Test
    void testAddLightJson() {
        Light l1 = new Light(0);
        test.addLightJson(l1);
        assertTrue(test.getLightList().contains(l1));
    }
}
