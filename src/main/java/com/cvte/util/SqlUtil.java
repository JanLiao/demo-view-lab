package com.cvte.util;

import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.alibaba.fastjson.JSON;
import com.cvte.cons.Constant;
import com.cvte.cons.SqlConsts;

public class SqlUtil {

	// 初始化connection
	public static void connInit() {
		String rootpath = System.getProperty("user.dir").replace('\\', '/');
//		File file = new File(rootpath + "/admin/");
//		if(!file.exists()) {
//			file.mkdirs();
//		}		
		
		StringBuffer connStr = new StringBuffer("");
		connStr.append(SqlConsts.DriverStr);
		connStr.append(rootpath);
		connStr.append("/admin/");
		connStr.append(SqlConsts.DBName);
//		try {
//			Class.forName("org.sqlite.JDBC");
//			SqlConsts.conn = DriverManager.getConnection(connStr.toString());
//			
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		
		// 创建admin.db数据库
		boolean flag = createDB(rootpath, connStr.toString());
		if(flag) {
			// 插入数据到db
			//insertDB();
			
			// 方式一
//			SqlThread thread = new SqlThread();
//			thread.start();
			
			// 方式二
			SqlThread thread = new SqlThread();
			ExecutorService executor = Executors.newCachedThreadPool();
			thread.setExecutor(executor);
			executor.submit(thread);
		}else {
			SqlConsts.CreateFlag = 1;
		}
	}
	
	//插入数据
	public static void insertDB() {
		List<String> list = Constant.AnalysisMix.getImgList();
		Statement stat = null;
		try {
			stat = SqlConsts.conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0, len = list.size(); i < len; i++) {
			String path = list.get(i).split(",")[0];
			String imgName = list.get(i).split(",")[1];
			//System.out.println("path + imgName = " + imgName + "-" + path);
			// 开始插入数据库
			List<String> allLabel = readInsert(path, imgName);
			String label = JSON.toJSONString(allLabel);
			System.out.println(label);
			try {
				stat.addBatch("insert into img_data values(" + "'" + imgName + "', '" + label + "')");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			stat.executeBatch();
			if(stat != null) {				
				stat.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertAdmin(String imgName, List<String> allLabel) {
		String label = JSON.toJSONString(allLabel);
		try {
			Statement stat = SqlConsts.conn.createStatement();
			stat.addBatch("");
			
			stat.executeUpdate("insert into img_data values("
					+ "'" + imgName + "', '" + label + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> readInsert(String dir, String imgName) {
		File[] files = new File(dir).listFiles();
		List<String> allLabel = new ArrayList<String>();
		String imgname = imgName;
		//String user = "";
		StringBuffer user = new StringBuffer("");
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Future<String>[] futures = new Future[files.length];
		long star = System.currentTimeMillis();
		for(int i = 0, len = files.length; i < len; i++) {
			String name = files[i].getName();
			user.append(name);
			user.append(",");
			//当前user下单个csv 名
			String soleName = TableImageView.getSoleName(dir, name, imgName);
			ReadTask task = new ReadTask();
			task.setDir(dir);
			task.setImgName(imgname);
			task.setName(name);
			task.setSoleName(soleName);
			task.setStart(star);
			futures[i] = executor.submit(task);
		}
		
		for(int i = 0, len = files.length; i < len; i++) {
			StringBuffer tmp = new StringBuffer("");
			String name = files[i].getName();
			tmp.append(name);
			tmp.append("=");
			String st = "";
			try {
				st = futures[i].get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			tmp.append(st);
			allLabel.add(tmp.toString());
		}
		executor.shutdown();
		return allLabel;
	}
	
	public static boolean createDB(String rootpath, String dbpath) {
		boolean flag = false;
		File file = new File(rootpath + "/admin/admin.db");
		if (file.exists()) {
			try {
				Class.forName("org.sqlite.JDBC");
				SqlConsts.conn = DriverManager.getConnection(dbpath);

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}		
			System.out.println("已存在该db数据库");
		} else {
			try {
				Class.forName("org.sqlite.JDBC");
				SqlConsts.conn = DriverManager.getConnection(dbpath);
				Statement stat = SqlConsts.conn.createStatement();

				stat.executeUpdate("create table if not exists img_data(name varchar(255), data varchar(1500));");
				stat.executeUpdate("CREATE INDEX idx ON img_data(name)");

				flag = true;
				stat.close();
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static List<String> queryByName(String imgName) {
		long start = System.currentTimeMillis();
		List<String> list = new ArrayList<String>();
		try {
			Statement stat = SqlConsts.conn.createStatement();
			String sql = "select a.data from img_data a where a.name = " + 
			"'" + imgName + "';";
			System.out.println("sql = " + sql);
			ResultSet res = stat.executeQuery(sql);
			String label = res.getString(1);
			list = JSON.parseArray(label, String.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("list 1 = " + list.size() + "=" + list.get(0));
		System.out.println("sql 时间 = " + (System.currentTimeMillis() - start));
		return list;
	}
}
