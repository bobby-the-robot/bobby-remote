const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    stompClient.subscribe('/topic/frames', function(messageOutput) {
        document.getElementById('response').src = 'data:image/jpg;base64,' + messageOutput.body;
    });
});
