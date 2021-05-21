package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LightTest {
    private Light test;
    private static final int MAXIMUM_BRIGHTNESS = 5;
    private static final int MINIMUM_BRIGHTNESS = 1;

    @BeforeEach
    void runBefore(){
        test = new Light(0);
    }

    @Test
    void testPowerSwitchOffToOn(){
        test.powerSwitch();
        assertTrue(test.getStatus());
    }

    @Test
    void testPowerSwitchOnToOff(){
        test.powerSwitch();
        test.powerSwitch();
        assertFalse(test.getStatus());
    }

    @Test
    void testChangeLabel(){
        test.changeLabel(1);
        assertEquals("Light #1", test.getLabel());
    }

    @Test
    void testSetBrightnessLevelString(){
        assertEquals("brightness level set to level 5", test.setBrightness(5));
    }

    @Test
    void testSetBrightnessLevelInternal(){
        test.setBrightness(5);
        assertEquals(5, test.getBrightness());
    }

    @Test
    void testIncreaseBrightnessLowerThanMax(){
        test.setBrightness(MAXIMUM_BRIGHTNESS - 1);
        assertEquals("brightness level increased to " + MAXIMUM_BRIGHTNESS, test.increaseBrightness());
        assertEquals(MAXIMUM_BRIGHTNESS, test.getBrightness());
    }

    @Test
    void testIncreaseBrightnessAlreadyAtMax(){
        test.setBrightness(MAXIMUM_BRIGHTNESS);
        assertEquals("brightness level already at maximum", test.increaseBrightness());
        assertEquals(MAXIMUM_BRIGHTNESS, test.getBrightness());
    }

   /* @Test
    void testIncreaseBrightnessLowerThanMaxInternal(){
        test.setBrightness(MAXIMUM_BRIGHTNESS - 1);
        test.increaseBrightness();
        assertEquals(MAXIMUM_BRIGHTNESS, test.getBrightness());
    } */

    @Test
    void testDecreaseBrightnessHigherThanMinString(){
        test.setBrightness(MAXIMUM_BRIGHTNESS);
        assertEquals("brightness level decreased to " + (MAXIMUM_BRIGHTNESS-1), test.decreaseBrightness());
        assertEquals(MAXIMUM_BRIGHTNESS-1, test.getBrightness());
    }

    @Test
    void testDecreaseBrightnessAlreadyAtMin(){
        test.setBrightness(MINIMUM_BRIGHTNESS);
        assertEquals("brightness level already at lowest", test.decreaseBrightness());
        assertEquals(MINIMUM_BRIGHTNESS, test.getBrightness());
    }

    @Test
    void testEqualsTrue() {
        Light l1 = new Light(1);
        Light l2 = new Light(1);
        assertEquals(l1, l2);
    }

    @Test
    void testEqualsFalse() {
        Light l1 = new Light(1);
        Light l2 = new Light(2);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testDifferentClasses() {
        Camera c1 = new Camera(0);
        Light l1 = new Light(0);
        assertFalse(l1.equals(c1));
    }

    @Test
    void testCompareNull() {
        Light l1 = new Light(0);
        assertFalse(l1.equals(null));
    }

    @Test
    void testHashCode() {
        Light l1 = new Light(1);
        Light l2 = new Light(1);
        assertEquals(l1.hashCode(), l2.hashCode());
    }

}
