package client;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GroupChatGui extends JFrame
{
	private JFrame frame;
	private JPanel panelMessage;
	private JTextField txtMessage;
	private JButton btnSend;
	
	public GroupChatGui()
	{
		Initialize();
		frame.setVisible(true);
	}
	
	public void Initialize()
	{
		frame = new JFrame();
		frame.setTitle("Group Chat");
		frame.setBounds(100, 100, 673, 645);
    	frame.setResizable(false);
    	frame.getContentPane().setLayout(null);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	panelMessage = new JPanel();
		panelMessage.setBounds(6, 363, 649, 201);
		panelMessage.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Message"));
		frame.getContentPane().add(panelMessage);
		panelMessage.setLayout(null);
		
		txtMessage = new JTextField("");
		txtMessage.setBounds(10, 21, 479, 62);
		panelMessage.add(txtMessage);
		txtMessage.setColumns(10);
		
		btnSend = new JButton("");
		btnSend.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnSend.setBounds(551, 33, 65, 39);
		btnSend.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSend.setContentAreaFilled(false);
		panelMessage.add(btnSend);
		btnSend.setIcon(new javax.swing.ImageIcon(ChatGui.class.getResource("/image/send.png")));
	}
}