/**
+-------------------------------------------------------------------
* jQuery hDialog - 弹出层表单插件 - http://smwell.sinaapp.com/
+-------------------------------------------------------------------
* @version 1.0.0
* @since 2014.10.27
* @author haibao <hhb219@163.com> <http://smwell.sinaapp.com/>
+-------------------------------------------------------------------
*/
;(function($, window, document, undefined) {	
	var _doc = $(document), $body = $('body');
	methods = {
        init: function (options) {
           	return this.each(function() {
				var $this = $(this), opt = $this.data('hDialog');
                if(typeof(opt) == 'undefined') {
                	var defaults = {
                		title: '',              //弹框标题
						box: '#HBox',           //弹框默认选择器
						boxBg: '#fff',          //弹框默认背景颜色
						modalBg: 'rgba(0,0,0,0.5)', //遮罩默认背景颜色
						closeBg: '#ccc',        //弹框关闭按钮默认背景颜色
						width: 300,             //弹框默认宽度
						height: 270,            //弹框默认高度
						positions: 'center',    //弹框位置(默认center：居中，top：顶部居中，left：顶部居左)
						triggerEvent: 'click',  //触发方式(默认click：点击，mouseenter：悬浮)
						effect: 'hide',         //弹框关闭效果(默认hide，淡出关闭：fadeOut)
						resetForm: true,        //是否清空表单(默认true：清空，false：不清空)
						modalHide: true,        //是否点击遮罩背景关闭弹框(默认true：关闭，false：不可关闭)						
                		closeHide: true,        //是否隐藏关闭按钮(默认true：不隐藏，false：隐藏)
                		escHide: true,          //是否支持ESC关闭弹框(默认true：关闭，false：不可关闭)
                		beforeShow: function(){}, //显示前的回调方法
                		afterHide: function(){}   //隐藏后的回调方法
                	};
					opt = $.extend({}, defaults, options);
					$this.data('hDialog', opt);
                }
                opt = $.extend({}, opt, options);
                $(opt.box).hide(); //隐藏容器
				$this.on(opt.triggerEvent,function() { //元素点击事件
					//重置表单
					if(opt.resetForm) {
						var $obj = $(opt.box);
		           	 	$obj.find('input[type=text],textarea').val('');
		           	 	$obj.find('select option').removeAttr('selected');
		            	$obj.find('input[type=radio],input[type=checkbox]').removeAttr('checked');
		       		}
					
					//支持ESC关闭
					if(opt.escHide) {
						$(document).keyup(function(event){
							 switch(event.keyCode) {
							 	case 27:
							 		methods.close(opt);
							 	break;
							 }
						});
					}
					
					methods.fire.call(this, opt.beforeShow); //调用显示之前回调函数
					methods.add(opt,$this); //显示弹框
					
					//点击关闭事件
					var $close = $('#HCloseBtn'); 
					if(opt.modalHide){ $close = $('#HOverlay,#HCloseBtn'); }
					$close.on('click',function(event) {
						event = event || window.event;
						event.stopPropagation();
						methods.close(opt);
					});
				});
			});
        },
        add: function (o,$this) { //显示弹框
        	var w,h,t,l,m; $obj = $(o.box); title = o.title; c = $this.attr("class"); modalBg = o.modalBg; closeBg = o.closeBg;
			w = o.width != undefined ? parseInt(o.width) : '300';
			h = o.height != undefined ? parseInt(o.height) : '270';
			m = ""+(-(h/2))+'px 0 0 '+(-(w/2))+"px";
			
			//弹框位置
			switch (o.positions) {
				case 'center': 
					t = l = '50%'; 
				break;
				case 'top': 
					t = 0; l = '30%'; m = "0 0 0 "+(-(w/2))+"px";
				break;
				case 'left': 
					t = l = m = 0;
				break;
				default: 
					t = l = '50%'; 
			}
					
        	methods.remove('#HOverlay,#HCloseBtn,#HTitle');
			$body.stop().append("<div id='HOverlay' style='width:"+_doc.width()+"px;height:"+_doc.height()+"px;background-color:"+modalBg+";position:fixed;top:0;left:0;z-index:9999;'></div>");				
			if(o.title != ''){ $obj.stop().prepend('<div id="HTitle" style="padding:10px 45px 10px 12px;border-bottom:1px solid #ddd;background-color:#f2f2f2;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">'+o.title+'</div>'); }
			if(o.closeHide != false){
				$obj.stop().append('<a id="HCloseBtn" title="关闭" style="width:24px;height:24px;line-height:18px;display:inline-block;cursor:pointer;background-color:'+closeBg+';color:#fff;font-size:1.4em;text-align:center;position:absolute;top:8px;right:8px;"><span style="width:24px;height:24px;display:inline-block;">×</span></a>').css({'position':'fixed','backgroundColor':o.boxBg,'top':t,'left':l,'margin':m,'zIndex':'100000'});
			}
			$obj.stop().animate({'width':o.width,'height':o.height},300).removeAttr('class').addClass('animated '+c+'').show();
        },
        close: function (o, urls) { //关闭弹框
        	var $obj = $(o.box);
        	//关闭效果
			switch(o.effect){
				case "hide":
					$obj.stop().hide(_effect);
				break;
				case "fadeOut":
					$obj.stop().fadeOut(_effect);
				break;
				default:
					$obj.stop().hide(_effect);
			}
        	
	    	function _effect() {
	    		methods.remove('#HOverlay,.HTooltip');
				$(this).removeAttr('class').removeAttr('style').addClass('animated').hide(); 
	    		if(urls != undefined) { setTimeout(function() { window.location.href = urls; },1000); }
	    		methods.fire.call(this, o.afterHide); //隐藏后的回调方法
			}
	    },
        remove: function (a) { //移除元素
        	$(a).remove();
        },
	    fire: function (event, data) { //调用回调函数 
			if($.isFunction(event)) { return event.call(this, data); }
	    }
    };
    
	$.fn.hDialog = function (method) {
		if(methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        }else if(typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        }else {
            $.error('Error! Method' + method + 'does not exist on jQuery.hDialog！');
        }
	};
	
	/**
	+----------------------------------------------------------
	* 弹出层内置扩展
	+----------------------------------------------------------
	*/
	$.extend({  
		/**
		 * @decription 给方法添加加载函数
		 * @param t : string 加载文字
		 * @param w : string 加载框宽度
		 * @param h : string 加载框高度
		 */
	    showLoading: function (t, w, h) { //显示加载
        	t = t != undefined ? t : '正在加载...';
        	w = w != undefined ? parseInt(w) : '90';
        	h = h != undefined ? parseInt(h) : '30';
        	var margin = ""+(-(h/2))+'px 0 0 '+(-(w/2))+"px";
        	methods.remove('#HLoading');
        	$body.stop().append('<div id="HLoading" style="width:'+w+'px;height:'+h+'px;line-height:'+h+'px;background:rgba(0,0,0,0.6);color:#fff;text-align:center;position:fixed;top:30%;left:50%;margin:'+margin+';">'+t+'</div>');
	    },
	    hideLoading: function () { //移除加载
        	methods.remove('#HLoading');
        },
        /**
		 * @decription 给方法添加提示函数
		 * @param t1 : string 提示文字
		 * @param t2 : string 提示时间
		 * @param t3 : boolean 提示类型，默认为false
		 */
        tooltip: function (t1, t2, t3) {
        	t1 = t1 != undefined ? t1 : '哎呀，出错啦 ！';
        	t2 = t2 != undefined ? parseInt(t2) : 2500;
        	var tip = '<div class="HTooltip shake animated" style="width:280px;padding:10px;text-align:center;background-color:#D84C31;color:#fff;position:fixed;top:10px;left:50%;z-index:100001;margin-left:-150px;box-shadow:1px 1px 5px #333;-webkit-box-shadow:1px 1px 5px #333;">'+t1+'</div>';
    		if(t3) { tip = '<div class="HTooltip fadeIn animated" style="width:280px;padding:10px;text-align:center;background-color:#5cb85c;color:#fff;position:fixed;top:10px;left:50%;z-index:100001;margin-left:-150px;box-shadow:1px 1px 5px #333;-webkit-box-shadow:1px 1px 5px #333;">'+t1+'</div>'; }
    		methods.remove('.HTooltip');
    		$body.stop().append(tip);
    		setTimeout(function() { methods.remove('.HTooltip'); },t2);
        }
	});
})(jQuery, window, document);
