import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.io.Serial;

public class ColumnsContainer extends Container
{
    @Serial
    private static final long serialVersionUID = 750659166953951107L;
    public ArrayList<Label> Labels;
    public RowsContainer rows;
    public Label Status;

    public ColumnsContainer(Label status)
    {
        this.setLayout(new GridLayout(1, 4));
        String[] columns = new String[] { "Name", "Surname", "Address", "Phone number" };
        Labels = new ArrayList<>();

        for (String column: columns)
        {
            Label colLabel = new Label(column, Label.CENTER);
            colLabel.setBackground(Color.GRAY);
            colLabel.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent e)
                {
                    for (Label label : Labels)
                    {
                        if (e.getComponent() == label)
                        {
                            label.setBackground(Color.GRAY);
                            Sort(label.getText());
                        }
                        else
                        {
                            label.setBackground(Color.GRAY);
                        }
                    }
                }
            });
            this.add(colLabel);
            Labels.add(colLabel);
        }
        Status = status;
    }

    public void SetRows(RowsContainer container)
    {
        rows = container;
    }

    public void Sort(String parameter)
    {
        rows.Sort(parameter);
        Status.setText("Sorted by " + parameter);
    }

    public void DeselectAll()
    {
        for (Label label : Labels)
        {
            label.setBackground(Color.GRAY);
        }
    }
}