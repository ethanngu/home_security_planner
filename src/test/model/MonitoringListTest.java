package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MonitoringListTest {
    private MonitoringList test;

    @BeforeEach
    void runBefore(){
        test = new MonitoringList();
    }

    @Test
    void testAddSection(){
        Section first = new Section("entrance");
        assertEquals("Section has been added",test.addSection(first));
        assertEquals(1, test.getLength());
        assertTrue(test.containsSection(first));
    }


    @Test
    void testRemoveSectionPresentInList(){
        Section first = new Section("entrance");
        test.addSection(first);
        assertEquals("Successfully removed",test.removeSection(first));
        assertEquals(0, test.getLength());
        assertFalse(test.containsSection(first));
    }

    @Test
    void testRemoveSectionWhenDifferentInList(){
        Section first = new Section("entrance");
        Section second = new Section("exit");
        test.addSection(first);
        test.addSection(second);
        assertEquals("Successfully removed",test.removeSection(first));
        assertEquals(1, test.getLength());
        assertFalse(test.containsSection(first));
        assertTrue(test.containsSection(second));
    }

    @Test
    void testRemoveSectionNotPresentInList(){
        Section first = new Section("entrance");
        assertEquals("Section is not within list", test.removeSection(first));
        assertEquals(0, test.getLength());
        assertFalse(test.containsSection(first));
    }

    @Test
    void testContainsSectionTrue(){
        Section first = new Section("entrance");
        test.addSection(first);
        assertTrue(test.containsSection(first));
    }

    @Test
    void testContainsSectionFalse(){
        Section first = new Section("entrance");
        assertFalse(test.containsSection(first));
    }

    @Test
    void testListSectionsOne(){
        List entrance = new ArrayList();
        entrance.add("entrance");
        Section first = new Section("entrance");
        test.addSection(first);
        assertEquals(entrance, test.listSections());
    }

    @Test
    void testListSectionsEmpty(){
        List empty = new ArrayList();
        assertEquals(empty, test.listSections());
    }

    @Test
    void testListSectionsTwo(){
        List doors = new ArrayList();
        doors.add("entrance");
        doors.add("exit");
        Section first = new Section("entrance");
        Section second = new Section ("exit");
        test.addSection(first);
        test.addSection(second);
        assertEquals(doors, test.listSections());
    }

    @Test
    void testGetSectionAtPosition(){
        Section first = new Section("entrance");
        test.addSection(first);
        assertEquals(first, test.getSectionAtPosition(0));
        assertEquals(1, test.getLength());
    }

    @Test
    void testGetSectionEmpty(){
        assertEquals(0, test.getSection().size());
    }

    @Test
    void testGetSectionNotEmpty() {
        Section first = new Section("entrance");
        test.addSection(first);
        assertEquals(1, test.getSection().size());
    }





}
