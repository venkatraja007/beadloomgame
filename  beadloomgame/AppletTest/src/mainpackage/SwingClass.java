package mainpackage;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SwingClass extends JPanel implements ActionListener
{
	protected JButton b1;
	
	public SwingClass()
	{
		super (new BorderLayout());
		
		//Create components
		b1 =  new JButton("Click me!! NAO!");
		b1.setVerticalAlignment(AbstractButton.CENTER);
		b1.setHorizontalTextPosition(AbstractButton.LEADING);
		b1.setMnemonic(KeyEvent.VK_C);
		b1.setActionCommand("showMessage");
		b1.addActionListener(this);
		b1.setToolTipText("Click me for a message!");
		b1.setMaximumSize(new Dimension(10,10));
		
		//Layout everything
		add(b1);
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if("showMessage".equals(e.getActionCommand()))
		{
			showStuff();
		}
		
	}
	
	private void showStuff()
	{
		JOptionPane.showMessageDialog(null, "Oh Hai!!");
		
		
	}
	
	public static void createAndShowGUI()
	{
		JFrame frame = new JFrame("SwingClass");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SwingClass newContentPane = new SwingClass();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		
		frame.pack();
		frame.setVisible(true);

		
	}
}
