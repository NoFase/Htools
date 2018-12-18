package GUI.controllers;

public class ServerInner{
    private String abr;
    private String name;
    private String ip;

    public String getAbr() {
        return abr;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public ServerInner(String abr, String name, String ip) {
        this.abr = abr;
        this.name = name;
        this.ip = ip;
    }
}
