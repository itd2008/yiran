;(function($){

  $.fn.lubo=function(options){

    // var defaults={

    // }

    // //通过覆盖来传参数
    // var options=$.extend(defaults,options);

    return this.each(function(){

      var _lubo=jQuery('.lubo');

      var _box=jQuery('.lubo_box');

      var _this=jQuery(this); //

      var luboHei=_box.height();

      var Over='mouseover';

      var Out='mouseout';

      var Click='click';

      var Li="li";

      var _cirBox='.cir_box';

      var cirOn='cir_on';

      var _cirOn='.cir_on';

      var cirlen=_box.children(Li).length; //圆点的数量  图片的数量

      var luboTime=2000; //轮播时间

      var switchTime=400;//图片切换时间

      cir();

      Btn();

      //根据图片的数量来生成圆点

      function cir(){

        _lubo.append('<ul class="cir_box"></ul>');

        var cir_box=jQuery('.cir_box');

        for(var i=0; i<cirlen;i++){

          cir_box.append('<li style="" value="'+i+'"></li>');
        }

        var cir_dss=cir_box.width();

        cir_box.css({

          left:'50%',

          marginLeft:-cir_dss/2,

          bottom:'10%'

        });

        cir_box.children(Li).eq(0).addClass(cirOn);

      }

      //生成左右按钮

      function Btn(){

        _lubo.append('<div class="lubo_btn"></div>');

        var _btn=jQuery('.lubo_btn');

        _btn.append('<div class="left_btn"><</div><div class="right_btn">></div>');

        var leftBtn=jQuery('.left_btn');

        var rightBtn=jQuery('.right_btn');

        //点击左面按钮

        leftBtn.bind(Click,function(){

          var cir_box=jQuery(_cirBox);

          var onLen=jQuery(_cirOn).val();

          _box.children(Li).eq(onLen).stop(false,false).animate({

            opacity:0

          },switchTime);

          if(onLen==0){

            onLen=cirlen;

          }

          _box.children(Li).eq(onLen-1).stop(false,false).animate({

            opacity:1

          },switchTime);

          cir_box.children(Li).eq(onLen-1).addClass(cirOn).siblings().removeClass(cirOn);

        })

        //点击右面按钮

        rightBtn.bind(Click,function(){

          var cir_box=jQuery(_cirBox);

          var onLen=jQuery(_cirOn).val();

          _box.children(Li).eq(onLen).stop(false,false).animate({

            opacity:0

          },switchTime);

          if(onLen==cirlen-1){

            onLen=-1;

          }

          _box.children(Li).eq(onLen+1).stop(false,false).animate({

            opacity:1

          },switchTime);

          cir_box.children(Li).eq(onLen+1).addClass(cirOn).siblings().removeClass(cirOn);

        })
      }

      //定时器

      int=setInterval(clock,luboTime);

      function clock(){

        var cir_box=jQuery(_cirBox);

        var onLen=jQuery(_cirOn).val();

        _box.children(Li).eq(onLen).stop(false,false).animate({

          opacity:0

        },switchTime);

        if(onLen==cirlen-1){

          onLen=-1;

        }

        _box.children(Li).eq(onLen+1).stop(false,false).animate({

          opacity:1

        },switchTime);

        cir_box.children(Li).eq(onLen+1).addClass(cirOn).siblings().removeClass(cirOn);

      }

      // 鼠标在图片上 关闭定时器

      _lubo.bind(Over,function(){

        clearTimeout(int);

      });

      _lubo.bind(Out,function(){

        int=setInterval(clock,luboTime);

      });

      //鼠标划过圆点 切换图片

      jQuery(_cirBox).children(Li).bind(Over,function(){

        var inde = jQuery(this).index();

        jQuery(this).addClass(cirOn).siblings().removeClass(cirOn);

        _box.children(Li).stop(false,false).animate({

          opacity:0

        },switchTime);

        _box.children(Li).eq(inde).stop(false,false).animate({

          opacity:1

        },switchTime);

      });


    });

  }

})(jQuery);


/*调用*/

// <script type="text/javascript">

// $(function(){

//     $(".lubo").lubo({

//     });

// })

// </script>



/*css样式*/

// <style type="text/css">
//   *{ margin:0; padding:0; }
//   img{ display: block; border:none;}
//   ul,li{ list-style: none;}
//   .lubo{ width: 100%;clear: both; position: relative; height:368px;}
//   .lubo_box{ position: relative; width: 100%; height:368px; }
//   .lubo_box li{ float: left;position: absolute; top: 0; left: 0; width: 100%; height:368px; opacity: 0;filter:alpha(opacity=0);}
//   .lubo_box li img{ width: 100%; height: 368px;}

//   /*圆点*/
//   .cir_box{ overflow: hidden; position: absolute; z-index: 100;}
//   .cir_box li{ float: left; width: 30px; height: 5px; margin:0 5px; cursor: pointer; background: #fff; opacity: 0.8;filter:alpha(opacity=80);}
//   .cir_on{ background: #000 !important;}

//   /*按钮*/
//   .lubo_btn{ position: absolute; width: 100%; top: 140px;}
//   .left_btn, .right_btn{ width: 30px; height: 80px; background: #000;opacity: 0.8;filter:alpha(opacity=80); cursor: pointer; color: #fff; line-height: 80px; font-size: 30px; text-align: center;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;}
//   .left_btn{ float: left;}
//   .right_btn{ float: right;}
// </style>

/*html*/
// <div class="lubo">
//   <ul class="lubo_box">
//     <li style=" opacity: 1;filter:alpha(opacity=100);"><a href=""><img src="1.jpeg"></a></li>
//     <li><a href=""><img src="2.jpeg"></a></li>
//     <li><a href=""><img src="3.jpeg"></a></li>
//     <li><a href=""><img src="4.jpeg"></a></li>
//     <li><a href=""><img src="5.jpeg"></a></li>
//     <li><a href=""><img src="2.jpeg"></a></li>
//     <li><a href=""><img src="3.jpeg"></a></li>
//     <li><a href=""><img src="4.jpeg"></a></li>
//     <li><a href=""><img src="5.jpeg"></a></li>
//   </ul>
// </div>


