const FORWARD = "FORWARD";
const RIGHT = "RIGHT";
const LEFT = "LEFT";
const BACK = "BACK";
const STOP = "STOP";

const move = direction => {
    fetch("/move",
    {
        headers: {
          'Content-Type': 'application/json'
        },
        method: "POST",
        body: direction
    });
}

const init = () => {
    const body = document.body;
    const forward = document.querySelector("div#forward");
    const left = document.querySelector("div#left");
    const right = document.querySelector("div#right");
    const back = document.querySelector("div#back");

    //onmousedown
    forward.onmousedown = () => {
        move(FORWARD);
    };
    left.onmousedown = () => {
        move(LEFT);
    };
    right.onmousedown = () => {
        move(RIGHT);
    };
    back.onmousedown = () => {
        move(BACK);
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

        move(direction);
    };
};

init();
