/**
 * 删除评论
 * @param id
 */
function deleteComment(id) {
    swal({
        title: "确定删除此评论?",
        text: "此操作不可恢复！",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    type: "GET",
                    url: "deleteComment",
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

function replyComment() {
    var id = $('#id').val().trim();
    var content = $('#content').val().trim();
    var sentEmail = $('#sendEmail').is(':checked');
    if (content == ""){
        swal("警告","回复留言内容不能为空！", "warning");
    }else {
        $.ajax({
            type: "POST",
            url: "replyComment",
            dataType: "json",
            data: {replyId:id,content:content,sentEmail:sentEmail},
            success: function (data) {
                if (data['code']== 200){
                    swal("回复成功", "", "success");
                    setInterval(reload, 2000);
                }else {
                    swal("错误", "服务器发生了一个错误", "error");
                }
            },
            error: function (e) {
            }
        });
    }
}

/**
 * 刷新页面
 */
function reload() {
    window.location.reload();
}