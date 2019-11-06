
(function($){
  $.fn.num1 = function(){
    n1 = $(this).width();
    h1 = $(this).height();


    $(this).hover(function(){
      var becurr = "background:#0F698A;position:absolute;"
      // top边框
      var divTop ="<div style='"+becurr+"top:-2px;left:-2px;width:0px;height:2px' class='divTop'></div>";

      // right边框
      var divRight ="<div style='"+becurr+"bottom:-2px;right:-2px;width:2px;height:0px;' class='divRight'></div>";

      // Bottom边框
      var divBottom ="<div style='"+becurr+"bottom:-2px;right:-2px;width:0px;height:2px' class='divBottom'></div>";

      // Left边框
      var divLeft ="<div style='"+becurr+"top:-2px;left:-2px;width:2px;height:0px;' class='divLeft'></div>";

      $(this).append(divTop,divRight,divBottom,divLeft);

      $(this).find("div:nth-child(odd)").stop().animate({width:n1+3.5},300);
      $(this).find("div:nth-child(even)").stop().animate({height:h1+3.5},300);
    },function(){
      $(this).find("div:nth-child(odd)").stop().animate({width:0},300);
      $(this).find("div:nth-child(even)").stop().animate({height:0},300,function(){
        $(".num1 .divTop,.num1 .divRight,.num1 .divBottom,.num1 .divLeft").remove()
      });
    })
  }
})(jQuery);


(function($){
  $.fn.num2 = function(){
    $(this).hover(function(){
      // 通用Class
      var becurr = "background:#0F698A;position:absolute;border-radius:10px;opacity:0;"
      // top边框
      var divTop = "<div style='"+becurr+"height:2px;width:50px;top:-2px;left:-80px;' class='divTop'></div";

      // right边框
      var divRight = "<div style='"+becurr+"height:50px;width:2px;top:-80px;right:-2px;' class='divRight'></div";

      // Bottom边框
      var divBottom = "<div style='"+becurr+"height:2px;width:50px;bottom:-2px;right:-80px;' class='divBottom'></div";

      // Left边框
      var divLeft = "<div style='"+becurr+"height:50px;width:2px;bottom:-80px;left:-2px;' class='divLeft'></div";

      $(this).append(divTop,divRight,divBottom,divLeft);

      $(this).find("div:nth-child(1)").stop().show().animate({left:-2,opacity:1},250).fadeOut(0);
      $(this).find("div:nth-child(2)").stop().show().animate({top:-2,opacity:1},250).fadeOut(0);
      $(this).find("div:nth-child(3)").stop().show().animate({right:-2,opacity:1},250).fadeOut(0);
      $(this).find("div:nth-child(4)").stop().show().animate({bottom:-2,opacity:1},250).fadeOut(0);
    },function(){
      $(this).find("div:nth-child(1)").stop().show().animate({left:-80,opacity:0},250).hide(0);
      $(this).find("div:nth-child(2)").stop().show().animate({top:-80,opacity:0},250).hide(0);
      $(this).find("div:nth-child(3)").stop().show().animate({right:-80,opacity:0},250).hide(0);
      $(this).find("div:nth-child(4)").stop().show().animate({bottom:-80,opacity:0},250,function(){
        $(".num2 .divTop,.num2 .divRight,.num2 .divBottom,.num2 .divLeft").remove()
      }).hide(0);
    })
  }
})(jQuery);


(function($){
  $.fn.num3 = function(){
    n1 = $(this).width();
    h1 = $(this).height();


    $(this).hover(function(){
      var becurr = "background:#0F698A;position:absolute;"
      // top边框
      var divTop ="<div style='"+becurr+"top:-2px;left:-2px;width:0px;height:2px' class='divRight'></div>";

      // right边框
      var divRight ="<div style='"+becurr+"top:-2px;right:-2px;width:2px;height:0px;' class='divRight'></div>";

      // Bottom边框
      var divBottom ="<div style='"+becurr+"bottom:-2px;right:-2px;width:0px;height:2px' class='divBottom'></div>";

      // Left边框
      var divLeft ="<div style='"+becurr+"bottom:-2px;left:-2px;width:2px;height:0px;' class='divLeft'></div>";

      $(this).append(divTop,divRight,divBottom,divLeft);

      $(this).find("div:nth-child(odd)").stop().animate({width:n1+3.5},200);
      $(this).find("div:nth-child(even)").stop().animate({height:h1+3.5},200);
    },function(){
      $(this).find("div:nth-child(odd)").stop().animate({width:0},200);
      $(this).find("div:nth-child(even)").stop().animate({height:0},200,function(){
        $(".num3 .divTop,.num3 .divRight,.num3 .divBottom,.num3 .divLeft").remove()
      });
    })
  }
})(jQuery);



(function($){
  $.fn.num4 = function(){
    n1 = $(this).width();
    h1 = $(this).height();


    $(this).hover(function(){
      var becurr = "background:#0F698A;position:absolute;"
      // top边框
      var divTop ="<div style='"+becurr+"top:-2px;left:-2px;width:0px;height:2px' class='divTop'></div>";

      // right边框
      var divRight ="<div style='"+becurr+"top:-2px;right:-2px;width:2px;height:0px;' class='divRight'></div>";

      // Bottom边框
      var divBottom ="<div style='"+becurr+"bottom:-2px;right:-2px;width:0px;height:2px' class='divBottom'></div>";

      // Left边框
      var divLeft ="<div style='"+becurr+"bottom:-2px;left:-2px;width:2px;height:0px;' class='divLeft'></div>";

      $(this).append(divTop,divRight,divBottom,divLeft);

      $(this).find("div").eq(0).stop().animate({width:n1+3.5},200,function(){
        $(this).parent().find("div").eq(1).stop().animate({height:h1+3.5},200,function(){
          $(this).parent().find("div").eq(2).stop().animate({width:n1+3.5},200,function(){
            $(this).parent().find("div").eq(3).stop().animate({height:h1+3.5},200)
          });
        });
      })
    },function(){
      $(this).find("div").stop();
      $(this).find("div").eq(3).stop().animate({height:0},200,function(){
        $(this).parent().find("div").eq(2).stop().animate({width:0},200,function(){
          $(this).parent().find("div").eq(1).stop().animate({height:0},200,function(){
            $(this).parent().find("div").eq(0).stop().animate({width:0},200,function(){
              $(".num4 .divTop,.num4 .divRight,.num4 .divBottom,.num4 .divLeft").remove()
            })
          });
        });
      })

    })
  }
})(jQuery);




(function($){
  $.fn.num5 = function(){
    n1 = $(this).width();
    h1 = $(this).height();


    $(this).hover(function(){
      var becurr = "background:#0F698A;position:absolute;"
      // top边框
      var divTop ="<div style='"+becurr+"top:-2px;left:-2px;width:0px;height:2px' class='divTop'></div>";

      // right边框
      var divRight ="<div style='"+becurr+"top:-2px;right:-2px;width:2px;height:0px;' class='divRight'></div>";

      // Bottom边框
      var divBottom ="<div style='"+becurr+"bottom:-2px;right:-2px;width:0px;height:2px' class='divBottom'></div>";

      // Left边框
      var divLeft ="<div style='"+becurr+"bottom:-2px;left:-2px;width:2px;height:0px;' class='divLeft'></div>";

      $(this).append(divTop,divRight,divBottom,divLeft);

      $(this).find("div:nth-child(odd)").stop().animate({width:n1+3.5},300,function(){
        $(this).parent().find("div:nth-child(even)").stop().animate({height:h1+3.5},300);
      });

    },function(){
      $(this).find("div:nth-child(odd)").stop().animate({width:0},300,function(){
        $(this).parent().find("div:nth-child(even)").stop().animate({height:0},300,function(){
          $(".num5 .divTop,.num5 .divRight,.num5 .divBottom,.num5 .divLeft").remove()
        });
      });

    })
  }
})(jQuery);



(function($){
  $.fn.num6 = function(){
    n1 = $(this).width();
    h1 = $(this).height();
    $(this).hover(function(){
      var becurr = "background:#0F698A;position:absolute;"
      // top边框
      var divTop ="<div style='"+becurr+"top:-2px;left:"+n1/2+"px;width:0;height:2px' class='divTop'></div>";

      // right边框
      var divRight ="<div style='"+becurr+"top:"+h1/2+"px;right:-2px;width:2px;height:0;' class='divRight'></div>";

      // Bottom边框
      var divBottom ="<div style='"+becurr+"bottom:-2px;right:"+n1/2+"px;width:0;height:2px' class='divBottom'></div>";

      // Left边框
      var divLeft ="<div style='"+becurr+"bottom:"+h1/2+"px;left:-2px;width:2px;height:0;' class='divLeft'></div>";

      $(this).append(divTop,divRight,divBottom,divLeft);

      $(this).find("div:nth-child(odd)").stop().animate({width:n1+3.5,left:-2},300);
      $(this).find("div:nth-child(even)").stop().animate({height:h1+3.5,top:-2},300);
    },function(){
      $(this).find("div:nth-child(odd)").stop().animate({width:0,left:n1/2},300);
      $(this).find("div:nth-child(even)").stop().animate({height:0,top:h1/2},300,function(){
        $(".num6 .divTop,.num6 .divRight,.num6 .divBottom,.num6 .divLeft").remove()
      });
    })
  }
})(jQuery);



(function($){
  $.fn.num7 = function(){
    n1 = $(this).width();
    h1 = $(this).height();

    $(this).hover(function(){
      var becurr = "background:#0F698A;position:absolute;"
      // toplf边框
      var divToplf ="<div style='"+becurr+"top:-2px;left:-2px;width:2px;height:2px' class='divToplf'></div>";

      // Rightri边框
      var divRighttop ="<div style='"+becurr+"top:-2px;right:-2px;width:2px;height:2px' class='divRighttop'></div>";

      // topri边框
      var divTopri ="<div style='"+becurr+"top:-2px;right:-2px;width:2px;height:2px' class='divTopri'></div>";

      // right边框
      var divRight ="<div style='"+becurr+"bottom:-2px;right:-2px;width:2px;height:2px;' class='divRight'></div>";

      // Bottom边框
      var divBottom ="<div style='"+becurr+"bottom:-2px;right:-2px;width:2px;height:2px' class='divBottom'></div>";

      // Left边框
      var divLeft ="<div style='"+becurr+"top:-2px;left:-2px;width:2px;height:2px;' class='divLeft'></div>";

      // Leftbottom边框
      var divLeftbottom ="<div style='"+becurr+"bottom:-2px;left:-2px;width:2px;height:2px;' class='divLeftbottom'></div>";

      // Leftbottom边框
      var divRightLeft ="<div style='"+becurr+"bottom:-2px;left:-2px;width:2px;height:2px;' class='divRightLeft'></div>";



      $(this).append(divToplf,divRighttop,divTopri,divRight,divBottom,divLeft,divLeftbottom,divRightLeft);

      $(this).find("div:nth-child(odd)").stop().animate({width:n1/2+3.5},300);
      $(this).find("div:nth-child(even)").stop().animate({height:h1/2+3.5},300);
    },function(){
      $(this).find("div:nth-child(odd)").stop().animate({width:0},300);
      $(this).find("div:nth-child(even)").stop().animate({height:0},300,function(){
        $(".num7 .divToplf,.num7 .divRighttop,.num7 .divTopri,.num7 .divRight,.num7 .divBottom,.num7 .divLeft,.num7 .divLeftbottom,.num7 .divRightLeft").remove()
      });
    })
  }
})(jQuery);


