int sensorId = 1;//Modify the id here
int sensorValue = 0;
int lastValue = 0;
long waitingTime = 2000;//Time to wait between each reading
unsigned long lastSending = 0;//Time when last sending occured
long maxTime = 600000;//10 minutes max between each sending (milliseconds)
long timePassed = 0;
const int sensor = A0;

void sendFrame(int numArduino, int numPin, long value);

void setup() {                
  // initialize the digital pin as an output.
  pinMode(sensor, INPUT); 
  Serial.begin(9600);  
}

void loop() {
  //Read sensor value
  sensorValue = analogRead(sensor);
  sensorValue = map(sensorValue, 0, 1023, 0, 255);
  timePassed = millis() - lastSending;
  //May occured when millis() will overflow, after approximately 50 days 
  timePassed = timePassed >= 0 ? timePassed : -timePassed;
  
  if(sensorValue != lastValue || timePassed > maxTime) {
    sendFrame(sensorId, sensorValue);
    lastValue = sensorValue;
    lastSending = millis();
  }
  delay(waitingTime);
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
