
$(function () {



    // $("form").on('submit', function (e) {
    //     e.preventDefault();
    // });

    videojs('example_video_1',{
        muted: true,
        controls : true/false,
        height:500,
        width:1065,
        loop : true,
        // 更多配置.....
    });

    $("#registerBtn").click(register);
    $("#loginBtn").click(login);

})

function register() {

    var formdata = $('#registerForm').serializeJSON();
    console.log(formdata)
    //提交登陆验证
    $.ajax({
        url: '/user/register',
        data:formdata,
        dataType: 'json',
        type: 'post',
        success:function(rtn){

            alert("success")

            $("#RegisterModal").modal("hide")
        }
    });
}

function login() {
    var formdata = $('#LoginFrom').serializeJSON();
    console.log(formdata)
    //提交登陆验证
    $.ajax({
        url: '/user/login',
        data:formdata,
        dataType: 'json',
        type: 'post',
        success:function(data){
            console.log(data)
            if(data.sign==200)
            {
                $("#login").text(data.username)
                $("#login").attr("data-target","#")
                $("#messageInput").removeAttr("disabled");
                $("#messageInput").attr("placeholder","请输入")
                $("#LoginModal").modal("hide")
                start();
            }else
            {
                //密码错误
            }
        }
    });
}
