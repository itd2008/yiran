//获取顶部高度

$(document).ready(function(){
	// 头部获取高度
	$(".main-container,#sortNav,#listNav,#Introheader,#commheader").css("margin-top",$("header").height());
	
	//选择配送点判断字数变大
	var objLength = $("#head-delivery").text().length;
	if(objLength == 5){
		$("#head-delivery").css({"font-size":".5rem"});
	}
	
	// Home Search Start
	$(document).ready(function(){
	  $("#search-input").focus(function(){
	  	$("#search-box").css({"left":".25rem","right":"6.1rem"});
	    $("#search-box .icon-sousuo").css({"left":".15rem"});
	    $("#search-input").css({"padding-left":"1.9rem"});
	    $("menu").css({"display":"none"});
			
			if ($("#search-input").val()==""){
				$("#search-cancel").show();
				$("#search-submit").hide();
			}else{
				$("#search-cancel").hide();
				$("#search-submit").show();
			}
	
	    $("#search h1").hide();
	    $("#search-content").show();
	    $("#search-input").bind("propertychange input",function(){
	
				if ($("#search-input").val()==""){
					$("#search-cancel").show();
					//$("#search-cancel").show();
					$("#search-submit").hide();
				}else{
					$("#search-cancel").hide();
					$("#search-submit").show();
				}
				
	    })
	    
	  });
	  $("#search-cancel").bind('touchstart',function(){
	  	$(this).css("background","rgba(0,0,0,.1)")
	  })
	  
	  $("#search-cancel").click(function(){
	  	$("#search-box").removeAttr("style");
	    $("#search-box .icon-sousuo").removeAttr("style");
	    $("#search-input").val("").removeAttr("style");
	    $("#search-cancel").hide().removeAttr("style");
	    $("#search h1").fadeIn();
	    $("#search-content").hide();
	    $("menu").show();
	  });
	
	});
	// Home Search end

	//弹出层 start  // 点击、显示内容、关闭、背景层
	function PopUplayer(objClick,showCon,objClose,BackLayer){
		//点击
		$(objClick).bind('click',function(){
			
	    if($(showCon).is(":hidden")){
	    	
	    	$("html,body").css({"overflow":"hidden"}); //为html,body添加样式
				$(showCon).show(); //显示内容
				
				$("body").append("<samp class='LayerBackground fadeIn' id='"+BackLayer+"'></samp>"); //插入背景层
				
				//配送点
				if(showCon == "#DeliveryContent"){
					$("#"+BackLayer).css("top","2.5rem");
				}
				//配送说明
				if(showCon == "#DeliverysContent"){
					$("#"+BackLayer).css("z-index","104");
				}

			}else{
				
				$("html,body").removeAttr("style"); //清除html,body样式
				$("#"+BackLayer).addClass("fadeOut"); //添加背景层样式
				$(showCon).addClass("fadeOutBottom100"); //添加内容样式
				
				setTimeout(function(){ 
		      $(showCon).hide().removeClass("fadeOutBottom100"); //关闭内容
					$("#"+BackLayer).remove(); //清除背景层
		    }, 800);
	    
			}
	  })
		//关闭
		$(objClose).bind('touchstart',function(){
			
			$("html,body").removeAttr("style"); //清除html,body样式
			$("#"+BackLayer).addClass("fadeOut"); //添加背景层样式
			if(showCon == "#orderPay"){
				$(showCon).addClass("fadeOutBottom20"); //添加内容样式
			}else{
				$(showCon).addClass("fadeOutBottom100"); //添加内容样式
			}
			
			setTimeout(function(){ 
	      $(showCon).hide().removeClass("fadeOutBottom100").removeClass("fadeOutBottom20"); //关闭内容
				$("#"+BackLayer).remove(); //清除背景层
	    }, 800);

		});
	}

	PopUplayer("#orderCart","#cartContent","#cartContent .close","cartLayer")  // 购物车
	PopUplayer("#Delivery","#DeliveryContent","#DeliveryContent .close","DeliveryLayer")  // 配送点
	PopUplayer("#DeliveryContent li","#DeliverysContent","#DeliverysContent .close","DeliverysLayer")  // 配送点说明
	PopUplayer("#list-content li a","#DetailContent","#DetailContent .button","DetailLayer")  // 产品介绍
	PopUplayer("#Edit-Address","#Edit-Address-Content","#Edit-Address-Content .close","Edit-Address-Layer")  // 编辑地址
	PopUplayer("#Add-Address,#order-Edit-address,#Edit-Address-Content .head-r","#Add-Address-Content","#Add-Address-Content .close","Add-Address-Layer")  // 添加地址
	PopUplayer("#orderSubmit","#orderPay","#orderPay .close","orderPayLayer")  // 支付
	//弹出层 End
	
	
	//实时获取浏览器宽度将字体大小赋值给html，实现在不同终端显示zoom效果
	/*(function (doc, win) {
	  var docEl = doc.documentElement,
	    resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
	    recalc = function () {
	      var clientWidth = docEl.clientWidth;
	      if (!clientWidth) return;
	      docEl.style.fontSize = 12 * (clientWidth / 320) + 'px';
	      $(".content").css("top",$("header").height());
	      $("#search-content").css("top",$("header").height());
	    };
	
	  if (!doc.addEventListener) return;
	  win.addEventListener(resizeEvt, recalc, false);
	  doc.addEventListener('DOMContentLoaded', recalc, false);
	})(document, window);*/

});

