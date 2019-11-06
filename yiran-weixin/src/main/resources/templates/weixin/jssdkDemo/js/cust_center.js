

window.onload = function () {
  var tag =  GetQueryString("tag");
  if(tag == null || tag != 0){
    tofirstPage();
  }
  console.log("enter"+tag);

};
