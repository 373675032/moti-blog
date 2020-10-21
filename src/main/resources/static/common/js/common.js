/**
 * 重建ElasticSearch
 */
function resetEs() {
    $.ajax({
        type: "GET",
        url: "admin/resetEs",
        dataType: "json",
        success: function (data) {
            if (data['code']== 200){
                swal("重建ElasticSearch成功", "共用时："+data['data']+"毫秒", "success");
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
 * 清空Redis
 */
function cleanRedis() {
    $.ajax({
        type: "GET",
        url: "admin/cleanRedis",
        dataType: "json",
        success: function (data) {
            if (data['code']== 200){
                swal("清空Redis成功", "共用时："+data['data']+"毫秒", "success");
                setInterval(reload, 2000);
            }else {
                swal("错误", "服务器发生了一个错误", "error");
            }
        },
        error: function (e) {
        }
    });
}