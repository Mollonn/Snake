import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class GameField extends JPanel implements ActionListener
{
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private Image dot;
    private Image apple;
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int snake_segment;
    private Timer Time;
    private boolean inGame = true;


    public GameField()
    {
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    public void initGame()
    {
        snake_segment = 3;
        for (int i = 0; i < snake_segment; i++)
        {
            x[i] = DOT_SIZE*3;
            y[i] = DOT_SIZE*3;
        }
        Time = new Timer(150,this);
        Time.start();
        createApple();
    }

    public void createApple()
    {
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }

    public void loadImages()
    {
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(inGame)
        {
            g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < snake_segment; i++)
            {
                g.drawImage(dot,x[i],y[i],this);
            }
        } else
            {
            String str = "Game Over";
            g.setColor(Color.white);
            g.drawString(str,320/2-15,SIZE/2);
        }
    }

    public void move()
    {
        for (int i = snake_segment; i > 0; i--)
        {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left)
        {
            x[0] -= DOT_SIZE;
        }
        if(right)
        {
            x[0] += DOT_SIZE;
        } if(up)
        {
            y[0] -= DOT_SIZE;
        } if(down)
        {
            y[0] += DOT_SIZE;
        }
    }

    public void checkApple()
    {
        if(x[0] == appleX && y[0] == appleY)
        {
            snake_segment++;
            createApple();
        }
    }

    public void checkCollisions()
    {
        for (int i = snake_segment; i >0 ; i--)
        {
            if(i>4 && x[0] == x[i] && y[0] == y[i])
            {
                inGame = false;
            }
        }

        if(x[0]>SIZE)
        {
            inGame = false;
        }
        if(x[0]<0)
        {
            inGame = false;
        }
        if(y[0]>SIZE)
        {
            inGame = false;
        }
        if(y[0]<0)
        {
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(inGame)
        {
            checkApple();
            checkCollisions();
            move();

        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right)
            {
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left)
            {
                right = true;
                up = false;
                down = false;
            }

            if(key == KeyEvent.VK_UP && !down)
            {
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up)
            {
                right = false;
                down = true;
                left = false;
            }
        }
    }
}
