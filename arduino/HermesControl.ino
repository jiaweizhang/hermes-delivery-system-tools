int red = 5; // hall flash
int yellow = 4; // signal flash
int green = 0; // pi on
int white = 2; // wifi on

int wifi = 14;
int pi = 12;

int hall = 13;

int magnet = 0;

int holdCount = 0;

void setup()
{
  Serial.begin(9600);

  pinMode(red, OUTPUT);
  pinMode(yellow, OUTPUT);
  pinMode(green, OUTPUT);
  pinMode(white, OUTPUT);

  pinMode(wifi, OUTPUT);
  pinMode(pi, OUTPUT);

  pinMode(hall, INPUT);

  // delay so hall sensor turns on if there exists power
  turnOff();
  delay(5000);
}

void loop()
{
  magnet = digitalRead(hall);
  if (magnet == LOW) {
    // magnet is held
    digitalWrite(red, HIGH);
    holdCount = holdCount + 1;
    if (holdCount == 4) {
      flash(1);
    } else if (holdCount == 8) {
      flash(2);
    }
    delay(500);
    return;
  }
  digitalWrite(red, LOW);
  if (holdCount >= 8) {
    // turn off
    turnOff();
    holdCount = 0;
  } else if (holdCount >= 4) {
    // turn on
    turnOn();
    holdCount = 0;
  }
  delay(500);
}

void turnOn() {
  digitalWrite(green, HIGH);
  digitalWrite(white, HIGH);
  digitalWrite(wifi, HIGH);
  digitalWrite(pi, HIGH);
}

void turnOff() {
  digitalWrite(green, LOW);
  digitalWrite(white, LOW);
  digitalWrite(wifi, LOW);
  digitalWrite(pi, LOW);
}

void flash(int n) {
  for (int i=0; i<n; i++) {
    delay(100);
    digitalWrite(yellow, HIGH);
    delay(100);
    digitalWrite(yellow, LOW);
  }
}
