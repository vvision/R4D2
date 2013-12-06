var express = require('express');
var app = express();
var fs = require('fs');
var values = [];


app.use(express.logger());
app.use(express.bodyParser({uploadDir:'./tmp'}));
//app.use(express.favicon(__dirname + '/public/favicon.ico'))
app.use(app.router); 
app.use(express.static('./public'));
app.use(function(req, res) {
    fs.createReadStream( './public/index.html').pipe(res);
});

app.listen(8080, 'localhost', function () {
  console.log('Server running on port 8080');
});

app.post('/valeur', function(req, res, next) {
  var arduino = req.body.arduino;
  var pin = req.body.pin;
  var val = req.body.val;
  
  if(values.length >= 10) {
    values.shift();
  }
  
  /*values.push({
    //"arduino": arduino,
    "pin": parseInt(pin),
    "val": parseInt(val)
  });*/
  values.push([parseInt(pin), parseInt(val)]);
  
  console.log("Stored!");
  res.send(200);
});

app.get('/valeurs', function(req, res, next) {
  res.send(values);
});

app.get('/val', function(req, res, next) {
  console.log(values);
  console.log(values[9]);
  console.log(values[9][1]);
  res.send([values[9]]);
});


