package Project;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class Game implements KeyListener,ActionListener{
	public JFrame firstframe;
	public JLabel hangonlabel;
	public JLayeredPane firstpane;
	public JMenuBar firstmenubar;
	public boolean visibility=true;
	public static boolean signed_in=false;
	private File User = new File("Users.txt");
	private String username = "",Password = "";
	private int number=0;
	public static int RLbutton;
	public static int FBbutton;
	public Player player;
	public JFrame frame;
	public Road road;
	public static final int WIDTH = 900,HEIGHT = WIDTH/16*9;
	public Game() throws InterruptedException {
		
	}


	@SuppressWarnings("deprecation")
	public void start(boolean visb) throws InterruptedException {
		player = new Player();
		road=new Road();
		frame=new JFrame("hangon");
		JMenuBar menubar=new JMenuBar();
		JMenuItem start=new JMenuItem("Start");
		start.setActionCommand("Start2");
		start.addActionListener(this);
		JMenuItem pause=new JMenuItem("Pause");
		pause.setActionCommand("Pause2");
		pause.addActionListener(this);
		JMenuItem restart=new JMenuItem("Restart");
		restart.setActionCommand("Restart2");
		restart.addActionListener(this);
		JMenuItem quit=new JMenuItem("Quit");
		quit.addActionListener(this);
		menubar.add(start);
		menubar.add(pause);
		menubar.add(restart);
		menubar.add(quit);
		frame.setJMenuBar(menubar);
		
		road.speedLabel.setBounds(700, 0, 180, 24);
		road.meterLabel.setBounds(0, 0, 250, 24);
		player.timeLabel.setBounds(400,0,140,24);
		JLayeredPane pane = new JLayeredPane();
		JLabel backk = new JLabel(new ImageIcon("images/Bakgg.png"));
		backk.setSize(3824,506);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
	
		
		player.CharacterMov();
		road.SlideRoad();
		pane.add(player,new Integer(3));
		pane.add(road,new Integer(2));
		pane.add(road.speedLabel,new Integer(2));
		pane.add(road.meterLabel,new Integer(2));
		pane.add(player.timeLabel,new Integer(2));
		pane.add(backk,new Integer(1));
		pane.setBounds(0, 0, 3824, 506);
		
		frame.add(pane);
		
		frame.addKeyListener(this);
		frame.setVisible(visb);
		
	}
	@SuppressWarnings("deprecation")
	public void LoginScreen() {
		firstframe=new JFrame("Game");
		firstframe.setSize(WIDTH, HEIGHT);
		firstframe.setResizable(false);
		firstpane = new JLayeredPane();
		firstmenubar = new JMenuBar();
		firstframe.setResizable(false);
		firstframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		firstframe.setLayout(null);
		firstframe.setSize(WIDTH, HEIGHT + firstmenubar.getHeight());
		firstframe.setLocationRelativeTo(null);
		firstframe.setJMenuBar(firstmenubar);
		JMenuItem start = new JMenuItem("Start");
		firstmenubar.add(start);
		start.setActionCommand("Start");
		start.addActionListener(this);
		JMenuItem login = new JMenuItem("Log-In");
		firstmenubar.add(login);
		login.setActionCommand("Log-in");
		login.addActionListener(this);
		JMenuItem register = new JMenuItem("Register");
		firstmenubar.add(register);
		register.setActionCommand("Register");
		register.addActionListener(this);
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(this);
		firstmenubar.add(quit);
		hangonlabel=new JLabel(new ImageIcon("images/hangon.png"));
		hangonlabel.setBounds(0,0,firstframe.getWidth(),firstframe.getHeight());
		firstpane.add(hangonlabel, new Integer(1));
		firstpane.setBounds(0,0,900,507);
		firstframe.add(firstpane);
		firstframe.setVisible(true);		
		
	}
	public static void main(String args[]) throws InterruptedException {
		Game game=new Game();
		game.LoginScreen();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		String Key=KeyEvent.getKeyText(e.getKeyCode());
	
		if(Key.compareTo("Left")==0) {
			RLbutton=2;
			
		}
		if(Key.compareTo("Right")==0) {
			RLbutton = 1;
			
		}
		if(Key.compareTo("Up")==0) {
			
			FBbutton=1;
		
		}
		if(Key.compareTo("Down")==0) {
			FBbutton = -1;
		
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		String whichKey=KeyEvent.getKeyText(e.getKeyCode());
		if(whichKey.compareTo("Right")==0 || whichKey.compareTo("Left")==0){
			RLbutton=0;
		}
		if(whichKey.compareTo("Up")==0 || whichKey.compareTo("Down")==0) {
			FBbutton=0;
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String action = e.getActionCommand();
		if(action.equals("Start")) {
			if(signed_in) {
			firstframe.dispose();
			try {
				this.start(true);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		}
		
		if(action.equals("Log-in")) 
		{
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(User));
			} 
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} 

			JLabel label_login = new JLabel("Username:");
			JTextField login = new JTextField();
			
			JLabel label_password = new JLabel("Password:");
			JPasswordField password = new JPasswordField();
			 
			Object[] array = { label_login,  login, label_password, password };
			 
			int reg = JOptionPane.showConfirmDialog(null, array, "Login", 
			        JOptionPane.OK_CANCEL_OPTION,
			        JOptionPane.PLAIN_MESSAGE);
			
			if (reg == JOptionPane.OK_OPTION) {
			   username=new String(login.getText().trim());
			   Password=new String(password.getPassword());
			}
			  
			String string; 
			try {
				while ((string = br.readLine()) != null) {
				   number = string.indexOf('-');
				   int j =string.lastIndexOf('-'); 
				   if(username.equals(string.substring(0, number))) {
				    	if(Password.equals(string.substring(number+1, j))) 
				    	{
				    		signed_in=true;
				    	}
				   }
				}
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			visibility=false;
		}
		
		
		
		if(action.equals("Register")) {
			FileWriter fp = null;
			try {
				
				fp = new FileWriter(User, true);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			BufferedWriter br = new BufferedWriter(fp);
			PrintWriter pr = new PrintWriter(br);
			
			JLabel label_login = new JLabel("Username:");
			JTextField login = new JTextField();
			 
			JLabel label_password = new JLabel("Password:");
			JPasswordField password = new JPasswordField();
			 
			Object[] array = { label_login,  login, label_password, password };
			 
			int reg = JOptionPane.showConfirmDialog(null, array, "Register", 
			        JOptionPane.OK_CANCEL_OPTION,
			        JOptionPane.PLAIN_MESSAGE);
			if (reg == JOptionPane.OK_OPTION) {
			    pr.println(login.getText().trim() + "-" + new String(password.getPassword())+"-");
			}
			pr.close();
			try {
				br.close();
				fp.close();
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(action.equals("Start2")) {
			road.setPause(false);
		}
		if(action.equals("Pause2")) {
			road.setPause(true);
			}
			
		
		if(action.equals("Restart2")) {
			player.setTime(0);
			player.setMotorImg(new ImageIcon(player.getImgs(8)));
			player.setImgs_index(8);
			road.setSpeed(10);
			road.setDistance(0);
			Road.setHorizontalLoc(-1455);
			road.setPause(false);
			Road.gamefinished=false;
			
		}
		if(action.equals("Quit")) {
			System.exit(0);
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	}
