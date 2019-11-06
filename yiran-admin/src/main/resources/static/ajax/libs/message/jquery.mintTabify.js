(function (window) {
	function mintTabify(el) {
		var tabs = $(el).find(".nav-tabs li").toArray();
		var currentTab = tabs[0];
		var currentPane = document.getElementById(currentTab.getElementsByTagName("a")[0].href.split("#")[1]);

		for (var i = 0, len = tabs.length; i < len; i++) {
			(function (tab) {
				var link = tab.getElementsByTagName("a")[0];
				tab.pane = document.getElementById(link.href.split("#")[1]);
				link.onclick = function () {
					currentTab.className = currentTab.className.replace("active", "");
					currentTab = tab;
					currentTab.className += " active";

					currentPane.className = currentPane.className.replace("active", "");
					currentPane = currentTab.pane;
					currentPane.className += " active";
					return false;
				};
			})(tabs[i]);
		};
	};

	$.fn.mintTabify = function(){
		return this.each(function(){
			mintTabify(this);
		});
	};
})(window);