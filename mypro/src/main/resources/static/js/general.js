var App = function () {
  var config = {//Basic Config
    tooltip: true,
    popover: true,
    nanoScroller: true,
    nestableLists: true,
    hiddenElements: true,
    bootstrapSwitch:true,
    dateTime:true,
    tags:true
  }; 
  
  /*Speech Recognition*/
  var speech_commands = [];
      function toggleSideBar(_this){
        var b = $("#sidebar-collapse")[0];
        var w = $("#cl-wrapper");
        var s = $(".cl-sidebar");
        if(w.hasClass("sb-collapsed")){
          $(".fa",b).addClass("fa-angle-left").removeClass("fa-angle-right");
          w.removeClass("sb-collapsed");
        }else{
          $(".fa",b).removeClass("fa-angle-left").addClass("fa-angle-right");
          w.addClass("sb-collapsed");
        }
      }
  return {
    init: function (options) {
      $.extend( config, options );
      
      /*VERTICAL MENU*/
      $(".cl-vnavigation li ul").each(function(){
          $(this).parent().addClass("parent");
      });
      
      $(".cl-vnavigation li ul li.active").each(function(){
          $(this).parent().show().parent().addClass("open");
      });
      
      $(".cl-vnavigation").delegate(".parent > a","click",function(e){
        $(".cl-vnavigation .parent.open > ul").not($(this).parent().find("ul")).slideUp(300, 'swing',function(){
            $(this).parent().removeClass("open");
        });
        
        var ul = $(this).parent().find("ul");
        ul.slideToggle(300, 'swing', function () {
          var p = $(this).parent();
          if(p.hasClass("open")){
             p.removeClass("open");
          }else{
             p.addClass("open");
          }
           $("#cl-wrapper .nscroller").nanoScroller({ preventPageScrolling: true });
        });
        e.preventDefault();
      });
      $(".cl-vnavigation .parent.open ul li").click(function(){
       	  $(this).addClass('active').siblings().removeClass('active');
       })
      /*Small devices toggle*/
      $(".cl-toggle").click(function(e){
    	 
        var ul = $(".cl-vnavigation");
            ul.slideToggle(300, 'swing', function () {
        });
        e.preventDefault();
      });
      
      /* 菜单隐藏 */
      $("#sidebar-collapse").click(function(){
          toggleSideBar();
      });
      
      
      if($("#cl-wrapper").hasClass("fixed-menu")){
        var scroll =  $("#cl-wrapper .menu-space");
        scroll.addClass("nano nscroller");
 
        function update_height(){
          var button = $("#cl-wrapper .collapse-button");
          var collapseH = button.outerHeight();
          var navH = $("#head-nav").height();
          var height = $(window).height() - ((button.is(":visible"))?collapseH:0) - navH;
          scroll.css("height",height);
          $("#cl-wrapper .nscroller").nanoScroller({ preventPageScrolling: true });
        }
        
        $(window).resize(function() {
          update_height();
        });    
            
        update_height();
        $("#cl-wrapper .nscroller").nanoScroller({ preventPageScrolling: true });
        
      }else{
	        $(window).resize(function(){
	        }); 
      }

      
       // 回到顶部
      var offset = 220;
      var duration = 500;
      var button = $('<a href="#" class="back-to-top"><i class="fa fa-angle-up"></i></a>');
      button.appendTo("body");
      
      jQuery(window).scroll(function() {
        if (jQuery(this).scrollTop() > offset) {
            jQuery('.back-to-top').fadeIn(duration);
        } else {
            jQuery('.back-to-top').fadeOut(duration);
        }
      });
    
      jQuery('.back-to-top').click(function(event) {
          event.preventDefault();
          jQuery('html, body').animate({scrollTop: 0}, duration);
          return false;
      });
      
      
      
    },
      
    widgets: function(){
      widgets();
    }
  };
 
}();

$(function(){
    var top = getTopWinow();
  //$("body").animate({opacity:1,'margin-left':0},500);
  $("body").css({opacity:1,'margin-left':0});
  $.ajaxSetup( {
	    url: "/" , // 默认URL
	    aysnc: false , // 默认同步加载
	    type: "POST" , // 默认使用POST方式
	    cache : false,//false是不缓存，true为缓存
	    error: function(data, textStatus, errorMsg){ // 出错时默认的处理函数
	       console.log(data.status);
            if(data.status == 12022||data.status == 12023){
   		     toastr.error(JSON.parse(data.responseText).msg);
   	       }else if (data.status == 401) {//未授权
                top.location.href = '/login';
           } else if(data.status == 403){//没有权限
                top.location.href = '/error';
           } else if(data.status == 404){
	             toastr.error('URL NOT FOUND!');
           } else if(data.status == 500){
           	     toastr.error('系统异常,请稍后重试~~');
           }
	    	// jqXHR 是经过jQuery封装的XMLHttpRequest对象
	        // textStatus 可能为： null、"timeout、"error"、"abort"或"parsererror"
	        // errorMsg 可能为： "Not Found"、"Internal Server Error"等
	    },complete : function(data, TS) {
	    	console.log("当前执行status : "+data.status);
	    	$.mask_close_all();
	    }
	} );
});
//定义命名空间
$.namespace = function() {
    var a=arguments, o=null, i, j, d;
    for (i=0; i<a.length; i=i+1) {
        d=a[i].split(".");
        o=window;
        for (j=0; j<d.length; j=j+1) {
            o[d[j]]=o[d[j]] || {};
            o=o[d[j]];
        }
    }
    return o;
};

function getTopWinow(){
    var p = window;
    while(p != p.parent){
        p = p.parent;
    }
    return p;
}

