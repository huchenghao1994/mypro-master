var properties = {
    //ajax url 和类型
    tableAjax : {
        url : '/rds_role/queryRdsRoleList',
        type : "GET",
    },
    //table的html id
    tableId : 'rds_role',
    uniqueId : 'roleId',
    //table的列
    tableColumn : [ {
        checkbox : true
    }, {
        field : 'roleId',
        title : '角色代码',
    }, {
        field : 'roleName',
        title : '角色名称',
    }, {
        field : 'roleDesc',
        title : '角色描述',
    }, {
        field : 'createTime',
        title : '创建日期',
    }, {
        field : 'updateTime',
        title : '更新日期',
    },]
}

$(function() {
    //1.初始化Table
    var oTable = new TableInit(properties);
    oTable.Init();
});

function search() {
    $("#rds_role").bootstrapTable('refreshOptions',{pageNumber:1});
}
function resetForm() {
    searchForm.roleName.value="";
    search();
}
//批量删除
function deleteRoleByRoleIds() {
    //获取所有被选中的记录
    var rows = $("#rds_role").bootstrapTable('getSelections');
    if (rows.length == 0) {
        toastr.warning('请先选择要删除的记录!');
        return;
    }
    var roleIds = {};
    for (var i = 0; i < rows.length; i++) {
        roleIds[i] = rows[i]['roleId'];
    }

    Dialog.confirm({
        message : "确认要删除选择的数据吗？"
    }).on(function(e) {
        if (!e) {
            return;
        }
        $.ajax({
            async : false,
            type : "POST",
            url : '/rds_role/deleteRdsRoleList',
            data : {
                roleIds : roleIds
            },
            dataType : 'json',
            success : function(data) {
                if (data.code == "0") {
                    toastr.success('删除数据成功!');
                } else {
                    toastr.error("删除数据失败!");
                }
                search();
            },
            error : function(data) {
                toastr.error("删除数据失败!");
                search();
            }
        });
    });
}

function toAddRdsRole() {
    Dialog.modal({url : '/rds_role/toAddRdsRole'});
}

function toUpdateRdsRole() {
    //获取所有被选中的记录
    var rows = $("#rds_role").bootstrapTable('getSelections');
    if (rows.length == 0) {
        toastr.warning('请选择一条记录!');
        return;
    } else if (rows.length > 1) {
        toastr.warning('最多选择一条记录!');
        return;
    }
    var roleId = rows[0]['roleId'];

    Dialog.modal({url : '/rds_role/toUpdateRdsRole?roleId='+ roleId});
}