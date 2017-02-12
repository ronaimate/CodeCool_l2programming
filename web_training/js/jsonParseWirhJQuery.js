var getInfos = function () {
    var url = "http://www.omdbapi.com/?t=Matrix";
    $.getJSON(url, function (json) {
        document.getElementById("title").innerText = json.Title;
        document.getElementById("imdbRating").innerText = json.imdbRating;
    });
};
getInfos();