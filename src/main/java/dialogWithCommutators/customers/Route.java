package dialogWithCommutators.customers;

import java.util.ArrayList;

public class Route {
    private String route;
    private ArrayList<SRT> srts;

    public String getRoute() {
        return route;
    }

    public ArrayList<SRT> getSrts() {
        return srts;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setSrts(ArrayList<SRT> srts) {
        this.srts = srts;
    }
}
