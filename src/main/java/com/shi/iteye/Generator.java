package com.shi.iteye;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author RedRain
 *
 */
public class Generator {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"/iteye-context.xml"});

		BlogGenerator4Iteye generator = (BlogGenerator4Iteye) context.getBean("iteyeBlogGenerator");
		generator.generateBlog("/tmp/iteye");
		
		new BufferedReader(new InputStreamReader(System.in)).readLine(); 
	}

}
