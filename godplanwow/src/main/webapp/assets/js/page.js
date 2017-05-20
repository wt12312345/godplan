/**初始化对象,分页对象*/
function Page(action,form){
	this.zysubtnObj=$("#zysubtn");//显示几分之几页
	this.currentObj=$("#current");
	this.totalPageObj=$("#totalPage");
	this.sizeObj=$("#size");
	this.totalObj=$("#total");
	this.recountObj=$("#recount");
	this.frmObj=form;
	this.current=parseInt(this.currentObj.val());//当前页
	this.totalPage=parseInt(this.totalPageObj.val());//总页数
	this.size=parseInt(this.sizeObj.val());//每页条数
	this.total=parseInt(this.totalObj.val());//总条数
	this.recount=this.recountObj.val();//是否重新字段总条数
	this.digit=0;
	this.action=action;
	this.zysubtnObj.attr("placeholder", this.current+'/'+this.totalPage);
	this.goUrl=function goUrl(num){
		this.current=parseInt(num);
		this.currentObj.val(this.current);
		this.frmObj.attr("action", this.action);
		this.frmObj.submit();
	};
	this.doQuery=function(){
		this.recount=true;
		this.recountObj.val(this.recount);
		this.goUrl(1);
	};
	this.doPage=function(num){
		this.goUrl(num);
	};
	this.zdbtn=function zdbtn(){
		if(this.zysubtnObj.val()==""){
			this.zysubtnObj.focus();
		}
		var zdconut=this.zysubtnObj.val();
		if(!isNaN(zdconut)&&zdconut>=1&&zdconut<=this.totalPage){
			this.goUrl(zdconut);
		}else{
			this.zysubtnObj.css("background-color","#fcc");
		}
		this.zysubtnObj.val("");
	};
	this.fengpage=$("#fengpage");
	this.goprev=$("#goprev");
	this.gonext=$("#gonext");
	this.zysu=$("#zysu");
	//初始化的一些操作
	if(this.current==1){
		this.goprev.attr("class","disabled");
	}else{
		this.goprev.click(function(){
			doPage(parseInt($("#current").val())-1);
		});
	}
	if(this.current==this.totalPage){
		this.gonext.attr("class","disabled");
	}else{
		this.gonext.click(function(){
			doPage(parseInt($("#current").val())+1);
		});
	}
	if(this.current<10){
		this.digit=1;
	}else if(this.current==this.totalPage){
		this.digit=parseInt(this.current/10)*10-9;
	}else{
		this.digit=parseInt(this.current/10)*10;
	}
	for(var i=this.digit;i<this.digit+10&&i<=this.totalPage;i++){
		var li=document.createElement('li');
		var a=document.createElement('a');
		a.href='#;return false;';
		if(i==this.current){
			a.innerHTML=i;
			li.className='active';
		}else{
			a.innerHTML=i;
			!function(i){a.onclick=function(){doPage(i);};}(i);
		}
		li.appendChild(a);
		$(li).insertBefore(this.gonext);
	}
}
