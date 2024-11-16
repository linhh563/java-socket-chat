package client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import tags.Tags;

public class CreatePrivateChat extends JFrame {
	
	private Client clientNode;
	private static String IPClient = "", nameUser = "", dataUser = "";
	private static int portClient = 0;
	private MainGui mainGui;
	
	private JFrame frame;
	private JTextField txtNameFriend;
	private JLabel lblUsersOnline;
	private JButton btnChat, btnExit;
	private static JList<String> listActive;
	private static DefaultListModel<String> model = new DefaultListModel<>();
	
	public CreatePrivateChat() throws Exception
	{
		Initialize();
		clientNode = new Client(IPClient, portClient, nameUser, dataUser);			
	}
	
	public CreatePrivateChat(String arg, int arg1, String name, String msg) throws Exception
	{
		IPClient = arg;
		portClient = arg1;
		nameUser = name;
		dataUser = msg;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreatePrivateChat window = new CreatePrivateChat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	public static void updateFriendMainGui(String msg) {
		model.addElement(msg);
	}

	public static void resetList() {
		model.clear();
	} 
	
	public void ShowCreatePrivateChat() throws Exception
	{
		Initialize();
//		clientNode = new Client(IPClient, portClient, nameUser, dataUser);
		frame.setVisible(true);
	}
	
	private void Initialize()
	{
		frame = new JFrame();
		frame.setBounds(120, 120, 300, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Create Private Chat");
		
		lblUsersOnline = new JLabel("Users online");
		lblUsersOnline.setBounds(12, 15, 200, 30);
		lblUsersOnline.setFont(new Font("Segoe UI", Font.BOLD, 15));
		frame.getContentPane().add(lblUsersOnline);
		
		listActive = new JList<>(model);
		listActive.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		listActive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String value = (String)listActive.getModel().getElementAt(listActive.locationToIndex(arg0.getPoint()));
				txtNameFriend.setText(value);
			}
		});
		listActive.setBounds(12, 50, 200, 300);
		frame.getContentPane().add(listActive);

		txtNameFriend = new JTextField("");
		txtNameFriend.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtNameFriend.setColumns(10);
		txtNameFriend.setBounds(20, 375, 200, 30);
		txtNameFriend.setEditable(false);
		frame.getContentPane().add(txtNameFriend);
		
		
		btnChat = new JButton("Chat");
		btnChat.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnChat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String name = txtNameFriend.getText();
				if (name.equals("") || Client.clientarray == null) {
					Tags.show(frame, "Invaild username", false);
					return;
				}
				
				if (name.equals(nameUser)) {
					Tags.show(frame, "This software doesn't support chat yourself function", false);
					return;
				}
				
				int size = Client.clientarray.size();
				
				for (int i = 0; i < size; i++) {
					System.out.println("user in list" + Client.clientarray.get(i).getName());
					if (name.equals(Client.clientarray.get(i).getName())) {
						try {
							clientNode.intialNewChat(Client.clientarray.get(i).getHost(), Client.clientarray.get(i).getPort(), name);
							return;
						} catch (Exception e) {
							System.out.println("exception debug");
							e.printStackTrace();
						}
					}
				}
				Tags.show(frame, "Friend is not found. Please wait to update your list friend", false);
			}
		});
		btnChat.setBounds(20, 425, 200, 45);
		frame.getContentPane().add(btnChat);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Segoe UI", Font.BOLD, 17));
//		btnExit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				int result = Tags.show(frame, "Are you sure ?", true);
//				if (result == 0) {
//					try {
////						clientNode.exit();
//						frame.dispose();
//					} catch (Exception e) {
//						frame.dispose();
//					}
//				}
//			}
//		});
		btnExit.setBounds(20, 500, 200, 45);
		frame.getContentPane().add(btnExit);
	}
	
	public static void main(String[] args) throws Exception {
		new CreatePrivateChat();
	}
}
