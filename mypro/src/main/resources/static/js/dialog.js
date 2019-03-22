/**
 * description:对话框
 * huchenghao
 * @returns 
 */

(function($) {
	window.Dialog = function() {
		var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">'
				+ '<div class="modal-dialog modal-sm">'
				+ '<div class="modal-content">'
				+ '<div class="modal-header">'
				+ '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'
				+ '<h4 class="modal-title" id="modalLabel">[Title]</h4>'
				+ '</div>'
				+ '<div class="modal-body">'
				+ '<p>[Message]</p>'
				+ '</div>'
				+ '<div class="modal-footer">'
				+ '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>'
				+ '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>'
				+ '</div>' + '</div>' + '</div>' + '</div>';

		var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">'
				+ '<div class="modal-dialog">'
				+ '<div class="modal-content">'
				+ '<div class="modal-header">'
				+ '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'
				+ '<h4 class="modal-title" id="modalLabel">[Title]</h4>'
				+ '</div>'
				+ '<div class="modal-body">'
				+ '</div>'
				+ '</div>'
				+ '</div>' + '</div>';
		var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
		var generateId = function() {
			var date = new Date();
			return 'mdl' + date.valueOf();
		}
		
		var init = function(options) {
			options = $.extend({}, {
				title : "操作提示",
				message : "提示内容",
				btnok : "确定",
				btncl : "取消",
				width : 200,
				auto : false
			}, options || {});
			var modalId = generateId();
			var content = html.replace(reg, function(node, key) {
				return {
					Id : modalId,
					Title : options.title,
					Message : options.message,
					BtnOk : options.btnok,
					BtnCancel : options.btncl
				}[key];
			});
			$('body').append(content);
			$('#' + modalId).modal({
				width : options.width,
				backdrop : 'static'
			});
			$('#' + modalId).on('hide.bs.modal', function(e) {
				$('body').find('#' + modalId).remove();
			});
			return modalId;
		}

		return {
			alert : function(options) {
				if (typeof options == 'string') {
					options = {
						message : options
					};
				}
				var id = init(options);
				var modal = $('#' + id);
				modal.find('.ok').removeClass('btn-success').addClass(
						'btn-primary');
				modal.find('.cancel').hide();

				return {
					id : id,
					on : function(callback) {
						if (callback && callback instanceof Function) {
							modal.find('.ok').click(function() {
								callback(true);
							});
						}
					},
					hide : function(callback) {
						if (callback && callback instanceof Function) {
							modal.on('hide.bs.modal', function(e) {
								callback(e);
							});
						}
					}
				};
			},
			confirm : function(options) {
				var id = init(options);
				var modal = $('#' + id);
				modal.find('.ok').removeClass('btn-primary').addClass(
						'btn-success');
				modal.find('.cancel').show();
				return {
					id : id,
					on : function(callback) {
						if (callback && callback instanceof Function) {
							modal.find('.ok').click(function() {
								callback(true);
							});
							modal.find('.cancel').click(function() {
								callback(false);
							});
						}
					},
					hide : function(callback) {
						if (callback && callback instanceof Function) {
							modal.on('hide.bs.modal', function(e) {
								callback(e);
							});
						}
					}
				};
			},
			dialog : function(options) {
				options = $.extend({}, {
					title : 'title',
					url : '',
					width : 800,
					height : 550,
					onReady : function() {
					},
					onShown : function(e) {
					}
				}, options || {});
				var modalId = generateId();

				var content = dialogdHtml.replace(reg, function(node, key) {
					return {
						Id : modalId,
						Title : options.title
					}[key];
				});
				$('body').append(content);
				var target = $('#' + modalId);
				target.find('.modal-body').load(options.url);
				if (options.onReady())
					options.onReady.call(target);
				target.modal();
				target.on('shown.bs.modal', function(e) {
					if (options.onReady(e))
						options.onReady.call(target, e);
				});
				target.on('hide.bs.modal', function(e) {
					$('body').find(target).remove();
				});
			},
			/*
			 *author:huchenghao 
			 *return medal
			 *Backdrop Boolean或字符串“static” 默认 True True:有背景，点击modal外部，modal消失。 False:无背景，点击modal外部，modal不消失。Static:有背景，点击modal外部，modal不消失。
			 *Keyboard Boolean 默认True True:键盘上的esc按下关闭modal False：键盘上的esc按下不关闭modal
			 *Show Boolean True 默认True：显示modal False：不显示modal
			 *remote 跳转地址
			 */
			modal : function(options){
					 var defaults = {
				    			title:'myModal',
				    			show :'true',
				    			backdrop:'static',
				    		    Keyboard:'false',
				    			width:'45%',
					    	};
					    var settings = $.extend(defaults, options);
					    /** 模态框（Modal）* */
						var modalHtml = '<div class="modal fade" id="'+settings.title+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog" style="width:'+settings.width+'"><div class="modal-content"></div></div></div>';
						 $("body").append(modalHtml);
					     $('#'+settings.title).on('hidden.bs.modal', function() {
							$(this).removeData('bs.modal');
						});
						
					    return $("#"+settings.title).modal({
									show : settings.show,
									remote : settings.url,
									backdrop : settings.backdrop,
									Keyboard : settings.Keyboard,
								});
			}
		}
	}();
})(jQuery);

/*!
 * jQuery loading Plugin v1.0.1
 *
 * Copyright 2017 Nick Han
 */
(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD
		define(['jquery'], factory);
	} else if (typeof exports === 'object') {
		// CommonJS
		factory(require('jquery'));
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function ($) {
	//给窗口添加滚动事件，在滚动时遮罩跟着元素移动
	$(window).bind("scroll",function() {
		var masks = $(".mask");
		for (var i = 0; i < masks.length; i++) {
			var ele_id = $(masks[i]).attr("ele");
			var eleTop= $(ele_id).offset().top;
			var gun = $(document).scrollTop();
            var top = eleTop-gun;
			$(masks[i]).css({
				"top": top+'px'
			});
		}
	});
	
	//timeout cache
	var cache = {};
	
	/*
	 * full screen loading mask
	 */
	$.mask_fullscreen = function(timeout){
		if($(".mask[ele=full_screen]").length > 0){
			return;
		}
		//禁止滚动
		$("body").addClass("loading-scroll-off");
		var mask = '<div class="loading-mask" ele="full_screen"><div>数据加载中...</div></div>';
		$("body").append(mask);
		clearTimeout(cache["full_screen"]);
		if(timeout && timeout > 0){
			var s = setTimeout(function(){
				$(".mask[ele=full_screen]").remove();
				$("body").removeClass("loading-scroll-off");
			}, timeout);
			cache["full_screen"] = s;
		}
	}
	
	/*
	 * certain element loading mask
	 */
	$.mask_element = function(ele_id,dis_msg, timeout){
		//判断当前元素是否已经添加遮罩，如果已添加，则直接返回
		if($(".mask[ele="+ele_id+"]").length > 0){
			return;
		}
		if(!dis_msg){
			dis_msg = "数据加载中...";
		}
		//添加遮罩元素
		var mask = '<div class="loading-mask" ele='+ele_id+' style="width: '+$(ele_id).width()+'px !important; height: '+$(ele_id).height()+'px !important; left: '+$(ele_id).offset().left+'px !important; top: '+$(ele_id).offset().top+'px !important;"><div><span>'+dis_msg+'<span></div></div>';
		$("body").append(mask);
		clearTimeout(cache[ele_id]);
		if(timeout && timeout > 0){
			var s = setTimeout(function(){
				$(".mask[ele="+ele_id+"]").remove();
			}, timeout);
			cache[ele_id] = s;
		}
	}
	
	/*
	 * close certain loading mask
	 */
	$.mask_close = function(ele_id){
		$(".mask[ele="+ele_id+"]").remove();
	}
	
	/*
	 * close all loading mask
	 */
	$.mask_close_all = function(){
		$(".loading-mask").remove();
	}
}));