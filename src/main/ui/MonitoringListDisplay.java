package ui;

import model.MonitoringList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// display window for list of sections
public class MonitoringListDisplay {
    JFrame window;
    JPanel panel;

    //Creates a new window to display sections that have had features added to them
    MonitoringListDisplay(MonitoringList monitor) {
        initializeWindow();

        initializePanel();

        // Takes sections within monitoringList and converts it to JList of Strings of the name
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Object s : monitor.listSections()) {
            listModel.addElement(s.toString());
        }
        JList<String> viewList = new JList<String>(listModel);
        panel.add(viewList);


    }

    //MODIFIES: this
    //EFFECTS: initializes panel in which names of sections will be displayed
    private void initializePanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setSize(800, 700);
        window.add(panel);
    }

    //MODIFIES: this
    //EFFECTS: initializes window
    private void initializeWindow() {
        window = new JFrame();
        window.setVisible(true);
        window.setLayout(null);
        window.setSize(800, 700);
        window.setTitle("View Monitoring List");

    }




}
