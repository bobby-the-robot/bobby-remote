const FORWARD = "FORWARD";
const RIGHT = "RIGHT";
const LEFT = "LEFT";
const BACK = "BACK";
const STOP = "STOP";

const stompClient = function() {
    const socket = new SockJS('/ui');
    const stompClient = Stomp.over(socket);
    stompClient.connect({});
    return stompClient;
}();

const sendDirection = direction => {
    const uri = "/server/direction";
    const headers = {};
    const body = JSON.stringify({'direction': direction});
    stompClient.send(uri, headers, body);
}

const init = function() {
    const body = document.body;
    const forward = document.querySelector("div#forward");
    const left = document.querySelector("div#left");
    const right = document.querySelector("div#right");
    const back = document.querySelector("div#back");

    //onmousedown
    forward.onmousedown = () => {
        sendDirection(FORWARD);
    };
    left.onmousedown = () => {
        sendDirection(LEFT);
    };
    right.onmousedown = () => {
        sendDirection(RIGHT);
    };
    back.onmousedown = () => {
        sendDirection(BACK);
    };

    //onmouseup
    forward.onmouseup = () => {
        sendDirection(STOP);
    };
    left.onmouseup = () => {
        sendDirection(STOP);
    };
    right.onmouseup = () => {
        sendDirection(STOP);
    };
    back.onmouseup = () => {
        sendDirection(STOP);
    };

    //onkeydown
    body.onkeydown = event => {
        let direction;

        switch(event.keyCode) {
            case 87: //W
                direction = FORWARD;
                break;
            case 68: //D
                direction = RIGHT;
                break;
            case 65: //A
                direction = LEFT;
                break;
            case 83: //S
                direction = BACK;
                break;
            default:
                direction = STOP;
        }

        sendDirection(direction);
    };

    body.onkeyup = () => {
        sendDirection(STOP);
    };
}();
