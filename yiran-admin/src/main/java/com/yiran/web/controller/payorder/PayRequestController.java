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
import com.yiran.payorder.domain.PayRequest;
import com.yiran.payorder.service.IPayRequestService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 请求，用于控制唯一 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Controller
@RequestMapping("/payorder/payRequest")
public class PayRequestController extends BaseController
{
    private String prefix = "payorder/payRequest";
	
	@Autowired
	private IPayRequestService payRequestService;
	
	@RequiresPermissions("payorder:payRequest:view")
	@GetMapping()
	public String payRequest()
	{
	    return prefix + "/payRequest";
	}
	
	/**
	 * 查询请求，用于控制唯一列表
	 */
	@RequiresPermissions("payorder:payRequest:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayRequest payRequest)
	{
		startPage();
        List<PayRequest> list = payRequestService.selectPayRequestList(payRequest);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出请求，用于控制唯一列表
	 */
	@RequiresPermissions("payorder:payRequest:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayRequest payRequest)
    {
    	List<PayRequest> list = payRequestService.selectPayRequestList(payRequest);
        ExcelUtil<PayRequest> util = new ExcelUtil<PayRequest>(PayRequest.class);
        return util.exportExcel(list, "payRequest");
    }
	
	/**
	 * 新增请求，用于控制唯一
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存请求，用于控制唯一
	 */
	@RequiresPermissions("payorder:payRequest:add")
	@Log(title = "请求，用于控制唯一", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayRequest payRequest)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payRequestService.insertPayRequest(payRequest));
	}

	/**
	 * 修改请求，用于控制唯一
	 */
	@GetMapping("/edit/{paymentSeqNo}")
	public String edit(@PathVariable("paymentSeqNo") Integer paymentSeqNo, ModelMap mmap)
	{
		PayRequest payRequest = payRequestService.selectPayRequestById(paymentSeqNo);
		mmap.put("payRequest", payRequest);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存请求，用于控制唯一
	 */
	@RequiresPermissions("payorder:payRequest:edit")
	@Log(title = "请求，用于控制唯一", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayRequest payRequest)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payRequestService.updatePayRequest(payRequest));
	}
	
	/**
	 * 删除请求，用于控制唯一
	 */
	@RequiresPermissions("payorder:payRequest:remove")
	@Log(title = "请求，用于控制唯一", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payRequestService.deletePayRequestByIds(ids));
	}
	
}
