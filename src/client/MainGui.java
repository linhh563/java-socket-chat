package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import tags.Tags;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import java.awt.Color;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGui {

	private Client clientNode;
	private static String IPClient = "", nameUser = "", dataUser = "";
	private static int portClient = 0;
	private JFrame frameMainGui;
	private JButton btnChat, btnExit;
	private JLabel lblLogo;
//	private static JList<String> listActive;
	
	static DefaultListModel<String> model = new DefaultListModel<>();
	private JLabel lblUsername;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frameMainGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGui(String arg, int arg1, String name, String msg) throws Exception {
		IPClient = arg;
		portClient = arg1;
		nameUser = name;
		dataUser = msg;
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frameMainGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGui() throws Exception {
		initialize();
		clientNode = new Client(IPClient, portClient, nameUser, dataUser);
	}
	
	private void initialize() {
		frameMainGui = new JFrame();
		frameMainGui.setTitle("Java Socket Chat");
		frameMainGui.setResizable(false);
		frameMainGui.setBounds(100, 100, 700, 600);
		frameMainGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMainGui.getContentPane().setLayout(null);

		btnChat = new JButton("Private Chat");
		btnChat.setFont(new Font("Segoe UI", Font.BOLD, 17));

		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
//					new CreatePrivateChat(clientNode);
				}
				catch(Exception e)
				{
					
				}
			}
		});
		btnChat.setBounds(20, 465, 200, 44);
		frameMainGui.getContentPane().add(btnChat);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = Tags.show(frameMainGui, "Are you sure ?", true);
				if (result == 0) {
					try {
						clientNode.exit();
						frameMainGui.dispose();
					} catch (Exception e) {
						frameMainGui.dispose();
					}
				}
			}
		});
		btnExit.setBounds(520, 465, 129, 44);
		frameMainGui.getContentPane().add(btnExit);
		
		lblLogo = new JLabel("WELCOME TO JAVA SOCKET CHAT");
		lblLogo.setForeground(new Color(0, 0, 205));
		lblLogo.setIcon(new javax.swing.ImageIcon(MainGui.class.getResource("/image/connect.png")));
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLogo.setBounds(51, 13, 413, 38);
		frameMainGui.getContentPane().add(lblLogo);
		
		lblUsername = new JLabel(nameUser);
		lblUsername.setForeground(Color.RED);
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblUsername.setBounds(75, 76, 156, 28);
		frameMainGui.getContentPane().add(lblUsername);
	
			
	}
		

	public static int request(String msg, boolean type) {
		JFrame frameMessage = new JFrame();
		return Tags.show(frameMessage, msg, type);
	}
	
	public Client ClientNode()
	{
		return clientNode;
	}
}
