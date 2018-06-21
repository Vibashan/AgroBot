/*
 * rosserial Subscriber 
 * Gets input and performs the mechanism actions
 */

#include <ros.h>
#include <std_msgs/String.h>

#include <Servo.h>

Servo myservo;  // create servo object to control a servo
Servo myservo1;
Servo myservo2;

int pos = 0;    // variable to store the servo position
int sensor_pin = A0;
int output_value ;

int potpin = 0;  // analog pin used to connect the potentiometer
int val;         // variable to read the value from the analog pin

ros::NodeHandle nh;

void messageCb( const std_msgs::String& toggle_msg){
  digitalWrite(13, HIGH-digitalRead(13));   // blink the led
}


void moistCb( const std_msgs::String& moist_msg){
  for (pos = 0; pos <= 180; pos += 1) { // goes from 0 degrees to 180 degrees
                                        // in steps of 1 degree
    myservo.write(pos);                 // tell servo to go to position in variable 'pos'
    delay(15);                          // waits 15ms for the servo to reach the position
  }

  delay(2000);
  
  output_value= analogRead(sensor_pin);

  output_value = map(output_value,550,0,0,100);

  Serial.print("Mositure : ");

  Serial.print(output_value);

  Serial.print("%");

  if(output_value<=2)
   Serial.println("extreme stress - irrigate");
  else if(output_value>2 && output_value<=5)
   Serial.println("stress");
  else if(output_value>5 && output_value<=10)
   Serial.println("optimal");
  else if(output_value>10 && output_value<=15)
   Serial.println("excess");
  else
   Serial.println("saturation");
    
  delay(2000);
  
  for (pos = 180; pos >= 0; pos -= 1) { // goes from 180 degrees to 0 degrees
    myservo.write(pos);                 // tell servo to go to position in variable 'pos'
    delay(15);                          // waits 15ms for the servo to reach the position
  }
}


void ploughCb( const std_msgs::String& plough_msg){
  val = 0;
  val = map(val, 0, 1023, 0, 180);     // scale it to use it with the servo (value between 0 and 180)
  myservo1.write(val);
  
  delay(500);                           // waits for the servo to get there
  val = 1024;
  val = map(val, 0, 1023, 0, 180);     // scale it to use it with the servo (value between 0 and 180)
  myservo1.write(val);
  
  delay(500);
}


void seedCb( const std_msgs::String& seed_msg){
  val = 700;
  val = map(val, 0, 1023, 0, 180);     // scale it to use it with the servo (value between 0 and 180)
  myservo2.write(val);
  
  delay(500);                           // waits for the servo to get there
  val = 1023;
  val = map(val, 0, 1023, 0, 180);     // scale it to use it with the servo (value between 0 and 180)
  myservo2.write(val);
  
  delay(500);
}


void pumpCb( const std_msgs::String& pump_msg){
  analogWrite(10, pwm);
  delay(3000);
}

ros::Subscriber<std_msgs::Empty> sub("toggle_led", &messageCb );
ros::Subscriber<std_msgs::Empty> sub("moisture_servo", &moistCb );
ros::Subscriber<std_msgs::Empty> sub("plough_servo", &ploughCb );
ros::Subscriber<std_msgs::Empty> sub("seed_sow_servo", &seedCb );
ros::Subscriber<std_msgs::Empty> sub("fertilizer_pump", &pumpCb );

void setup()
{
  myservo.attach(10);  // attaches the servo on pin 10 to the servo object  
  myservo1.attach(11);
  myservo2.attach(9);

  pinMode(5, OUTPUT);
  
  Serial.begin(9600);

  pinMode(13, OUTPUT);
  
  nh.initNode();
  nh.subscribe(sub);
}

void loop()
{
  nh.spinOnce();
  delay(1);
}
