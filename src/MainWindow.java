import javax.swing.*;


public class MainWindow extends JFrame
{

    public MainWindow()
    {
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(370,390);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args)
    {
        MainWindow mw = new MainWindow();
    }
}
