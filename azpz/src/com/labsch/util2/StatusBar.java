package com.labsch.util2;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class StatusBar extends JMenuBar
{

	
	private JLabel statusText;
	
	
	public StatusBar()
	{
		
		// Das Standard-Layout der JMenuBar ist das Box-Layout
		
		
		this.setLayout(new BorderLayout());
		
		statusText = new JLabel();
		this.add(statusText);
		
		
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		
	}
	
	public void setText(String text)
	{
		statusText.setText(text);
	}
	
	public void setMessage(String message)
	{
		statusText.setText(" " + message);
	}
	

	public String getText()
	{
		return statusText.getText();
	}
	
	
	public void setBorder(Border border)
	{
		super.setBorder(border);
	}
	
	
	public void setStatusLabelFont(Font font)
	{	
		statusText.setFont(font);
	}
	
	
	public Font getStatusLabelFont()
	{
		return statusText.getFont();
	}
	
	public void setHorizontalAlignment(int alignment)
	{
		statusText.setHorizontalAlignment(alignment);
	}
	
}
