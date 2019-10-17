var stompClient = null;
var movidePath = 'http://10.11.72.22:81/video/';
//与服务器建立连接
function connect() {
    // var from = $("#from").val();
    var socket = new SockJS('/endpoint-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        //接收服务端发送来的聊天信息
        stompClient.subscribe('/topic/chat/together', function (result) {
            console.log("together 调用")
            //一旦收到信息就调用showContent 实时显示
            showContent(JSON.parse(result.body));
        });

        // 接收服务端发送来的用户信息
        stompClient.subscribe('/topic/onlineuser', function (result) {
            console.log("subscribe 调用")
            showUser(JSON.parse(result.body));
        });

        //监听播放状态
        stompClient.subscribe('/topic/movieStatus', function (result) {
            console.log("body==== "+result.body)
            let data = JSON.parse(result.body);

            switch (data.status){
                case "nothing":alert("影片暂未开始，请稍等");break;
                case "pause":
                        $("#movieTitle").text("当前正在播放:    "+data.from)
                        player.pause();break;
                case "start":
                        let videoPath = player.src();
                        let p = movidePath+data.path;
                        if(videoPath!=p)
                        {
                            player.src(p)
                        }
                        $("#movieTitle").text("当前正在播放:    "+data.from)
                        player.play();
                        player.currentTime(data.content);
                        break;
                case "change":
                        let path = data.path;
                        player.src(movidePath+path);
                        console.log(movidePath+path)
                        player.play();break;
            }
        });

        // 向服务器发送人数增加的信息
        stompClient.send("/app/chat/add", {}, JSON.stringify({'from': $("#login").text()}));
    });



}


// function addPerson() {
//     // 向服务器发送人数增加的信息
//     stompClient.send("/app/chat/add", {}, JSON.stringify({'from': $("#login").text()}));
// }

//客户端向服务器发送聊天信息
function sendMes() {
    stompClient.send("/app/chat/sendMessage", {}, JSON.stringify({'content': $("#messageInput").val(),'from':$("#login").text()}));
}

function showUser(body) {
    $("#onlineUser").text(body.content);
}

function showContent(body) {
    $("#chatArea").append("<tr><td>" + body.content + "</td></tr>");
}

function start() {

    connect()
    $("#send").click(function() { sendMes(); });

};

function videoStart() {


    var time = player.currentTime();
    let name = $("#movieList").val()
    stompClient.send("/app/video/start", {}, JSON.stringify({'from': name,"content":String(time)}));
}

function videoPause() {

    var time = player.currentTime();
    let name = $("#movieList").val()
    stompClient.send("/app/video/pause", {}, JSON.stringify({'from': name,"content":String(time)}));
}

function changeMovie() {
    let name = $("#movieList").val()
    stompClient.send("/app/video/changeMovie", {}, JSON.stringify({'from': name}));

}

function videoEnd() {

    let name = $("#movieList").val()
    let array = new Array(); //定义数组
    let movideIndex = 0;
    $("#movieList option").each(function(index,op){ //遍历全部option
        let txt = $(this).text(); //获取option的内容
        if(txt==name)
        {
            movideIndex = index;
        }
        array.push(txt); //添加到数组中
    });
    let total = array.length;
    movideIndex+=1;
    movideIndex%=total

    $("#movieList").val(array[movideIndex])
    console.log(array[movideIndex])
    stompClient.send("/app/video/changeMovie", {}, JSON.stringify({'from': array[movideIndex]}));
}

