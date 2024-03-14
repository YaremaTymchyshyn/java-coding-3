import java.awt.*;
import java.awt.event.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.io.Serial;

public class ModalFrame extends Dialog
{
    @Serial
    private static final long serialVersionUID = 4127503810008979798L;
    public MainFrame mainframe;
    public Label nameLabel = new Label("Name:");
    public Label surnameLabel = new Label("Surname:");
    public Label addressLabel = new Label("Address:");
    public Label phoneLabel = new Label("Phone number:");
    public TextField nameField = new TextField();
    public TextField surnameField = new TextField();
    public TextField addressField = new TextField();
    public TextField phoneField = new TextField();
    public Button submit = new Button("Submit");

    public ModalFrame(MainFrame mainframe)
    {
        super(mainframe, "Add contact...", Dialog.ModalityType.DOCUMENT_MODAL);
        this.mainframe = mainframe;
        mainframe.status.setText("Adding contact...");

        configureComponents();

        this.setMinimumSize(new Dimension(400, 330));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addComponents();

        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CreateContact(mainframe.addresses.rowsCont);
            }
        });

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
        });

        this.setVisible(true);
    }

    public ModalFrame(MainFrame mainframe, Contact contact)
    {
        super(mainframe, "Edit contact...", Dialog.ModalityType.DOCUMENT_MODAL);
        this.mainframe = mainframe;
        mainframe.status.setText("Editing contact...");

        configureComponents();

        nameField.setText(contact.Name);
        surnameField.setText(contact.Surname);
        addressField.setText(contact.Address);
        phoneField.setText(contact.PhoneNumber);

        this.setMinimumSize(new Dimension(400, 330));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addComponents();

        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                EditContact(mainframe.addresses.rowsCont.GetSelected());
            }
        });

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
        });

        this.setVisible(true);
    }

    public void CreateContact(RowsContainer rowCont)
    {
        Contact toAdd = new Contact(nameField.getText(), surnameField.getText(), addressField.getText(), phoneField.getText());
        rowCont.contacts.add(toAdd);
        rowCont.unfiltered.add(toAdd);
        rowCont.Populate();
        mainframe.status.setText("Contact added!");
        this.dispose();
    }

    public void EditContact(Contact contact)
    {
        if (contact != null)
        {
            contact.Name = nameField.getText();
            contact.Surname = surnameField.getText();
            contact.Address = addressField.getText();
            contact.PhoneNumber = phoneField.getText();
            mainframe.addresses.rowsCont.Populate();
            mainframe.status.setText("Contact edited!");
            this.dispose();
        }
    }

    private void configureComponents()
    {
        configureComponent(nameField, nameLabel, "Name:");
        configureComponent(surnameField, surnameLabel, "Surname:");
        configureComponent(addressField, addressLabel, "Address:");
        configureComponent(phoneField, phoneLabel, "Phone number:");
        submit.setPreferredSize(new Dimension(500, 50));
        submit.setMaximumSize(new Dimension(500, 50));
    }

    private void configureComponent(TextField textField, Label label, String labelText)
    {
        textField.setPreferredSize(new Dimension(500, 20));
        textField.setMaximumSize(new Dimension(500, 20));
        label.setPreferredSize(new Dimension(500, 20));
        label.setMaximumSize(new Dimension(500, 20));
    }

    private void addComponents()
    {
        addComponent(nameLabel, 5);
        addComponent(nameField, 10);
        addComponent(surnameLabel, 5);
        addComponent(surnameField, 10);
        addComponent(addressLabel, 5);
        addComponent(addressField, 10);
        addComponent(phoneLabel, 5);
        addComponent(phoneField, 10);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(submit);
    }

    private void addComponent(Component component, int rigidAreaHeight)
    {
        this.add(component);
        this.add(Box.createRigidArea(new Dimension(0, rigidAreaHeight)));
    }
}