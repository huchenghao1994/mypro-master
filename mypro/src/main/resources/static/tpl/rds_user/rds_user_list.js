var properties = {
    //ajax url 和类型
    tableAjax : {
        url : '/rds_user/findRdsUserList',
        type : "GET",
    },
    //table的html id
    tableId : 'rds_user',
    uniqueId : 'userId',
    singleSelect:true,
    //table的列
    tableColumn : [ {
        checkbox : true,
        align : 'center'
    }, {
        field : 'userId',
        title : '用户ID',
        visible : true
    }, {
        field : 'username',
        title : '用户名'
    }, ]
}
$(function() {
    //初始化Table
    var oTable = new TableInit(properties);
    oTable.Init();
});

//新增柜员
function toDetailRdsUserByTellerId() {
    var rows = $("#rds_user").bootstrapTable('getSelections');
    if (rows.length == 0) {
        toastr.warning('请选择一条记录!');
        return;
    } else if (rows.length > 1) {
        toastr.warning('最多选择一条记录!');
        return;
    }
    var tellerId = rows[0]['tellerId'];
    Modal({
        width : '60%',
        title : "detailRdsUser",
        url : '/rds_user/toDetailRdsUserByTellerId?tellerId='
        + tellerId
    });
}

//修改密码
function updatePasswordById() {
    //获取所有被选中的记录
    var rows = $("#rds_user").bootstrapTable('getSelections');
    if (rows.length == 0) {
        toastr.warning('请选择一条记录!');
        return;
    } else if (rows.length > 1) {
        toastr.warning('最多选择一条记录!');
        return;
    }
    var tellerId = rows[0]['tellerId'];
    var token=$("#token").val();
    Dialog.confirm({
        message : "确认要重置密码吗？"
    }).on(function(e) {
        if (!e) {
            return;
        }
        $.ajax({
            async : false,
            type : "POST",
            url : '/rds_user/changePassword',
            data : {
                tellerId : tellerId,
                token : token
            },
            dataType : 'json',
            success : function(data) {
                if (data.code == "0") {
                    toastr.success('密码重置成功!');
                } else {
                    toastr.error("密码重置失败!");
                }
                search();
            },
            error : function(data) {
                toastr.error("密码重置失败!");
                search();
            }
        });
    });

}

//修改密码
function setRdsRoleById() {
    //获取所有被选中的记录

    var rows = $("#rds_user").bootstrapTable('getSelections');
    if (rows.length == 0) {
        toastr.warning('请选择一条记录!');
        return;
    } else if (rows.length > 1) {
        toastr.warning('最多选择一条记录!');
        return;
    }

    var userId = rows[0]['userId'];
    Dialog.modal({
        url : '/rds_user/setRdsRoleByUserId?userId=' + userId
    });
}

function search() {
    $.ajax({
        url : '/rds_user/getRdsRoleMap',
        success : function(result) {
            rdsRoleMap=result;
        }
    })
    $("#rds_user").bootstrapTable('refreshOptions',{pageNumber:1});
}

function resetForm() {
    searchForm.tellerId.value = "";
    searchForm.tellerName.value = "";
    search();
}
