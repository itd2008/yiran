package com.yiran.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * fastDFS配置
 * @author pandaa
 *
 */
@Component
public class FastDFSConfig {

	@Value("${fdfs.outerNetHttpHost}")
	private String outerNetHttpHost;
	
	@Value("${fdfs.interNetHttpHost}")
	private String interNetHttpHost;

	public String getOuterNetHttpHost() {
		return outerNetHttpHost;
	}

	public void setOuterNetHttpHost(String outerNetHttpHost) {
		this.outerNetHttpHost = outerNetHttpHost;
	}

	public String getInterNetHttpHost() {
		return interNetHttpHost;
	}

	public void setInterNetHttpHost(String interNetHttpHost) {
		this.interNetHttpHost = interNetHttpHost;
	}
	
	
}
