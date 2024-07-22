import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Log_In extends JFrame implements ActionListener {
	
	
	JButton btnlog = new JButton(new ImageIcon("bin/images/login.png"));
	JButton btncan = new JButton(new ImageIcon("bin/images/cancel.png"));
	JTextField txtuser = new JTextField(10);
	JPasswordField txtpass = new JPasswordField(10);
	
	JLabel lab = new JLabel(new ImageIcon("bin/images/STI.JPG"));
	
	JLabel user = new JLabel("Username");
	JLabel pass = new JLabel("Password");
	
	
	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	
	public Log_In() {
		
		super("LogIn");
		
		JPanel pane = new JPanel(new FlowLayout());
		
		pane.add(lab);

		pane.add(user);
		pane.add(txtuser);
		
		pane.add(pass);
		pane.add(txtpass);
		
		pane.add(btnlog);
		pane.add(btncan);
		btnlog.addActionListener(this);
		btncan.addActionListener(this);
		
		setContentPane(pane);
		
		try {
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:db1");
			JOptionPane.showMessageDialog(null, "Connection has been istablished");
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Failed to load driver", "Error", JOptionPane.ERROR_MESSAGE);
			
			// TODO: handle exception
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to Connect", "Error", JOptionPane.ERROR_MESSAGE);
			
			
			// TODO: handle exception
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source==btnlog) {
			
			try {
				
				String username = "";
				String password = "";
				
				String struname = txtuser.getText();
				String strpas = txtpass.getText();
				
				if (struname.length()==0 || strpas.length()==0) {
					JOptionPane.showMessageDialog(null, "Please enter Username/Password", "Warning", JOptionPane.WARNING_MESSAGE);
					
				}else {
					st = con.createStatement();
					
					rs = st.executeQuery("Select * FROM Log_in WHERE Username = '" + txtuser.getText() + "'AND Password = '" + txtpass.getText() +"'");
					
					while(rs.next()) {
						username = rs.getString("Username");
						password = rs.getString("Password");
						
					}
					if (!username.equals(txtuser.getText()) || !password.equals(txtpass.getText())) {
						
						JOptionPane.showMessageDialog(null, "Wrong Username/Password", "Try Again", JOptionPane.ERROR_MESSAGE);
						txtuser.setText("");
						txtpass.setText("");
						txtuser.requestFocus(true);
					}else {
						JOptionPane.showMessageDialog(null, "Successfully Login", "Sucess", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						
						Main mn = new Main();
						mn.setVisible(true);
						mn.setResizable(false);
						mn.setBounds(450,250,415,232);
						
					}
				}
				
			}catch(SQLException ex) {
				
			}
					
			
		}
		if (source==btncan) {
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to EXIT?", "Message", JOptionPane.YES_NO_OPTION);
			
			if (reply == JOptionPane.YES_OPTION) {
				
				System.exit(0);

			}else {

			}

		}
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log_In lg = new Log_In();
		lg.setBounds(450,250,415,232);
		lg.setVisible(true);
		lg.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
