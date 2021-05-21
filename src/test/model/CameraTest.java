package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CameraTest {
    private Camera test;

    @BeforeEach
    void runBefore() {
        test = new Camera(0);
    }

    @Test
    void testChangeName() {
        test.changeName(1);
        assertEquals("Camera #1", test.getIdentification());
    }

    @Test
    void testPowerOn() {
       test.powerOn();
       assertTrue(test.getStatus());
    }

    @Test
    void testPowerOff() {
        test.powerOn();
        test.powerOff();
        assertFalse(test.getStatus());
    }

    @Test
    void testRotateRightFromCenter() {
        test.rotateRight();
        assertEquals("right", test.getPosition());
    }

    @Test
    void testRotateRightFromLeft() {
        test.rotateLeft();
        test.rotateRight();
        assertEquals("center", test.getPosition());
    }

    @Test
    void testRotateLeftFromCenter() {
        test.rotateLeft();
        assertEquals("left", test.getPosition());
    }

    @Test
    void testRotateLeftFromRight() {
        test.rotateRight();
        test.rotateLeft();
        assertEquals("center", test.getPosition());
    }

    @Test
    void testEqualsShouldBeEqual() {
        Camera c1 = new Camera(0);
        Camera c2 = new Camera(0);
        assertEquals(c1, c2);
    }

    @Test
    void testEqualsNotEqual() {
        Camera c1 = new Camera(0);
        Camera c2 = new Camera(1);
        assertFalse(c1.equals(c2));
    }

    @Test
    void testDifferentClasses() {
        Camera c1 = new Camera(0);
        Light l1 = new Light(0);
        assertFalse(c1.equals(l1));
    }

    @Test
    void testCompareNull() {
        Camera c1 = new Camera(0);
        assertFalse(c1.equals(null));
    }

    @Test
    void testHashCode() {
        Camera c1 = new Camera(0);
        Camera c2 = new Camera(0);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

}