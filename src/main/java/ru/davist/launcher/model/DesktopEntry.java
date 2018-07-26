package ru.davist.launcher.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DesktopEntry that = (DesktopEntry) o;
        return Objects.equals(path, that.path) &&
                Objects.equals(name, that.name) &&
                Objects.equals(exec, that.exec);
    }

    @Override
    public int hashCode() {

        return Objects.hash(path, name, exec);
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
