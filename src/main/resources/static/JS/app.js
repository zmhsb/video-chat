var stompClient = null;

//与服务器建立连接
function connect() {
    // var from = $("#from").val();
    var socket = new SockJS('/endpoint-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        // 向服务器发送人数增加的信息
      stompClient.send("/app/chat/add", {}, JSON.stringify({'from': $("#login").text()}));

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


    });
}

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
    $( "#send" ).click(function() { sendMes(); });

};