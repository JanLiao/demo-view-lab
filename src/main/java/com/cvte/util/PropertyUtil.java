package com.cvte.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

import com.jfoenix.controls.JFXCheckBox;

/** 
* @author: jan 
* @date: 2018年5月9日 下午2:17:20 
*/
public class PropertyUtil {
	private static final String clientPropPath = "/user.properties";
	
	public static String Account;
	
	public static String Password;
	
	public static String rem;

	public boolean loadProperty() {
		
		try {
			Properties prop = new Properties();// 属性集合对象
			String rootPath = System.getProperty("user.dir").replace("\\", "/");
			FileInputStream fis = new FileInputStream(rootPath + clientPropPath);
			
			InputStreamReader isr = null;
			isr = new InputStreamReader(fis, "UTF-8");
			prop.load(isr);// 将属性文件流装载到Properties对象中   
			fis.close();// 关闭流 
			
			Account = prop.getProperty("account", "");
			Password = prop.getProperty("password", "");
			rem = prop.getProperty("rem", "");
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		PropertyUtil util = new PropertyUtil();
		util.loadProperty();
		System.out.println(PropertyUtil.Account);
		System.out.println(PropertyUtil.Password);
	}

	public static void write(JFXCheckBox jfxCheckBox) {
		if(jfxCheckBox.isSelected()) {
			try{
				String rootPath = System.getProperty("user.dir").replace("\\", "/");
				File f5=new File(rootPath + clientPropPath);
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f5),"UTF-8"); 
				//FileWriter fw=new FileWriter(f5);
		        BufferedWriter bw=new BufferedWriter(osw);
		        bw.write("account=admin");
		        bw.newLine();
		        bw.write("password=a66abb5684c45962d887564f08346e8d");
		        bw.newLine();
		        bw.write("rem=1");
				bw.close();
				osw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else {
			try{
				String rootPath = System.getProperty("user.dir").replace("\\", "/");
				File f5=new File(rootPath + clientPropPath);
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f5),"UTF-8"); 
				//FileWriter fw=new FileWriter(f5);
		        BufferedWriter bw=new BufferedWriter(osw);
		        bw.write("account=admin");
		        bw.newLine();
		        bw.write("password=a66abb5684c45962d887564f08346e8d");
		        bw.newLine();
		        bw.write("rem=0");
				bw.close();
				osw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
