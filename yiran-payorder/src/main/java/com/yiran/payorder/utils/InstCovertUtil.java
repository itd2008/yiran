
package com.yiran.payorder.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yiran.payorder.domain.FundoutRequest;
import com.yiran.payorder.domain.RefundRequest;
import com.yiran.payorder.request.ChannelControlRequest;
import com.yiran.payorder.request.ChannelFundRequest;
import com.yiran.payorder.request.ChannelRequest;
import com.yiran.payorder.request.QueryRequest;

/**
 * <p>实例转换工具类</p>
 */
public class InstCovertUtil {
    /**
    * 转换控制类或资金控制类请求实例
    *
    * @param request 原始请求
    * @return
    */
   public static String convert(ChannelRequest request) {
      if (request instanceof QueryRequest) {
           return JSON
               .toJSONString((QueryRequest) request, SerializerFeature.UseISO8601DateFormat);
       } else if (request instanceof ChannelControlRequest) {
           return JSON.toJSONString((ChannelControlRequest) request,
               SerializerFeature.UseISO8601DateFormat);
       } else if (request instanceof ChannelFundRequest) {
           return JSON.toJSONString((ChannelFundRequest) request,
               SerializerFeature.UseISO8601DateFormat);
       } else {
           return JSON.toJSONString(request, SerializerFeature.UseISO8601DateFormat);
       }
   }

   /**
   * 转换资金类请求对象
   *
   * @param request 原始请求
   * @return
   */
  public static String convert(ChannelFundRequest request) {
     if (request instanceof FundoutRequest) {
          return JSON.toJSONString((FundoutRequest) request,
              SerializerFeature.UseISO8601DateFormat);
      } else if (request instanceof RefundRequest) {
          return JSON.toJSONString((RefundRequest) request,
              SerializerFeature.UseISO8601DateFormat);
      } else if (request instanceof ChannelControlRequest) {
          return JSON.toJSONString((ChannelControlRequest) request,
              SerializerFeature.UseISO8601DateFormat);
      }else {
          return JSON.toJSONString(request,
              SerializerFeature.UseISO8601DateFormat);
      }
  }
}
