import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class TaskFrame extends Frame
{
    private Color lineColor = Color.pink;
    private Random random = new Random();
    private float thickness = 1;
    private int cap = 1;
    private float[] dashes = {random.nextFloat(), random.nextFloat()};
    private boolean dash = false;
    private double a;

    public TaskFrame(double a)
    {
        this.a = a;
        Label b = new Label("Tymchyshyn Yarema AMI-33 Var12");
        b.setBounds(10, 30, 150, 15);
        add(b);

        setSize(500, 500);
        setTitle("Cardioid");
        setLayout(null);
        setVisible(true);

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
        });

        addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent componentEvent)
            {
                repaint();
            }
        });

        addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                do
                {
                    thickness = random.nextFloat() * 20;
                } while (thickness == 0);

                lineColor = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
                cap = random.nextInt(3);

                if (random.nextFloat() > 0.5)
                {
                    dashes[0]= random.nextFloat() * 10;
                    dashes[1]= random.nextFloat() * 10;
                    dash = true;
                }
                else
                {
                    dash = false;
                }
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;

        g2d.drawLine(0, this.getSize().height / 2, this.getSize().width, this.getSize().height / 2);
        g2d.drawLine(this.getSize().width/ 2, 0, this.getSize().width / 2, this.getSize().height);
        g2d.translate(this.getSize().width / 2, this.getSize().height / 2);
        g2d.setColor(this.lineColor);

        if(!dash)
        {
            g2d.setStroke(new BasicStroke(thickness, cap, 0));
        }
        else
        {
            g2d.setStroke(new BasicStroke(thickness, cap, 0, 10.0f, dashes, 0));
        }

        int points = 100;
        for (int i = 0; i < points; i++)
        {
            double t1 = 4 * Math.PI / points * i;
            double t2 = 4 * Math.PI / points * (i + 1);

            int x1 = x_equation(t1);
            int x2 = x_equation(t2);
            int y1 = y_equation(t1);
            int y2 = y_equation(t2);

            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    private int x_equation(double t)
    {
        double x = (a * (2 * Math.cos(t) - Math.cos(2 * t))) * getSize().width / 20;
        return (int) x;
    }

    private int y_equation(double t)
    {
        double y = (a * (2 * Math.sin(t) - Math.sin(2 * t))) * getSize().height / 20;
        return (int) y;
    }
}