/**
 * Bootstrap Table Chinese translation
 * Author: Zhixin Wen<wenzhixin2010@gmail.com>
 */
(function ($) {
    'use strict';

    $.fn.bootstrapTable.locales['zh-CN'] = {
        formatLoadingMessage: function () {
        	 return '<div class="w-loading"><span class="loading-inner"></span></div>';
        },
        formatRecordsPerPage: function (pageNumber) {
            return '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;每页 ' + pageNumber + ' 条记录';
        },
        formatShowingRows: function (pageFrom, pageTo, totalRows) {
            return '显示 ' + pageFrom + ' 到  ' + pageTo + ' 项，共 ' + totalRows + ' 条';
        },
        formatSearch: function () {
            return '搜索';
        },
        formatNoMatches: function () {
        	 return '<div class="m-empty"><b class="empty-icon">&nbsp;</b> <span class="empty-text"><b class="text1">很抱歉，没有找到相关的数据</b></span> </div>';
        },
        formatPaginationSwitch: function () {
            return '隐藏/显示分页';
        },
        formatRefresh: function () {
            return '刷新';
        },
        formatToggle: function () {
            return '切换';
        },
        formatColumns: function () {
            return '列';
        },
        formatExport: function () {
            return '导出数据';
        },
        formatClearFilters: function () {
            return '清空过滤';
        }
    };

    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);

})(jQuery);
