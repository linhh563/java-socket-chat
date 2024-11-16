package client;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;
import data.Peer;
import tags.Tags;

public class GroupChatCreator extends JFrame {
    private static DefaultListModel<String> model = new DefaultListModel<>();
    
    private JFrame frame;    
    private JButton btnCreateGroup;
    private JButton btnExit;    
    private JList<String> userList;
    private JButton createGroupButton;
    private JLabel lblUsersOnline;
    
    private JPanel usersPanel;
    
    private void Initialize()
    {
    	frame = new JFrame();
    	
    	frame.setTitle("Create Gropup Chat");
//    	frame.setSize(675, 645);
    	frame.setBounds(100, 100, 673, 645);
    	frame.setResizable(false);
    	frame.getContentPane().setLayout(null);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	btnCreateGroup = new JButton("Create Group Chat");
		btnCreateGroup.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnCreateGroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try
                {
                	GroupChatGui window = new GroupChatGui();
                }
                catch(Exception e)
                {
                	
                }
            }
        });
        btnCreateGroup.setBounds(250, 525, 200, 44);
        btnCreateGroup.setIcon(new javax.swing.ImageIcon(MainGui.class.getResource("/image/chat.png")));
        frame.getContentPane().add(btnCreateGroup);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int result = Tags.show(frame, "Are you sure ?", true);
                if (result == 0) {
                    try {
//                        clientNode.exit();
                        frame.dispose();
                    } catch (Exception e) {
                        frame.dispose();
                    }
                }
            }
        });
        btnExit.setBounds(500, 525, 129, 44);
        btnExit.setIcon(new javax.swing.ImageIcon(MainGui.class.getResource("/image/stop.png")));
        frame.getContentPane().add(btnExit);
        
        lblUsersOnline = new JLabel("Online Users");
        lblUsersOnline.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblUsersOnline.setBounds(73, 75, 110, 16);
        frame.getContentPane().add(lblUsersOnline);
        
        usersPanel = new JPanel();
        for (var user : model.toArray())
        {
        	JCheckBox item = new JCheckBox(user.toString());
        	item.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        	usersPanel.add(item);
        }
        usersPanel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        usersPanel.setBounds(73, 100, 525, 300);
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(usersPanel);
    }
    
    public GroupChatCreator()
    {    	
    	Initialize();
    }
    
    public GroupChatCreator(ArrayList<Peer> users)
    {
    	GroupChatCreator window = new GroupChatCreator();
    	window.frame.setVisible(true);
                
        add(new JScrollPane(userList), "Center");
        add(createGroupButton, "South");
    }
    
    public static void UpdateOnlineUsers(String msg)
    {
    	model.addElement(msg);
    }
    
    public static void ResetList() {
        model.clear();
    }
//    
//    public GroupChatCreator(List<String> users) {
//        setTitle("Create Group Chat");
//        setSize(300, 400);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        
//        userList = new JList<>(new Vector<>(users));
//        userList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        
//        createGroupButton = new JButton("Create Group Chat");
//        createGroupButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                List<String> selectedUsers = userList.getSelectedValuesList();
//                // Gửi yêu cầu tới server để tạo group chat
//                createGroupChat(selectedUsers);
//            }
//        });
//        
//        add(new JScrollPane(userList), "Center");
//        add(createGroupButton, "South");
//    }
//    
    public void ShowSelectGroupMember()
    {
//    	GroupChatCreator window = new GroupChatCreator();
    	if (this.frame == null)
    	{
    		return;
    	}
    	
		this.frame.setVisible(true);
    }

    private void createGroupChat(List<String> selectedUsers) {
        // Logic gửi yêu cầu tạo group chat tới server
    }
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try 
				{
					GroupChatCreator window = new GroupChatCreator();
					window.frame.setVisible(true);					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}				
			}

		});
	}
}
