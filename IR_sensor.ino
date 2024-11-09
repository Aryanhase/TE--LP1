const int IRSensor = 9;
const int LED = 13; 

void setup(){
  Serial.begin(115200); // Init Serila at 115200 Baud
  pinMode(IRSensor, INPUT); // IR Sensor pin INPUT
  pinMode(LED, OUTPUT); // LED Pin Output
}

void loop(){

  int sensorStatus = digitalRead(IRSensor);
  if (sensorStatus == 1){
    Serial.println("Motion Detected!");
    digitalWrite(LED, HIGH);
    delay(2000);
    digitalWrite(LED, LOW);
  } else {
    Serial.println("Motion Ended!"); 
  }
}