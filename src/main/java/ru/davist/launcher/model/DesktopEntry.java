package ru.davist.launcher.model;

public class DesktopEntry {

    private String path;

    private String name;

    private String exec;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExec() {
        return exec;
    }

    public void setExec(String exec) {
        this.exec = exec;
    }

    @Override
    public String toString() {
        return "DesktopEntry{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", exec='" + exec + '\'' +
                '}';
    }
}
