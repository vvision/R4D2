var express = require('express')
  , app = express()
  , fs = require('fs')
  , db = require('./model/db')
  , mongoose = require('mongoose')
  , Measurement = mongoose.model('Measurement')
  , conf = require('./config')
  , serial = require('./serial');

app.use(express.logger());
app.use(express.bodyParser({uploadDir:'./tmp'}));
//app.use(express.favicon(__dirname + '/public/favicon.ico'))
app.use(app.router); 
app.use(express.static('./public'));
app.use(function(req, res) {
    fs.createReadStream( './public/index.html').pipe(res);
});

app.listen(conf.port, conf.host, function () {
  console.log('Server running on ' + conf.host + ':' + conf.port);
});

app.get('/mock', function(req, res, next) {
  var data = {
    allValues: [
      {
        sensorId: 1,
        timestamp: 42,
        value: 21
      },
      {
        sensorId: 1,
        timestamp: 21,
        value: 11
      }
    ]
  };
  
  res.send(data);
});

app.get('/values', function(req, res, next) {
  var id = req.query.sensor;
  
  if(id) {
    Measurement.find({sensorId: id}, function(err, docs) {
		  if(err) console.error(err);
		  console.log(docs);
		  if(docs) {
		    res.send({allValues: docs, err: null});
		  } else {
		    res.send({allValues: null, err: 'No data for this sensor.'});
		  }
	  });
	} else {
	  res.send({allValues: null, err: 'Wrong sensor id.'});
	}
  
});






