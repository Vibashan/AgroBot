#if (ARDUINO >= 100)
 #include <Arduino.h>
#else
 #include <WProgram.h>
#endif
#include <ros.h>
#include <geometry_msgs/Vector3Stamped.h>
#include <geometry_msgs/Twist.h>
#include <ros/time.h>

#define RH_ENCODER_A 3 
#define RH_ENCODER_B 5
#define LH_ENCODER_A 4
#define LH_ENCODER_B 2

#define RH_Directionf 6 
#define RH_Directionb 7
#define LH_Directionf 8
#define LH_Directionb 11
#define RH_Pwm 10 
#define LH_Pwm 9
#define LOOPTIME        100   // PID loop time(ms)
#define SMOOTH      10

// variables to store the number of encoder pulses
// for each motor
volatile double leftCount = 0;
volatile double rightCount = 0;
volatile double prev, now ,start, time_delay =0, counter=0, prev_rotR, prev_rotL, cur_rotR, cur_rotL;
volatile double error_l = 0, last_error1_l = 0 ;
volatile double error_r = 0, last_error1_r = 0 ;
volatile double int_errorl;
volatile double int_errorr;
unsigned long lastMilli = 0;       // loop timing 
unsigned long lastMilliPub = 0;
double Required_rpml = 0;
double Required_rpmr = 0;
double RPM_left = 0;
double RPM_right = 0;
double Required_rpml_smoothed = 0;
double Required_rpmr_smoothed = 0;
char direction1 ;
char direction2 ;
int PWM_val1 = 0;
int PWM_val2 = 0;
float Kp = 150;
float Kd = 70;
float Ki = 40;

ros::NodeHandle nh;

void handle_cmd( const geometry_msgs::Twist& cmd_msg)
{
  double x = cmd_msg.linear.x;
  double z = cmd_msg.angular.z;
  if (z == 0) {     // go straight
    // convert m/s to rpm
    Required_rpml = x*60/(0.3454);       ///x < .157
    Required_rpmr = Required_rpml;
  }
  else if (x == 0) {
    // convert rad/s to rpm
    Required_rpmr = z*0.55*60/(0.3454*2);///z < .307
    Required_rpml = -Required_rpmr;
  }
  else {
    Required_rpml = x*60/(0.3454)-z*0.55*60/(0.3454*2);
    Required_rpmr = x*60/(0.3454)+z*0.55*60/(0.354*2);
  }
  int_errorr = 0;
  int_errorl = 0;
}

ros::Subscriber<geometry_msgs::Twist> sub("cmd_vel", handle_cmd);
geometry_msgs::Vector3Stamped rpm_msg;
ros::Publisher rpm_pub("rpm", &rpm_msg);
ros::Time current_time;
ros::Time last_time;

void setup()
{
 Required_rpml = 0;
 Required_rpmr = 0;
 RPM_left = 0;
 RPM_right = 0;
 PWM_val1 = 0;
 PWM_val2 = 0;
 nh.initNode();
 nh.getHardware()->setBaud(57600);
 nh.subscribe(sub);
 nh.advertise(rpm_pub);
  
  pinMode(LH_ENCODER_A, INPUT);
  pinMode(LH_ENCODER_B, INPUT);
  pinMode(RH_ENCODER_A, INPUT);
  pinMode(RH_ENCODER_B, INPUT);
  
  pinMode(RH_Directionf, OUTPUT);
  pinMode(RH_Directionb, OUTPUT);
  pinMode(LH_Directionf, OUTPUT);
  pinMode(LH_Directionb, OUTPUT);
  pinMode(RH_Pwm, OUTPUT);
  pinMode(LH_Pwm, OUTPUT);
    
  // initialize hardware interrupts
  attachInterrupt(0, leftEncoderEvent, CHANGE);
  attachInterrupt(1, rightEncoderEvent, CHANGE);

  prev_rotR = 0;
  prev_rotL = 0;
  prev = 0;
}

void loop()
{
  nh.spinOnce();
  unsigned long time = millis();
  if(time-lastMilli>= LOOPTIME)  
  {      // enter tmed loop
  time_delay = time - prev;
  cur_rotR = rightCount/5000;
  cur_rotL = leftCount/5000 ;
  RPM_left = (cur_rotR - prev_rotR)*622;
  RPM_right = (cur_rotL - prev_rotL)*622; 
  
  prev_rotR = cur_rotR;
  prev_rotL = cur_rotL; 
  
  error_l = - RPM_left + Required_rpml;
  int_errorl+= error_l;
  int Value_l =  Value_l + 255*((Kp*error_l + Kd*(error_l-last_error1_l)) + Ki*(int_errorl))/(Kp*200 + Kd*400 );
  last_error1_l = error_l;
  PWM_val1 = Value_l;
  Serial.print(RPM_left);
  Serial.print("   ");

  
  error_r = - RPM_right + Required_rpmr;
  int_errorr+= error_r;
  int Value_r =  Value_r + 255*((Kp*error_r + Kd*(error_r-last_error1_r)) + Ki*(int_errorr))/(Kp*200 + Kd*400);
  last_error1_r = error_r;
  PWM_val2 = Value_r;
  Serial.println(RPM_right);

  

    if(PWM_val1 > 0) direction1 = 'f';
    else if(PWM_val1 < 0) direction1 = 'b';
    if (Required_rpml == 0) direction1 = 's';
    if(PWM_val2 > 0) direction2 = 'f';
    else if(PWM_val2 < 0) direction2 = 'b';
    if (Required_rpmr == 0) direction2 = 's';

    PWM_val1 = abs(PWM_val1);
    PWM_val2 = abs(PWM_val2);

    if ( direction1 == 'f')
    {
       digitalWrite(LH_Directionf, HIGH);
       digitalWrite(LH_Directionb, LOW);
       analogWrite(LH_Pwm, PWM_val1);
    }
    else if( direction1 == 'b')
    {
      digitalWrite(LH_Directionf, LOW);
      digitalWrite(LH_Directionb, HIGH);
      analogWrite(LH_Pwm, PWM_val1);
      
    }
    else if(direction1 == 's')
    {
      digitalWrite(LH_Directionf, LOW);
      digitalWrite(LH_Directionb, LOW);
      analogWrite(LH_Pwm, PWM_val1);
    }

    if ( direction2 == 'f')
    {
      digitalWrite(RH_Directionf, HIGH);
      digitalWrite(RH_Directionb, LOW);
      analogWrite(RH_Pwm, PWM_val2); 
    }
    else if( direction2 == 'b')
    {
      digitalWrite(RH_Directionf, LOW);
      digitalWrite(RH_Directionb, HIGH);
      analogWrite(RH_Pwm, PWM_val2);  
    }
    else if(direction2 == 's')
    {
      digitalWrite(RH_Directionf, LOW);
      digitalWrite(RH_Directionb, LOW);
      analogWrite(RH_Pwm, PWM_val2); 
    }
    
    publishRPM(time-lastMilli);
    lastMilli = time;
  }
  if(time-lastMilliPub >= LOOPTIME) 
  {
  //  publishRPM(time-lastMilliPub);
    lastMilliPub = time;
  }
}

void publishRPM(unsigned long time)
{
  rpm_msg.header.stamp = nh.now();
  rpm_msg.vector.x = RPM_left;
  rpm_msg.vector.y = RPM_right;
  rpm_msg.vector.z = double(time)/1000;
  rpm_pub.publish(&rpm_msg);
  nh.spinOnce();
}

void leftEncoderEvent()
  {
  if (digitalRead(LH_ENCODER_A) == HIGH) {
    if (digitalRead(LH_ENCODER_B) == LOW) {
      leftCount++;
    } else {
      leftCount--;
    }
  } else {
    if (digitalRead(LH_ENCODER_B) == LOW) {
      leftCount--;
    } else {
      leftCount++;
    }
  }
}

// encoder event for the interrupt call
void rightEncoderEvent() 
{
  if (digitalRead(RH_ENCODER_A) == HIGH) {
    if (digitalRead(RH_ENCODER_B) == LOW) {
      rightCount++;
    } else {
      rightCount--;
    }
  } else {
    if (digitalRead(RH_ENCODER_B) == LOW) {
      rightCount--;
    } else {
      rightCount++;
    }
  }
}
