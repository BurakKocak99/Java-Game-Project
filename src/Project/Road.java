package Project;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Road extends JLabel {
	private static final long serialVersionUID = 1L;
	Thread roadThread;
	private double speed=10;
	public float distance=(float) 0.1;
	private static int horizontalLoc=-1455;
	static boolean pause=false;
	static boolean gamefinished=false;
	public  JLabel speedLabel  = new JLabel("Speed " +getSpeed()+ " KM/H");
	public JLabel meterLabel = new JLabel("Traveled "+ distance+" M");
			
	ImageIcon icon=new ImageIcon("images/road(0).png");
	public Road(){
		super();
		this.setIcon(icon);
		this.setSize(3824,314);
		meterLabel.setForeground(Color.YELLOW);
		meterLabel.setFont(new Font("Algerian", Font.BOLD, 20));

	}
	public void SlideRoad() {
		
		roadThread = new Thread() {
			public void run() {
				int i=0;
				while(true) {
					
					if(pause) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}	
					else if(distance < 10000) {
						
						try {
							Thread.sleep((long) (150-(10*speed/22)));//Motorun hizina gore yolun hizini ayarlamak icin.
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						setLocation(horizontalLoc,193);
						if(Game.FBbutton==1) {
							if(speed<200)
								speed+=(double)(250/speed);
							else {
								
							speed+=(double)((-8*speed*speed/50000+speed*21/500)/2);//Hiz arttikca daha yavas hizlanmak icin.
						
							}	
						}
						if(speed >=250) {
							speed=250;
						}
						if(Game.FBbutton==-1) {
							speed-=20;
						}
						if(Game.FBbutton==0) {
							speed-=1;
						}
						if(speed <=10)
							speed=10;
						distance+=(float)speed/50;
						icon=new ImageIcon("images/road("+i%5+").png");
						setIcon(icon);
						speedLabel.setText("Speed " +(int)getSpeed()+ " KM/H");
						meterLabel.setText("Traveled "+String.format("%.2f", distance)+" M");
						i++;
						if(Game.RLbutton==1) {
							horizontalLoc-=6;
				
						}
						if(Game.RLbutton==2) {
							horizontalLoc+=6;
						}
						
						
						}
						
				
				if(distance>10000 && !gamefinished) {
					System.out.println("Game is Finished");
					pause=true;
					gamefinished=true;
					
					}
					
				}


				
			
			}
			
		};
		roadThread.start();
	}
	
	public boolean isPause() {
		return pause;
	}
	public void setPause(boolean pause) {
		Road.pause = pause;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public static int getHorizontalLoc() {
		return horizontalLoc;
	}
	public static void setHorizontalLoc(int horizontalLoc) {
		Road.horizontalLoc = horizontalLoc;
	}
	
}
