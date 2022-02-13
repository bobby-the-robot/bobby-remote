const socket = new SockJS('/video');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    stompClient.subscribe('/topic/frames', function(messageOutput) {
        const base64Image = btoa(messageOutput.body);
        document.getElementById('response').src = 'data:image/jpg;base64,' + base64Image;
    });
});
