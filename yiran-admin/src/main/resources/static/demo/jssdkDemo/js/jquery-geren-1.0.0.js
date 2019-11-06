
/**
 * 调用之前请注意引入CryptoJS AES加密
 */
var AESEncrypt = function(word, key) {
	if (CryptoJS) {
		var key = CryptoJS.enc.Utf8.parse(key);
		var srcs = CryptoJS.enc.Utf8.parse(word);
		var encrypted = CryptoJS.AES.encrypt(srcs, key, {
			mode : CryptoJS.mode.ECB,
			padding : CryptoJS.pad.Pkcs7
		});
		return encrypted.toString();
	} else
		alert("页面未加载CryptoJS！");
}

/**
 * Base64加密字符串！
 */
var Base64Encode = function(str) {
	var s = CryptoJS.enc.Utf8.parse(str);
	var base64 = CryptoJS.enc.Base64.stringify(s);
	return  Md5Encode(base64);
}
/**
 * Base64解密字符串
 */
var Base64Decode = function(encodestr) {
	try{
        return CryptoJS.enc.Base64.parse(encodestr).toString(CryptoJS.enc.Utf8);
	}catch (e){
		return "";
	}

}
/**
 * Md5加密
 */
var Md5Encode = function(str) {
	var md5 = CryptoJS.MD5(str).toString();
	return md5.toUpperCase();
}

//是否包含一个字符串
function isContains(str, substr) {
    return str.indexOf(substr) >= 0;
}

//验证码
function isJuge() {
  return "4A17378BAE7198D5D83D592DF5153959";
}


/**
 * data是一个json对象 保存登录信息到本地！
 */
var setUserData = function(data) {
  if (data) {
    var datastr = JSON.stringify(data);
    var base64str = Base64Encode(datastr);
    localStorage.setItem("UERSTR", base64str);
  } else {
    alert("data不是一个有效的字符串！")
  }
}

var getUserObj = function() {
  var userstr = localStorage.getItem("UERSTR");
  if(!userstr || userstr.length==0)
    return {};
  var datastr = Base64Decode(userstr);
  if(datastr.length==0){
    setUserData({});
    return {};
  }
  var obj = $.parseJSON(datastr);
  return obj;
}


//获取地址栏参数

function GetQueryString(name)
{
  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if(r!=null)return  unescape(r[2]); return null;
}


