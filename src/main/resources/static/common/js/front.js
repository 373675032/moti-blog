/**
 * 上传背景图片
 */
function uploadBackground() {
    var formdata=new FormData();
    formdata.append("image",$("#background").get(0).files[0]);
    formdata.append("flag",7);
    $.ajax({
        async: false,
        type: "POST",
        url: "image/upload",
        dataType: "json",
        data: formdata,
        contentType:false,//ajax上传图片需要添加
        processData:false,//ajax上传图片需要添加
        success: function (data) {
            if (data['code']== 200){
                $('#img_background').attr("src",data['data']);
                swal("成功", "上传成功", "success");
            }else {
                swal("错误", "文件过大，请上传小于1M的图片", "error");
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 上传公告图片
 */
function uploadNotice() {
    var formdata=new FormData();
    formdata.append("image",$("#notice").get(0).files[0]);
    formdata.append("flag",8);
    $.ajax({
        async: false,
        type: "POST",
        url: "image/upload",
        dataType: "json",
        data: formdata,
        contentType:false,//ajax上传图片需要添加
        processData:false,//ajax上传图片需要添加
        success: function (data) {
            if (data['code']== 200){
                $('#img_notice').attr("src",data['data']);
                swal("成功", "上传成功", "success");
            }else {
                swal("错误", "文件过大，请上传小于1M的图片", "error");
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 更新前台信息
 */
function updateFront() {

    var title = $('#title').val().trim();
    var subTitle = $('#subTitle').val().trim();
    var notice = $('#info').val().trim();
    var target = $('#target').val().trim();

    if (title == ''){
        swal("警告", "标题不能为空", "warning");
        return;
    }
    if (subTitle == ''){
        swal("警告", "副标题不能为空", "warning");
        return;
    }
    if (target == ''){
        swal("警告", "链接地址不能为空", "warning");
        return;
    }
    $.ajax({
        type: "POST",
        url: "updateFront",
        dataType: "json",
        data: {title:title,subTitle:subTitle,notice:notice,imgTarget:target},
        success: function (data) {
            if (data['code']== 200){
                swal("更新成功", "", "success");
            }else {
                swal("错误", "服务器发生了一个错误", "error");
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 添加菜单
 */
function addMenu() {

    var name = $('#name').val().trim();
    let location = $('#location').find('option:selected').val();
    var target = $('#target').val().trim();

    if (name == ''){
        swal("警告", "标题不能为空", "warning")
        return;
    }
    if (target == ''){
        swal("警告", "链接地址不能为空", "warning")
        return;
    }
    $.ajax({
        type: "POST",
        url: "addMenu",
        dataType: "json",
        data: {name:name,url:target,location:location},
        success: function (data) {
            if (data['code']== 200){
                swal("添加成功", "", "success");
                setInterval(reload, 2000);
            }else {
                swal("错误", "服务器发生了一个错误", "error");
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 添加友情链接
 */
function addLink() {

    var name = $('#name').val().trim();
    var target = $('#target').val().trim();

    if (name == ''){
        swal("警告", "标题不能为空", "warning");
        return;
    }
    if (target == ''){
        swal("警告", "链接地址不能为空", "warning");
        return;
    }
    $.ajax({
        type: "POST",
        url: "addLink",
        dataType: "json",
        data: {name:name,url:target},
        success: function (data) {
            if (data['code']== 200){
                swal("添加成功", "", "success");
                setInterval(reload, 2000);
            }else {
                swal("错误", "服务器发生了一个错误", "error");
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 删除菜单
 * @param id
 */
function deleteMenu(id) {
    swal({
        title: "确定删除此菜单?",
        text: "此操作不可恢复！",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    type: "GET",
                    url: "deleteMenu",
                    dataType: "json",
                    data: {id:id},
                    success: function (data) {
                        if (data['code']== 200){
                            swal("删除成功", "", "success");
                            setInterval(reload, 2000);
                        }else {
                            swal("错误", "服务器发生了一个错误", "error");
                        }
                    },
                    error: function (e) {
                    }
                });
            }
        });
}

/**
 * 删除友情链接
 * @param id
 */
function deleteLink(id) {
    swal({
        title: "确定删除此链接?",
        text: "此操作不可恢复！",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    type: "GET",
                    url: "deleteLink",
                    dataType: "json",
                    data: {id:id},
                    success: function (data) {
                        if (data['code']== 200){
                            swal("删除成功", "", "success");
                            setInterval(reload, 2000);
                        }else {
                            swal("错误", "服务器发生了一个错误", "error");
                        }
                    },
                    error: function (e) {
                    }
                });
            }
        });
}

/**
 * 刷新页面
 */
function reload() {
    window.location.reload();
}