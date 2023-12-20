import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
// Declaring properties of TextEditor

    //Declaring jframe
    JFrame frame;

    //Declaring menubar
    JMenuBar menuBar;

    JMenu file , edit;

    //file menu items
    JMenuItem newFile, openFile, saveFile;

    //edit menu items
    JMenuItem cut, copy, paste, selectAll, close;

    //text area
    JTextArea textArea;


    TextEditor(){
        //initialize jframe
        frame= new JFrame();

        //initilize menubar
        menuBar= new JMenuBar();

        //initialize text area
        textArea= new JTextArea();

        //add text area to frame
        frame.add(textArea);

        file = new JMenu("File");
        edit = new JMenu("Edit");

        //initialize file menu items
        newFile= new JMenuItem("New Window");
        openFile= new JMenuItem("Open File");
        saveFile= new JMenuItem("Save File");

        //add to action listener
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);


        //add menu item to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize edit menu items
        cut= new JMenuItem("Cut");
        copy= new JMenuItem("Copy");
        paste= new JMenuItem("Paste");
        selectAll= new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //adding action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);




        //Add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);


        //set MenuBar
        frame.setJMenuBar(menuBar);

        //Create content Pane
        JPanel panel= new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //Add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //Create Scroll Pane
        JScrollPane scrollPane= new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Add Scroll Pane to panel
        panel.add(scrollPane);

        //Add panel to frame
        frame.add(panel);

        //set the dimention of frame
        frame.setBounds(100,100,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    public void actionPerformed(ActionEvent actionEvent){

        if(actionEvent.getSource()==cut){
            //Perform the cut operation
            textArea.cut();
        } else if (actionEvent.getSource() == copy){
            //Perform the copy operation
            textArea.copy();
        } else if (actionEvent.getSource()==paste) {
            //Perform the paste operation
            textArea.paste();
        } else if (actionEvent.getSource()==selectAll) {
            //Perform the selectAll operation
            textArea.selectAll();
        } else if (actionEvent.getSource()==close) {
            //Perform the close operation
            System.exit(0);
        } else if (actionEvent.getSource()==openFile) {
            //Open a File Chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption= fileChooser.showOpenDialog(null);

            //if we have to click on open button
            if (chooseOption==JFileChooser.APPROVE_OPTION){
                //getting selected file
                File file = fileChooser.getSelectedFile();

                //get the path of selected file
                String filePath= file.getPath();
                try{
                    //initialize file reader
                    FileReader fileReader= new FileReader(filePath);

                    //initialized buffer reader
                    BufferedReader bufferedReader= new BufferedReader(fileReader);
                    String intermediate="", output="";
                    //Read content of file line by line
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //Set the output String to text Area
                    textArea.setText(output);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        else if(actionEvent.getSource()==saveFile){

            //initialize the file picker
            JFileChooser fileChooser= new JFileChooser("C:");

            //get choose option from file chooser
            int chooseOption= fileChooser.showSaveDialog(null);

            //check is we click on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //initialize file writer
                    FileWriter fileWriter= new FileWriter(file);

                    //initialize buffered writer
                    BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);

                    //write content of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();

                }
                 catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        }
        //Create new window
        else if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor= new TextEditor();
        }

    }
    public static void main(String[] args) {

        //System.out.println("Hello world!");
        TextEditor t= new TextEditor();

    }
}