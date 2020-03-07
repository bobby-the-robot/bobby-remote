const stompClient = function() {
    const socket = new SockJS('/ui');
    const stompClient = Stomp.over(socket);
    stompClient.connect({});
    return stompClient;
}();

const sendDirection = function(direction) {
    const uri = "/server/direction";
    const headers = {};
    const body = JSON.stringify({'direction': direction});
    stompClient.send(uri, headers, body);
}

const init = function() {
    const forward = document.querySelector("div#forward");
    const left = document.querySelector("div#left");
    const right = document.querySelector("div#right");
    const back = document.querySelector("div#back");
    forward.onclick = function() {
        sendDirection('FORWARD');
    };
    left.onclick = function() {
        sendDirection('LEFT');
    };
    right.onclick = function() {
        sendDirection('RIGHT');
    };
    back.onclick = function() {
        sendDirection('BACK');
    };
}();
