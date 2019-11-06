var array = new Array();
$(function(){
	$.ajax({
		type: 'get',
		url: ctx +"system/notice/all_list",
		data: {},
		async: false,
		success:function(result){
			if(result.code == 0){
				array = result.data;
			}
		 }
	});
	if(array.length == 1){
		$("<li class='line-limit-length'>" + array[0].noticeTitle +"</li>").appendTo($("#announce"));
	}else if(array.length > 1){
		announceArray();
		announceNoticeList();
	}
	
})
    
/***
 * 循环跳动
 */
function announceArray(){
	if(array.length > 0){
		for(var i = 0; i < array.length; i++){
			$("<a  onclick='showMessage("+array[i].noticeId+");'><li class='line-limit-length' style='color:#FFF;font-size: 13px;'>"+ array[i].noticeTitle +"</li></a>").appendTo($("#announce"));
		}
	}else{
		$("<li>暂无消息!</li>").appendTo($("#announce"));
	}
}

function showMessage(noticeId){
	var url = ctx +"system/notice/showNotice/"+noticeId;
	$.modal.openNoBtn("系统通知",url)
}
/***
 * 消息滚动
 */
function announceNoticeList(){
	//滚动公告
    var $noticeList = $(".notice-list");
    var $noticeItems = $noticeList.children();
    var noticeIndex = 0;
    $noticeItems.css("position", "absolute").slice(1).remove();

    function noticeList() {
    	$noticeItems.eq(noticeIndex).animate({
    		top: "-100%"
    	}, 400, function() {
    		$(this).remove();
    	});
    	noticeIndex = noticeIndex >= $noticeItems.length - 1 ? 0 : noticeIndex + 1;
    	$noticeItems.eq(noticeIndex).css("top", "100%").appendTo($noticeList).animate({
    		top: 0
    	}, 400);
    };
    $noticeList.hover(function() {
        clearInterval(this.timer);
    }, function() {
        this.timer = setInterval(noticeList, 5e3);
    }).triggerHandler("mouseleave");
}
