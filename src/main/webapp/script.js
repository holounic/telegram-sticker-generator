let IMAGE_INPUT;
let OWNER_INPUT;
let PACK_INPUT;
let METHOD_INPUT;
let IMAGE_OUTPUT;
let DOWNLOAD_BUTTON;

window.onload = function () {
    IMAGE_INPUT = document.getElementById("image-input");
    OWNER_INPUT = document.getElementById("owner-input");
    PACK_INPUT = document.getElementById("pack-input");
    METHOD_INPUT = document.getElementById("method-input")
    IMAGE_OUTPUT = document.getElementById("image-output");
    DOWNLOAD_BUTTON = document.getElementById("download-image-button");

    IMAGE_INPUT.onchange = function () {
        let image = IMAGE_INPUT.files[0];
        let owner = OWNER_INPUT.value;
        let pack = PACK_INPUT.value;
        let method = METHOD_INPUT.value;
        postImage(image, owner, pack, method);
    }
}

//TODO: add checkers for owner and pack input

function createDownloadButton(url) {
    DOWNLOAD_BUTTON.download = "sticker.png";
    DOWNLOAD_BUTTON.href = url;
    DOWNLOAD_BUTTON.innerText = "click to download";
}

function displaySticker(url) {
    IMAGE_OUTPUT.src = url;
}

function processSticker(blob) {
    const urlCreator = window.URL || window.webkitURL;
    var url = urlCreator.createObjectURL(blob);

    displaySticker(url);
    createDownloadButton(url);
}

function postImage(image, owner, pack, method) {
    const data = new FormData();
    data.append("file", image);
    data.append("owner", owner);
    data.append("pack", pack);
    data.append("method", method);

    const request = new XMLHttpRequest();
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