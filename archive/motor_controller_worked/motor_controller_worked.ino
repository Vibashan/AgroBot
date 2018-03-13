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
#define LOOPTime        100   // PID loop Time(ms)
#define SMOOTH      10

// variables to store the number of encoder pulses
// for each motor
volatile double leftCount = 0;
volatile double rightCount = 0;
volatile int prev, now ,start, counter=0, Time_delay, prev_rotR =0, prev_rotL=0, cur_rotR=0, cur_rotL=0;
unsigned long lastMilli = 0;       // loop timing 
unsigned long lastMilliPub = 0;
double Req_RPM_right = 0;
double Req_RPM_left = 100;
double Act_RPM_right = 0;
double Act_RPM_left = 0;
double Req_RPM_right_smoothed = 0;
double Req_RPM_left_smoothed = 0;
char direction1 ;
char direction2 ;
int PWM_val1 = 0;
int PWM_val2 = 0;
volatile long count1 = 0;          // rev counter
volatile long count2 = 0;
  static double last_error1 = 0;
  static double last_error2 = 0;
  static double int_error1 = 0;
  static double int_error2 = 0;
long countAnt1 = 0;
long countAnt2 = 0;
float Kp =   0.4;
float Kd =   0.02;
float Ki =   0;
int Time = 0;

void setup()
{
  Serial.begin(9600);
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
  unsigned long Time = millis();
  //Serial.println(Time);
  if(Time-lastMilli>= LOOPTime)  
  {     
    Time_delay = Time-lastMilli;
    cur_rotR = rightCount/5000;
    cur_rotL = leftCount/5000;
    Act_RPM_right = double((cur_rotR - prev_rotR)*600);
    Act_RPM_left = double((cur_rotL - prev_rotL)*600);
//    Serial.println(Act_RPM_right);
   Serial.println(Act_RPM_left);
    
    prev_rotR = cur_rotR;
    prev_rotL = cur_rotL;
    
    PWM_val1 = updatePid(1, PWM_val1, Req_RPM_right, Act_RPM_right);
    PWM_val2 = updatePid(2, PWM_val2, Req_RPM_left, Act_RPM_left);

    if(PWM_val1 > 0) direction1 = 'f';
    else if(PWM_val1 < 0) direction1 = 'b';
    if (Req_RPM_right == 0) direction1 = 's';
    if(PWM_val2 > 0) direction2 = 'f';
    else if(PWM_val2 < 0) direction2 = 'b';
    if (Req_RPM_left == 0) direction2 = 's';

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
    
    lastMilli = Time;
    delay(100);
  }
  if(Time-lastMilliPub >= LOOPTime) 
  {
  //  publishRPM(Time-lastMilliPub);
    lastMilliPub = Time;
  }
}

int updatePid(int id, int command, double targetValue, double currentValue)
{
  double pidTerm = 0;                            // PID correction
  double error = 0;
  double new_pwm = 0;
  double new_cmd = 0;
  
  error = targetValue-currentValue;
  if (id == 1) 
  {
    int_error1 += error;
    pidTerm = Kp*error + Kd*(error-last_error1) + Ki*int_error1;
    last_error1 = error;
  }
  else 
  {
    int_error2 += error;
    pidTerm = Kp*error + Kd*(error-last_error2) + Ki*int_error2;
    last_error2 = error;
  }
  new_pwm = constrain(double(command)*160/255 + pidTerm, -160, 160);
  new_cmd = 255*new_pwm/160;
  return int(new_cmd);
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
