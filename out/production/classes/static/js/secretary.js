var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/doctorEndpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
      //  setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/errors', function (notification) {
            showResult(JSON.parse(notification.body).errors);
        });
    });
}

function showResult(notification) {
    console.log(notification)
    if (notification.length == 0){
        alert("doctor notified!");
    }
    else {
        alert(notification.join("\n"));
    }
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/notifyDoctor", {}, JSON.stringify({'userId': $("#did").val()}));
}


$(function () {
    console.log("script stared");
    // $("form").on('submit', function (e) {
    //     e.preventDefault();
    // });
    //connect();
    // $( "#connect" ).click(function() { connect(); });
    // $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});