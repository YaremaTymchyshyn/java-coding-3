import java.awt.*;
import java.io.Serial;

public class ButtonContainer extends Container
{
    @Serial
    private static final long serialVersionUID = 6915773005545944560L;
    public Button addNew = new Button("Add new contact...");
    public Button editExisting = new Button("Edit selected...");
    public Button deleteExisting = new Button("Delete selected...");
    public TextField toFilter = new TextField();
    public Button filterButton = new Button("Filter");
    public MainFrame mainFrame;

    public ButtonContainer(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        this.setLayout(new GridLayout(1, 5));

        add(toFilter);
        filterButton.addActionListener(e -> applyFilter());
        add(filterButton);

        addNew.setEnabled(true);
        addNew.addActionListener(e -> addContactModal());
        add(addNew);

        editExisting.setEnabled(false);
        editExisting.addActionListener(e -> editContactModal());
        add(editExisting);

        deleteExisting.setEnabled(false);
        deleteExisting.addActionListener(e -> deleteSelectedContact());
        add(deleteExisting);

        SetupButtons();
    }

    private void applyFilter()
    {
        String filterText = toFilter.getText().trim();
        mainFrame.addresses.rowsCont.Filter(filterText);
        if (!filterText.isEmpty())
        {
            mainFrame.status.setText("Filtered by \"" + filterText + "\"");
        }
        else
        {
            mainFrame.status.setText("Filter removed");
        }
        SetupButtons();
    }

    private void addContactModal()
    {
        new ModalFrame(mainFrame);
        mainFrame.addresses.columnsCont.DeselectAll();
        SetupButtons();
    }

    private void editContactModal()
    {
        new ModalFrame(mainFrame, mainFrame.addresses.rowsCont.GetSelected());
        mainFrame.addresses.columnsCont.DeselectAll();
        SetupButtons();
    }

    private void deleteSelectedContact()
    {
        mainFrame.addresses.rowsCont.deleteSelected();
        mainFrame.status.setText("Contact deleted");
        SetupButtons();
    }

    public void SetupButtons()
    {
        if (mainFrame.addresses.rowsCont.contacts.isEmpty())
        {
            editExisting.setEnabled(false);
            deleteExisting.setEnabled(false);
        }
        else
        {
            editExisting.setEnabled(true);
            deleteExisting.setEnabled(true);
        }
    }
}