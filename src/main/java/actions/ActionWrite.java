package actions;

import forms.MainForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import utils.ExcelWriter;
import utils.FileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@Data
@AllArgsConstructor
public class ActionWrite implements ActionListener {

    private final MainForm mainForm;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        FileDialog fd = FileReader.saveDialog(mainForm);
        if (fd.getFiles().length == 0)
            return;
        File file = fd.getFiles()[0];
        ExcelWriter.createXLSXFile(file.getAbsolutePath(), mainForm.getTextSheet().getText(), mainForm.getContentModel());
        JOptionPane.showMessageDialog(null, "Writing done!");
    }
}
