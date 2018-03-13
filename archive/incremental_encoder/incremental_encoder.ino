/*
 *
 * Records encoder ticks for each wheel
 * and prints the number of ticks for
 * each encoder every 500ms
 *
 */

// pins for the encoder inputs
#define RH_ENCODER_A 3 
#define RH_ENCODER_B 5
#define LH_ENCODER_A 4
#define LH_ENCODER_B 2

#define PWML 9
#define PWMR 10
#define RGT1 6
#define RGT2 7
#define LT1 8
#define LT2 11
#define LOOPTIME        100   // PID loop time(ms)
#define SMOOTH      10

// variables to store the number of encoder pulses
// for each motor
volatile double leftCount = 0;
volatile double rightCount = 0;
unsigned long lastMilli = 0;       // loop timing 
unsigned long lastMilliPub = 0;
volatile float prev, now ,start, counter=0, time_delay, RPM_left, RPM_right, prev_rotR, prev_rotL, cur_rotR, cur_rotL;

void setup() {
  pinMode(LH_ENCODER_A, INPUT);
  pinMode(LH_ENCODER_B, INPUT);
  pinMode(RH_ENCODER_A, INPUT);
  pinMode(RH_ENCODER_B, INPUT);

  pinMode(PWML,OUTPUT);
  pinMode(PWMR,OUTPUT);
  pinMode(RGT1,OUTPUT);
  pinMode(RGT2,OUTPUT);
  pinMode(LT1,OUTPUT);
  pinMode(LT2,OUTPUT);
  
  // initialize hardware interrupts
  
  attachInterrupt(0, leftEncoderEvent, CHANGE);
  attachInterrupt(1, rightEncoderEvent, CHANGE);
  
  Serial.begin(9600);

  prev = millis();
  prev_rotR = 0;
  prev_rotL = 0;
}

void loop() {
  //Serial.print("Right Count: ");
unsigned long time = millis();
  if(time-lastMilli>= LOOPTIME)  
  {      // enter tmed loop
  analogWrite(PWML, 255);
  analogWrite(PWMR, 255);
  digitalWrite(RGT1, HIGH);
  digitalWrite(RGT2, LOW);
  digitalWrite(LT1, HIGH);
  digitalWrite(LT2, LOW);
  
  time_delay = time-lastMilli;
  cur_rotR = rightCount/5000;
  cur_rotL = leftCount/5000;
  RPM_right = (cur_rotR-prev_rotR)*622;
  RPM_left = (cur_rotL-prev_rotL)*622;
  //Serial.print(digitalRead(LH_ENCODER_A));
  //Serial.print(digitalRead(LH_ENCODER_B));
  Serial.print(RPM_left);
  Serial.print(" ");
  Serial.println(RPM_right);
  //Serial.println();
  prev_rotR = cur_rotR;
  prev_rotL = cur_rotL;
  //delay(100);
      lastMilli = time;
  }
  if(time-lastMilliPub >= LOOPTIME) 
  {
  //  publishRPM(time-lastMilliPub);
    lastMilliPub = time;
  }
  //delay(100);
  }

// encoder event for the interrupt call
void leftEncoderEvent() {
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
void rightEncoderEvent() {
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
