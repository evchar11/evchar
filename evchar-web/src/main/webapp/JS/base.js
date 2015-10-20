//所有页面延时加载
$(function() {
  $("img").lazyload({ 
  placeholder : "IMG/loading.gif",
         effect: "fadeIn"
   });  
});
//多页
var crturl='';//当前链接
var dftanchor='anchor_1';
var crtanchor='';var nmb='';

var crtanchor='';var startanchor=1;var endanchor=$('.acr').length;

function svrst(ca){//svrst several set; ca crtanchor
	//控制右侧导航栏
	$("a[href=#"+ca+"]").parent().parent().find('span').removeClass('active');
	$("a[href=#"+ca+"]").children('span').addClass('active');
	//控制下部向下按钮
	if(ca.split('_')[1]==endanchor){
		$('.nxt').hide();
	}else{
		$('.nxt').show();
	}

}

//初始化
crturl=window.location.href;
if(crturl.indexOf('#')==-1){
	crtanchor=dftanchor;
}else{
	crtanchor=crturl.split('#')[1];
	if(crtanchor.indexOf('anchor')==-1){
		crtanchor=dftanchor;
	}
}
svrst(crtanchor);

$body=$('body');

$body.mousewheel(function(y) {

	
	
	if(y<0){//说明向下
		//根据当前锚点塑造需要跳转的锚点
		nmb=crtanchor.split('_')[1];//编号
		if(nmb==endanchor){//若目前已经到底就不要往下了
			crtanchor='anchor_'+nmb;
		}else{
			crtanchor='anchor_'+(parseInt(nmb)+1);
		}
		
		
	}else if(y>0){
		nmb=crtanchor.split('_')[1];//编号
		if(nmb==startanchor){//若目前已经到头就不要往上了
			crtanchor='anchor_'+nmb;
		}else{
			crtanchor='anchor_'+(parseInt(nmb)-1);
		}
		

	}
	
	$("a[href=#"+crtanchor+"]").trigger('click');
	//不论如何值都是在trigger后的链接中获取，因为那东西只能一格格递增的，暂不明
	crturl=window.location.href;
	crtanchor=crturl.split('#')[1];
	svrst(crtanchor);
	


});
$body.mousewheel('preventDefault', false);


$(function(){
	
	$('.nxt').click(function(){
		
  	
		//根据当前锚点塑造需要跳转的锚点
		nmb=crtanchor.split('_')[1];//编号
		if(nmb==endanchor){//若目前已经到底就不要往下了
			crtanchor='anchor_'+nmb;
		}else{
			crtanchor='anchor_'+(parseInt(nmb)+1);
		}
	  		
	  		
	  	
	  	
	  	$("a[href=#"+crtanchor+"]").trigger('click');
	  	//不论如何值都是在trigger后的链接中获取，因为那东西只能一格格递增的
	  	crturl=window.location.href;
	  	crtanchor=crturl.split('#')[1];
	  	svrst(crtanchor);
	  	
	})
	
	$('.idct').click(function(event){
		
		event.stopPropagation();//点击他不会触发其他的各种父辈的东东
		event.preventDefault();//点击阻止自己默认的，一般跟着stopPropagation，但是这里其实用不到，因为确实点击他本来就没行动
		//其实以上两点就拒绝了所有的行为了。。
		$(this).parent().trigger('click');
		crturl=window.location.href;
	  	crtanchor=crturl.split('#')[1];
	  	svrst(crtanchor);
	  	
	})


	$(window).resize(function() {
		adjstrlsize();
	});

})
  
function adjstrlsize(){
	$('#roll').height($(window).height());
}

adjstrlsize();