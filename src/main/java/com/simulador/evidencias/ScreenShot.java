package com.simulador.evidencias;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenShot {
	
	static long yourmilliseconds = System.currentTimeMillis();
	
	
	public static void captureScreenShot(WebDriver ldriver){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy_HH_mm");    
		Date resultdate = new Date(yourmilliseconds);
		
		// Take screenshot and store as a file format             
		 File src=((TakesScreenshot)ldriver).getScreenshotAs(OutputType.FILE);           
		try {
		// now copy the  screenshot to desired location using copyFile method 
		FileUtils.copyFile(src, new File("C:\\Users\\jose.maciel\\eclipse-workspace\\grocerycrud\\src\\main\\resources\\"
				+sdf.format(resultdate).toString()+"-"+System.currentTimeMillis()+".png"));
		
		} catch (IOException e)
		{
		  System.out.println(e.getMessage());
		}
	  }
}
