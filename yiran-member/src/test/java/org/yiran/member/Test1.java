package org.yiran.member;

import java.util.UUID;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.constant.MaConstant;
import com.yiran.member.enums.MemberTypeEnum;
import com.yiran.member.utils.MemberTypeUtil;
import com.yiran.system.domain.SysOperLog;

import ch.qos.logback.core.net.SyslogOutputStream;
import cn.hutool.core.util.RandomUtil;

public class Test1 {
	
	public static void main(String[] args) {

	       // System.out.println(RandomUtil.randomInt(10000000));
		System.out.println(uuid());
	}
	
	/**
	     * 生成随机账号
	     * @return
	     */
	public static String uuid() {
		int machineId = 1;//最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {//有可能是负数
			hashCodeV = -hashCodeV;
		}
		return machineId + String.format("%011d", hashCodeV);
	}
}
