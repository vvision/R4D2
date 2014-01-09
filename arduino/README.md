Arduino
=======


#Requirements

At least 2 Arduinos with a Xbee module on a wireless proto shield.
On of the arduino will have a sensor connected to it.
We use TinkerKit to manage the sensor easily.

#Configurations

Configuration must be applied with wireless proto shield in usb mode and the emptySketch running on it.

* Coordinator 

<table>
  <tr>
    <th>Parameter</th><th>Value</th><th>Comments</th>
  </tr>
  <tr>
    <td>CH (Channel)</td><td>0x0F</td><td>Identical</td>
  </tr>
  <tr>
    <td>ID (PAN ID)</td><td>0x5241</td><td>Identical</td>
  </tr>
  <tr>
    <td>DH</td><td>0x0</td><td></td>
  </tr>
  <tr>
    <td>DL</td><td>0x0000FFFF</td><td>Broadcast Mode</td>
  </tr>
  <tr>
    <td>MY (Source Address)</td><td>0xFF01</td><td>Unique</td>
  </tr>
  <tr>
    <td>CE (Coordinator Enable)</td><td>1</td><td></td>
  </tr>
  <tr>
    <td>A2 (Coordinator Association)</td><td>0x04</td><td>Allow end devices to associate to it.</td>
  </tr>
  <tr>
    <td>RO (Packetization Timeout)</td><td>0x20</td><td>Large enough for the delay between any two chars.</td>
  </tr>
</table>

* End device

(All end devices use the same configuration)


<table>
  <tr>
    <th>Parameter</th><th>Value</th><th>Comments</th>
  </tr>
  <tr>
    <td>CH (Channel)</td><td>0x0F</td><td>Identical</td>
  </tr>
  <tr>
    <td>ID (PAN ID)</td><td>0x5241</td><td>Identical</td>
  </tr>
  <tr>
    <td>DH</td><td>0x0</td><td></td>
  </tr>
  <tr>
    <td>DL</td><td>0xFF01</td><td>The coordinator address</td>
  </tr>
  <tr>
    <td>MY (Source Address)</td><td>0xFFFE</td><td>Will change to be 0xFFFE after association</td>
  </tr>
  <tr>
    <td>CE (Coordinator Enable)</td><td>0</td><td></td>
  </tr>
  <tr>
    <td>A1 (End Device Association)</td><td>0x04</td><td>Allow associate to coordinator.</td>
  </tr>
  <tr>
    <td>RO (Packetization Timeout)</td><td>0x20</td><td>Large enough for the delay between any two chars.</td>
  </tr>
</table>

Note: After the end device joints the PAN, the MY becomes 0xFFFE, the DL,DH becomes the address of coordinator. (DH = 0, DL = 0xFF01) 


#Installation

* Upload sensor on the arduino which uses TinkerKit and the sensor.
* Upload bigBro on the arduino connected to the computer. It will gather data coming from other arduinos.
* Make sure all arduinos are powered up.

#License

MIT, see License.
