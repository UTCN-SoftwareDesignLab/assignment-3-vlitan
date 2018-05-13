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
            showResult(JSON.parse(notification.body));
        });
    })
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function showResult(notification) {
    console.log(notification)
    if (notification.errors.length == 0) {
        alert(notification.response);
    }
}

$(function () {
    $("form").on('submit', function (e) {
     //   e.preventDefault();
    });
   // connect();
    // $( "#connect" ).click(function() { connect(); });
    // $( "#disconnect" ).click(function() { disconnect(); });
    // $( "#send" ).click(function() { sendName(); });
});