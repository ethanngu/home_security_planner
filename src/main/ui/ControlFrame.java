package ui;

import exception.NumberUsedException;
import model.Camera;
import model.Light;
import model.MonitoringList;
import model.Section;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.*;

//GUI for system
public class ControlFrame extends JFrame implements ActionListener {
    //Frame
    JFrame frame;

    //Panels
    JPanel monitoringListPanel;
    JPanel sectionFeaturesPanel;
    JPanel featuresPanel;
    JPanel cameraPanel;
    JPanel lightPanel;
    JPanel savePanel;
    JPanel loadPanel;
    JPanel addSectionPanel;




    //Buttons
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    JButton b5;
    JButton b6;
    JButton b7;

    //TextFields
    JTextField cameraText;
    JTextField lightText;
    JTextField addSectionText;


    //For display panel
    JTextArea featuresLabel;

    //ComboBoxes
    JComboBox<Section> cameraDropDown;
    JComboBox<Section> sectionsDropDown;
    JComboBox<Section> lightsDropDown;



    private MonitoringList monitor;
    private static final String JSON_STORE = "./data/monitoringlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public ControlFrame() throws FileNotFoundException {
        frame = new JFrame();

        //Initializing monitoringList
        monitor = new MonitoringList();


        //Save and Load
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        //MonitoringListPanel
        initMonitoringListPanel();

        //ViewSectionFeaturesPanel
        initSectionFeaturesPanel();

        // Features Panel
        initDisplayFeaturesPanel();

        //CameraPanel
        initCameraPanel();

        //LightPanel
        initLightPanel();

        //SavePanel
        initSavePanel();

        //LoadPanel
        initLoadPanel();

        //AddSectionPanel
        initAddSectionPanel();

        //initializes this frame
        initFrame();

    }

    private void initFrame() {
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(1000, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Control Center");
        frame.add(monitoringListPanel);
        frame.add(sectionFeaturesPanel);
        frame.add(cameraPanel);
        frame.add(lightPanel);
        frame.add(savePanel);
        frame.add(loadPanel);
        frame.add(featuresPanel);
        frame.add(addSectionPanel);
    }

    //EFFECTS: initializes add section panel with buttons, textfield and label
    private void initAddSectionPanel() {
        addSectionPanel = new JPanel();
        addSectionPanel.setBackground(Color.GREEN);
        addSectionPanel.setBounds(300, 700, 400, 75);

        JLabel addSectionLabel = new JLabel();
        addSectionLabel.setText("Add Section");
        addSectionLabel.setHorizontalTextPosition(JLabel.CENTER);
        addSectionLabel.setVerticalTextPosition(JLabel.BOTTOM);

        addSectionPanel.add(addSectionLabel);


        //AddSection TextField
        addSectionText = new JTextField();
        addSectionText.setPreferredSize(new Dimension(60, 30));

        addSectionPanel.add(addSectionText);


        //AddSection Button
        b7 = new JButton("Submit");
        b7.setPreferredSize(new Dimension(50, 20));
        b7.addActionListener(this);

        addSectionPanel.add(b7);
    }

    //EFFECTS: initializes load panel with button and label
    private void initLoadPanel() {
        loadPanel = new JPanel();
        loadPanel.setBackground(Color.PINK);
        loadPanel.setBounds(300, 625, 400, 75);

        JLabel loadLabel = new JLabel();
        loadLabel.setText("Load Previously Saved Monitoring List");
        loadLabel.setHorizontalTextPosition(JLabel.CENTER);
        loadLabel.setVerticalTextPosition(JLabel.BOTTOM);

        loadPanel.add(loadLabel);

        //LoadPanel Button
        b6 = new JButton();
        b6.setPreferredSize(new Dimension(50, 20));
        b6.addActionListener(this);

        loadPanel.add(b6);
    }

    //MODIFIES: this
    //EFFECTS: initializes save panel with button and label
    private void initSavePanel() {
        savePanel = new JPanel();
        savePanel.setBackground(Color.GRAY);
        savePanel.setBounds(300, 550, 400, 75);

        JLabel saveLabel = new JLabel();
        saveLabel.setText("Save Current Monitoring List");
        saveLabel.setHorizontalTextPosition(JLabel.CENTER);
        saveLabel.setVerticalTextPosition(JLabel.BOTTOM);

        savePanel.add(saveLabel);


        //SavePanel Button
        b5 = new JButton();
        b5.setPreferredSize(new Dimension(50, 20));
        b5.addActionListener(this);

        savePanel.add(b5);
    }

    //EFFECTS: initializes light panel with button, dropdown bar, text field, and label
    private void initLightPanel() {
        lightPanel = new JPanel();
        lightPanel.setBackground(Color.orange);
        lightPanel.setBounds(300, 350, 400, 100);

        JLabel lightLabel = new JLabel();
        lightLabel.setText("Lights");
        lightLabel.setHorizontalTextPosition(JLabel.CENTER);
        lightLabel.setVerticalTextPosition(JLabel.BOTTOM);

        lightPanel.add(lightLabel);


        //LightPanel text field
        lightText = new JTextField();
        lightText.setPreferredSize(new Dimension(40, 30));

        lightPanel.add(lightText);


        //LightPanel Button
        b4 = new JButton("Submit");
        b4.setPreferredSize(new Dimension(50, 20));
        b4.addActionListener(this);

        lightPanel.add(b4);


        //LightPanel DropDown
        lightsDropDown = new JComboBox(monitor.getSection().toArray());

        lightPanel.add(lightsDropDown);
    }

    //MODIFIES: this
    //EFFECTS: initializes camera panel with button, textfield, labels, and dropdown bar
    private void initCameraPanel() {
        cameraPanel = new JPanel();
        cameraPanel.setBackground(Color.GRAY);
        cameraPanel.setBounds(300, 250, 400, 100);

        JLabel cameraLabel = new JLabel();
        cameraLabel.setText("Cameras");
        cameraLabel.setHorizontalTextPosition(JLabel.CENTER);
        cameraLabel.setVerticalTextPosition(JLabel.BOTTOM);

        cameraPanel.add(cameraLabel);

        //CameraPanel TextField
        cameraText = new JTextField();
        cameraText.setPreferredSize(new Dimension(40, 30));

        cameraPanel.add(cameraText);


        //CameraPanel Button
        b3 = new JButton("Submit");
        b3.setPreferredSize(new Dimension(50, 20));
        b3.addActionListener(this);

        cameraPanel.add(b3);


        //CameraPanel ComboBox
        cameraDropDown = new JComboBox(monitor.getSection().toArray());

        cameraPanel.add(cameraDropDown);
    }

    //MODIFIES: this
    //EFFECTS: initializes panel to display the features in selected section
    private void initDisplayFeaturesPanel() {
        featuresPanel = new JPanel();
        featuresPanel.setBackground(Color.lightGray);
        featuresPanel.setBounds(0, 0, 275, 800);
        featuresPanel.setLayout(new BoxLayout(featuresPanel, BoxLayout.Y_AXIS));

        //Features Panel text
        featuresLabel = new JTextArea();
        featuresLabel.setEditable(false);
        featuresLabel.setLineWrap(true);
        featuresLabel.setBackground(Color.lightGray);
        featuresPanel.add(featuresLabel);
    }


    //MODIFIES: this
    //EFFECTS: initializes ViewSectionFeatures panel with its texts, buttons, and dropdown bar
    private void initSectionFeaturesPanel() {
        sectionFeaturesPanel = new JPanel();
        sectionFeaturesPanel.setBackground(Color.lightGray);
        sectionFeaturesPanel.setBounds(300, 150, 400, 100);
//        sectionFeaturesPanel.setAlignmentY(1.0f);

        JLabel sectionLabel = new JLabel();
        sectionLabel.setText("View Section Features");
        sectionLabel.setHorizontalTextPosition(JLabel.CENTER);
        sectionLabel.setVerticalTextPosition(JLabel.BOTTOM);

        sectionFeaturesPanel.add(sectionLabel);

        //ViewSectionFeatures Button
        b2 = new JButton();
        b2.setPreferredSize(new Dimension(100, 20));
        b2.addActionListener(this);
        b2.setText("Clear list");

        sectionFeaturesPanel.add(b2);

        //ViewSectionFeatures ComboBox
        sectionsDropDown = new JComboBox(monitor.getSection().toArray());
        sectionsDropDown.addActionListener(this);
        sectionFeaturesPanel.add(sectionsDropDown);
    }

    //MODIFIES: this
    //EFFECTS: initializes MonitoringListPanel with its texts and buttons
    private void initMonitoringListPanel() {
        monitoringListPanel = new JPanel();
        monitoringListPanel.setBackground(Color.cyan);
        monitoringListPanel.setBounds(300, 50, 400, 100);


        JLabel monitorLabel = new JLabel();
        monitorLabel.setText("View Monitoring List");
        monitorLabel.setHorizontalTextPosition(JLabel.CENTER);
        monitorLabel.setVerticalTextPosition(JLabel.BOTTOM);

        monitoringListPanel.add(monitorLabel);

        //MonitoringListPanel's Button
        b1 = new JButton("Open");
        //b1.setBounds(300, 50, 50,25);
        b1.setPreferredSize(new Dimension(50, 20));
        b1.addActionListener(this);



        monitoringListPanel.add(b1);
    }


    //EFFECTS: performs action depending on the button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) { //b1 = monitoringList display button
            trySound();
            MonitoringListDisplay window = new MonitoringListDisplay(monitor);
        } else if (e.getSource() == b2) { //b2 = view section features button
            featuresLabel.setText("");
        } else if (e.getSource() == b3) { //b3 = add camera button
            trySound();
            doAddCameraButton();

        } else if (e.getSource() == b4) { //b4 = add light button
            trySound();
            doAddLightButton();

        } else if (e.getSource() == b5) { //b5 = save
            trySound();
            saveMonitoringList();
        } else if (e.getSource() == b6) { //b6 = load
            trySound();
            loadMonitoringList();
        } else if (e.getSource() == sectionsDropDown) {
            doViewSectionsBar();
        } else if (e.getSource() == b7) {
//            System.out.println("testing");
            trySound();
            doAddSectionButton();
        }

    }

    //EFFECTS: creates a new section with given text input as name
    private void doAddSectionButton() {
        String givenID = addSectionText.getText();
        doAddSection(givenID);
    }

    //EFFECTS: takes given section and output a list of security features in that section
    private void doViewSectionsBar() {
        Section viewFeatures = (Section) sectionsDropDown.getSelectedItem();
        String convertToString = String.join(", ", viewFeatures.securityFeatures());
        featuresLabel.setText(convertToString);
    }

    //EFFECTS: attempts to call the playSound method, if fails catches the the following exceptions
    private void trySound() {
        try {
            playSound();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        } catch (LineUnavailableException lineUnavailableException) {
            lineUnavailableException.printStackTrace();
        }
    }

    //EFFECTS: takes given text input and selected section and passes it to doAddLight
    private void doAddLightButton() {
        String inputLightID = lightText.getText();
        int lightID = Integer.parseInt(inputLightID);
        Section getInputtedSectionLight = (Section) lightsDropDown.getSelectedItem();
        String inputSectionNameLight = getInputtedSectionLight.getSectionName();
        doAddLight(lightID, inputSectionNameLight);
    }

    //EFFECTS: takes given text input and selected section and passes it to doAddCamera
    private void doAddCameraButton() {
        String inputCameraID = cameraText.getText();
        int cameraID = Integer.parseInt(inputCameraID);
        Section getInputtedSection = (Section) cameraDropDown.getSelectedItem();
        String inputSectionName = getInputtedSection.getSectionName();
        doAddCamera(cameraID, inputSectionName);
    }

    //MODIFIES: this
    //EFFECTS: creates a new section with given name and adds it to the monitoring list
    private void doAddSection(String name) {
        Section createdSection = new Section(name);
        monitor.addSection(createdSection);
        sectionsDropDown.setModel(new DefaultComboBoxModel(monitor.getSection().toArray()));
        lightsDropDown.setModel(new DefaultComboBoxModel(monitor.getSection().toArray()));
        cameraDropDown.setModel(new DefaultComboBoxModel(monitor.getSection().toArray()));
    }


    //MODIFIES: this
    //EFFECTS: creates a camera and adds it to selected section
    private void doAddCamera(int id, String sectionName) {
        Camera unit = new Camera(id);
        for (int i = 0; i < monitor.getLength(); i++) {
            if (sectionName == monitor.getSectionAtPosition(i).getSectionName()) {
                try {
                    monitor.getSectionAtPosition(i).addCamera(unit);
                } catch (NumberUsedException e) {
                    JOptionPane.showMessageDialog(null,"Camera with this ID already exists",
                            "Camera Not Added", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

    }

    //MODIFIES: this
    //EFFECTS: creates a new light and adds it to selected section
    private void doAddLight(int id, String sectionName) {
        Light unit = new Light(id);
        for (int i = 0; i < monitor.getLength(); i++) {
            if (sectionName == monitor.getSectionAtPosition(i).getSectionName()) {
                try {
                    monitor.getSectionAtPosition(i).addLight(unit);
                } catch (NumberUsedException e) {
                    JOptionPane.showMessageDialog(null,"Light with this ID already exists",
                            "Light Not Added", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }



    //EFFECTS: saves the MonitoringList to file
    private void saveMonitoringList() {
        try {
            jsonWriter.open();
            jsonWriter.write(monitor);
            jsonWriter.close();
            sectionsDropDown.setModel(new DefaultComboBoxModel(monitor.getSection().toArray())); //TODO: new
            lightsDropDown.setModel(new DefaultComboBoxModel(monitor.getSection().toArray()));
            cameraDropDown.setModel(new DefaultComboBoxModel(monitor.getSection().toArray()));
            System.out.println("Saved Monitoring List");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }
    }

    //MODIFIES: this
    //EFFECTS: loads MonitoringList from file
    private void loadMonitoringList() {
        try {
            monitor = jsonReader.read();
            System.out.println("Loaded Monitoring List from saved file");
            sectionsDropDown.setModel(new DefaultComboBoxModel(monitor.getSection().toArray())); //TODO: new
            lightsDropDown.setModel(new DefaultComboBoxModel(monitor.getSection().toArray()));
            cameraDropDown.setModel(new DefaultComboBoxModel(monitor.getSection().toArray()));
        } catch (IOException e) {
            System.out.println("Unable to read from file ");
        }
    }

    //EFFECTS: plays sound effect when button is pressed, throws IOException, UnsupportedAudioFileException,
    // LineUnavailableException
    private void playSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File sound = new File("./data/BeepShort.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(sound);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }




}
