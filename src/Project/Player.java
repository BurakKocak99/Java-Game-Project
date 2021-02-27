package Project;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Player extends JLabel {
	private static final long serialVersionUID = 1L;
	public Thread playerThread;
	Road road = new Road();
	private int imgs_index=7;
	private final String[] imgs = {"images/motor_left_7.png","images/motor_left_6.png","images/motor_left_5.png","images/motor_left_4.png","images/motor_left_3.png","images/motor_left_2.png","images/motor_left_1.png","images/motor_center.png","images/motor_right_1.png","images/motor_right_2.png","images/motor_right_3.png","images/motor_right_4.png","images/motor_right_5.png","images/motor_right_6.png","images/motor_right_7.png"};
	private final String[] imgs2= {"images/motor_left_fall_11.png","images/motor_left_fall_10.png","images/motor_left_fall_9.png","images/motor_left_fall_8.png","images/motor_left_fall_7.png","images/motor_left_fall_6.png","images/motor_left_fall_5.png","images/motor_left_fall_4.png","images/motor_left_fall_3.png","images/motor_left_fall_2.png","images/motor_left_fall_1.png","images/motor_right_fall_1.png","images/motor_right_fall_2.png","images/motor_right_fall_3.png","images/motor_right_fall_4.png","images/motor_right_fall_5.png","images/motor_right_fall_6.png","images/motor_right_fall_7.png","images/motor_right_fall_8.png","images/motor_right_fall_9.png","images/motor_right_fall_10.png","images/motor_right_fall_11.png"};

	private ImageIcon motorImg = new ImageIcon(imgs[imgs_index]);
	public double time=0;
	public JLabel timeLabel = new JLabel("Time "+(int)time);
	
	Player(){
		super();
		this.setIcon(motorImg);
		this.setSize(900, 800);
		
		
		
		timeLabel.setForeground(Color.RED);
		timeLabel.setFont(new Font("Algerian", Font.BOLD, 20));
		
		
		
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	public void CharacterMov()  {
		try {
		 playerThread=new Thread() {
		 public void run() {
		while(true){
			if(Road.pause) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(!Road.pause) {
					try {
						time+=0.03;
						timeLabel.setText("Time "+(int)time);
						Thread.sleep(30);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					motorImg= new ImageIcon(imgs[imgs_index]);
					setIcon(motorImg);
					if(Game.RLbutton == 0 ) {
						if(imgs_index < 7)
							imgs_index++;
						if(imgs_index > 7)
							imgs_index--;
				}
					else if(imgs_index<imgs.length-1 && imgs_index>=1)	
						 {
							if(Game.RLbutton==1) {
								imgs_index++;
				
						}
							if(Game.RLbutton==2) {
								imgs_index--;	
						}
						}
					if(Road.getHorizontalLoc() < -1800 || Road.getHorizontalLoc() > -1030 ) {
						Road.pause=true;
						Road.gamefinished=true;
						for(int j=11;j<22;j++) {
							motorImg= new ImageIcon(imgs2[j]);
							setIcon(motorImg);
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
			
			if(imgs_index>14)
				imgs_index=12;
		
		
			if(imgs_index<0)
				imgs_index=2;
			
			
			}
			}
		}
		};
		playerThread.start();
		 }
		catch(Exception e) {
			System.out.println("Error!!");
	}
	}
	public int getImgs_index() {
		return imgs_index;
	}
	public void setImgs_index(int imgs_index) {
		this.imgs_index = imgs_index;
	}
	public ImageIcon getMotorImg() {
		return motorImg;
	}
	public void setMotorImg(ImageIcon motorImg) {
		this.motorImg = motorImg;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public String getImgs(int num) {
		return imgs[num];
	}
}

