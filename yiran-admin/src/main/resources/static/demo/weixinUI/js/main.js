
//关闭当前页面
function closeWindows(){
  if (navigator.userAgent.indexOf("MSIE") > 0) {
    if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
      window.opener = null;
      window.close();
    } else {
      window.open('', '_top');
      window.top.close();
    }
  }
  else if (navigator.userAgent.indexOf("Firefox") > 0) {
    window.location.href = 'about:blank ';
  } else {
    //window.opener = null;
    //window.open('', '_self', '');
    //window.close();
    window.location.href="about:blank";
    window.close();
  }
}


function  toBageUrl(){
  location.href = "http://www.busge.com/youtubus/web/jsp/app_download";
}

function  toindex(){
  setTimeout(1000,intoindex());
}
function  intoindex(){
  location.href = "w.html?tag=0";
}

function  tofirstPage(){
  location.href = "jj.html";
}

