import javax.swing.*;

public class Menu
{
    static JFrame jFrame = getFrame();

    public void StartMenu ()
    {

    }

    static JFrame getFrame()
    {
        JFrame jFrame = new JFrame() {};
        jFrame.setVisible(true);
        jFrame.setBounds(1920/2-370/2, 1080/2-390/2, 370, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jFrame;

    }











}