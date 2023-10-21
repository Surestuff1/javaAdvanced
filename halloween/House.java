package halloween;

import java.util.ArrayList;
import java.util.List;

public class House {
    private int capacity;
    private List<Kid> data;

    public House(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }
    public void addKid(Kid kid) {
        if (data.size() < capacity) {
            data.add(kid);
        }
    }
    public boolean removeKid(String name) {
        Kid kid = this.data.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst()
                .orElse(null);
        return this.data.remove(kid);
    }
    public Kid getKid(String street){
        return data.stream().filter(e -> e.getStreet().equals(street)).findFirst().orElse(null);
    }
    public int getAllKids(){
        return data.size();
    }

    public String getStatistics(){

        StringBuilder sb = new StringBuilder();
        sb.append("Children who visited a house for candy:");

        this.data.forEach(kid -> {
            sb.append(System.lineSeparator());
            sb.append(String.format("%s from %s street", kid.getName(), kid.getStreet()));
        });

        return sb.toString();
    }
}
