/**
 * Created by Administrator on 2019/3/18.
 */
var Properties = {
    //ajax url 和类型
    tableAjax : {
        url : '/rds_menu/queryRdsMenuList',
        type : "GET",
    },
    //table的html id
    tableId : 'rds_menu',
    uniqueId : 'menuId',
    //table的列
    tableColumn : [ {
        checkbox : true
    }, {
        field : 'menuName',
        title : '资源名称',
        sortable : true
    }, {
        field : 'url',
        title : '资源路径'
    }, {
        field : 'menuType',
        title : '类型',
        formatter : function(value, row, index) {
            if(value=="page"){
                return "页面";
            }else if(value=="module"){
                return "模块";
            }else if(value=="button"){
                return "按钮";
            }

        }
    }, {
        field : 'percode',
        title : '权限代码'
    }, /*{
     field : 'icon',
     title : '图标',
     formatter : function(value, row, index) {
     return "<i class='iconfont'>" + value + "</i>";
     }
     },*/{
        field : 'orderNum',
        title : '排序'
    } ]
}

$(function() {
    // 1. 初始化Table
    var oTable = new TableInit(Properties);
    oTable.Init();
});

function search() {
    $("#rds_menu").bootstrapTable('refreshOptions',{pageNumber:1});
}

function resetForm() {
    searchForm.menuName.value="";
    searchForm.menuType.value="";
    search();
}
//批量删除
function deleteMenuByMenuIds() {
    //获取所有被选中的记录
    var rows = $("#rds_menu").bootstrapTable('getSelections');
    if (rows.length == 0) {
        toastr.warning('请先选择要删除的记录!');
        return;
    }
    var zTree = $.fn.zTree.getZTreeObj("zTree");
    var menuIds = {};
    for (var i = 0; i < rows.length; i++) {
        menuIds[i] = rows[i]['menuId'];
        var node = zTree.getNodeByParam("id", rows[i]['menuId']);
        if (node.isParent) {
            toastr.warning('无法将含有下级资源的"' + rows[i]['menuName'] + '"删除!');
            return;
        }
    }
    Dialog.confirm({
        message : "确认要删除选择的数据吗？"
    }).on(function(e) {
        if (!e) {
            return;
        }
        $.ajax({
            type : "POST",
            url : '/rds_menu/deleteRdsMenuList',
            data : {
                menuIds : menuIds
            },
            dataType : 'json',
            success : function(data) {
                if (data.code == "0") {
                    toastr.success('删除数据成功!');
                    search();
                    initZTree();
                } else {
                    toastr.error("删除数据失败!");
                }
            }
        });
    });
}

function toAddRdsMenu() {
    var pid = $("#pid").val();
    if (pid == "") {
        toastr.warning("请选择父级资源!");
        return;
    }
    Dialog.modal({url:'/rds_menu/toAddRdsMenu?pid='+pid});
}

function toUpdateRdsMenu() {
    //获取所有被选中的记录
    var rows = $("#rds_menu").bootstrapTable('getSelections');
    if (rows.length == 0) {
        toastr.warning('请选择一条记录!');
        return;
    } else if (rows.length > 1) {
        toastr.warning('最多选择一条记录!');
        return;
    }
    var menuId = rows[0]['menuId'];

    Dialog.modal({url:'/rds_menu/toUpdateRdsMenu?menuId='+ menuId});
}

var setting = {
    data : {
        simpleData : {
            enable : true,
            idKey : 'id',
            pIdKey : 'pid',
            rootPId : 0
        }
    },
    view : {
        showLine : true, //显示辅助线
        dblClickExpand : true,
        showIcon : true,
        selectdMutil : true,
    },
    callback : {
        onClick : function(e, treeId, treeNode) {
            $("#pid").val(treeNode.id);
            search();
        }
    }
};

$(document).ready(function() {
    initZTree();
});

//初始化树
function initZTree() {
    $("#ztree-loading").show();
    $.ajax({
        url : "/rds_menu/getRdsMenuByZtree",
        type : "post",
        async : true,
        dataType : "json",
        success : function(data) {
            //异常需要处理
            $("#ztree-loading").hide();
            var zNodes = {
                name : "一级菜单",
                id : "0",
                pid : "null",
                isParent : true
            };
            data.data.push(zNodes);
            var zTreeObj = $.fn.zTree.init($("#zTree"), setting,
                data.data);
            //让第一个父节点展开
            var rootNode_0 = zTreeObj
                .getNodeByParam('pid', 0, null);
            zTreeObj.expandNode(rootNode_0, true, false, false,
                false);
            //全部展开
            // zTreeObj.expandAll(true);
        },
        error : function() {
            toastr.error('资源树加载失败!');
        }
    });
}