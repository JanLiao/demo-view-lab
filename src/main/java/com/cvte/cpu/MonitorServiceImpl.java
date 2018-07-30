package com.cvte.cpu;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MonitorServiceImpl {
	private static final int CUPTIME = 30;

	private static final int PERCENT = 100;
	
	private static final int FAULTLENGTH = 10;
	
	
	public static MonitorInfoBean getMonitorInfoBean() throws Exception {
		int kb = 1024 * 1024;
		
		// 可使用内存
		long totalMemory = Runtime.getRuntime().totalMemory() / kb;
		
		// 剩余内存
		long freeMemory = Runtime.getRuntime().freeMemory() / kb;
		
		// 最大可使用内存
		long maxMemory = Runtime.getRuntime().maxMemory() / kb;
		
		//OperatingSystemMXBean osmxb = ManagementFactory.getOperatingSystemMXBean();
		
		// 操作系统
		String osName = System.getProperty("os.name");
		
		// 总的物理内存
		//long totalMemorySize = osmxb.getT
		
		// 获得线程总数
		ThreadGroup parentThread;
		for(parentThread = Thread.currentThread().getThreadGroup(); parentThread.getParent() != null;
				parentThread = parentThread.getParent());
		int totalThread = parentThread.activeCount();
		
		double cpuRatio = 0;
		
		if(osName.toLowerCase().startsWith("windows")) {
			cpuRatio = getCpuRatioForWindows();
		}
		
		MonitorInfoBean info = new MonitorInfoBean();
		info.setTotalMemory(totalMemory);
		info.setFreeMemory(freeMemory);
		info.setCpuRatio(cpuRatio);
		info.setMaxMemory(maxMemory);
		info.setMaxMemory(maxMemory);
		info.setOsName(osName);
		info.setTotalThread(totalThread);
		
		return info;
	}



	public static double getCpuRatioForWindows() {
		InputStream is = null;
		InputStreamReader isr = null;
		StringTokenizer tokenStat = null;
		return 0;
	}

}
