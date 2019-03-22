function addRdsRole() {
    $.mask_element('#addRoleForm','数据保存中...');
    $.ajax({
        url : '/rds_user/addUserRdsRole',
        data : $("#addRoleForm").serialize(),
        cache : false,//false是不缓存，true为缓存
        async : true,//true为异步，false为同步
        success : function(result) {
            if (result.code==0) {
                //请求成功时
                toastr.success('分配角色成功!');
            }else{
                toastr.error('分配角色失败!');
            }
            $.mask_close_all();
            $(".close").click();
            reload();
        },
        error : function(result) {
            toastr.error(result.data);
            $.mask_close_all();
            reload();
        }
    })

}