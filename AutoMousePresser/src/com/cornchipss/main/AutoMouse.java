package com.cornchipss.main;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class AutoMouse implements Runnable
{
	public static int CPS = 20;
	public static int CPS_DELAY = (int) Math.floor(1000 / CPS); // To get an average of about 7 clicks per second
	
	Thread thread = new Thread(this);
	static int ie = InputEvent.BUTTON1_MASK;
	KeyLogger kl;
	private boolean clicking = false, running = false;
	private Window window;
	
	public AutoMouse()
	{
		running = true;
		kl = new KeyLogger(this);
		thread.start();
		window = new Window();
		try
		{
			GlobalScreen.registerNativeHook();
		}
		catch(NativeHookException ex)
		{
			ex.printStackTrace();
		}
		GlobalScreen.getInstance().addNativeKeyListener(kl);
		System.out.println("Initialized");
		
		while(running)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() 
	{
		clicking = false;
	}
	public void begin()
	{
		clicking = true;
	}
	public void close()
	{
		running = false;
		thread.interrupt();
		System.out.println("Closing...");
		System.exit(0);
	}
	
	public static void click(int x, int y)
	{
		try
		{
			Robot bot = new Robot();
		    bot.mouseMove(x, y);
		    bot.mousePress(ie);
		    bot.mouseRelease(ie);
		}
		catch(AWTException ex)
		{ /* Program Ended most Likely */ }
	}

	@Override
	public void run()
	{
		while(running)
		{
			while(clicking)
			{
				click(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
				try 
				{
					Thread.sleep(CPS_DELAY);
				} 
				catch (InterruptedException e) 
				{ /* Program Ended most Likely */ }
			}
			try
			{
				// So I don't max out the CPU
				Thread.sleep(10);
			}
			catch (InterruptedException e) 
			{ /* Program Ended most Likely */ }
		}
	}
	
	public static void switchMB(AutoMouse main)
	{
		if(ie == InputEvent.BUTTON1_MASK)
		{
			ie = InputEvent.BUTTON3_MASK;
			main.getWindow().setStatus("Right Clicking");
		}
		else
		{
			ie = InputEvent.BUTTON1_MASK;
			main.getWindow().setStatus("Left Clicking");
		}
	}

	public static void addCps(int amt, AutoMouse main) 
	{
		CPS += amt;
		if(CPS < 1)
		{
			CPS = 1;
		}
		CPS_DELAY = (int) Math.floor(1000 / CPS);
		System.out.println(CPS + " CPS");
		main.getWindow().setCps(CPS);
	}
	
	public Window getWindow()
	{
		return window;
	}
}
