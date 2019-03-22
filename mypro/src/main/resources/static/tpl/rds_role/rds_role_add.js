function addRdsRole() {
$('#addForm').data('bootstrapValidator').validate();
if (!($('#addForm').data('bootstrapValidator').isValid())) {
    return;
}
$.mask_element('#addForm','数据保存中...');
$.ajax({
    url : '/rds_role/addRdsRole',
    data : $("#addForm").serialize(),
    success : function(result) {
        //请求成功时
        toastr.success('增加角色成功!');
        $(".close").click();
        search();
    }
})

}

var setting = {
    data:{
        simpleData:{
            enable: true,
            idKey:'id',
            pIdKey:'pid',
            rootPId: 0
        }
    },
    check: {
        enable: true,
        chkStyle : "checkbox",    //复选框
        chkboxType : {
            "Y" : "ps",
            "N" : "ps"
        }
    },
    view:{
        showLine: false, //显示辅助线
        dblClickExpand: true,
        showIcon: false,
        selectdMutil:true,
    },
    callback: {
        onCheck: zTreeOnClick
    }

};
$(document).ready(function(){
    initZTree();
});

//初始化树
function initZTree(){
    $.ajax({
        url:"/rds_menu/getRdsMenuByZtree",
        type:"post",
        dataType: "json",
        success: function(data){
            var zNodes ={name:"一级菜单", id:"0",pid:"null",isParent:true};
            data.data.push(zNodes);
            var zTreeObj = $.fn.zTree.init($("#zTree"),setting, data.data);
            //让第一个父节点展开
            // var rootNode_0 = zTreeObj.getNodeByParam('pid',0,null);
            //zTreeObj.expandNode(rootNode_0, true, false, false, false);
            //全部展开
            zTreeObj.expandAll(true);
        },
        error: function(){
            toastr.error('资源加载失败!');
        }
    });
}

function zTreeOnClick(e,treeId,treeNode){
    var treeObj=$.fn.zTree.getZTreeObj("zTree");
    nodes=treeObj.getCheckedNodes(true);
    $("#menuIds").remove();
    $(".treeflow").append("<div id='menuIds'></div>")
    for(var i=0;i<nodes.length;i++){
        $("#menuIds").append("<input name='menuIds' type='hidden' value='"+nodes[i].id+"'/>");
    }
}


$(function() {
    //初始化表单验证
    formValidator();
})
//初始化表单验证
function formValidator() {
    $('#addForm').bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        fields : {
            //密码
            roleId : {
                validators : {
                    notEmpty : {
                        message : '角色代码不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 5,
                        message: '角色代码不能小于0位或超过5位'
                    }
                }
            },
            roleName : {
                validators : {
                    notEmpty : {
                        message : '角色名称不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 50,
                        message: '角色名称小于0位或超过50位'
                    }
                }
            },
            roleDesc : {
                validators : {
                    stringLength: {
                        min: 0,
                        max: 100,
                        message: '角色名称小于0位或超过100位'
                    }
                }
            }
        }
    });
}