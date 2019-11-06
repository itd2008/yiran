package com.yiran.weixin.service;
/**
 * 二维码service
 * @author pandaa
 *
 */
public interface IWqrService {
	/**
	 * 永久二维码
	 * @param snum
	 * @return
	 */
	public String loadTicketByBaseQr(int snum);
	/**
	 * 临时二维码
	 * @param snum
	 * @return
	 */
	public String loadTicketByTempQr(int snum);
}
