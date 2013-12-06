// These constants won't change.  They're used to give names
// to the pins used:
const int analogInPin = A0;  // Analog input pin that the potentiometer is attached to
const int analogOutPin = 13; // Analog output pin that the LED is attached to
const int ledPin = 11;
int ledState = LOW;

int sensorValue = 0;        // value read from the pot
int outputValue = 0;        // value output to the PWM (analog out)
int lastValue = 0;

void setup() {
  // initialize serial communications at 9600 bps:
  Serial.begin(9600);
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, HIGH);
}

void loop() {
  // read the analog in value:
  sensorValue = analogRead(analogInPin);            
  // map it to the range of the analog out:
  outputValue = map(sensorValue, 0, 1023, 0, 255);  
  // change the analog out value:
  analogWrite(analogOutPin, outputValue); 
  
  if(outputValue != lastValue) {
    // print the results to the serial monitor:   
    Serial.print("##1,0,");
    Serial.println(outputValue);
    lastValue = outputValue;
  }

  if (ledState == LOW)
    ledState = HIGH;
  else
    ledState = LOW;  
    
  digitalWrite(ledPin, ledState);
  delay(sensorValue);                    
}
