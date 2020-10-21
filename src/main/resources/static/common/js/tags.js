/**
 * 添加新标签
 */
function addTag() {
    let name = $('#name').val().trim();
    let introduce = $('#info').val().trim();
    if (name == ''){
        swal("警告","名称不能为空！", "warning");
        return;
    }
    $.ajax({
        type: "POST",
        url: "addTag",
        data: {name:name,introduce:introduce},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                swal("添加成功", "你已经成功创建了名称为《"+name+"》的标签了", "success");
                setInterval(reload, 2000);
            }else if (data['code']== 501){
                swal("添加失败", "你已经创建过名称为《"+name+"》的标签了，请换一个新名称吧~", "error");
            }else{
                swal("出错啦", "服务器发生了一个错误", "error");
            }
        }
    });
}

/**
 * 刷新页面
 */
function reload() {
    window.location.reload();
}

/**
 * 更新标签
 */
function updateTag() {
    let name = $('#name').val().trim();
    let id = $('#id').val().trim();
    let introduce = $('#info').val().trim();
    if (name == ''){
        swal("警告","名称不能为空！", "warning");
        return;
    }
    $.ajax({
        type: "POST",
        url: "updateTag",
        data: {name:name,introduce:introduce,id:id},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                swal("更新成功", "你已经成功更新了这个标签", "success");
                setInterval(reload, 2000);
            }else if (data['code']== 501){
                swal("提示", "你没有修改任何信息", "info");
            }else if (data['code']== 502){
                swal("添加失败", "你已经创建过名称为《"+name+"》的标签了，请换一个新名称吧~", "error");
            }else{
                swal("出错啦", "服务器发生了一个错误", "error");
            }
        }
    });
}

/**
 * 上传标签封面
 */
function uploadImg() {
    let id = $('#id').val().trim();
    var formdata=new FormData();
    formdata.append("image",$("#img").get(0).files[0]);
    formdata.append("flag",5);
    formdata.append("id",id);
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
                $('#img_head').attr("src",data['data']);
                swal("成功", "上传成功", "success");
            }else {
                swal("错误", "服务器发生了一个错误", "error");
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 删除标签
 */
function deleteTag(id) {
    swal({
        title: "确定删除此标签?",
        text: "删除此标签之后，关联的文章不会一起删除！",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                var location = window.location.href;
                let strings = location.split("moti-blog");
                window.location.href = strings[0]+"moti-blog/deleteTag?id="+id;
            }
        });
}