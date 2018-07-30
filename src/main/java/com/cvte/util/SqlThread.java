package com.cvte.util;

import java.util.concurrent.ExecutorService;

import com.cvte.cons.SqlConsts;

public class SqlThread extends Thread {
	private ExecutorService executor;

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	@Override
	public void run() {
		SqlUtil.insertDB();
		SqlConsts.CreateFlag = 1;
		this.getExecutor().shutdown();
	}
}
