import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main extends JFrame implements ActionListener {
	
	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	
	JLabel lab = new JLabel("What would you like to do?",new ImageIcon("bin/images/STI.JPG"), JLabel.CENTER);
	
	JButton btnadd = new JButton("Add Student Information");
	JButton btncalc = new JButton("Caculate Grade");

	
	public Main() {
		super("Grading System");
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout(FlowLayout.CENTER));

		pane.add(lab);
		lab.setVerticalTextPosition(JLabel.BOTTOM);
		lab.setHorizontalTextPosition(JLabel.CENTER);

		
		pane.add(btnadd);
		pane.add(btncalc);
		
		btnadd.addActionListener(this);
		btncalc.addActionListener(this);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == btnadd) {
			
			AddStd sd = new AddStd();
			sd.setVisible(true);
			sd.setBounds(450,100,425,500);
			
		}
		
		if (source == btncalc) {
			
			Calculate cal = new Calculate();
			cal.setVisible(true);
			cal.setBounds(450,100,425,550);
		}
		dispose();		
		
	}

}
