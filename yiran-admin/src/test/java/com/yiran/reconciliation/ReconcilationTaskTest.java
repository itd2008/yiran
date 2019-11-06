package com.yiran.reconciliation;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiran.base.BaseJunit;
import com.yiran.quartz.task.ReconciliationTask;

public class ReconcilationTaskTest extends BaseJunit{
	@Autowired
	private ReconciliationTask reconciliationTask;
	
	@Test
	public void runTest(){
		reconciliationTask.taskRun();
	}

}
