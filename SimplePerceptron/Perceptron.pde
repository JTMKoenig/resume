class Perceptron {
  float[] weights; //array of weights for inputs
  float r; //learning constant
  
  //Perceptron params n weights, and learning constant
  Perceptron(int n, float r_) {
    weights = new float[n];
   //Initialize weights randomly
   for (int i = 0; i < weights.length; i++){
     weights[i] = random(-1,1);
   }
   r = r_;
  }
  //Training Function
  //Weights Adjusted based on Target
  void train(float[] inputs, int target){
    //Perceptron's Guess
    int guess = guess(inputs);
    //Calculate Error = Target - Guess
    int error = target - guess;
    
    //Tune all the weights
    for (int i = 0; i < weights.length; i++){
      weights[i] += error * inputs[i] * r ;
    }
  }
  //Perceptron guesses -1 or 1 based on inputs and weights
  int guess(float[] inputs) {
      float sum = 0;
      for (int i = 0; i < weights.length; i++){
      sum += inputs[i]*weights[i];
      }
      return sign(sum);
  }
  
  //The Activation Function
  int sign(float n){
    if (n >= 0){
      return 1;
    } else{
      return -1;
    }
  }

  float[] getWeights(){
    return weights;
  }
  
  
}