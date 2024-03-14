import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class MainFrame extends Frame
{
    @Serial
    private static final long serialVersionUID = 4962955995889938882L;
    public Label status = new Label("Waiting for user input...");
    public Addresses addresses = new Addresses(status);
    public ButtonContainer buttons;
    public MainFrame self;
    public String FilePath;
    public String FileName;

    public MainFrame()
    {
        setSize(800, 600);
        setMinimumSize(new Dimension(600, 400));
        setLayout(new BorderLayout());
        setTitle("Address Book");

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        status.setBackground(Color.BLACK);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("Arial", Font.BOLD, 14));

        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);

        // Create a "File" menu
        Menu fileMenu = new Menu("File");
        menuBar.add(fileMenu);

        MenuItem openFileItem = new MenuItem("Open file");
        openFileItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                openFile();
            }
        });

        MenuItem saveAsItem = new MenuItem("Save as");
        saveAsItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                saveFileAs();
            }
        });

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        fileMenu.add(openFileItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        setAddresses("src/data.txt");

        buttons = new ButtonContainer(this);
        add(status, BorderLayout.SOUTH);
        add(addresses, BorderLayout.CENTER);
        add(buttons, BorderLayout.NORTH);

        self = this;

        setVisible(true);
    }

    // Helper method to open a file and set addresses
    private void openFile()
    {
        FileDialog fileDialog = new FileDialog(self, "Open file", FileDialog.LOAD);
        fileDialog.setFile("*.txt");
        fileDialog.setVisible(true);
        String filename = fileDialog.getFile();
        if (filename != null)
        {
            try
            {
                setAddresses(fileDialog.getDirectory() + filename);
                status.setText("Opened " + filename);
                buttons.toFilter.setText("");
                addresses.columnsCont.DeselectAll();
                FilePath = fileDialog.getDirectory();
                FileName = filename;
            }
            catch (Exception ee)
            {
                status.setText("Failed to open " + filename + ". Wrong format");
            }
        }
    }

    // Helper method to save the current data to a file
    private void saveFile()
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath + FileName));
            writer.write(addresses.rowsCont.getData());
            writer.close();
            status.setText("Saved to " + FileName);
        }
        catch (Exception ee)
        {
            status.setText("Failed to save!");
        }
    }

    // Helper method to save the current data to a new file
    private void saveFileAs()
    {
        FileDialog fileDialog = new FileDialog(self, "Save as...", FileDialog.SAVE);
        fileDialog.setFile("data.txt");
        fileDialog.setVisible(true);
        String filename = fileDialog.getFile();
        if (filename != null)
        {
            FileName = filename;
            FilePath = fileDialog.getDirectory();
            saveFile();
        }
    }

    // Helper method to set addresses from a file
    private void setAddresses(String filename)
    {
        File toOpen = new File(filename);
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(toOpen));
            String line;
            ArrayList<Contact> contacts = new ArrayList<>();
            while ((line = br.readLine()) != null)
            {
                if (line.trim().length() != 0)
                {
                    String[] params = line.split(", ");
                    contacts.add(new Contact(params[0], params[1], params[2], params[3]));
                }
            }
            addresses.ProvideContacts(contacts);
            br.close();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }
}