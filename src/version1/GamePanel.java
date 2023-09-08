package version1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	int[]snakeX=new int[600];
	int[]snakeY=new int[600];
	int length;
	String direction;
	boolean isStart = false;
	boolean isDie = false;
	Timer timer;
	int foodX, foodY;
	int score;
	
	public void init() { //Initialize the state of the snake
		length=2;  //length of the snake
		snakeX[0]=175; //head
		snakeY[0]=275;
		snakeX[1]=150; //first section of body
		snakeY[1]=275;
		direction ="R";
		foodX = 300;
		foodY = 200;
	}
	
	public GamePanel() {
		init();
		this.setFocusable(true);
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				int keyCode=e.getKeyCode();
				if(keyCode==KeyEvent.VK_SPACE) {
					if(isDie) {
						init();
						isDie = false;
					}
					else {
						isStart = !isStart;
						repaint(); //repaint the panel
					}
					
				}
				if(keyCode==KeyEvent.VK_RIGHT) {
					direction ="R";
				}
				if(keyCode==KeyEvent.VK_LEFT) {
					direction ="L";
				}
				if(keyCode==KeyEvent.VK_UP) {
					direction ="U";
				}
				if(keyCode==KeyEvent.VK_DOWN) {
					direction ="D";
				}
				
			}
		});
		timer =new Timer (100,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isStart && isDie==false) {
					//movement of the body
					for(int i = length-1; i > 0; i--) {
						snakeX[i]=snakeX[i-1];
						snakeY[i]=snakeY[i-1];
					}
					//movement of the head
					if("R".equals(direction)) {
						snakeX[0]=snakeX[0]+25;
					}
					if("L".equals(direction)) {
						snakeX[0]=snakeX[0]-25;
					}
					if("U".equals(direction)) {
						snakeY[0]=snakeY[0]-25;
					}
					if("D".equals(direction)) {
						snakeY[0]=snakeY[0]+25;
					}
					//go through walls
					if(snakeX[0] > 650){
                        snakeX[0] = 25;
                    }
                    if(snakeY[0] < 75 ){
                        snakeY[0] = 625;
                    }
                    if(snakeX[0] <25){
                        snakeX[0] = 650;
                    }
                    if(snakeY[0] > 625){
                        snakeY[0] = 75;
                    }
                    //eat food, body length plus 1
                    if(snakeX[0] == foodX && snakeY[0] == foodY){
                        length++;
                        foodX = 25 + 25*(new Random().nextInt(24)); //reset the position of food
                        foodY = 75 + 25*(new Random().nextInt(22));
                        score = (length-2)*10;
                    }
                    //death of the snake
                    for(int i = 1; i < length; i++) {
                    	if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]) {
                    		isDie = true;
                    	}
                    }
                    
                    repaint();
				}
			}
		});
		timer.start();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(new Color(208,220,226));
		g.setColor(new Color(219, 226, 219));
		g.fillRect(10,70,670,585);
		
		//paint the head of the snake
		if("R".equals(direction)) {
			Images.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);
		}
		if("L".equals(direction)) {
			Images.leftImg.paintIcon(this, g, snakeX[0], snakeY[0]);
		}
		if("U".equals(direction)) {
			Images.upImg.paintIcon(this, g, snakeX[0], snakeY[0]);
		}
		if("D".equals(direction)) {
			Images.downImg.paintIcon(this, g, snakeX[0], snakeY[0]);
		}
		
		//paint the body of the snake
		for (int i = 1;i<length;i++){
	       Images.bodyImg.paintIcon(this,g,snakeX[i],snakeY[i]);
	    }
		 
		//Start or pause
		if(isStart == false) {
			g.setColor(new Color(0,0,0));
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.drawString("Press space to start the game.", 120, 300);
		}
		
		//paint food of the game
		Images.foodImg.paintIcon(this, g, foodX, foodY);
		
		//Score of the game
		g.setColor(new Color(0, 0, 0));
	    g.setFont(new Font("Arial",Font.BOLD,25));
	    g.drawString("Snake Game       By: Patricia Tang       Score: "+score,60,45);
	    
		//if snake is dead
	    if(isDie) {
		    g.setColor(new Color(255, 0, 0));
		    g.setFont(new Font("Arial",Font.BOLD,25));
		    g.drawString("The snake is dead. Press space to restart the game.",30,300);
	    }
	}
	
	

}
