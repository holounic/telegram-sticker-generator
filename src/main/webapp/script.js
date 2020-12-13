let IMAGE_INPUT;
let POST_IMAGE_HEADER;
let IMAGE_OUTPUT;

window.onload = function () {
    IMAGE_INPUT = document.getElementById("image-input");
    POST_IMAGE_HEADER = new Headers({"Content-Type": "application/json"});
    IMAGE_OUTPUT = document.getElementById("image-output");

    IMAGE_INPUT.onchange = function () {
        let image = IMAGE_INPUT.files[0];
        postImage(image);
    }
}

function createDownloadButton(url) {

}

function displaySticker(url) {
    IMAGE_OUTPUT.src = url;
}

function processSticker(blob) {
    const urlСreator = window.URL || window.webkitURL;
    var url = urlСreator.createObjectURL(blob);

    displaySticker(url);
    createDownloadButton(url);
}

function postImage(image) {
    var data = new FormData();
    data.append("file", image);

    var request = new XMLHttpRequest();
    request.open("POST", "/process", true);
    request.send(data);
    request.responseType = "arraybuffer";

    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            var blob = new Blob([new Uint8Array(request.response)], {type: "image/png"});
            processSticker(blob);
        }
    }

}