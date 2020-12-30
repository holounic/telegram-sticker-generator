let IMAGE_INPUT;
let OWNER_INPUT;
let PACK_INPUT;
let PROCESS_METHOD_INPUT;
let SEARCH_METHOD_INPUT;
let VALUE_INPUT;
let IMAGE_OUTPUT;
let DOWNLOAD_BUTTON;
let SUBMIT_BUTTON;

window.onload = function () {
    IMAGE_INPUT = document.getElementById("image-input");
    OWNER_INPUT = document.getElementById("owner-input");
    PACK_INPUT = document.getElementById("pack-input");
    PROCESS_METHOD_INPUT = document.getElementById("process-method-input");
    SEARCH_METHOD_INPUT = document.getElementById("search-method-input");
    VALUE_INPUT = document.getElementById("value-input");
    IMAGE_OUTPUT = document.getElementById("image-output");
    DOWNLOAD_BUTTON = document.getElementById("download-image-button");
    SUBMIT_BUTTON = document.getElementById("submit-search-data");

    SUBMIT_BUTTON.onclick = function () {
        let method = SEARCH_METHOD_INPUT.value;
        let value = VALUE_INPUT.value;
        getStickersBy(method, value);
    }

    IMAGE_INPUT.onchange = function () {
        let image = IMAGE_INPUT.files[0];
        let owner = OWNER_INPUT.value;
        let pack = PACK_INPUT.value;
        let method = PROCESS_METHOD_INPUT.value;
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

function processSticker(blob, createButton = false) {
    const urlCreator = window.URL || window.webkitURL;
    const url = urlCreator.createObjectURL(blob);
    displaySticker(url);
    if (createButton) {
        createDownloadButton(url);
    }
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
            console.log(request.response)
            var blob = new Blob([new Uint8Array(request.response)], {type: "image/png"});
            processSticker(blob, true);
        }
    }
}

function getStickerById(id) {
    const path = "/gallery/sticker?id=" + id;
    const request = new XMLHttpRequest();
    request.open("GET", path, true);
    request.send();
    request.responseType = "arraybuffer";

    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            console.log(request.response)
            var blob = new Blob([new Uint8Array(request.response)], {type: "image/png"});
            processSticker(blob);
        }
    }
}

function getStickersBy(method, value) {
    const path = "/gallery/method=" + method + "/value=" + value;
    fetch(path)
        .then(response => response.json())
        .then(json => console.log(json));
}

function getAllPacks() {
    const path = "/gallery/packs";
    fetch(path)
        .then(response => response.json())
        .then(json => console.log(json));
}

function getAllOwners() {
    const path = "/gallery/owmers";
    fetch(path)
        .then(response => response.json())
        .then(response => console.log(response));
}