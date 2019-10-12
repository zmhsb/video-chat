
//与服务器建立连接
function connect() {
    var from = $("#from").val();
    var socket = new SockJS('/endpoint-websocket');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        console.log("in here1111111")
        //向服务器发送人数增加的信息
        stompClient.send("/app/single/add", {}, JSON.stringify({'from': $("#from")[0].innerText}));

        console.log("in here")
        //接收服务端发送来的聊天信息
        stompClient.subscribe('/chat/together', function (result) {
            console.log("together 调用")
            //一旦收到信息就调用showContent 实时显示
            showContent(JSON.parse(result.body));
        });

        //接收服务端发送来的用户信息
        stompClient.subscribe('/topic/onlineuser', function (result) {
            console.log("subscribe 调用")
            showUser(JSON.parse(result.body));
        });


    });
}

//客户端向服务器发送聊天信息
function sendMes() {
    stompClient.send("/app/single/chat", {}, JSON.stringify({'content': $("#content").val(),'from':$("#from")[0].innerText}));
}

function showUser(body) {
    console.log("inininnini")
    $("#userList").get(0).innerHTML = "<p>" + body.content +"</p>";
}

function showContent(body) {
    $("#notice").append("<tr><td>" + body.content +"      "+ "</td> <td>"+new Date(body.time).toLocaleString()+"</td></tr>");
}

function start() {

    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    connect();

    $( "#send" ).click(function() { sendMes(); });

};