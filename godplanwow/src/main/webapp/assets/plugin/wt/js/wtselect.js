var wtSelect = {};
wtSelect.Init = function(_div, _dataArr, args) {
	$("#" + _div).addClass("wt-sel");
	var optshtmls = "<ol class='wt-sel-opts'>";
	for (var j = 0; j < _dataArr.length; j++) {
		if (j % 2 == 0) {
			optshtmls += "<li class='wt-sel-opt wt-sel-opt2'>" + _dataArr[j]
					+ "</li>";

		} else {
			optshtmls += "<li class='wt-sel-opt'>" + _dataArr[j] + "</li>";
		}
	}
	var defaultValue = "请选择";
	if (args.value) {
		defaultValue = args.value;
	}

	optshtmls += "</ol>";
	var resultHtml = "";
	if (args.inputname) {
		resultHtml = '<input type="hidden" name="' + args.inputname + '"';
		if (args.value) {
			resultHtml += ' value="' + defaultValue + '"';
		}
		resultHtml += ' />';
	}
	resultHtml = resultHtml
			+ '<div class="wt-sel-optarr"></div><div class="wt-sel-optvalue">'
			+ defaultValue + '</div>' + optshtmls;

	$("#" + _div).html(resultHtml);
	var ol = $("#" + _div + " .wt-sel-opts");
	$("#" + _div + " .wt-sel-optarr,#" + _div + " .wt-sel-optvalue").click(
			function() {
				ol.toggle();
			});
	$("#" + _div + " .wt-sel-opt").click(function() {
		var text = $(this).html();
		$("#" + _div + " .wt-sel-optvalue").html(text);
		$("#" + _div + " input").val(text);
		$("#" + _div + " .wt-sel-opts").css("display", "none");
		if (args.callback) {
			args.callback();
		}
	});
	
	$("body").click(function (e) {
	    if (!$(e.target).closest("#" + _div).length) {
	    	ol.css("display", "none");
	    }
	});
};