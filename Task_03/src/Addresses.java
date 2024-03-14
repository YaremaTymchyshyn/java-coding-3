import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Label;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import java.io.Serial;

public class Addresses extends Container
{
    @Serial
    private static final long serialVersionUID = 4017264560749769005L;
    public RowsContainer rowsCont;
    public ColumnsContainer columnsCont;

    public Addresses(Label status)
    {
        setLayout(new BorderLayout());
        rowsCont = new RowsContainer();
        columnsCont = new ColumnsContainer(status);
        columnsCont.SetRows(rowsCont);
        this.removeAll();
        this.add(columnsCont, BorderLayout.NORTH);
        this.add(new JScrollPane(rowsCont), BorderLayout.CENTER);
    }

    public void ProvideContacts(ArrayList<Contact> contacts)
    {
        rowsCont.contacts = new ArrayList<>(contacts);
        rowsCont.unfiltered = new ArrayList<>(contacts);
        rowsCont.Populate();
        columnsCont.DeselectAll();
        this.revalidate();
    }
}