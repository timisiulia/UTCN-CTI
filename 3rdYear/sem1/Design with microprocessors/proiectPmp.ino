#include <FastLED.h>
#include <TimerOne.h>
#define NUM_LEDS 12
#define DATA_PIN 6
#define MIC_PIN 2
#define OFFSET 900
int btn1 = 8, btn2 = 9, btn3 = 10, btn4 = 11;
int nn = 0;
int citire = 0;
int b11;
int b22;
int b33;
int b44;


CRGB leds[NUM_LEDS];

void setup() {
  Serial.begin(9600);
  pinMode(MIC_PIN, INPUT);
  pinMode(btn1, INPUT_PULLUP);
  pinMode(btn2, INPUT_PULLUP);
  pinMode(btn3, INPUT_PULLUP);
  pinMode(btn4, INPUT_PULLUP);
  FastLED.addLeds<NEOPIXEL, DATA_PIN>(leds, NUM_LEDS);
  Timer1.initialize(15000);
  Timer1.attachInterrupt(citireSenzori); 


}

void citireSenzori(void)
{
  citire = 1;
}
//transformare semnal digital in unul analogic. Microfonul trimite 1 logic cand e dezacticvat
int transformare() {
  int sum = 0;
  //deoarece initial e un semnal digital trebuie sa citesc o val intre 0 si 1023 ca sa o iau drept valoare analogica
  for ( int i = 0; i < 1023; i++) {
    sum += digitalRead(MIC_PIN);

  }

  return 1023 - sum;
}
//la apasarea primului buton
void curcubeuCuNumarLeduri(int valCitita) {
  int numLedsToLight = map(valCitita, 0, OFFSET, 0, NUM_LEDS);

  // First, clear the existing led values
  FastLED.clear();
  for (int led = 0; led < numLedsToLight; led++) {
    int color = map(rand(), 0, 1000, 0, 255);
    leds[led].setHue( color );
    FastLED.setBrightness(50);
  }
  FastLED.show();

  delay(10);
}
//al 2 lea buton, se apride rosu dupa luminozitate
void dimLeduriRed(int valCitita) {

  // First, clear the existing led values
  FastLED.clear();
  for (int led = 0; led < NUM_LEDS; led++) {
    leds[led].r = 255;
    leds[led].g = 10;
    leds[led].b = 10;
  }
  int val_afis = map(valCitita, 0, OFFSET, 0, 150);
  FastLED.setBrightness(val_afis);
  FastLED.show();

  delay(10);

}
//se aprinde una si una , dupa mij
void deLaMijloc(int valCitita)  {
  int numLedsToLight = map(valCitita, 0, OFFSET / 2, 0, 6);

  // First, clear the existing led values
  FastLED.clear();
  for (int led = 0; led < numLedsToLight; led++) {
    int color = map(rand(), 0, 1000, 0, 255);
    leds[led].setHue( color );
    leds[12 - led].setHue( color );
    FastLED.setBrightness(50);
  }
  FastLED.show();

  delay(10);

}


//se schimba doar culorile, toate sunt aprinse
void schimbaCulori(int valCitita) {
  int numLedsToLight = map(valCitita, 0, OFFSET / 2, 0, 6);
  for (int led = 0; led < numLedsToLight; led++) {
    int color = map(rand(), 0, 1000, 0, 255);
    leds[led].setHue( color );
    leds[12 - led].setHue( color );
    FastLED.setBrightness(50);
  }
  FastLED.show();

  delay(10);

}


void loop() {
  int valoare = transformare();
  if (nn == 0)  schimbaCulori(valoare);
  if (nn  == 1) dimLeduriRed(valoare);
  if (nn  == 2) deLaMijloc(valoare);
  if (nn  == 3) curcubeuCuNumarLeduri(valoare);

  // variabile pentru starea butoanelor
  if (citire == 1) {
    citire = 0;
    b11 = digitalRead(btn1);
    b22 = digitalRead(btn2);
    b33 = digitalRead(btn3);
    b44 = digitalRead(btn4);
  }


  if (b11 == LOW) {
    nn = 0;
  }
  if ( b22 == LOW) {
    nn = 1;
  }
  if (b33 == LOW) {
    nn = 2;
  }

  if (b44 == LOW) {
    nn = 3;
  }
}
