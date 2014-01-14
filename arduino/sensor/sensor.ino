//Sensor 1
const int idSensor1 = 1;
const int pinSensor1 = A0;
int lastValueSensor1 = 0;

//Sensor 2
const int idSensor2 = 2;
const int pinSensor2 = A1;
int lastValueSensor2 = 0;

long waitingTime = 2000;//Time to wait between each reading

void sendFrame(int numArduino, long value);
int readSendSensor(const int sensorId, const int pin, int lastValue);

void setup() {                
  pinMode(pinSensor1, INPUT); 
  pinMode(pinSensor2, INPUT);
  Serial.begin(9600);  
}

void loop() {
  lastValueSensor1 = readSendSensor(idSensor1, pinSensor1, lastValueSensor1);
  lastValueSensor2 = readSendSensor(idSensor2, pinSensor2, lastValueSensor2);
  delay(waitingTime);
}

//Read the value and send over serial
int readSendSensor(const int sensorId, const int pin, int lastValue) {
  int sensorValue = 0;
  
  sensorValue = analogRead(pin);
  sensorValue = map(sensorValue, 0, 1023, 0, 255);
  
  //Check if value has changed
  if(sensorValue != lastValue) {
    sendFrame(sensorId, sensorValue);
  }
  
  return sensorValue;
}

void sendFrame(int numArduino, long value){
  //Create the string to send
  String outputString = "";
  outputString += numArduino;
  outputString += ",";
  outputString += value;
  //Send data over Serial
  Serial.println(outputString);
}
