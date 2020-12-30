let TABCONTENT;
let IMAGE_INPUT;
let OWNER_INPUT;
let PACK_INPUT;
let PROCESS_METHOD_INPUT;
let SEARCH_METHOD_INPUT;
let VALUE_INPUT;
let IMAGE_OUTPUT;
let DOWNLOAD_BUTTON;
let SUBMIT_BUTTON;
let URL_CREATOR;

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
    TABCONTENT = document.getElementsByClassName("tabcontent");
    URL_CREATOR = window.URL || window.webkitURL

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

    document.getElementById("new-sticker-switcher").onclick = function () {
        switchTab("new-sticker-tab");
    };
    document.getElementById("find-sticker-switcher").onclick = function () {
        switchTab("find-sticker-tab");
    };
    document.getElementById("random-sticker-switcher").onclick = function () {
        switchTab("random-sticker-tab");
    };
    document.getElementById("info-switcher").onclick = function () {
        switchTab("info-tab");
    }

}

//TODO: add checkers for owner and pack input

// new sticker tab

function createDownloadButton(url) {
    DOWNLOAD_BUTTON.download = "sticker.png";
    DOWNLOAD_BUTTON.href = url;
    DOWNLOAD_BUTTON.innerText = "click to download";
}

function displaySticker(url) {
    IMAGE_OUTPUT.src = url;
}

function processSticker(blob, createButton = false) {
    const url = URL_CREATOR.createObjectURL(blob);
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
            var blob = new Blob([new Uint8Array(request.response)], {type: "image/png"});
            processSticker(blob, true);
        }
    }
}

// Find sticker tab

function getStickerImageById(id, display) {
    const path = "/gallery/sticker/id=" + id + "/image";
    const request = new XMLHttpRequest();
    request.open("GET", path, true);
    request.send();
    request.responseType = "arraybuffer";

    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            const blob = new Blob([new Uint8Array(request.response)], {type: "image/png"});
            document.getElementById(display).src = URL_CREATOR.createObjectURL(blob);
        }
    }
}

function getStickerDataById(id, display) {
    const path = "/gallery/sticker/id=" + id + "/data";
    fetch(path)
        .then(response => response.json())
        .then(json => document.getElementById(display).innerText = "Sticker by " + json["owner"] + " from " + json["pack"]);
}

function loadImages(ids, limit= 1) {
    for (let i = 0; i < ids.length && i < limit; i++) {
        const image = getStickerImageById(ids[i], "search-image-output");
    }
}

function getStickersBy(method, value) {
    const path = "/gallery/method=" + method + "/value=" + value;
    fetch(path)
        .then(response => response.json())
        .then(ids => loadImages(ids));
}

// Info Tab
function displayStats(value, fieldId, suffix) {
    document.getElementById(fieldId).innerText = value + suffix;
}
function getNumStats(path, fieldId, suffix) {
    fetch(path + "/number")
        .then(response => response.json())
        .then(n => displayStats(n, fieldId, suffix));
}

function getNumPacks() {
    return getNumStats("/gallery/packs", "number-of-packs", " packs");
}

function getNumOwners() {
    return getNumStats("/gallery/owners", "number-of-users", " users");
}

function getNumStickers() {
    return getNumStats("/gallery/stickers", "number-of-stickers", " stickers");
}

function buildInfoTab() {
    getNumStickers();
    getNumOwners();
    getNumPacks();
}

// Random sticker tab

function buildRandomStickerTab() {
    const path = "/gallery/random";
    fetch(path)
        .then(response => response.json())
        .then(id =>  {
            getStickerImageById(id, "random-image-output");
            getStickerDataById(id, "random-sticker-data");
        });
}

// Tab switchers

function disableAllTabs() {
    for (let i = 0; i < TABCONTENT.length; i++) {
        TABCONTENT[i].style.display = "none";
    }
}

function switchTab(tabName) {
    disableAllTabs();
    document.getElementById(tabName).style.display = "block";

    if (tabName === "info-tab") {
        buildInfoTab();
    }
    if (tabName === "random-sticker-tab") {
        buildRandomStickerTab();
    }
}