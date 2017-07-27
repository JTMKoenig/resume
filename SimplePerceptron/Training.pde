class Trainer {
  float[] inputs;
  int target;
  
  Trainer(float x, float y, int a) {
    inputs = new float[3];
    inputs[0] = x;
    inputs[1] = y;
    inputs[2] = 1;
    
    target = a;
  }
}