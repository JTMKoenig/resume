//A list of points to train Perception
Trainer[] training = new Trainer[2000];

//A Perceptron Object
Perceptron ptron;

//Perceptron is trained one point at a time
int count = 0;

// Coordinate Space
float xmin = -400;
float ymin = -100;
float xmax = 400;
float ymax = 100;

//Function to describe to line
//param x-coord, return y-coord
float f(float x) {
  //y = slope*x + y-intercept
  return 8*x-3;
}

void setup(){
  size(640,360);
  
  //Perceptron takes 3 inputs, x,y,bias
  //Second param is 'Learning Constant'
  ptron = new Perceptron(3, .0001);
  
  // Create a random set of training points and
  //calculate known answer
 
 for(int i = 0; i < training.length; i++){
   float x = random(xmin,xmax);
   float y = random(ymin,ymax);
   int target = 1;
   if (y < f(x)){
     target = -1;
   }
   training[i] = new Trainer(x,y,target);
 }
 smooth();
}

void draw() {
  background(255);
  //put point (0,0) at window center
  translate(width/2,height/2);
  
  //Draw the line
  strokeWeight(4);
  stroke(127);
  float x1 = xmin;
  float y1 = f(x1);
  float x2 = xmax;
  float y2 = f(x2);
  line(x1,y1,x2,y2);
  
  //draw the line based on the current weights
  stroke(0);
  strokeWeight(1);
  float[] weights = ptron.getWeights();
  x1 = xmin;
  y1 = (-weights[2] - weights[0]*x1)/weights[1];
  x2 = xmax;
  y2 = (-weights[2] - weights[0]*x2)/weights[1];
  line(x1,y1,x2,y2);
  float slope = (y2-y1)/(x2-x1);
  float y_int = y1-(slope*x1);
  println("Guessed Line: y="+slope+"x +" + y_int);
  
  //Train the perceptron with one point at a time
  ptron.train(training[count].inputs, training[count].target);
  count = (count + 1) % training.length;
  
  //Draw all the points based on Perceptron guess
  for (int i = 0; i < count; i++){
    stroke(0);
    strokeWeight(1);
    fill(0);
    int guess = ptron.guess(training[i].inputs);
    if(guess > 0) noFill();
    
    ellipse(training[i].inputs[0], training[i].inputs[1], 8,8);
  }
}