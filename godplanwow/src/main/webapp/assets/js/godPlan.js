var wwwPath = "http://" + window.location.host + '/';
var imgPath = wwwPath + "trends/img/";
var asstesImg = wwwPath + "assets/img/";

var godPlan = {
	ShowFxWin : function() {
		var html = '<div class="wt-win-bak"></div><div class="wt-win fxwin" onclick="wtHideWin()"><img class="fximg" alt="" src="'
				+ imgPath + 'fx.png" /></div></div>';
		$("body").append(html);
	},
	wxjsTitle : "",
	wxjsDesc : "",
	wxjsImgUrl : "",
	wxjsUrl : "",
	fxSucFun : null,
	GetWxJs : function(title, desc, url, wxjsImgUrl, fxSucFun) {
		godPlan.wxjsTitle = title;
		godPlan.wxjsDesc = desc;
		godPlan.wxjsImgUrl = wxjsImgUrl;
		godPlan.wxjsUrl = url;
		wtJquery.Ajax(wwwPath + "wx/getWxSdk", "url="
				+ location.href.split('#')[0], function(data) {
			var args = {
				appId : data.obj.appId,
				nonceStr : data.obj.nonceStr,
				timestamp : data.obj.timestamp,
				signature : data.obj.signature
			};
			wtWx.SetWxJsConfig(1, args, godPlan.initWxJsSuc);
		});
		if (fxSucFun) {
			godPlan.fxSucFun = fxSucFun;
			// 回调里有参数arg,1 朋友，2 朋友圈，3 QQ，4 QQ微博
		}
	},
	initWxJsSuc : function() {
		wtWx.WxFx(godPlan.wxjsTitle, godPlan.wxjsDesc, godPlan.wxjsUrl,
				godPlan.wxjsImgUrl, godPlan.fxSucFun, null);
	}
};

var chart = {
	bar : function(_id, title, titleSub, arrX, arrData) {
		var myChart = echarts.init(document.getElementById(_id));
		var option = {
			title : {
				text : title,
				subtext : titleSub,
				left : 'center'
			},
			color : [ '#3398DB' ],
			tooltip : {
				trigger : 'axis',
				axisPointer : {
					type : 'shadow'
				},
				formatter : function(params) {
					return params[0].name + '：' + params[0].value;
				}
			},
			grid : {
				x : 100,
				left : '3%',
				right : '3%',
				bottom : '3%',
				containLabel : true
			},

			xAxis : [ {
				type : 'category',
				data : arrX,
				axisTick : {
					alignWithLabel : true
				}
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				// name : '直接访问',
				type : 'bar',
				barWidth : '60%',
				data : arrData,
				itemStyle : {
					normal : {
						color : '#c23531',
						shadowColor : 'rgba(0, 0, 0, 0.7)',
						shadowBlur : 10
					}
				},
				label : {
					normal : {
						show : true,
						formatter : function(params) {
							return params.value;
						}
					}
				}
			} ]
		};
		myChart.setOption(option);
	},
	line : function(_id, title, titleSub, arrX, arrData) {
		var myChart = echarts.init(document.getElementById(_id));
		var option = {
			title : {
				text : title,
				subtext : titleSub,
				left : 'center'
			},
			color : [ '#3398DB' ],
			tooltip : {
				trigger : 'axis',
				axisPointer : {
					type : 'shadow'
				},
				// 动画
				axisPointer : {
					animation : false
				},
				formatter : function(params) {
					return params[0].name + '：' + params[0].value;
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : {
				type : 'category',
				data : arrX,
				axisLabel : {
					formatter : function(value, idx) {
						// 这个idx是序号，0开始
						return value;
					}
				},
				splitLine : {
					show : false
				},
				boundaryGap : false
			},
			yAxis : {
				axisLabel : {
					formatter : function(val) {
						return val;
					}
				},
				splitNumber : 3,
				splitLine : {
					show : false
				}
			},
			series : [ {
				type : 'line',
				smooth : true,
				data : arrData,
				hoverAnimation : false,
				symbolSize : 6,
				itemStyle : {
					normal : {
						color : '#c23531'
					}
				},
				lineStyle : {
					normal : {
						shadowColor : 'rgba(0, 0, 0, 0.8)',
						shadowBlur : 50,
						width : 5
					}
				},
				label : {
					normal : {
						show : true,
						position : 'top'
					}
				}
			} ],
		};
		myChart.setOption(option);
	},
	/**
	 * 
	 * @param _id
	 * @param title
	 * @param titleSub
	 * @param arrX ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
	 * @param arrData [{value: 10, name: 'rose1' }, { value: 5, name: 'rose2' }]
	 */
	pie : function(_id, title, titleSub, arrX, arrData,hasLegent) {
		var myChart = echarts.init(document.getElementById(_id));
		var option = {
			title : {
				text : title,
				subtext : titleSub,
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				show : hasLegent,
				orient : "vertical",
				x : "left",
				y : "center",
				data : arrX
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'pie', 'funnel' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			series : [ {
				name : title,
				type : 'pie',
				radius : '50%',
				center : [ '50%', '50%' ],
				roseType : 'radius',
				label : {
					normal : {
						show : true,
					},
					emphasis : {
						show : true
					}
				},
				lableLine : {
					normal : {
						show : false,
						smooth : 0.2
					},
					emphasis : {
						show : true
					}
				},
				itemStyle : {
					normal : {
						shadowBlur : 150,
						shadowColor : 'rgba(0, 0, 0, 0.3)'
					}
				},
				data : arrData
			} ]
		};
		myChart.setOption(option);
	}
};