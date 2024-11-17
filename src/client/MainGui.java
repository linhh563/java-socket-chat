package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import server.ServerGui;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import tags.Encode;
import tags.Tags;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainGui {

	private Client clientNode;
	private static String IPClient = "", nameUser = "", dataUser = "";
	private static int portClient = 0;
	private JFrame frameMainGui;
	private JButton btnChat, btnExit;
	private JLabel lblLogo;
	private JPanel panelMessage;
	private JTextField txtMessage;
	private JTextPane txtDisplayChat;
	private JButton btnSend, btnSendLike;
	
	private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
	
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
		clientNode = new Client(IPClient, portClient, nameUser, dataUser);
		initialize();
	}
	
	private void initialize() {
		frameMainGui = new JFrame();
		frameMainGui.setTitle("Java Socket Chat");
		frameMainGui.setResizable(false);
		frameMainGui.setBounds(100, 50, 700, 750);
		frameMainGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMainGui.getContentPane().setLayout(null);
		
		panelMessage = new JPanel();
		panelMessage.setBounds(20, 500, 650, 130);
		panelMessage.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Server Chat"));
		frameMainGui.getContentPane().add(panelMessage);
		panelMessage.setLayout(null);
		
		txtMessage = new JTextField("");
		txtMessage.setBounds(10, 30, 479, 40);
		panelMessage.add(txtMessage);
		
		txtMessage.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
//				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
//					String msg = txtMessage.getText();
//					if (isStop) {
//						updateChat_send(txtMessage.getText().toString());
//						txtMessage.setText("");
//						return;
//					}
//					if (msg.equals("")) {
//						txtMessage.setText("");
//						txtMessage.setCaretPosition(0);
//						return;
//					}
//					try {
//						chat.sendMessage(Encode.sendMessage(msg));
//						updateChat_send(msg);
//						txtMessage.setText("");
//						txtMessage.setCaretPosition(0);
//					} catch (Exception e) {
//						txtMessage.setText("");
//						txtMessage.setCaretPosition(0);
//					}
//				}
			}
		});
		
		btnSend = new JButton("");
		btnSend.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnSend.setBounds(550, 30, 65, 39);
		btnSend.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSend.setContentAreaFilled(false);
		panelMessage.add(btnSend);
		btnSend.setIcon(new javax.swing.ImageIcon(ChatGui.class.getResource("/image/send.png")));
		
		btnSend.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
//				if (isSendFile)
//					try {
//						chat.sendMessage(Encode.sendFile(nameFile));
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//				if (isStop) {
//					updateChat_send(txtMessage.getText().toString());
//					txtMessage.setText(""); //reset text Send
//					return;
//				}
//				String msg = txtMessage.getText();
				sendMessage();
//				if (msg.equals(""))
//					return;
//				try {
//					chat.sendMessage(Encode.sendMessage(msg));
//					updateChat_send(msg);
//					txtMessage.setText("");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
		});
		
		btnSendLike = new JButton("");
		btnSendLike.setBackground(new Color(240, 240, 240));
		btnSendLike.setBounds(500, 30, 50, 43);
		btnSendLike.setIcon(new javax.swing.ImageIcon(ChatGui.class.getResource("/image/like.png")));
		//transparent button
		btnSendLike.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSendLike.setContentAreaFilled(false);
		panelMessage.add(btnSendLike);
		
		/*
		JButton btnSmileIcon = new JButton("");
		btnSmileIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "<img src='" + ChatGui.class.getResource("/image/smile.png") +"'></img>";
				System.out.println("Tin nhan truoc khi bi encode: " +  msg);
				System.out.println("Tin nhan sau khi bi encode: " +  Encode.sendMessage(msg));
				try {
					chat.sendMessage(Encode.sendMessage(msg));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateChat_send_Symbol(msg);
			}
		});
		btnSmileIcon.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSmileIcon.setContentAreaFilled(false);
		btnSmileIcon.setBounds(62, 96, 50, 36);
		btnSmileIcon.setIcon(new javax.swing.ImageIcon(ChatGui.class.getResource("/image/smile.png")));
		panelMessage.add(btnSmileIcon);
		
		Label label_1 = new Label("Icon");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_1.setBounds(10, 107, 39, 22);
		panelMessage.add(label_1);
		
		btnSmileBigIcon = new JButton("");
		btnSmileBigIcon.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSmileBigIcon.setContentAreaFilled(false);
		btnSmileBigIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "<img src='" + ChatGui.class.getResource("/image/smile_big.png") +"'></img>";
				try {
					chat.sendMessage(Encode.sendMessage(msg));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateChat_send_Symbol(msg);
			}		});
		btnSmileBigIcon.setBounds(124, 96, 50, 36);
		panelMessage.add(btnSmileBigIcon);
		btnSmileBigIcon.setIcon(new javax.swing.ImageIcon(ChatGui.class.getResource("/image/smile_big.png")));
		
		btnCryingIcon = new JButton("");
		btnCryingIcon.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCryingIcon.setContentAreaFilled(false);
		btnCryingIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "<img src='" + ChatGui.class.getResource("/image/crying.png") +"'></img>";
				try {
					chat.sendMessage(Encode.sendMessage(msg));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateChat_send_Symbol(msg);
			}
		});
		btnCryingIcon.setBounds(186, 96, 65, 36);
		panelMessage.add(btnCryingIcon);
		btnCryingIcon.setIcon(new javax.swing.ImageIcon(ChatGui.class.getResource("/image/crying.png")));
		
		btnSmileCryingIcon = new JButton("");
		btnSmileCryingIcon.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSmileCryingIcon.setContentAreaFilled(false);
		btnSmileCryingIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "<img src='" + ChatGui.class.getResource("/image/smile_cry.png") +"'></img>";
				try {
					chat.sendMessage(Encode.sendMessage(msg));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateChat_send_Symbol(msg);
			}
		});
		btnSmileCryingIcon.setBounds(255, 96, 56, 39);
		panelMessage.add(btnSmileCryingIcon);
		btnSmileCryingIcon.setIcon(new javax.swing.ImageIcon(ChatGui.class.getResource("/image/smile_cry.png")));
		
		btnHeartEyeIcon = new JButton("");
		btnHeartEyeIcon.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHeartEyeIcon.setContentAreaFilled(false);
		btnHeartEyeIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "<img src='" + ChatGui.class.getResource("/image/heart_eye.png") +"'></img>";
				try {
					chat.sendMessage(Encode.sendMessage(msg));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateChat_send_Symbol(msg);
			}
		});
		btnHeartEyeIcon.setBounds(323, 96, 75, 36);
		panelMessage.add(btnHeartEyeIcon);
		btnHeartEyeIcon.setIcon(new javax.swing.ImageIcon(ChatGui.class.getResource("/image/heart_eye.png")));
		
		buttonScaredIcon = new JButton("");
		buttonScaredIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "<img src='" + ChatGui.class.getResource("/image/scared.png") +"'></img>";
				try {
					chat.sendMessage(Encode.sendMessage(msg));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateChat_send_Symbol(msg);
			}
		});
		buttonScaredIcon.setIcon(new javax.swing.ImageIcon(ChatGui.class.getResource("/image/scared.png")));
		buttonScaredIcon.setContentAreaFilled(false);
		buttonScaredIcon.setBorder(new EmptyBorder(0, 0, 0, 0));
		buttonScaredIcon.setBounds(394, 96, 75, 36);
		panelMessage.add(buttonScaredIcon);
		
		buttonSadIcon = new JButton("");
		buttonSadIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "<img src='" + ChatGui.class.getResource("/image/sad.png") +"'></img>";
				try {
					chat.sendMessage(Encode.sendMessage(msg));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateChat_send_Symbol(msg);
			}
		});
		buttonSadIcon.setIcon(new javax.swing.ImageIcon(ChatGui.class.getResource("/image/sad.png")));
		buttonSadIcon.setContentAreaFilled(false);
		buttonSadIcon.setBorder(new EmptyBorder(0, 0, 0, 0));
		buttonSadIcon.setBounds(476, 96, 75, 36);
		panelMessage.add(buttonSadIcon);
		
		//action when press button Send
		btnSend.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if (isSendFile)
					try {
						chat.sendMessage(Encode.sendFile(nameFile));
					} catch (Exception e) {
						e.printStackTrace();
					}

				if (isStop) {
					updateChat_send(txtMessage.getText().toString());
					txtMessage.setText(""); //reset text Send
					return;
				}
				String msg = txtMessage.getText();
				if (msg.equals(""))
					return;
				try {
					chat.sendMessage(Encode.sendMessage(msg));
					updateChat_send(msg);
					txtMessage.setText("");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		*/

		btnChat = new JButton("Private Chat");
		btnChat.setFont(new Font("Segoe UI", Font.BOLD, 17));

		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
//					new CreatePrivateChat(IPClient, portClient, nameUser, dataUser);
					ShowCreatePrivateChatUI();
				}
				catch(Exception e)
				{
					
				}
			}
		});
		btnChat.setBounds(20, 650, 200, 44);
		btnChat.setBackground(Color.white);
		frameMainGui.getContentPane().add(btnChat);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = Tags.show(frameMainGui, "Are you sure ?", true);
				if (result == 0) {
					try {
						clientNode.exit();
						writer.close();
			            reader.close();
			            socket.close();
						frameMainGui.dispose();
					} catch (Exception e) {
						frameMainGui.dispose();
					}
				}
			}
		});
		btnExit.setBounds(520, 650, 129, 44);
		btnExit.setBackground(Color.white);
		frameMainGui.getContentPane().add(btnExit);
		
		lblLogo = new JLabel("JAVA SOCKET CHAT");
		lblLogo.setForeground(new Color(0, 0, 205));
		lblLogo.setIcon(new javax.swing.ImageIcon(MainGui.class.getResource("/image/connect.png")));
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLogo.setBounds(50, 15, 413, 38);
		frameMainGui.getContentPane().add(lblLogo);
		
		lblUsername = new JLabel("Welcome to Server chat " + nameUser);
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblUsername.setBounds(50, 65, 300, 28);
		frameMainGui.getContentPane().add(lblUsername);		
		
		txtDisplayChat = new JTextPane();
		txtDisplayChat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtDisplayChat.setEditable(false);
		txtDisplayChat.setContentType( "text/html" );
		txtDisplayChat.setMargin(new Insets(6, 6, 6, 6));
		txtDisplayChat.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
		txtDisplayChat.setBounds(25, 100, 630, 380);
		frameMainGui.getContentPane().add(txtDisplayChat);
		appendToPane(txtDisplayChat, "<div class='clear' style='background-color:white'></div>"); 
	}
	
	public void appendToPane(JTextPane tp, String msg){
	    HTMLDocument doc = (HTMLDocument)tp.getDocument();
	    HTMLEditorKit editorKit = (HTMLEditorKit)tp.getEditorKit();
	    try {
	    	
	      editorKit.insertHTML(doc, doc.getLength(), msg, 0, 0, null);
	      tp.setCaretPosition(doc.getLength());
	      
	    } catch(Exception e){
	      e.printStackTrace();
	    }
	  }
	
	public void updateChat_send(String msg) {
		appendToPane(txtDisplayChat, "<table class='bang' style='color: white; clear:both; width: 100%;'>"
				+ "<tr align='right'>"
				+ "<td style='width: 59%; '></td>"
				+ "<td style='width: 40%; background-color: #0084ff;'>" + msg 
				+"</td> </tr>"
				+ "</table>");
	}
	
	public void updateChat_receive(String msg) {
		appendToPane(txtDisplayChat, "<div class='left' style='width: 40%; background-color: #f1f0f0;'>"+ msg +"</div>");
	}
	
	public void updateChat_notify(String msg) {
		appendToPane(txtDisplayChat, "<table class='bang' style='color: white; clear:both; width: 100%;'>"
				+ "<tr align='right'>"
				+ "<td style='width: 59%; '></td>"
				+ "<td style='width: 40%; background-color: #f1c40f;'>" + msg 
				+"</td> </tr>"
				+ "</table>");
	}

	public static int request(String msg, boolean type) {
		JFrame frameMessage = new JFrame();
		return Tags.show(frameMessage, msg, type);
	}
	
	public Client ClientNode()
	{
		return clientNode;
	}
	
	private void ShowCreatePrivateChatUI() throws Exception
	{		
		CreatePrivateChat createPrivateChat = new CreatePrivateChat(this);
		createPrivateChat.ShowCreatePrivateChat();
	}
	
	public void CreateChat(String name)
	{
		if (name.equals("") || Client.clientarray == null) {
			Tags.show(frameMainGui, "Invaild username", false);
			return;
		}
		
		if (name.equals(nameUser)) {
			Tags.show(frameMainGui, "This software doesn't support chat yourself function", false);
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
		Tags.show(frameMainGui, "Friend is not found. Please wait to update your list friend", false);
	}
	
	private void sendMessage() {
        String message = txtMessage.getText();
        
        if (!message.isEmpty()) {
        	System.out.println("send a message");
            writer.println(nameUser + ": " + message);
            txtMessage.setText("");
        }
    }
	
	private class MessageReader implements Runnable {
        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    appendToPane(txtDisplayChat, message + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
