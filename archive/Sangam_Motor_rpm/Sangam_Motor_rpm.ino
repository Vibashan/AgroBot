
// pins for the encoder inputs
#define RH_ENCODER_A 3 
#define RH_ENCODER_B 5
#define LH_ENCODER_A 4
#define LH_ENCODER_B 2
#define RH_Directionf 8 
#define RH_Directionb 11
#define LH_Directionf 6
#define LH_Directionb 7
#define RH_Pwm 10 
#define LH_Pwm 9

// variables to store the number of encoder pulses
// for each motor
volatile double leftCount = 0;
volatile double rightCount = 0;
volatile int prev, time ,start, counter=0, time_delay, RPM_left, RPM_right, prev_rotR, prev_rotL, cur_rotR, cur_rotL;

void setup()
{
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
  
  Serial.begin(9600);

  prev = 0;
  prev_rotR = 0;
  prev_rotL = 0;
}

void loop()
{
  //Serial.print("Right Count: ");
  time = millis();
  time_delay = time - prev;
  cur_rotR = (rightCount*60)/1425;
  cur_rotL = (leftCount*60)/1425;
  RPM_right = -(cur_rotR - prev_rotR)*0.7407;//10
  RPM_left = -(cur_rotL - prev_rotL)*0.6688;//9.03

  digitalWrite(RH_Directionf, HIGH);
  digitalWrite(RH_Directionb, LOW);
  analogWrite(RH_Pwm, 250);
  
  digitalWrite(LH_Directionf, HIGH);
  digitalWrite(LH_Directionb, LOW);
  analogWrite(LH_Pwm, 250); 
   
  Serial.print(rightCount);
  Serial.print(" ");
  Serial.print(leftCount);
  Serial.print(" ");
  Serial.println(RPM_left);

  /*Serial.print(cur_rotL);
  Serial.print(" ");
  Serial.println(cur_rotR);*/
  
  prev_rotR = cur_rotR;
  prev_rotL = cur_rotL;
  delay(100);
}

// encoder event for the interrupt call
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
