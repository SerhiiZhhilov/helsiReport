package utils;

import com.sun.xml.internal.ws.util.StringUtils;
import forms.MainForm;
import org.apache.commons.collections4.CollectionUtils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
    private static final String workingPath = System.getProperty("user.dir");

    public static FileDialog openDialog(MainForm mainForm) {
        FileDialog fd = new FileDialog(mainForm, "Choose a file", FileDialog.LOAD);
        fd.setDirectory(workingPath);
        fd.setFile("*.txt");
        fd.setVisible(true);
        return fd;
    }

    public static FileDialog saveDialog(MainForm mainForm) {
        FileDialog fd = new FileDialog(mainForm, "Choose a file", FileDialog.SAVE);
        fd.setDirectory(workingPath);
        fd.setFile("All_Patients.xlsx");
        fd.setVisible(true);
        return fd;
    }

    public static List<String> readFile(File file) {
        if (file == null || file.getAbsolutePath().isEmpty())
            return Collections.emptyList();

        return readFile(file.getAbsolutePath());
    }

    private static List<String> readFile(String fileName) {
        List<String> content;
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            content = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            content = Collections.emptyList();
        }
        return content;
    }


}
