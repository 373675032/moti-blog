/**
 * 更新基本信息
 */
function updateBasic() {
    let name = $('#name').val().trim();
    let address = $('#address').val().trim();
    let career = $('#career').val().trim();
    let birthdayStr = $('#birthdayStr').val().trim();
    let sex = $("input[name='sex']:checked").val();
    // 处理选填字段
    address = address == ''?' ':address;
    career = career == ''?' ':career;

    if (name == ''){
        swal("请输入昵称", "昵称为必填项", "warning");
    }else {
        $.ajax({
            type: "POST",
            url: "admin/update",
            data: {name:name, address:address,career:career,birthdayStr:birthdayStr,sex:sex,flag:'basic'},
            dataType: "json",
            success: function(data){
                if (data['code']== 200){
                    swal("成功", "你已成功修改了基本信息", "success");
                }else if (data['code']== 501){
                    swal("提示", "你没有修改任何信息", "info");
                }else if (data['code']== 500){
                    swal("错误", "服务器发生了一个错误", "error");
                }
            }
        });
    }
}

/**
 * 更新其他信息
 */
function updateOther() {
    let info = $('#info').val().trim();
    // 处理选填字段
    info = info == ''?' ':info;
    $.ajax({
        type: "POST",
        url: "admin/update",
        data: {info:info,flag:'other'},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                swal("成功", "你已成功修改了其他信息", "success");
            }else if (data['code']== 501){
                swal("提示", "你没有修改任何信息", "info");
            }else if (data['code']== 500){
                swal("错误", "服务器发生了一个错误", "error");
            }
        }
    });
}

/**
 * 更新联系方式
 */
function updateContact() {
    let email = $('#email').val().trim();
    let phone = $('#phone').val().trim();
    let qq = $('#qq').val().trim();
    // 处理选填字段
    email = email == ''?' ':email;
    phone = phone == ''?' ':phone;
    qq = qq == ''?' ':qq;
    // 正则验证格式
    var emailReg = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    var phoneReg = /^1[34578]\d{9}$/;
    var qqReg = /^[1-9][0-9]{4,9}$/;
    if (email != ' '){
        if(!emailReg.test(email)){
            swal("警告", "请输入正确的邮箱格式", "warning");
            return;
        }
    }
    if (phone != ' '){
        if(!phoneReg.test(phone)){
            swal("警告", "请输入正确的手机号码", "warning");
            return;
        }
    }
    if (qq != " "){
        if(!qqReg.test(qq)){
            swal("警告", "请输入正确的QQ号码", "warning");
            return;
        }
    }

    $.ajax({
        type: "POST",
        url: "admin/update",
        data: {email:email, phone:phone,qq:qq,flag:'contact'},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                swal("成功", "你已成功修改了联系方式", "success");
            }else if (data['code']== 501){
                swal("提示", "你没有修改任何信息", "info");
            }else if (data['code']== 500){
                swal("错误", "服务器发生了一个错误", "error");
            }
        }
    });
}

/**
 * 上传头像
 */
function uploadImg() {
    var formdata=new FormData();
    formdata.append("image",$("#img").get(0).files[0]);
    formdata.append("flag",1);
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
                $('#img_head1').attr("src",data['data']);
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
 * 上传微信二维码
 */
function uploadWechat() {
    var formdata=new FormData();
    formdata.append("image",$("#wechat").get(0).files[0]);
    formdata.append("flag",2);
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
                $('#img_wechat').attr("src",data['data']);
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
 * 上传微信公众号二维码
 */
function uploadPublicWechat() {
    var formdata=new FormData();
    formdata.append("image",$("#public_wechat").get(0).files[0]);
    formdata.append("flag",3);
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
                $('#img_public_wechat').attr("src",data['data']);
                swal("成功", "上传成功", "success");
            }else {
                swal("错误", "文件过大，请上传小于1M的图片", "error");
            }
        },
        error: function (e) {
        }
    });
}