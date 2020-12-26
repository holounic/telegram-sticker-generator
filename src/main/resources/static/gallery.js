let VALUE_INPUT;
let METHOD_INPUT;
let SUBMIT_BUTTON;

window.onload = function () {
    VALUE_INPUT = document.getElementById("value-input");
    METHOD_INPUT = document.getElementById("method-input");
    SUBMIT_BUTTON = document.getElementById("submit-search-data");

    SUBMIT_BUTTON.onclick = function () {
        let method = METHOD_INPUT.value;
        let value = VALUE_INPUT.value;
        sendStickerRequest(method, value);
    }
}

function sendStickerRequest(method, value) {
    const requestDest = "/gallery/method=" + method + "/value=" + value;
    const request = new XMLHttpRequest();
    const data = new FormData()
    request.open("GET", requestDest, false);
    request.send(data);
    request.responseType = "arraybuffer";

    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            var blob = new Blob([new Uint8Array(request.response)], {type: "image/png"});

        }
    }

}