var serialport = require("serialport");
var request = require("request");
var SerialPort = serialport.SerialPort;
var serialPort = new SerialPort("/dev/ttyACM0", {
  baudrate: 9600,
  buffersize: 576,
  parser: serialport.parsers.readline("\n")
});

serialPort.on("open", function () {
  console.log('open');
  serialPort.on('data', function(data) {
    console.log('data received: ' + data);
    data = data.split(',');
    var arduino = data[0].charAt(2);
    var pin = data[1];
    var value = data[2].split('\r');
    var val = value[0];
    
    console.log({
        arduino: arduino,
        pin: pin,
        val: val
      });
    
    var options = {
      url: 'http://localhost:8080/valeur',
      method: 'POST',
      json: {
        arduino: arduino,
        pin: pin,
        val: val
      }
    };
    
    request(options, function(err, response, body) {
       if(err) console.error(err);
       if(response.statusCode == 200){
        console.log("OK");
       }
    }); 
  });
});

