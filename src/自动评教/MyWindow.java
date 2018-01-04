package 自动评教;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.eclipse.jetty.util.security.Password;

public class MyWindow extends JFrame implements ActionListener{
	
	JTextField username=new JTextField();
	JTextField passwrod=new JTextField();
	JTextField code=new JTextField();
	JButton submit = new JButton("提交");
	
	private String strusername;
	private String strpass;
	private String strcode;
	public MyWindow(byte[] imageData){
		super("自动评教");
		
		JPanel p = new JPanel();
		
		p.setLayout(new FlowLayout());
		p.add(new JLabel("用户名"));
	username.setPreferredSize(new Dimension(120,20));
		p.add(username);
		p.add(new JLabel("密码"));
	passwrod.setPreferredSize(new Dimension(120,20));
		p.add(passwrod);
		p.add(new JLabel("验证码"));
	code.setPreferredSize(new Dimension(80,20));
		p.add(code);
		JLabel l = new JLabel();
		l.setSize(75, 28);
		l.setIcon(new ImageIcon(imageData));
		p.add(l);
		p.add(submit);
		submit.addActionListener(this);
		setBounds(50, 50, 600, 150);
		add(p);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.strusername = username.getText(); 
		this.strpass = passwrod.getText();
		
		this.strcode = code.getText();
		this.dispose();
		//this.setVisible(false);
	}

	public String getStrusername() {
		return strusername;
	}

	public String getStrpass() {
		return strpass;
	}

	public String getStrcode() {
		return strcode;
	}
	
	
}
