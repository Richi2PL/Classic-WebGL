package org.oyasunadev.minecraft;

import java.io.File;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/23/12
 * Time: 4:04 PM
 */
public class Start
{
	public static void main(String[] args)
	{
		new Start();
	}

	public Start()
	{
		System.setProperty("org.lwjgl.librarypath", new File("./run/native/linux").getAbsolutePath());
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		SPFrame spFrame = new SPFrame();

		spFrame.setVisible(true);

		spFrame.startMinecraft();
		spFrame.finish();
	}
}
