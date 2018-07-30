package com.cvte.cons;

import java.sql.Connection;

public class SqlConsts {
	
	public String root = "root";
	
	public static String psw = "123456";
	
	public static String DriverStr = "jdbc:sqlite:";
	
	public static String DBName = "admin.db";

	public static Connection conn = null;
	
	public static int CreateFlag = 0;
}
