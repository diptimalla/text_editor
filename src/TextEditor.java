import javax.annotation.processing.FilerException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //declaraing properties of texteditor
    JFrame frame;

    JMenuBar menuBar;
    JMenu file, edit;
    //file menu items
    JMenuItem newFile, openFile, saveFile;
    //edit menu items
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;


    TextEditor(){
        //initialize frame
        frame = new JFrame();

        //initialize textArea
        textArea =new JTextArea();

        //Initialize menubar
        menuBar =new JMenuBar();


        //initialize menus
        file = new JMenu("File");
        edit=new JMenu("Edit");


        //initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save file");

        //adding actionlistener
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize edit menu items
        cut= new JMenuItem("Cut");
        copy= new JMenuItem("Copy");
        paste= new JMenuItem("Paste");
        selectAll= new JMenuItem("Select All");
        close= new JMenuItem("Close");

        //adding action listener
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);



        //add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);

        //SET menubar to frame
        frame.setJMenuBar(menuBar);

        //create content panel
        JPanel panel =new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //ADD SCROLL pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);

        //set dimensions of frame
        frame.setBounds(0,0,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == cut) {
            //perform cut operation
            textArea.cut();

        }
        if (actionEvent.getSource() == copy) {
            //perform copy operation
            textArea.copy();

        }
        if (actionEvent.getSource() == paste) {
            //perform paste operation
            textArea.paste();

        }
        if (actionEvent.getSource() == selectAll) {
            //perform selectAll operation
            textArea.selectAll();

        }
        if (actionEvent.getSource() == close) {
            //perform close operation
            System.exit(0);

        }
        if (actionEvent.getSource() == openFile) {
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on an open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //getting file selected
                File file = fileChooser.getSelectedFile();
                //get the path of the selected file
                String filePath = file.getPath();
                try {
                    //initiate file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //read contents of file line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);

                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if (actionEvent.getSource() == saveFile) {
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //if we have clicked on an save button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //create a new file with chosen directory path and filename
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialize file writer
                    FileWriter fileWriter =new FileWriter(file);
                    //initialize buffered writer
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    //write context of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if (actionEvent.getSource() == newFile) {
            TextEditor newTextEditor =new TextEditor();
        }

    }


    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}