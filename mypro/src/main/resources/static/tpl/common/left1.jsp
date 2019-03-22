<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="baseurl" value="${pageContext.request.contextPath}"></c:set>
<body>
<div class="cl-sidebar" data-position="right">
	<div class="cl-navblock">
		<div class="menu-space">
			<div class="content">
				<div class="side-user">
					<div class="avatar">
						<img src="/images/user1.png" alt="Avatar" />
					</div>
					<div class="info">
						<a href="#">${activeUser.tellerName }</a> <img
							src="/images/state_online.png" alt="Status" /> <span>Online</span>
					</div>
				</div>
				<ul class="cl-vnavigation" id="navMenu">
					<li><a href="#"><i class="iconfont">&#xe63a;</i><span>首页</span></a>
					</li>
					<c:forEach items="${activeUser.menus }" var="menus">
						<li >
							<a href="#"><i class="iconfont">${menus.icon }</i><span>${menus.name }</span></a>
							<ul class="sub-menu">
								<c:forEach items="${menus.children }" var="childrenMenus">
									<li id="${childrenMenus.type}${childrenMenus.id}"><a href="${childrenMenus.url }?menuId=${childrenMenus.type}${childrenMenus.id}">
									<i class="iconfont">${childrenMenus.icon }</i>${childrenMenus.name }</a></li>
								</c:forEach>
							</ul>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>
</body>
<script>
	$(function(){
		$("#navMenu li ul li a").click(function(){
	        $.cookie("navstation", $(this).html(), { path: "/" });
	    });
		var navstation = $.cookie("navstation");
		if(navstation != null){
		    $("#navMenu li ul li a").each(function(){
		        if($(this).html() == navstation){
		            $(this).parent().parent().css("display","block");
		        }
		    });
		}
		//定位到登录前的页面
		var menuId = getQueryString('menuId');
		$("#"+menuId).addClass("active");
		var parentid = $("#"+menuId).parent();
		if(parentid){
		   parentid.show();
		}
	})
	function getQueryString(name) { 
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
        var r = window.location.search.substr(1).match(reg); 
        if (r != null) return unescape(r[2]); 
        return null; 
    }  
</script>

