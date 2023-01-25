package forms;

import actions.ActionRead;
import actions.ActionWrite;
import entities.ContentModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.FileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainForm extends JFrame {
    private JButton buttonReadTxtFile;
    private JTextArea textData;
    private JTextField textSheet;
    private JButton buttonWriteToExcelFile;
    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelRight;

    private final ContentModel contentModel = new ContentModel();

    public MainForm(String title) {
        setTitle(title);
        setContentPane(this.panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,500);
        setVisible(true);
        initListeners();

        buttonReadTxtFile.addActionListener(new ActionRead(this));
        buttonWriteToExcelFile.addActionListener(new ActionWrite(this));
    }

    private void initListeners() {
    }


    public void fillTextByModelContent() {
        if (contentModel.isContentEmpty())
            return;
        textData.setLineWrap(true);
        textData.setWrapStyleWord(true);
        StringBuilder sb = new StringBuilder();
        this.contentModel.getContent().stream().forEach(l -> sb.append(l).append("\n"));
        textData.setText(sb.toString());
    }
}
