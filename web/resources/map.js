function coordinates() {
    var mysql = require('mysql');

    var coordinates;

    var con = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "provider",
        database: "depo"
    });

    con.connect(function (err) {
        if (err) throw err;
        con.query("SELECT depo.location.length, depo.location.width FROM depo.location",
            function (err, result, fields) {
                if (err) throw err;
                console.log(result);
                coordinates = result;
            });
    });
    return coordinates;
}
