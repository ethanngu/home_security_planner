//package ui;
//
//import jdk.nashorn.internal.ir.debug.JSONWriter;
//import model.Camera;
//import model.Light;
//import model.MonitoringList;
//import model.Section;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Scanner;
//
////STRUCTURE OF CODE IS BASED OFF ACCOUNTNOTROBUST REPO GIVEN TO US
//
//// Security Console application
//public class SecurityConsole {
//    private MonitoringList monitor;
//    private Section outside;
//    private Section inside;
//    private Scanner input;
//
//    private static final String JSON_STORE = "./data/monitoringlist.json";

//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//    //EFFECTS: runs the security console
//    public SecurityConsole() throws FileNotFoundException {
//        input = new Scanner(System.in);
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        runConsole();
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes use input
//    private void runConsole() {
//        boolean keepRunning = true;
//        String command = null;
//
//        initialize();
//
//        while (keepRunning) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("p")) {
//                keepRunning = false;
//            } else {
//                processCommand(command);
//            }
//        }
//        System.out.println("\nyou have logged out");
//    }
//
//    //MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("m")) {
//            doMonitoringList();
//        } else if (command.equals("s")) {
//            doCreateSection();
//        } else if (command.equals("c")) {
//            doAddCamera();
//        } else if (command.equals("l")) {
//            doAddLight();
//        } else if (command.equals("v")) {
//            viewSectionFeatures();
//        } else if (command.equals("r")) {
//            doRemoveCamera();
//        } else if (command.equals("a")) {
//            saveMonitoringList();
//        } else if (command.equals("b")) {
//            loadMonitoringList();
//        } else {
//            System.out.println("Invalid command, please try again");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes a section into the list
//    private void initialize() {
//        monitor = new MonitoringList();
//        outside = new Section("entrance");
//        inside = new Section("living room");
//        monitor.addSection(outside);
//        monitor.addSection(inside);
//        input = new Scanner(System.in);
//    }
//
//    //EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tm -> monitoring list");
//        System.out.println("\ts -> create new section");
//        System.out.println("\tv -> view section features");
//        System.out.println("\tc -> add camera");
//        System.out.println("\tr -> remove camera");
//        System.out.println("\tl -> add light");
//        System.out.println("\ta -> save monitoring list");
//        System.out.println("\tb -> load monitoring list");
//        System.out.println("\tp -> log off");
//    }
//
//    //EFFECTS: prints out a list of all monitored sections
//    private void doMonitoringList() {
//        System.out.println(monitor.listSections());
//
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates a new section and adds it to the monitoring list
//    private void doCreateSection() {
//        System.out.print("Enter name of new section");
//        String name = input.next();
//        Section area = new Section(name);
//        System.out.println(monitor.addSection(area));
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates a new camera and adds it to a section
//    private void doAddCamera() {
//        Section selected = selectSection();
//        System.out.println(("Enter id of camera"));
//        int id = input.nextInt();
//        Camera unit = new Camera(id);
//        //for (Section s : monitor.getSections()){
//        //}
//        System.out.println(selected.addCamera(unit));
//    }
//
//    //MODIFIES: this
//    //EFFECTS: removes a camera from a section if it is there
//    private void doRemoveCamera() {
//        Section selected = selectSection();
//        System.out.println(("Enter id of camera (eg: Camera #0)"));
//        input.nextLine();
//        String id = input.nextLine();
//        System.out.println(selected.removeCamera(id));
//    }
//
//
//    //MODIFIES: this
//    //EFFECTS: creates a new light and adds it to a section
//    private void doAddLight() {
//        Section selected = selectSection();
//        System.out.println("Enter id of light");
//        int id = input.nextInt();
//        Light unit = new Light(id);
//        System.out.println(selected.addLight(unit));
//    }
//
//    //EFFECTS: returns a list of security features within that selected section
//    private void viewSectionFeatures() {
//        Section selected = selectSection();
//        System.out.println(selected.securityFeatures());
//    }
//
//    this method is new (10/17/20)
//    //EFFECTS: generates a list of different sections one can select from
//    private void display() {
//        for (int i = 0; i < monitor.getLength(); i++) {
//            System.out.println(i + " -> " + monitor.getSectionAtPosition(i).getSectionName());
//        }
//    }
//
//    //this is new version of the method (10/17/20)
//    //EFFECTS: allows the user to select which section they would like to add/remove features to
//    private Section selectSection() {
//        int selection = 1000000000; // forces entry into loop
//        display();
//        selection = input.nextInt();
//        return monitor.getSectionAtPosition(selection);
//    }

//
//    //EFFECTS: saves the MonitoringList to file
//    private void saveMonitoringList() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(monitor);
//            jsonWriter.close();
//            System.out.println("Saved Monitoring List");
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file");
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: loads MonitoringList from file
//    private void loadMonitoringList() {
//        try {
//            monitor = jsonReader.read();
//            System.out.println("Loaded Monitoring List from saved file");
//        } catch (IOException e) {
//            System.out.println("Unable to read from file ");
//        }
//    }
//
//
//}
