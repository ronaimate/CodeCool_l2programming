var jsonMovies = '{"movies":{"movie":[{"title":"BaratokKozt","rate":2,"cast":{"person":[{"firstname":"Berenyi","lastname":"Miklos"},{"firstname":"Bartha","lastname":"Zsolt"}]}},{"title":"BreakingBad","rate":9,"cast":{"person":[{"firstname":"Sara","lastname":"Conor"}]}}]}}';
var objMovies = JSON.parse(jsonMovies);
var pid = document.getElementById("pid");

var text = "";

for (i = 0; i < objMovies.movies.movie.length; i++) {
    text += "<br>" + objMovies.movies.movie[i].title + "<br>" +
        objMovies.movies.movie[i].rate + "<br>";
    for (j = 0; j < objMovies.movies.movie[i].cast.person.length; j++) {
        text += "-----" + objMovies.movies.movie[i].cast.person[j].firstname + " " +
            objMovies.movies.movie[i].cast.person[j].lastname + "<br>";
    }
}

pid.innerHTML = text;