package com.yiran.web.controller.payorder;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yiran.common.annotation.Log;
import com.yiran.common.enums.BusinessType;
import com.yiran.payorder.domaindo.PayBankCardInfoDO;
import com.yiran.payorder.service.IPayBankCardInfoService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 银行卡 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Controller
@RequestMapping("/payorder/payBankCardInfo")
public class PayBankCardInfoController extends BaseController
{
    private String prefix = "payorder/payBankCardInfo";
	
	@Autowired
	private IPayBankCardInfoService payBankCardInfoService;
	
	@RequiresPermissions("payorder:payBankCardInfo:view")
	@GetMapping()
	public String payBankCardInfo()
	{
	    return prefix + "/payBankCardInfo";
	}
	
	/**
	 * 查询银行卡列表
	 */
	@RequiresPermissions("payorder:payBankCardInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayBankCardInfoDO payBankCardInfo)
	{
		startPage();
        List<PayBankCardInfoDO> list = payBankCardInfoService.selectPayBankCardInfoList(payBankCardInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出银行卡列表
	 */
	@RequiresPermissions("payorder:payBankCardInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayBankCardInfoDO payBankCardInfo)
    {
    	List<PayBankCardInfoDO> list = payBankCardInfoService.selectPayBankCardInfoList(payBankCardInfo);
        ExcelUtil<PayBankCardInfoDO> util = new ExcelUtil<PayBankCardInfoDO>(PayBankCardInfoDO.class);
        return util.exportExcel(list, "payBankCardInfo");
    }
	
	/**
	 * 新增银行卡
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存银行卡
	 */
	@RequiresPermissions("payorder:payBankCardInfo:add")
	@Log(title = "银行卡", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayBankCardInfoDO payBankCardInfo)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payBankCardInfoService.insertPayBankCardInfo(payBankCardInfo));
	}

	/**
	 * 修改银行卡
	 */
	@GetMapping("/edit/{bankCardId}")
	public String edit(@PathVariable("bankCardId") Integer bankCardId, ModelMap mmap)
	{
		PayBankCardInfoDO payBankCardInfo = payBankCardInfoService.selectPayBankCardInfoById(bankCardId);
		mmap.put("payBankCardInfo", payBankCardInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存银行卡
	 */
	@RequiresPermissions("payorder:payBankCardInfo:edit")
	@Log(title = "银行卡", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayBankCardInfoDO payBankCardInfo)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payBankCardInfoService.updatePayBankCardInfo(payBankCardInfo));
	}
	
	/**
	 * 删除银行卡
	 */
	@RequiresPermissions("payorder:payBankCardInfo:remove")
	@Log(title = "银行卡", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payBankCardInfoService.deletePayBankCardInfoByIds(ids));
	}
	
}
