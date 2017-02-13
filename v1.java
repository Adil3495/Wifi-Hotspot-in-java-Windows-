import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.color.*;
import java.io.IOException;
import java.io.InputStream;

class action_class1 extends JFrame implements ActionListener
{
		JButton help;
		JButton start;
		JButton stop;
		JButton exit;
		JTextField ssid;
		JPasswordField key;
		JLabel name;
		JLabel pass;
		JLabel status;
		
		JCheckBox show;
		ImageIcon icon = new ImageIcon("./1.png");
		
		action_class1()
		{
			setResizable(false);
			help = new JButton("HELP");
			help.setForeground(Color.GREEN);
			
			start = new JButton("Start");
			start.setForeground(Color.GREEN);
			
			stop = new JButton("Stop");
			stop.setForeground(Color.RED);
			
			exit = new JButton("Exit");
			
			name = new JLabel("Network Name (SSID)");
			pass = new JLabel("Password");
			
			ssid = new JTextField(20);
			key = new JPasswordField(20);
			
			show = new JCheckBox(" Show password");
			
			status = new JLabel("");
			
			ssid.setToolTipText("Enter hotspot name");
			key.setToolTipText("Enter password");
			start.setToolTipText("click this button to start hotspot");
			stop.setToolTipText("cick this button to stop hotspot");
			
			key.setEchoChar('*');
			
			setIconImage(icon.getImage());
			setLayout(null);
			
			help.setBounds(300,25,65,27);
			add(help);
			
			name.setBounds(30,85,125,20);
			add(name);
			
			ssid.setBounds(165,85,200,20);
			add(ssid);
			
			pass.setBounds(30,120,80,20);
			add(pass);
			
			key.setBounds(165,120,200,20);
			add(key);
			
			show.setBounds(250,148,120,20);
			add(show);
			
			start.setBounds(80,190,65,27);
			add(start);
			
			stop.setBounds(170,190,65,27);
			add(stop);
			
			exit.setBounds(260,190,65,27);
			add(exit);
			
			status.setBounds(160,230,100,30);
			add(status);
			
			help.addActionListener(this);
			start.addActionListener(this);
			stop.addActionListener(this);
			exit.addActionListener(this);
			show.addActionListener(this);
			
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == start)
			{
				if(ssid.getText().equals(""))
				{
					ErrorDialog1 dlg = new ErrorDialog1(this, "error", "Please enter Network Name");
				}
				else if(key.getText().equals(""))
				{
					ErrorDialog1 dlg = new ErrorDialog1(this, "error", "Please enter password");	
				}
				else
				{
					try
					{
						final Process setup = Runtime.getRuntime().exec("cmd /c netsh wlan set hostednetwork ssid=\""+ssid.getText()+"\" key=\""+key.getText()+"\" mode=allow");
						final Process setup_start =Runtime.getRuntime().exec("cmd /c netsh wlan start hostednetwork");
						final InputStream setup_ip = setup_start.getInputStream();
						int kk=0,ch;
						while((ch = setup_ip.read()) != -1)
						{
							++kk;
						}
						if(kk==32)
						{
							status.setText("Hotspot Started");
						}
						else
						{
							ErrorDialog1 dlg = new ErrorDialog1(this,"error", "Run Program as administrator");			
						}
					}
					catch(IOException f)
					{
						f.printStackTrace();
						ErrorDialog1 dlg = new ErrorDialog1(this, "error","error");
					}
				}
					
			}
			
			if(e.getSource() == stop)
			{
				if(ssid.getText().equals(""))
				{
					ErrorDialog1 dlg = new ErrorDialog1(this, "error", "Please enter Network Name");
				}
				else if(key.getText().equals(""))
				{
					ErrorDialog1 dlg = new ErrorDialog1(this, "error", "Please enter password");	
				}
				else
				{
					try
					{
						final Process setup_p =Runtime.getRuntime().exec("cmd /c netsh wlan stop hostednetwork");	
						final InputStream setup_ip = setup_p.getInputStream();
						int kk=0,ch;
						while((ch = setup_ip.read()) != -1)
						{
							++kk;
						}
						if(kk==32)
						{
							status.setText("Hotspot Stopped");
						}
						else
						{
							ErrorDialog1 dlg = new ErrorDialog1(this,"error", "Run Program as administrator");			
						}
					}
					catch(IOException g)
					{
						g.printStackTrace();
						ErrorDialog1 dlg = new ErrorDialog1(this,"error","error");
					}
				}
			
			}
			
			if(e.getSource() ==exit)
			{
				try
					{
						final Process setup_p =Runtime.getRuntime().exec("cmd /c netsh wlan stop hostednetwork");
						setVisible(false);
						ErrorDialog1 dlg = new ErrorDialog1(this,"Thank you","This program was developed by Md. Adil");	
						 
						dispose();
					}
					catch(IOException g)
					{
						g.printStackTrace();
						ErrorDialog1 dlg = new ErrorDialog1(this, "error","error");
					}
				
				
			}
			if(show.isSelected())
			{
				key.setEchoChar((char)0);
			}
			else
			{
				key.setEchoChar('*');
			}
			
			if(e.getSource() ==help)
			{
				HelpDialog1 hp = new HelpDialog1(this,"Help","");	
			}
			
		}

		
	
}


class ErrorDialog1 extends JDialog implements ActionListener 
{
  public ErrorDialog1(JFrame parent, String title, String message) 
  {
    super(parent, title, true);
    if (parent != null) 
   {
      Dimension parentSize = parent.getSize(); 
      Point p = parent.getLocation(); 
      setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
    }
	//setLocationRelativeTo(null);
    JPanel messagePane = new JPanel();
    messagePane.add(new JLabel(message));
    getContentPane().add(messagePane);
    JPanel buttonPane = new JPanel();
    JButton button = new JButton("OK"); 
    buttonPane.add(button); 
    button.addActionListener(this);
    getContentPane().add(buttonPane, BorderLayout.SOUTH);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   
	pack(); 
   
	setVisible(true);
	
  }
  public void actionPerformed(ActionEvent e) 
  {
    setVisible(false); 
    dispose(); 
  }
}


class HelpDialog1 extends JDialog implements ActionListener 
{
  public HelpDialog1(JFrame parent, String title, String message) 
  {
    super(parent, title, true);
    if (parent != null) 
   {
      Dimension parentSize = parent.getSize(); 
      Point p = parent.getLocation(); 
      setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
    }
	//setLocationRelativeTo(null);
    JPanel messagePane = new JPanel();
    messagePane.add(new JLabel(message));
    getContentPane().add(messagePane);
    JPanel buttonPane = new JPanel();
    JButton button = new JButton("OK"); 
    buttonPane.add(button); 
    button.addActionListener(this);
    getContentPane().add(buttonPane, BorderLayout.SOUTH);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	ImageIcon obj = new ImageIcon("./file.jpg");
	JLabel b = new JLabel(obj);
		//b.setBounds(0,0,100,100);
		add(b);
   
	pack(); 
   
	setVisible(true);
	
  }
  public void actionPerformed(ActionEvent e) 
  {
    setVisible(false); 
    dispose(); 
  }
}


class v1
{
	public static void main(String[] argv)
	{
		action_class1 t = new action_class1();
		t.setSize(420,300);
		t.setTitle("My Router! v1.0");
		t.setLocationRelativeTo(null);
		t.setVisible(true);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}