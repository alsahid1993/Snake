package game;
//https://www.youtube.com/watch?v=_SqnzvJuKiA&feature=youtu.be
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
    private boolean isRunning = true; //
    private boolean isPlaying = true;


    private ImageIcon titleImage;
    private  int[] snakeXlength = new int[750];
    private  int[] snakeYlength = new int[750];


    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;

    private int lengthOfSnake = 3;
    private Timer timer;
    private int delay = 100;
    //https://www.youtube.com/watch?v=_SqnzvJuKiA
    private ImageIcon snakeimage;

    private int [] foodXPos ={25,50,75,100,125,150,175,200,225,250,275,300,
                            325,350,375,400,425,450,475,500,525,550,575,600,
                            625,650,675,700,725,750,775,800,825,850};
    private int [] foodYPos = {75,100,125,150,175,200,225,250,275,300,
            325,350,375,400,425,450,475,500,525,550,575,600,
            625,650};
    private ImageIcon foodIconImage;
    private Random random = new Random();
    private int xPosition = random.nextInt(34);
    //34 food positions horizontally
    private int yPosition = random.nextInt(23);
    //23 food positions vertically
    private int score = 0;
    private int moves = 0;

    public Gameplay(){
        addKeyListener(this);//object for the glass that is implementing keylistener
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);//initiating timer class
        timer.start();
    }

    public void paint (Graphics g){
        //if game has just started then set default position to
        //this will only be executed upon the first time we start the game

        if (moves == 0)
        {
            snakeXlength[2] = 50;
            snakeXlength [1] = 75;
            snakeXlength [0] = 100;

            snakeYlength [2] = 100;
            snakeYlength [1] = 100;
            snakeYlength [0] = 100;
        }
        //draw title image border
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);

        //draw title image
        titleImage = new ImageIcon("..\\snake\\src\\snaketitle.jpg");
        titleImage.paintIcon(this, g, 25,11);

        //draw borders for gameplay
        g.setColor(Color.WHITE);
        g.drawRect(24,74,851,577);

        //draw background for the gameplay
        g.setColor(Color.black);
        g.fillRect(25,75,850,575);

        //draw length
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Score: " + score, 780,30);
        //draw length
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Length: " + lengthOfSnake, 780,50);

        rightmouth = new ImageIcon("..\\snake\\src\\rightmouth.png");
        rightmouth.paintIcon(this,g,snakeXlength[0],snakeYlength[0]);

        rightmouth = new ImageIcon("..\\snake\\src\\rightmouth.png");
        //head of snake will be stored in the first postion of the array, body will be stored in the other positions
        rightmouth.paintIcon(this,g,snakeXlength[0],snakeYlength[0]);

        for(int a = 0; a < lengthOfSnake; a++){//a identifies the first index of the snake
            if(a == 0 && right)
            {
                rightmouth = new ImageIcon("..\\snake\\src\\rightmouth.png");
                rightmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
            if(a == 0 && left)
            {
                leftmouth = new ImageIcon("..\\snake\\src\\leftmouth.png");
                leftmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
            if(a == 0 && down)
            {
                downmouth = new ImageIcon("..\\snake\\src\\downmouth.png");
                downmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
            if(a == 0 && up)
            {
                upmouth = new ImageIcon("..\\snake\\src\\upmouth.png");
                upmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
            if(a != 0)// this is not the face
            {
                snakeimage = new ImageIcon("..\\snake\\src\\snakeimage.png");
                snakeimage.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }

        }
        //drawing food
        foodIconImage = new ImageIcon("..\\snake\\src\\food.png");
        if((foodXPos[xPosition] == snakeXlength[0] && foodYPos[yPosition] ==snakeYlength[0]))
        //if snakes picks up the coin then implement the length of snake
        {
        score++;
        lengthOfSnake++;
        xPosition = random.nextInt(34);
        yPosition = random.nextInt(23);
        }
        foodIconImage.paintIcon(this,g,foodXPos[xPosition],foodYPos[yPosition]);

        for(int b = 1; b< lengthOfSnake; b++)
        {
           if(snakeXlength[b] ==snakeXlength[0] && snakeYlength[b] == snakeYlength[0]){

               right = false;
               left = false;
               up = false;
               down = false;

               g.setColor(Color.white);
               g.setFont( new Font("ariel",Font.BOLD,50));
               g.drawString("Game Over",300,300);

               g.setFont( new Font("ariel",Font.BOLD,20));
               g.drawString("Press space to restart",350,340);

           }
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e){

    timer.start();
    if(right)
    {
        for (int r = lengthOfSnake - 1; r >= 0; r--)
        {
            snakeYlength[r + 1] = snakeYlength[r];
        }
        for (int r = lengthOfSnake; r >= 0; r--)
        {
            if (r == 0)
            {
                snakeXlength[r] = snakeXlength[r] + 25;
            }
            else
            {
                snakeXlength[r] = snakeXlength[r - 1];
            }
            if(snakeXlength[r] > 850)
            {
                snakeXlength [r] = 25;
            }

        }
        repaint();

    }


    if(left)
    {
        for (int r = lengthOfSnake - 1; r >= 0; r--)
        {
            snakeYlength[r + 1] = snakeYlength[r];
        }
        for (int r = lengthOfSnake; r >= 0; r--)
        {
            if (r == 0)
            {
                snakeXlength[r] = snakeXlength[r] - 25;
            }
            else
            {
                snakeXlength[r] = snakeXlength[r - 1];
            }
            if(snakeXlength[r] < 25)
            {
                snakeXlength [r] = 850;
            }

        }
        repaint();
        }
    if(up)
    {//edit
        for (int r = lengthOfSnake - 1; r >= 0; r--) {
            snakeXlength[r + 1] = snakeXlength[r];
        }
        for (int r = lengthOfSnake; r >= 0; r--)
        {
            if (r == 0)
            {
                snakeYlength[r] = snakeYlength[r] - 25;
            }
            else
            {
                snakeYlength[r] = snakeYlength[r - 1];
            }
            if (snakeYlength[r] < 75) {
                snakeYlength[r] = 625;
            }

        }
        repaint();
    }
    if(down){
        for (int r = lengthOfSnake - 1; r >= 0; r--)
        {
            snakeXlength[r + 1] = snakeXlength[r];
        }
        for (int r = lengthOfSnake; r >= 0; r--)
        {
            if (r == 0)
            {
                snakeYlength[r] = snakeYlength[r] + 25;
            }
            else
            {
                snakeYlength[r] = snakeYlength[r - 1];
            }
            if(snakeYlength[r] > 625)
            {
                snakeYlength [r] = 75;
            }

        }
        repaint();
    }
    }


    @Override
    public void keyTyped(KeyEvent e){

    }
    @Override
    public void keyPressed(KeyEvent e){
       if(e.getKeyCode() == KeyEvent.VK_SPACE)
       {
            moves = 0;
            score = 0;
            lengthOfSnake = 3;
            repaint();
       }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        //if right is selected
        {
            moves++;
            right = true;
            if(!left)
            {
                right = true;
            }
            else
            {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        //if right is selected
        {
            moves++;
            left = true;   // left = up down = right
            if(!right)
            {
                left = true;
            }
            else
            {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP)
        //if right is selected
        {
            moves++;
            up = true;
            if(!down)
            {
                up = true;
            }
            else
            {
                up = false;
                down = true;
            }
            left = false;
            right = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        //if down is selected
        {
            moves++;
            down = true;
            if(!up)
            {
                down = true;
            }
            else
            {
                up = true;
                down= false;
            }
            left = false;
            right = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
