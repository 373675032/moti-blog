/**
 * 发表
 */
function publish() {
    let title = $('#title').val().trim();
    let content = $('#content').val().trim();
    let tags = $('#tags').val().trim();
    let introduce = $('#info').val().trim();
    let kind = $('#kind').find('option:selected').text();
    if (title == ''){
        swal("警告","标题不能为空！", "warning");
        return;
    }
    $.ajax({
        type: "POST",
        url: "publish",
        data: {title:title,content:content,tags:tags,kind:kind,introduce:introduce},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                swal("发表成功", "你现在可以给文章添加一个封面啦！", "success")
                    .then(() => {
                        var location = window.location.href;
                        let strings = location.split("moti-blog");
                        window.location.href = strings[0]+"moti-blog/edit?id="+data['data'];
                    });
            }else{
                swal("出错啦", "服务器发生了一个错误", "error");
            }
        }
    });
}

/**
 * 草稿发布
 */
function draftPublish() {
    let title = $('#title').val().trim();
    let content = $('#content').val().trim();
    let tags = $('#tags').val().trim();
    let introduce = $('#info').val().trim();
    let kind = $('#kind').find('option:selected').text();
    if (title == ''){
        swal("警告","标题不能为空！", "warning");
        return;
    }
    $.ajax({
        type: "POST",
        url: "draftPublish",
        data: {title:title,content:content,tags:tags,kind:kind,introduce:introduce},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                swal("保存成功", "你可以在草稿箱找到它！", "success")
                    .then(() => {
                        var location = window.location.href;
                        let strings = location.split("moti-blog");
                        window.location.href = strings[0]+"moti-blog/edit?id="+data['data'];
                    });
            }else{
                swal("出错啦", "服务器发生了一个错误", "error");
            }
        }
    });
}

/**
 * 移入回收站
 */
function deleteLight(id,from) {
    swal({
        title: "确定将此文章移入回收站?",
        text: "你可以在回收站找到它，并且可以重新发布！",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                var location = window.location.href;
                let strings = location.split("moti-blog");
                window.location.href = strings[0]+"moti-blog/deleteLight?id="+id+"&from="+from;
            }
        });
}

/**
 * 永久删除
 */
function deleteStrong(id,from) {
    swal({
        title: "确定将此文章永久删除?",
        text: "注意此操作不可恢复！",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                var location = window.location.href;
                let strings = location.split("moti-blog");
                window.location.href = strings[0]+"moti-blog/deleteStrong?id="+id+"&from="+from;
            }
        });
}

/**
 * 更新
 */
function updatePublish() {
    let id = $('#id').val().trim();
    let title = $('#title').val().trim();
    let content = $('#content').val().trim();
    let tags = $('#tags').val().trim();
    let introduce = $('#info').val().trim();
    let kind = $('#kind').find('option:selected').text();
    if (title == ''){
        swal("警告","标题不能为空！", "warning");
        return;
    }
    $.ajax({
        type: "POST",
        url: "update",
        data: {id:id,title:title,content:content,tags:tags,kind:kind,introduce:introduce,status:1},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                swal("更新成功", "", "success")
                    .then(() => {
                        var location = window.location.href;
                        let strings = location.split("moti-blog");
                        window.location.href = strings[0]+"moti-blog/edit?id="+data['data'];
                    });
            }else{
            }
        }
    });
}

/**
 * 更新草稿
 */
function updateDraft() {
    let id = $('#id').val().trim();
    let title = $('#title').val().trim();
    let content = $('#content').val().trim();
    let tags = $('#tags').val().trim();
    let introduce = $('#info').val().trim();
    let kind = $('#kind').find('option:selected').text();
    if (title == ''){
        swal("警告","标题不能为空！", "warning");
        return;
    }
    $.ajax({
        type: "POST",
        url: "update",
        data: {id:id,title:title,content:content,tags:tags,kind:kind,introduce:introduce,status:0},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                swal("保存草稿成功！", "", "success")
                    .then(() => {
                        var location = window.location.href;
                        let strings = location.split("moti-blog");
                        window.location.href = strings[0]+"moti-blog/edit?id="+data['data'];
                    });
            }else{
                swal("出错啦", "服务器发生了一个错误", "error");
            }
        }
    });
}

/**
 * 发表评论
 */
function addComment(id) {
    let name = $('#name').val().trim();
    let email = $('#email').val().trim();
    let content = $('#content').val().trim();
    if (name == ''){
        swal("警告","昵称不能为空！", "warning");
        return;
    }
    if (email == ''){
        swal("警告","邮箱不能为空！", "warning");
        return;
    }
    if (content == ''){
        swal("警告","留言内容不能为空！", "warning");
        return;
    }
    var emailReg = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    if(!emailReg.test(email)){
        swal("警告", "请输入正确的邮箱格式", "warning");
        return;
    }
    $.ajax({
        type: "POST",
        url: "addComment",
        data: {name:name,email:email,content:content,articleId:id},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                swal("成功", "发表评论成功，请等待作者回复！", "success");
                setInterval(reload, 2000);
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