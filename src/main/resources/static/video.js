const socket = new SockJS('/video');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    stompClient.subscribe('/topic/messages', function(messageOutput) {
        document.getElementById('response').src = 'data:image/jpg;base64,' + messageOutput.body;
    });
});
