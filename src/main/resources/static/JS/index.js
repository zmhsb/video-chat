var player = null;
var picPath = 'http://10.11.72.22:81/video/';
$(function () {

    player = videojs('example_video_1',{
        muted: true,
        controls : false,
        height:500,
        width:1065,
        // 更多配置.....
    });

    $("#registerBtn").click(register);
    $("#loginBtn").click(login);
    $("#movieList").change(changeMovie)

    $.ajax({
        url: '/video/getVideoList',
        type: 'get',
        success:function(date){
            console.log(date)
            for(let i =0;i<date.content.length;i++)
            {
                $("#movieList").append('<option>'+date.content[i].name+'</option>')
            }
        }
    });

    $.ajax({
        url: '/video/getPicList',
        type: 'get',
        success:function(date){
            console.log(date)
            for(let i =0;i<date.content.length;i++)
            {
                let path = picPath+date.content[i]
                console.log(path)
                $("#gift").append('<img style="margin-left: 5px" src='+path+'></img>')
            }
        }
    });

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
                if(data.role=="管理员")
                {
                    $("#movieList").toggle()
                    player.controls(true)
                    player.on("play",videoStart)
                    player.on("pause",videoPause)
                    player.on("ended",videoEnd)

                }
                $("#login").text(data.username)
                $("#login").attr("data-target","#")
                $("#messageInput").removeAttr("disabled");
                $("#messageInput").attr("placeholder","请输入")
                $("#LoginModal").modal("hide")
                start()
                // player.on("ended",videoEnd)
            }else
            {
                //密码错误
            }
        }
    });
}
