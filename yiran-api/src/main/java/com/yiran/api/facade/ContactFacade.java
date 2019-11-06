package com.yiran.api.facade;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiran.common.base.ResultWrapper;
import com.yiran.member.base.Response;
import com.yiran.member.request.ContactChangeRequest;
import com.yiran.member.request.ContactRequest;
import com.yiran.member.request.QueryContactRequest;
import com.yiran.member.response.ContactResponse;
import com.yiran.member.response.QueryContactsResponse;
import com.yiran.member.service.IMemberTmContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 联系信息
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiran/contact")
@Api(value="联系信息类接口",description="联系信息类接口")
public class ContactFacade {
	private Logger         logger = LoggerFactory.getLogger(ContactFacade.class);
	
	@Autowired
	private IMemberTmContactService memberTmContactService;
	/** 
     * 创建联系信息
     * @param environment
     * @param request
     * @return
     */
	@PostMapping("/createContact")
    @ApiOperation(value = "创建联系信息",notes="创建联系信息")
    public ResultWrapper<Map<String,Object>> createContact(@RequestBody ContactRequest request) {
        try {
            ContactResponse response = memberTmContactService.createContact(request);
            if (logger.isInfoEnabled()) {
                logger.info("创建联系信息返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("创建联系信息异常 : ", e);
            throw new RuntimeException("调用createContact接口异常", e);
        }
    }

    /** 
    * 修改联系信息
    * @param environment
    * @param request
    * @return
    */
	@PostMapping("/updateContact")
    @ApiOperation(value = "修改联系信息",notes="修改联系信息")
    public ResultWrapper<Map<String,Object>> updateContact(ContactChangeRequest request) {
        try {
            Response response = memberTmContactService.updateContact(request);
            if (logger.isInfoEnabled()) {
                logger.info("修改联系信息对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("修改联系信息异常 : ", e);
            throw new RuntimeException("调用updateContact接口异常", e);
        }
    }

    /** 
    * 查询联系信息
    * @param environment
    * @param request
    * @return
    */
	@PostMapping("/queryContact")
    @ApiOperation(value = "查询联系信息",notes="查询联系信息")
    public ResultWrapper<Map<String,Object>> queryContact(QueryContactRequest request) {
        try {
            QueryContactsResponse response = memberTmContactService.queryContact(request);
            if (logger.isInfoEnabled()) {
                logger.info("查询联系信息返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("查询联系信息异常 : ", e);
            throw new RuntimeException("调用queryContact接口异常", e);
        }
    }

    /** 
    * 删除联系信息
    * @param environment
    * @param contactId
    * @return
    */
	@PostMapping("/deleteContact")
    @ApiOperation(value = "删除联系信息",notes="删除联系信息")
    public ResultWrapper<Map<String,Object>> deleteContact(String contactId) {
        try {
            Response response = memberTmContactService.deleteContact(contactId);
            if (logger.isInfoEnabled()) {
                logger.info("删除联系信息回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("删除联系信息异常 : ", e);
            throw new RuntimeException("调用deleteContact接口异常", e);
        }
    }
	
}
