import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Calculate extends JFrame implements ActionListener {
	
	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	
	JTextField txid = new JTextField(10);
	JTextField txname = new JTextField(10);
	JTextField txcour = new JTextField(10);
	JTextField txpre = new JTextField(10);
	JTextField txmid = new JTextField(10);
	JTextField txprefi = new JTextField(10);
	JTextField txfin = new JTextField(10);
	JTextField txgwa = new JTextField(10);
	JTextField txrem = new JTextField(10);
	
	JLabel logo = new JLabel(new ImageIcon("bin/images/STI.JPG"));
	
	JLabel lab1 = new JLabel("ID Number");
	JLabel lab2 = new JLabel("Student Name");
	JLabel lab3 = new JLabel("Course");
	JLabel lab4 = new JLabel("Prelim");
	JLabel lab5 = new JLabel("MidTerm");
	JLabel lab6 = new JLabel("Pre-Final");
	JLabel lab7 = new JLabel("Final");
	JLabel lbgwa = new JLabel("GWA");
	JLabel lbrem = new JLabel("Remarks");

	
	JButton btnser = new JButton("Search Student");
	JButton btncal = new JButton("Calculate");
	JButton btnsub = new JButton("Submit");
	JButton btndel = new JButton("Delete");
	JButton btnback = new JButton("Back");
	
	public Calculate() {
		// TODO Auto-generated constructor stub
		super("Grading System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = getContentPane();
		pane.setLayout(null);
		
		pane.add(logo);
		logo.setBounds(0,0,415,100);
		
		pane.add(lab1);
		lab1.setBounds(100,130,60,10);
		pane.add(txid);
		txid.setBounds(190,125,100,20);
		
		pane.add(lab2);
		lab2.setBounds(100,160,80,10);
		pane.add(txname);
		txname.setBounds(190,155,100,20);
		
		pane.add(lab3);
		lab3.setBounds(100,190,96,10);
		pane.add(txcour);
		txcour.setBounds(190,185,100,20);
		
		pane.add(lab4);
		lab4.setBounds(100,220,60,10);
		pane.add(txpre);
		txpre.setBounds(190, 215, 100, 20);
		
		pane.add(lab5);
		lab5.setBounds(100,250,60,10);
		pane.add(txmid);
		txmid.setBounds(190,245,100,20);
		
		pane.add(lab6);
		lab6.setBounds(100,280,60,10);
		pane.add(txprefi);
		txprefi.setBounds(190,275,100,20);
		
		pane.add(lab7);
		lab7.setBounds(100,310,60,10);
		pane.add(txfin);
		txfin.setBounds(190,305,100,20);
		
		pane.add(lbgwa);
		lbgwa.setBounds(100,340,60,10);
		pane.add(txgwa);
		txgwa.setBounds(190,335,100,20);
		txgwa.setEditable(false);
				
		pane.add(lbrem);
		lbrem.setBounds(100,370,60,10);
		pane.add(txrem);
		txrem.setBounds(190,365,100,20);
		txrem.setEditable(false);
		
		txid.setEditable(false);
		txname.setEditable(false);
		txcour.setEditable(false);
		
		pane.add(btnser);
		btnser.setBounds(80,430,130,30);
		pane.add(btncal);
		btncal.setBounds(210,430,130,30);
		pane.add(btnsub);
		btnsub.setBounds(80,470,130,30);
		pane.add(btnback);
		btnback.setBounds(210,470,130,30);
	/*/	pane.add(btndel);
		btndel.setBounds(280,510,13,30); */
		
		btnser.addActionListener(this);
		btncal.addActionListener(this);
		btnsub.addActionListener(this);
		btndel.addActionListener(this);
		btnback.addActionListener(this);
		
		try {
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:db1");
		}catch (ClassNotFoundException e) {
			
		}catch (SQLException e) {
			
		}
	}
	
	public void clear() {
		
		txid.setText("");
		txname.setText("");
		txcour.setText("");
		txpre.setText("");
		txmid.setText("");
		txprefi.setText("");
		txfin.setText("");
		txgwa.setText("");
		txrem.setText("");
		
	}
	

	public void actionPerformed(ActionEvent e) {
	
		Object source = e.getSource();
		
		try {
			
			if (source==btnser) {
				
				try {
					
					String IDnumber ="";
					int tmp = 0;
					IDnumber = JOptionPane.showInputDialog(null, "Enter ID number to search.", "Grading System", JOptionPane.QUESTION_MESSAGE);
					st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM tbl_studentinfo WHERE ID_Number = '" + IDnumber + "'");
					
					while(rs.next()) {
						
						txid.setText(rs.getString(1));
						txname.setText(rs.getString(2));
						txcour.setText(rs.getString(6));
						

						tmp = 1;
						
					}
					st.close();
					if (tmp==0) {
						JOptionPane.showMessageDialog(null, "No record found", "Student Information", JOptionPane.INFORMATION_MESSAGE);
						
					}
					
				}catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Unable to search");
					
				}
				
			}
			
			if (source==btncal)  {
				
					
					double pre = Double.parseDouble(txpre.getText());
					double mid = Double.parseDouble(txmid.getText());
					double prefi = Double.parseDouble(txprefi.getText());
					double fin = Double.parseDouble(txfin.getText());
					
					double gwa = (pre*0.2)+(mid*0.2)+(prefi*0.2)+(fin*0.4);
					
					
					txgwa.setText(gwa+"");
					if (gwa<75) {
						txrem.setText("Failed");
					}else {
						txrem.setText("Passed");
					}
				
			} if (source==btnsub) {
				
				try {
					st = con.createStatement();
					ps = con.prepareStatement("INSERT INTO Grades" + " (ID_Number,Student_Name,Course,PreLim,Midterm,PreFinal,Final,GWA,Remarks)" + " VALUES(?,?,?,?,?,?,?,?,?)");
					ps.setString(1,txid.getText());
					ps.setString(2,txname.getText());
					ps.setString(3,txcour.getText());
					ps.setString(4,txpre.getText());
					ps.setString(5,txmid.getText());
					ps.setString(6,txprefi.getText());
					ps.setString(7,txfin.getText());
					ps.setString(8,txgwa.getText());
					ps.setString(9,txrem.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data sucessfully saved to the database", "Grading System", JOptionPane.INFORMATION_MESSAGE);
					txid.requestFocus(true);
					st.close();
				
				}catch(SQLException e1) {
					
				}
				clear();
				
			}
			if (source==btnback) {
				
				Main mn = new Main();
				mn.setVisible(true);
				mn.setResizable(false);
				mn.setBounds(450,150,425,232);
				
				dispose();
			}
			
		/*/	if (source==btndel) {
				
				try {
					
					JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this information?");
					
					ps = con.prepareStatement("DELETE FROM Grades WHERE ID_Number="+ txid.getText() + "");
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "inforrmation has been deleted", "Grading System", JOptionPane.INFORMATION_MESSAGE);
					txid.requestFocus();
					st.close();
				
				}catch (SQLException e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Unable to delete information", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} */
			
		} catch (NumberFormatException e2) {
			
			JOptionPane.showMessageDialog(null, "Input Numbers only", "ERROR", JOptionPane.ERROR_MESSAGE);
			txpre.setText("");
			txmid.setText("");
			txpre.setText("");
			txprefi.setText("");
			txfin.setText("");
			
			txpre.requestFocus(true);
		}

	}
}
