Station
=======

Store data coming from the arduino.
Make data available through http.

#Requirements

* Node.Js
* MongoDB.

#Installation

* Install dependencies: `npm install`.
* Run: `node server.js`.

#Configuration

The following parameters can be configured in config.json.
Default values should be overwritten to fit your settings.

<table>
  <tr>
    <th>Parameter</th><th>Default value</th><th>Comment</th>
  </tr>
  <tr>
    <td>host</td><td>localhost</td><td>Domain or IP on which the server is running.</td>
  </tr>
  <tr>
    <td>port</td><td>8080</td><td>Port to be used.</td>
  </tr>
  <tr>
    <td>serial</td><td>/dev/ttyACM0</td><td>The serial port to listen to.</td>
  </tr>
</table>

#API Description

You can retrieve data on /values and the parameter sensor containing the id of the sensor.
For example, to get all data concerning sensor 1: **curl -XGET localhost:8080/values?sensor=1**
`
{
  "allValues": [
    {
      "sensorId": 1,
      "timestamp": 1389344261521,
      "value": 168,
      "_id": "52cfb605dadbb2166b00000f",
      "__v": 0
    },
    {
      "sensorId": 1,
      "timestamp": 1389344255521,
      "value": 167,
      "_id": "52cfb5ffdadbb2166b00000e",
      "__v": 0
    },
    {
      "sensorId": 1,
      "timestamp": 1389344247518,
      "value": 168,
      "_id": "52cfb5f7dadbb2166b00000d",
      "__v": 0
    }
  ]
}
`

It is possible to get sample values using: **curl -XGET localhost:8080/mock**
`
{
  "allValues": [
    {
      "sensorId": 1,
      "timestamp": 42,
      "value": 21
    },
    {
      "sensorId": 1,
      "timestamp": 21,
      "value": 11
    }
  ],
  "err": null
}
`

##Data Structure

All data stored in Mongo contains the following field:
* timestamp
* sensorId
* value

#Contribution

Use 2 spaces indentation for all JavaScript code.

#License

MIT, see License.





