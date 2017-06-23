package com.cornchipss.main;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JLabel active, cps, status;
	private JLabel[] controls = new JLabel[6];
	
	public Window()
	{
		super("Cornchip's auto clicker");
		setLayout(new GridLayout(10, 1));
		active = new JLabel("Active: False");
		cps = new JLabel("CPS: 20");
		status = new JLabel("Status: Left Clicking");
		controls[0] = new JLabel("*: Turn on");
		controls[1] = new JLabel("/: Turn Off");
		controls[2] = new JLabel("Escape: Close");
		controls[3] = new JLabel("\\: Switch between right and left clicking");
		controls[4] = new JLabel("+: Add to the cps");
		controls[5] = new JLabel("-: Subtract from the cps");
		active.setToolTipText("Press * to turn this on");
		add(active);
		add(cps);
		add(status);
		for(int i = 0; i < controls.length; i++)
		{
			add(controls[i]);
		}
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setActive(boolean atv)
	{
		if(atv)
			active.setText("Active: True");
		else
			active.setText("Active: False");
	}
	
	public void setCps(int c)
	{
		cps.setText("CPS: " + c);
	}
	
	public void setStatus(String s)
	{
		status.setText("Status: " + s);
	}
}
