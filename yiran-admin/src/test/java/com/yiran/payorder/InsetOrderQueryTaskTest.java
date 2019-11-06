package com.yiran.payorder;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiran.base.BaseJunit;
import com.yiran.payorder.task.InstOrderQueryTask;

public class InsetOrderQueryTaskTest extends BaseJunit{

	@Autowired
	private InstOrderQueryTask instOrderQueryTask;
	@Test
	public void queryTaskTest(){
		instOrderQueryTask.run();
	}
}
