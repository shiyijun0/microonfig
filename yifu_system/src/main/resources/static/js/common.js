$(function() {
	$(window).resize(infinite);
	function infinite() {
		var htmlWidth = $("html").width();
		if(htmlWidth >= 1080) {
			$("html").css({
				"font-size" : "42px"
			});
		}else {
			$("html").css({
				"font-size" : 42/ 1080 * htmlWidth + "px"
			});
		}
	}infinite();
})
//流量统计
var _hmt = _hmt || [];
(function() {
	var hm = document.createElement("script");
	hm.src = "https://hm.baidu.com/hm.js?fc26bc27be50cc6ec016951eaecd87e9";
	var s = document.getElementsByTagName("script")[0];
	s.parentNode.insertBefore(hm, s);
})();