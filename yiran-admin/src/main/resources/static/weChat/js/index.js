function getOpenId() {
   var name,value;
   var str=location.href; //取得整个地址栏
   var num=str.indexOf("?")
   if(num == -1){
	   return "";
   }else{
	   str=str.substr(num+1); //取得所有参数   stringvar.substr(start [, length ]
	   var param = str.indexOf("=");
	   console.log(str)
	   var openid = str.substr(param+1);
	   return openid;
   }
   
}

$(function(){
	//将openId放入session中
	var openId = getOpenId();
	if(openId != ""){
		sessionStorage.openId = openId;
	}else{
		sessionStorage.openId = sessionStorage.openId
	}
	//查询首页轮播图片
	$.ajax({
		type: "post",
		url: base_url+"/api/yiran/weChatMall/queryRotaryPictures",
		async: false, //同步
		success: function (data) {
			var rotaryPictures = data.data;
			var rotaryPicturesHtml = "";
			if(data.code == '200'){
				for(var i = 0; i < rotaryPictures.length; i++){
					rotaryPicturesHtml += "<div class='swiper-slide'><a href='"+rotaryPictures[i].linkUrl+"'><img src='"+rotaryPictures[i].linkUrl+"' /></a></div>";
				}
			}
			$("#rotaryPictures").html(rotaryPicturesHtml);
		},
		//调用出错执行的函数
		error: function(){
			$("#rotaryPictures").html("请求服务器api失败，无法获取数据");
		}
	});
	
	//查询图标
	$.ajax({
		type: "post",
		url: base_url+"/api/yiran/weChatMall/queryIcon",
		async: false, //同步
		success: function (data) {
			var icondata = data.data;
			var icondataHtml = "";
			if(data.code == '200'){
				for(var i = 0; i < icondata.length; i++){
					icondataHtml += "<div class='weui-flex__item'><a href='#' class='wy-links-iconlist'><div class='img'><img src='"+icondata[i].iconPath+"'></div><p>"+icondata[i].name+"</p></a></div>";
				}
			}
			$("#queryIcon").html(icondataHtml);
		},
		//调用出错执行的函数
		error: function(){
			$("#rotaryPictures").html("请求服务器api失败，无法获取数据");
		}
	});
	
	//首页商品
	$.ajax({
		type: "post",
		url: base_url+"/api/yiran/weChatMall/product/queryIndexProduct",
		async: false, //同步
		success: function (data) {
			console.info(data.data)
			if(data.code == '200'){
				//猜你喜欢
				var loveList = data.data.loveList;
				var loveListHtml = "";
				for(var i = 0; i < loveList.length; i++){
					loveListHtml += "<li><a href='javascript:showProductDes("+loveList[i].productId+")'><div class='proimg'><img src='"+loveList[i].pictureUrl+"'></div><div class='protxt'><div class='name'>"+loveList[i].title+"</div><div class='wy-pro-pri'>¥<span>"+loveList[i].p4+"</span></div></div></a></li>";
				}
				
				$("#loveList").html(loveListHtml);
			}
		},
		//调用出错执行的函数
		error: function(){
			$("#rotaryPictures").html("请求服务器api失败，无法获取数据");
		}
	});
	
	//购物车数量
	$.ajax({
		type: "post",
		url: base_url+"/api/yiran/weChatMall/queryCarProduct/"+sessionStorage.openId,
		async: false, //同步
		success: function (data) {
			var cardata = data.data;
			var carHtml = "";
			if(data.code == '200'){
				if(cardata.length > 0){
					carHtml = cardata.length;
					$("#car_product_number").html(carHtml);
				}else{
					$("#car_product_badge_weui").hide();
				}
			}
			
		},
		//调用出错执行的函数
        error: function(){
            //请求出错处理
        }
	});
	
	$(".swiper-banner").swiper({
		loop: true,
		autoplay: 3000
	});
	$(".swiper-news").swiper({
		loop: true,
		direction: 'vertical',
		paginationHide :true,
		autoplay: 30000
	});
	$(".swiper-jingxuan").swiper({
		pagination: '.swiper-pagination',
		loop: true,
		paginationType:'fraction',
		slidesPerView:3,
		paginationClickable: true,
		spaceBetween: 2
	});
	$(".swiper-jiushui").swiper({
		pagination: '.swiper-pagination',
		paginationType:'fraction',
		loop: true,
		slidesPerView:3,
		slidesPerColumn: 2,
		paginationClickable: true,
		spaceBetween:2
	});
	
});
/**
 * 显示商品详情
 */
function showProductDes(productId){
	//将产品id存入sessionStorage
	sessionStorage.productId = productId;
	window.location.href="pro_info.html";
}