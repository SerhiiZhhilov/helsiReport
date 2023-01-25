package actions;

import forms.MainForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import utils.FileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@AllArgsConstructor
public class ActionRead implements ActionListener {

    private final MainForm mainForm;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        FileDialog fd = FileReader.openDialog(mainForm);
        if (fd.getFiles().length == 0) return;
        File file = fd.getFiles()[0];
        this.mainForm.getContentModel().assignContent(FileReader.readFile(file));
        this.mainForm.fillTextByModelContent();
        this.mainForm.getContentModel().transformContentToPatients();
        JOptionPane.showMessageDialog(null, "Reading done!");
    }
}
