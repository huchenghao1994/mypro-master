function updateRdsMenu() {
    $('#updateForm').data('bootstrapValidator').validate();
    if (!($('#updateForm').data('bootstrapValidator').isValid())) {
        return;
    }
    $.mask_element('#updateForm','数据修改中...');
    $.ajax({
        url : '/rds_menu/updateRdsMenu',
        data : $("#updateForm").serialize(),
        success : function(result) {
            toastr.success('修改资源成功!');
            $(".close").click();
            search();
            initZTree();
        }
    })

}
$(function() {
    //初始化表单验证
    formValidator();
})
//初始化表单验证
function formValidator() {
    $('#updateForm').bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        fields : {
            menuId : {
                validators : {
                    notEmpty : {
                        message : '资源代码不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 5,
                        message: '资源代码不能小于0位或超过5位'
                    }
                }
            },
            menuName : {
                validators : {
                    notEmpty : {
                        message : '资源名称不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 50,
                        message: '资源名称不能小于0位或超过50位'
                    }
                }
            },
            url : {
                validators : {
                    stringLength: {
                        min: 0,
                        max: 100,
                        message: '路径不能小于0位或超过100位'
                    }
                }
            },
            percode : {
                validators : {
                    notEmpty : {
                        message : '资源权限不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 50,
                        message: '资源权限不能小于0位或超过50位'
                    }
                }
            },
            orderNum : {
                validators : {
                    notEmpty : {
                        message : '排序不能为空'
                    },
                    digits : {
                        message : '请填写数字',
                    },
                    greaterThan:{
                        value: 0,
                        message : '请输入大于0的数字',
                    },
                    lessThan: {
                        value: 100,
                        message : '请输入小于100的数字',
                    }
                }
            }
        }
    });
}

function selectType(obj){
    if($(obj).val()=="page"){
        $("#url").val("");
        $("#divUrl").attr("style","display:");
    }else{
        $("#divUrl").attr("style","display:none");
    }

}