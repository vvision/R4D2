Arduino
=======


#Requirements

At least 2 Arduinos with a Xbee module on a wireless proto shield.
On of the arduino will have a sensor connected to it.
We use TinkerKit to manage the sensor easily.

#Configurations

Configuration must be applied with wireless proto shield in usb mode and the emptySketch running on it.

* Coordinator 

Parameter 	                   Value 	      Comments
CH (Channel) 	                 0x0F 	      Identical
ID (PAN ID) 	                 0x5241 	    Identical
DH 	                           0x0 	
DL 	                           0x0000FFFF   Broadcast Mode
MY (Source Address) 	         0xFF01 	    Unique
CE (Coordinator Enable) 	     1 	
A2 (Coordinator Association) 	 0x04 	      Allow end devices to associate to it.
RO (Packetization Timeout) 	   0x20 	      Large enough for the delay between any two chars

* End device (All end devices use the same configuration) 

Parameter 	                   Value 	      Comments
CH (Channel) 	                 0x0F 	      Identical
ID (PAN ID) 	                 0x5241 	    Identical
DH 	                           0x0 	
DL 	                           0xFF01 	    The coordinator address
MY (Source Address) 	         0xFFFE 	    Will change to be 0xFFFE after association
CE (Coordinator Enable) 	     0 	
A1 (End Device Association) 	 0x04 	      Allow associate to coordinator.
RO (Packetization Timeout) 	   0x20 	      Large enough for the delay between any two chars

Note: After the end device joints the PAN, the MY becomes 0xFFFE, the DL,DH becomes the address of coordinator. (DH = 0, DL = 0xFF01) 


#Installation

* Upload sensor on the arduino which uses TinkerKit and the sensor.
* Upload bigBro on the arduino connected to the computer. It will gather data coming from other arduinos.
* Make sure all arduinos are powered up.

#License

MIT, see License.
