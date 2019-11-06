$(function() {
	var currentUrl = window.location.href;
    if(currentUrl.indexOf("#")!=-1){   
       var str = currentUrl.substr(currentUrl.indexOf("#")+1);
       if(str == "tab2"){
				 $("#tab2").addClass("weui-tab__bd-item--active");
				 $("#maodian_tab2").addClass("weui-bar__item--on");
				 $("#tab1").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab1").removeClass("weui-bar__item--on");
				 $("#tab3").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab3").removeClass("weui-bar__item--on");
				 $("#tab4").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab4").removeClass("weui-bar__item--on");
				 $("#tab5").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab5").removeClass("weui-bar__item--on");
			 }else if(str == "tab3"){
				 $("#tab3").addClass("weui-tab__bd-item--active");
				 $("#maodian_tab3").addClass("weui-bar__item--on");
				 $("#tab1").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab1").removeClass("weui-bar__item--on");
				 $("#tab2").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab2").removeClass("weui-bar__item--on");
				 $("#tab4").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab4").removeClass("weui-bar__item--on");
				 $("#tab5").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab5").removeClass("weui-bar__item--on");
			 }else if(str == "tab4"){
				 $("#tab4").addClass("weui-tab__bd-item--active");
				 $("#maodian_tab4").addClass("weui-bar__item--on");
				 $("#tab1").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab1").removeClass("weui-bar__item--on");
				 $("#tab2").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab2").removeClass("weui-bar__item--on");
				 $("#tab3").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab3").removeClass("weui-bar__item--on");
				 $("#tab5").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab5").removeClass("weui-bar__item--on");
			 }else if(str == "tab5"){
				 $("#tab5").addClass("weui-tab__bd-item--active");
				 $("#maodian_tab5").addClass("weui-bar__item--on");
				 $("#tab1").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab1").removeClass("weui-bar__item--on");
				 $("#tab2").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab2").removeClass("weui-bar__item--on");
				 $("#tab3").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab3").removeClass("weui-bar__item--on");
				 $("#tab4").removeClass("weui-tab__bd-item--active");
				 $("#maodian_tab4").removeClass("weui-bar__item--on");
			 }
    }
	
	$.ajax({
		type: "post",
		url: base_url + "/api/yiran/weChatMall/order/getOrders/"+sessionStorage.openId,
		async: false, //同步
		success: function(data) {
			console.info(data.data);
			var allList = data.data.allList;
			var tab1Html = "";
			for (var i = 0; i < allList.length; i++) {
				tab1Html += "<div class='weui-panel weui-panel_access'><div class='weui-panel__hd'><span>单号：" + allList[i].orderNo +
					"</span>";
				if (allList[i].orderStatus == 0) { //待付款
					tab1Html += "<span class='ord-status-txt-ts fr'>待付款</span></div><div class='weui-media-box__bd  pd-10'>";
				} else if (allList[i].orderStatus == 1) { //待发货
					tab1Html += "<span class='ord-status-txt-ts fr'>待发货</span></div><div class='weui-media-box__bd  pd-10'>";
				} else if (allList[i].orderStatus == 2) { //待收货
					tab1Html += "<span class='ord-status-txt-ts fr'>待收货</span></div><div class='weui-media-box__bd  pd-10'>";
				} else if (allList[i].orderStatus == 3) { //待评价
					tab1Html += "<span class='ord-status-txt-ts fr'>待评价</span></div><div class='weui-media-box__bd  pd-10'>";
				} else if (allList[i].orderStatus == 8) { //已完成
					tab1Html += "<span class='ord-status-txt-ts fr'>已完成</span></div><div class='weui-media-box__bd  pd-10'>";
				}else if (allList[i].orderStatus == 7) { //已取消
					tab1Html += "<span class='ord-status-txt-ts fr'>已取消</span></div><div class='weui-media-box__bd  pd-10'>";
				}

				//列表
				var proList = allList[i].productCatList;
				for (var j = 0; j < proList.length; j++) {
					tab1Html +=
						"<div class='weui-media-box_appmsg ord-pro-list'><div class='weui-media-box__hd'><a href='#'><img class='weui-media-box__thumb' src='" +
						proList[j].pictureUrl +
						"' alt=''></a></div><div class='weui-media-box__bd'><h1 class='weui-media-box__desc'><a href='#' class='ord-pro-link'>" +
						proList[j].title + "</a></h1><p class='weui-media-box__desc'>" + proList[j].attr +
						"</p><div class='clear mg-t-10'><div class='wy-pro-pri fl'>¥<em class='num font-15'>" + proList[j].totalPrice +
						"</em></div><div class='pro-amount fr'><span class='font-13'>数量×<em class='name'>" + proList[j].totalNum +
						"</em></span></div></div></div></div>";
				}
				tab1Html += "</div><div class='ord-statistics'><span>共<em class='num'>" + allList[i].productCount +
					"</em>件商品，</span><span class='wy-pro-pri'>总金额：¥<em class='num font-15'>" + allList[i].productAmountTotal +
					"</em></span><span>(含运费<b>￥0.00</b>)</span></div><div class='weui-panel__ft'><div class='weui-cell weui-cell_access weui-cell_link oder-opt-btnbox'>"
				if (allList[i].orderStatus == 0) {
					tab1Html += "<a href='javascript:toDeleteOrder(\""+allList[i].orderNo+"\");' class='ords-btn-dele'>删除订单</a><a href='javascript:toPay(\""+allList[i].orderId+"\")' class='ords-btn-com'>去付款</a>";
				} else if (allList[i].orderStatus == 1) {
					tab1Html += "商品正在打包中，请您耐心等待....";
				} else if (allList[i].orderStatus == 2) {
					tab1Html +=
						"<a href='javascript:toReceipt(\""+allList[i].orderNo+"\");' class='ords-btn-com receipt'>确认收货</a>";
				} else if (allList[i].orderStatus == 3) {
					tab1Html += "<a href='javascript:toComment(\""+allList[i].orderNo+"\");' class='ords-btn-com'>去评价</a>";
				}
				tab1Html += "</div></div></div>";
			}
			$("#tab1").html(tab1Html);

			//待付款
			var unpaidList = data.data.unpaidList;
			var tab2Html = "";
			for (var i = 0; i < unpaidList.length; i++) {
				tab2Html += "<div class='weui-panel weui-panel_access'><div class='weui-panel__hd'><span>单号：" + unpaidList[i].orderNo +
					"</span>";
				if (unpaidList[i].orderStatus == 0) { //待付款
					tab2Html += "<span class='ord-status-txt-ts fr'>待付款</span></div><div class='weui-media-box__bd  pd-10'>";
				}

				//列表
				var proList = unpaidList[i].productCatList;
				for (var j = 0; j < proList.length; j++) {
					tab2Html +=
						"<div class='weui-media-box_appmsg ord-pro-list'><div class='weui-media-box__hd'><a href='#'><img class='weui-media-box__thumb' src='" +
						proList[j].pictureUrl +
						"' alt=''></a></div><div class='weui-media-box__bd'><h1 class='weui-media-box__desc'><a href='#' class='ord-pro-link'>" +
						proList[j].title + "</a></h1><p class='weui-media-box__desc'>" + proList[j].attr +
						"</p><div class='clear mg-t-10'><div class='wy-pro-pri fl'>¥<em class='num font-15'>" + proList[j].totalPrice +
						"</em></div><div class='pro-amount fr'><span class='font-13'>数量×<em class='name'>" + proList[j].totalNum +
						"</em></span></div></div></div></div>";
				}
				tab2Html += "</div><div class='ord-statistics'><span>共<em class='num'>" + unpaidList[i].productCount +
					"</em>件商品，</span><span class='wy-pro-pri'>总金额：¥<em class='num font-15'>" + unpaidList[i].productAmountTotal +
					"</em></span><span>(含运费<b>￥0.00</b>)</span></div><div class='weui-panel__ft'><div class='weui-cell weui-cell_access weui-cell_link oder-opt-btnbox'>"
				if (unpaidList[i].orderStatus == 0) {
					tab2Html += "<a href='javascript:javascript:toDeleteOrder(\""+unpaidList[i].orderNo+"\");' class='ords-btn-dele'>删除订单</a><a href='javascript:toPay(\""+unpaidList[i].orderId+"\")' class='ords-btn-com'>去付款</a>";
				}
				tab2Html += "</div></div></div>";
			}
			$("#tab2").html(tab2Html);

			//待发货订单
			var tobeShippedList = data.data.tobeShippedList;
			var tab3Html = "";
			for (var i = 0; i < tobeShippedList.length; i++) {
				tab3Html += "<div class='weui-panel weui-panel_access'><div class='weui-panel__hd'><span>单号：" +
					tobeShippedList[i].orderNo +
					"</span>";
				if (tobeShippedList[i].orderStatus == 1) { //待发货
					tab3Html += "<span class='ord-status-txt-ts fr'>待发货</span></div><div class='weui-media-box__bd  pd-10'>";
				}

				//列表
				var proList = tobeShippedList[i].productCatList;
				for (var j = 0; j < proList.length; j++) {
					tab3Html +=
						"<div class='weui-media-box_appmsg ord-pro-list'><div class='weui-media-box__hd'><a href='#'><img class='weui-media-box__thumb' src='" +
						proList[j].pictureUrl +
						"' alt=''></a></div><div class='weui-media-box__bd'><h1 class='weui-media-box__desc'><a href='#' class='ord-pro-link'>" +
						proList[j].title + "</a></h1><p class='weui-media-box__desc'>" + proList[j].attr +
						"</p><div class='clear mg-t-10'><div class='wy-pro-pri fl'>¥<em class='num font-15'>" + proList[j].totalPrice +
						"</em></div><div class='pro-amount fr'><span class='font-13'>数量×<em class='name'>" + proList[j].totalNum +
						"</em></span></div></div></div></div>";
				}
				tab3Html += "</div><div class='ord-statistics'><span>共<em class='num'>" + tobeShippedList[i].productCount +
					"</em>件商品，</span><span class='wy-pro-pri'>总金额：¥<em class='num font-15'>" + tobeShippedList[i].productAmountTotal +
					"</em></span><span>(含运费<b>￥0.00</b>)</span></div><div class='weui-panel__ft'><div class='weui-cell weui-cell_access weui-cell_link oder-opt-btnbox'>"
				if (tobeShippedList[i].orderStatus == 1) {
					tab3Html += "商品正在打包中，请您耐心等待....";
				}
				tab3Html += "</div></div></div>";
			}
			$("#tab3").html(tab3Html);

			//待收货
			var tobeReceivedList = data.data.tobeReceivedList;
			var tab4Html = "";
			for (var i = 0; i < tobeReceivedList.length; i++) {
				tab4Html += "<div class='weui-panel weui-panel_access'><div class='weui-panel__hd'><span>单号：" +
					tobeReceivedList[i].orderNo +
					"</span>";
				if (tobeReceivedList[i].orderStatus == 2) { //待收货
					tab4Html += "<span class='ord-status-txt-ts fr'>待收货</span></div><div class='weui-media-box__bd  pd-10'>";
				}

				//列表
				var proList = tobeReceivedList[i].productCatList;
				for (var j = 0; j < proList.length; j++) {
					tab4Html +=
						"<div class='weui-media-box_appmsg ord-pro-list'><div class='weui-media-box__hd'><a href='#'><img class='weui-media-box__thumb' src='" +
						proList[j].pictureUrl +
						"' alt=''></a></div><div class='weui-media-box__bd'><h1 class='weui-media-box__desc'><a href='#' class='ord-pro-link'>" +
						proList[j].title + "</a></h1><p class='weui-media-box__desc'>" + proList[j].attr +
						"</p><div class='clear mg-t-10'><div class='wy-pro-pri fl'>¥<em class='num font-15'>" + proList[j].totalPrice +
						"</em></div><div class='pro-amount fr'><span class='font-13'>数量×<em class='name'>" + proList[j].totalNum +
						"</em></span></div></div></div></div>";
				}
				tab4Html += "</div><div class='ord-statistics'><span>共<em class='num'>" + tobeReceivedList[i].productCount +
					"</em>件商品，</span><span class='wy-pro-pri'>总金额：¥<em class='num font-15'>" + tobeReceivedList[i].productAmountTotal +
					"</em></span><span>(含运费<b>￥0.00</b>)</span></div><div class='weui-panel__ft'><div class='weui-cell weui-cell_access weui-cell_link oder-opt-btnbox'>"
				if (tobeReceivedList[i].orderStatus == 2) {
					tab4Html +=
						"<a href='javascript:toReceipt(\""+tobeReceivedList[i].orderNo+"\");' class='ords-btn-com receipt'>确认收货</a>";
				}
				tab4Html += "</div></div></div>";
			}
			$("#tab4").html(tab4Html);

			//待评价
			var tobeEvaluatedList = data.data.tobeEvaluatedList;
			var tab5Html = "";
			for (var i = 0; i < tobeEvaluatedList.length; i++) {
				tab5Html += "<div class='weui-panel weui-panel_access'><div class='weui-panel__hd'><span>单号：" +
					tobeEvaluatedList[i].orderNo +
					"</span>";
				if (tobeEvaluatedList[i].orderStatus == 3) { //待评价
					tab5Html += "<span class='ord-status-txt-ts fr'>待评价</span></div><div class='weui-media-box__bd  pd-10'>";
				} else if (tobeEvaluatedList[i].orderStatus == 8) { //已完成
					tab5Html += "<span class='ord-status-txt-ts fr'>已完成</span></div><div class='weui-media-box__bd  pd-10'>";
				}

				//列表
				var proList = tobeEvaluatedList[i].productCatList;
				var totalCoumt = 0;
				var totalPrice = 0;
				for (var j = 0; j < proList.length; j++) {
					if(proList[j].isComment == 0){
						totalCoumt += parseFloat(proList[j].totalNum);
						totalPrice += parseFloat(proList[j].totalPrice);
						tab5Html +=
							"<div class='weui-media-box_appmsg ord-pro-list'><div class='weui-media-box__hd'><a href='#'><img class='weui-media-box__thumb' src='" +
							proList[j].pictureUrl +
							"' alt=''></a></div><div class='weui-media-box__bd'><h1 class='weui-media-box__desc'><a href='#' class='ord-pro-link'>" +
							proList[j].title + "</a></h1><p class='weui-media-box__desc'>" + proList[j].attr +
							"</p><div class='clear mg-t-10'><div class='wy-pro-pri fl'>¥<em class='num font-15'>" + proList[j].totalPrice +
							"</em></div><div class='pro-amount fr'><span class='font-13'>数量×<em class='name'>" + proList[j].totalNum +
							"</em></span></div></div></div></div>";
					}
					
				}
				if(totalCoumt != 0){
					tab5Html += "</div><div class='ord-statistics'><span>共<em class='num'>" + totalCoumt +
						"</em>件商品，</span><span class='wy-pro-pri'>总金额：¥<em class='num font-15'>" + totalPrice +
						"</em></span><span>(含运费<b>￥0.00</b>)</span></div><div class='weui-panel__ft'><div class='weui-cell weui-cell_access weui-cell_link oder-opt-btnbox'>"
				}
				
				if (tobeEvaluatedList[i].orderStatus == 3) {
					tab5Html += "<a href='javascript:toComment(\""+tobeEvaluatedList[i].orderNo+"\");' class='ords-btn-com'>去评价</a>";
				}
				tab5Html += "</div></div></div>";
			}
			$("#tab5").html(tab5Html);

		},
		//调用出错执行的函数
		error: function() {
			$.alert("请求服务器api失败，无法获取数据");
		}
	});
});

var toPay = function(orderId){
	//发起支付
	$.ajax({
		type: "post",
		url: base_url+"/api/yiran/weChatMall/order/createPay/"+orderId,
		async: false, //同步
		success: function (data) {
			console.info(data)
			var result = data.data.result;
			var returnUrl = data.data.returnUrl;
			sessionStorage.setItem('appId', result.appId);
			sessionStorage.setItem('timeStamp', result.timeStamp);
			sessionStorage.setItem('nonceStr', result.nonceStr);
			sessionStorage.setItem('package', result.packAge);
			sessionStorage.setItem('signType', result.signType);
			sessionStorage.setItem('paySign', result.paySign);
			sessionStorage.setItem('returnUrl', returnUrl);
			window.location.href="createpay.html";
		},
		//调用出错执行的函数
		error: function(){
			$.alert("请求服务器api失败，无法获取数据");
		}
	});
}

var toComment = function(orderNo){
	sessionStorage.commentOrderNo = orderNo;
	window.location.href="comment.html";
}

	var toDeleteOrder =function(orderNo){
		$.confirm("您确定要删除此订单吗?", "确认删除?", function() {
			$.ajax({
				type: "post",
				url: base_url+"/api/yiran/weChatMall/order/delete/"+orderNo+"/"+sessionStorage.openId,
				async: false, //同步
				success: function (data) {
					$.toast("订单已经删除!");
					window.location.href="all_orders.html";
				},
				//调用出错执行的函数
				error: function(){
					//请求出错处理
				}
			});
		}, function() {
			//取消操作
		});
	}
	
	
	var toReceipt = function(orderNo){
		$.ajax({
			type: "post",
			url: base_url+"/api/yiran/weChatMall/order/receipt/"+orderNo+"/"+sessionStorage.openId,
			async: false, //同步
			success: function (data) {
				$.toast("收货完成！赶快去评价吧！");
				window.location.href="all_orders.html";
			},
			//调用出错执行的函数
			error: function(){
				//请求出错处理
			}
		});
	}