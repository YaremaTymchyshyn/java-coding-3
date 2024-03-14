import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.Serial;

public class RowsContainer extends Container
{
    @Serial
    private static final long serialVersionUID = 7462890174266595897L;
    public ArrayList<Contact> unfiltered;
    public ArrayList<Contact> contacts;
    public ArrayList<ArrayList<Label>> labels = new ArrayList<>();
    public int Selected = -1;

    public RowsContainer()
    {
        this.setLayout(new GridLayout(0, 4, 0, 5));
        this.unfiltered = new ArrayList<>();
        this.contacts = new ArrayList<>();
    }

    public void SelectRow(int rowNum)
    {
        ArrayList<Label> row = labels.get(rowNum);
        row.forEach(label -> label.setBackground(Color.GRAY));
    }

    public void Deselect(int rowNum)
    {
        if (rowNum != -1)
        {
            ArrayList<Label> row = labels.get(rowNum);
            row.forEach(label -> label.setBackground(Color.LIGHT_GRAY));
        }
    }

    public void Sort(String parameter)
    {
        Comparator<Contact> comparator = switch (parameter)
        {
            case "Address" -> Comparator.comparing(one -> one.Address.toUpperCase());
            case "Name" -> Comparator.comparing(one -> one.Name.toUpperCase());
            case "Surname" -> Comparator.comparing(one -> one.Surname.toUpperCase());
            default -> Comparator.comparing(one -> one.PhoneNumber.toUpperCase());
        };
        contacts.sort(comparator);
        unfiltered.sort(comparator);
        Populate();
    }

    public void Populate()
    {
        labels.clear();
        this.removeAll();

        for(Contact contact: contacts)
        {
            String[] columns = new String[] { contact.Name, contact.Surname, contact.Address, contact.PhoneNumber };
            ArrayList<Label> row = new ArrayList<>();

            for (String column: columns)
            {
                Label colLabel = new Label(column, Label.CENTER);
                colLabel.setBackground(Color.LIGHT_GRAY);
                colLabel.setPreferredSize(new Dimension(-1, 100));
                colLabel.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        for (int i = 0; i < labels.size(); i++)
                        {
                            for (int j = 0; j < labels.get(i).size(); j++)
                            {
                                if (labels.get(i).get(j) == e.getComponent())
                                {
                                    Deselect(Selected);
                                    Selected = i;
                                    SelectRow(Selected);
                                }
                            }
                        }
                    }
                });
                this.add(colLabel);
                row.add(colLabel);
            }
            labels.add(row);
        }
        if (labels.size() != 0)
        {
            Selected = 0;
            SelectRow(Selected);
        }
        else
        {
            Selected = -1;
        }
        this.revalidate();
    }

    public void deleteSelected()
    {
        if (Selected != -1)
        {
            Deselect(Selected);
            Contact toRemove = contacts.remove(Selected);
            unfiltered.remove(toRemove);
            this.Populate();
        }
    }

    public Contact GetSelected()
    {
        if (Selected != -1)
        {
            return contacts.get(Selected);
        }
        else
        {
            return null;
        }
    }

    public void Filter(String filter)
    {
        String toSearch = filter.toUpperCase();
        contacts = new ArrayList<>(unfiltered.stream().filter(
                (v) -> v.Address.toUpperCase().contains(toSearch)
                || v.Name.toUpperCase().contains(toSearch)
                || v.Surname.toUpperCase().contains(toSearch)
                || v.PhoneNumber.toUpperCase().contains(toSearch)).collect(Collectors.toList()));
        Deselect(Selected);
        this.Populate();
    }

    public String getData()
    {
        StringBuilder data = new StringBuilder();
        for (Contact contact : contacts)
        {
            data.append(contact.toString()).append("\n");
        }
        return data.toString();
    }
}