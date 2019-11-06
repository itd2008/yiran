package com.yiran.quartz.task;

import org.springframework.stereotype.Component;

import com.yiran.weixin.kit.DuplicateMessageKit;

@Component("clearDuplicateMessageTask")
public class ClearDuplicateMessageTask {

	private void clearMessageKit(){
		DuplicateMessageKit.clear();
	}

}
