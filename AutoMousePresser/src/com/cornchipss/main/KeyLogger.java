package com.cornchipss.main;

import java.awt.event.KeyEvent;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * To notice when the escape key is pressed anywhere
 */
public class KeyLogger implements NativeKeyListener  
{
	AutoMouse main;
	public KeyLogger(AutoMouse main)
	{
		this.main = main;
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) 
	{
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_MULTIPLY)
		{
			main.begin();
			System.out.println("Beginning the clicker!");
			main.getWindow().setActive(true);
		}
		if(e.getKeyCode() == KeyEvent.VK_DIVIDE)
		{
			main.stop();
			System.out.println("Stopping the clicker!");
			main.getWindow().setActive(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_BACK_SLASH)
		{
			AutoMouse.switchMB(main);
		}
		if(e.getKeyCode() == KeyEvent.VK_MINUS)
		{
			AutoMouse.addCps(-1, main);
		}
		if(e.getKeyCode() == KeyEvent.VK_ADD)
		{
			AutoMouse.addCps(1, main);
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			main.close();
			System.out.println("Exiting!");
		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) 
	{
		
	}

}
