package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//represents a list of sections that are to be monitored
public class MonitoringList implements Writable {
    private List<Section> sections; //list of all sections being monitored

    //EFFECTS: creates a new empty list of sections that are being monitored
    public MonitoringList() {
        sections = new ArrayList<>();
    }

    //REQUIRES: section has not already been added
    //MODIFIES: this
    //EFFECTS: adds a new section to the list
    public String addSection(Section s) {
        sections.add(s);
        return "Section has been added";
    }

    //MODIFIES: this
    //EFFECTS: removes a section from the list
    public String removeSection(Section s) {
        if (sections.contains(s)) {
            sections.remove(s);
            return "Successfully removed";
        } else {
            return "Section is not within list";
        }
    }

    // EFFECTS: returns a list of all sections
    public List listSections() {
        List guarded = new ArrayList();
        for (Section s : sections) {
            guarded.add(s.getSectionName());
        }
        return guarded;
    }


    //EFFECTS: returns true if s is in list; false ow
    public Boolean containsSection(Section s) {
        return sections.contains(s);
    }

    //EFFECTS: returns the size of MonitoringList
    public int getLength() {
        return sections.size();
    }

    //EFFECTS: returns the section at the given index in the list
    public Section getSectionAtPosition(int position) {
        return sections.get(position);
    }

    //EFFECTS: returns list of all sections under surveillance
    public List getSection() {
        return sections;
    }

    //EFFECTS: takes monitoringlist and returns it as a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("sections", sectionsToJson());
        return json;
    }


    //EFFECTS: returns sections in this monitoringList as a JSON array
    private JSONArray sectionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Section s : sections) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }

}
