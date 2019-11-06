SPA_RESOLVE_INIT = function(transition) { 
	    // 系统公告
        document.getElementById('HBox').style.display="none";
        $("#example thead tr").html("");
        $("#example tbody tr").html("");
        var dataTable;
        $(document).ready(function() {
        $.ajax({
                   type: "get",
                   url: "json/swapBody1.json",
                   success: function(data){
                    console.log(data)
                    dataTable =data;
                    },
            });
            $.ajax({
              type: "get",
              url: "json/swapHead1.json",
              success: function(columName){
    for(var i =0;i<columName.length; i++){
    $("#example thead tr").append("<th>"+columName[i].data+"</th>");}
    var table = $('#example').DataTable( {
        ordering: false,//是否启用排序
        searching: true,//搜索
        language: {
        lengthMenu: '显示 <select style="height: 35px;width: 100px;font-size:14px" >'+
        '<option value="5" style="font-size:14px">5</option>'+
        '<option value="10" style="font-size:14px">10</option>'+
        '<option value="20" style="font-size:14px">20</option>'+
        '<option value="30" style="font-size:14px">30</option>'+
        '<option value="40" style="font-size:14px">40</option>'+
        '<option value="50" style="font-size:14px">50</option>'+
        '<option value="-1" style="font-size:14px">不限</option>'+
        '</select>',
        
         paginate: {//分页的样式内容。
         previous: "上一页",
         next: "下一页",
         first: "第一页",
         last: "最后"
              },
    zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
    //下面三者构成了总体的左下角的内容。
    info: "总共_PAGES_ 页，显示第_START_ 到第 _END_ 共_MAX_ 条记录 ",//左下角的信息显示，大写的词为关键字。
    infoEmpty: "0条记录",//筛选为空时左下角的显示。
    infoFiltered: ""//筛选之后的左下角筛选提示，
    },
    paging:true,
    pagingType: "full_numbers",//分页样式的类型
    data: dataTable,
    lengthChange: true,
    columns:columName,

    'dom': '<"right">tip',

    buttons: [ 'excel'],
    createdRow : function ( row, data, index ) {
    $('td', row).css("text-align","center").css('font-size','12px');},
        
    } );
                },
            }); 
        } );

	console.log("首页回调" + JSON.stringify(transition))
}