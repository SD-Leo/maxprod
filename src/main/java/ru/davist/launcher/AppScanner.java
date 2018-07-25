package ru.davist.launcher;

import ru.davist.launcher.model.DesktopEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;

public class AppScanner {

    List<String> locations;

    List<DesktopEntry> entries = new ArrayList<>();

    public AppScanner() {
        locations = new ArrayList<>();

        locations.add("/home/" + System.getProperty("user.name") + "/.local/share/applications");
        locations.add("/home/" + System.getProperty("user.name") + "/.local/share/flatpak/exports/share/application");
        locations.add("/var/lib/flatpak/exports/share/application");
        locations.add("/usr/local/share/applications");
        locations.add("/usr/share/applications");
    }


    public void scan() {

        List<File> files = findFiles();

        System.out.println(files.size() + " desktop files are found");


        try {
            for (File file : files) {
                DesktopEntry entry = new DesktopEntry();
                entry.setPath(file.getAbsolutePath());
//                System.out.println(entry.getPath());
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (line != null && !line.isEmpty()) {
                        if (line.startsWith("Name")) {
                            entry.setName(line.split("=")[1]);
                        }
                        if (line.startsWith("Exec")) {
                            entry.setExec(line.split("=")[1]);
                        }
                    }
                    if (entry.getName() != null && entry.getExec() != null) {
                        break;
                    }
                }
                if (entry.getExec() != null) {
                    entries.add(entry);
                } else {
                    System.out.println("Warn! No exec: " + file.getAbsolutePath());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


//        for (DesktopEntry entry : entries) {
//            System.out.println(entry.getName());
//        }

    }

    public List<DesktopEntry> find(String query) {
        if (query == null || query.isEmpty()) {
            return Collections.emptyList();
        }
        List<DesktopEntry> found = new ArrayList<>();
        for (DesktopEntry entry : entries) {
            if (entry.getName().toLowerCase().contains(query.toLowerCase())) {
                found.add(entry);
            }
        }

        return found;
    }

    private List<File> findFiles() {
        DesktopFilenameFilter filter = new DesktopFilenameFilter();
        List<File> files = new ArrayList<>();
        for (String location : locations) {
            File dir = new File(location);
            String[] list = dir.list(filter);
            if (list != null) {
                for (String desktopFile : list) {
                    String filePath = location + "/" + desktopFile;
                    files.add(new File(filePath));
                }
            } else {
                System.out.println("Nothing found in: " + dir.getAbsolutePath());
            }
        }
        return files;
    }

    private class DesktopFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".desktop");
        }
    }


    public static void main(String[] args) throws IOException {
        String command = "/opt/Enpass/bin/runenpass.sh %U";
        List<String> commAndArgs = Arrays.asList(command.split(" "));
        ProcessBuilder pb = new ProcessBuilder(commAndArgs);
        Process p = pb.start();


        System.out.println("Finished!!!: " + p.exitValue());

//        AppScanner scanner = new AppScanner();
//        scanner.scan();
//        for (DesktopEntry entry : scanner.find("enpass")) {
//            System.out.println(entry);
//        }
//        for (String location : new AppScanner().locations) {
//            System.out.println(location);
//        }
//        System.getProperties().list(System.out);
//        System.out.println(System.getProperty("user.name"));
    }
}
