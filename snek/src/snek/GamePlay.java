package snek;
/*Kwesi Owubah Copyright ©*/
//Open source: https://www.youtube.com/watch?v=_SqnzvJuKiA
//Credit to Awais Mirza
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//import java.util.Timer;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	
	private boolean left =false;
	private boolean right =false;
	private boolean up =false;
	private boolean down =false;
	
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon leftmouth;
	
	private int lengthOfsnake =3;
	private int score;
	
	
	private Timer timer;
	private int delay = 100;
	
	private ImageIcon snakeimage;
	
	private int [] enemyXpos={25,50,75,100,125,150,175,200,225,250,275,300,
			325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625,
			650, 675, 700, 725, 750, 775, 800, 825, 850};
	
	private int [] enemyYpos={75,100,125,150,175,200,225,250,275,300,
			325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 
			600, 625,};
	
	private ImageIcon enemyimage;
	private Random random = new Random();
	
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	
	
	private int moves =0;
	
	
	private ImageIcon titleImage;
	public GamePlay()
    {
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
      timer = new Timer(delay, this);
      timer.start();
    }
//	public GamePlay() {
//		addKeyListener(this);
//		setFocusable(true);
//		setFocusTraversalKeysEnabled(false);
//		//timer = new Timer();
//		//Schedule
//		//timer = new Timer (delay, this);
//		//timer.start();
//	}
	
	public void paint(Graphics g) {
		//if game has started, set default position to this
		if(moves ==0) {
			snakeXlength[2] = 50;
			snakeXlength[1] = 75;
			snakeXlength[0] = 100;
			
			
			snakeYlength[2] = 100;
			snakeYlength[1] = 100;
			snakeYlength[0] = 100;
		}
		//Draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55 );
		
		//Draw the title image
		titleImage = new ImageIcon("snaketitle3.jpg");
		titleImage.paintIcon(this,  g, 25, 11);
		
		//Draw Border for playing area
		g.setColor(Color.WHITE);
		g.drawRect(24, 74 ,  851,  577);
		//Draw Background for Gameplay
		g.setColor(Color.BLACK);
		g.fillRect(25,  75,  851 ,  575);
		
		//Draw scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Scores:" + score,  780, 30);
		
		//draw length of snake
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length : " + lengthOfsnake,  780, 50);
		
		rightmouth = new ImageIcon("rightmouth2.png");
		rightmouth.paintIcon(this,  g,  snakeXlength[0],  snakeYlength[0]);
		rightmouth = new ImageIcon("rightmouth");
		rightmouth = new ImageIcon("rightmouth");
		rightmouth = new ImageIcon("rightmouth");
		
		for(int i =0; i <lengthOfsnake; i++) {
			//detect direction of snake
			if(i==0 && right) {

				rightmouth = new ImageIcon("rightmouth2.png");
				rightmouth.paintIcon(this,  g,  snakeXlength[i],  snakeYlength[i]);
			}
			if(i==0 && left) {

				leftmouth = new ImageIcon("leftmouth2.png");
				leftmouth.paintIcon(this,  g,  snakeXlength[i],  snakeYlength[i]);
			}
			if(i==0 && down) {

				downmouth = new ImageIcon("downmouth2.png");
				downmouth.paintIcon(this,  g,  snakeXlength[i],  snakeYlength[i]);
			}
			if(i==0 && up) {

				upmouth = new ImageIcon("upmouth2.png");
				upmouth.paintIcon(this,  g,  snakeXlength[i],  snakeYlength[i]);
			}
			//body of the snake
			if(i != 0) {
				snakeimage = new ImageIcon("snakeimage2.png");
				snakeimage.paintIcon(this,  g,  snakeXlength[i],  snakeYlength[i]);
			}
		}
		
		//Detect if head of snake collides
		//int ran = (rand.nextInt(9) )+1;
		
		int romm = (int)(Math.random() * 50 + 1);
		enemyimage = new ImageIcon("enemy"+romm+".png");//To cycle between colors
		
		if(enemyXpos[xpos] == snakeXlength[0] && enemyYpos[ypos] == snakeYlength[0]) {
			score++;
			lengthOfsnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		
		enemyimage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);
		//Collision Detection
		for(int k=1; k <lengthOfsnake; k++) {
			if(snakeXlength[k] == snakeXlength[0] && snakeYlength[k] == snakeYlength[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("aria;", Font.BOLD, 50));
				g.drawString("Game Over, but Life's Not", 200, 300);
				
				g.setFont(new Font("aria;", Font.BOLD, 20));
				g.drawString("Take On Your Challenges, Ask For Help", 240, 340);
			}
		}
		g.dispose();
		
		//set to multiples of 25 b/c of sprites
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		if(right) {
			for(int i = lengthOfsnake-1; i >=0; i--) {
				snakeYlength[i+1] = snakeYlength[i];
			}
			for (int i = lengthOfsnake; i>=0; i --) {
				if(i==0) {
					snakeXlength[i] = snakeXlength[i] + 25;
				}else {
					snakeXlength[i] = snakeXlength[i-1];
				}
				if(snakeXlength[i] > 850) {
					snakeXlength[i] = 25;
				}
			}
			repaint();
		}
		if(left) {
			for(int i = lengthOfsnake-1; i >=0; i--) {
				snakeYlength[i+1] = snakeYlength[i];
			}
			for (int i = lengthOfsnake; i>=0; i --) {
				if(i==0) {
					snakeXlength[i] = snakeXlength[i] - 25;
				}else {
					snakeXlength[i] = snakeXlength[i-1];
				}
				if(snakeXlength[i] < 25) {
					snakeXlength[i] = 850;
				}
			}
			repaint();
		}
		if(up) {
			for(int i = lengthOfsnake-1; i >=0; i--) {
				snakeXlength[i+1] = snakeXlength[i];
			}
			for (int i = lengthOfsnake; i>=0; i --) {
				if(i==0) {
					snakeYlength[i] = snakeYlength[i] - 25;
				}else {
					snakeYlength[i] = snakeYlength[i-1];
				}
				if(snakeYlength[i] < 75) {
					snakeYlength[i] = 625;
				}
			}
			repaint();
		}
		if(down) {
			for(int i = lengthOfsnake-1; i >=0; i--) {
				snakeXlength[i+1] = snakeXlength[i];
			}
			for (int i = lengthOfsnake; i>=0; i --) {
				if(i==0) {
					snakeYlength[i] = snakeYlength[i] + 25;
				}else {
					snakeYlength[i] = snakeYlength[i-1];
				}
				if(snakeYlength[i] > 625) {
					snakeYlength[i] = 75;
				}
			}
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lengthOfsnake = 3;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			moves++;
			right = true;
			if(!left) {
				right = true;
			}else {
				right = false;
				left = true;
			}
			//left = false; 
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT ) {
			moves++;
			left = true;
			if(!right) {
				left = true;
			}else {
				left = false;
				right = true;
			}
			//left = false; 
			up = false;
			down = false;
		}
		

		if(e.getKeyCode() == KeyEvent.VK_UP ) {
			moves++;
			up = true;
			if(!down) {
				up = true;
			}else {
				up = false;
				down = true;
			}
			//left = false; 
			left = false;
			right = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN ) {
			moves++;
			down = true;
			if(!up) {
				down = true;
			}else {
				up = true;
				down = false;
			}
			//left = false; 
			left = false;
			right = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}	
