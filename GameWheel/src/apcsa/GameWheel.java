package apcsa;

import java.util.ArrayList;

public class GameWheel
{
  private ArrayList<Slice> slices; // List of slices making up the wheel
  private int currentPos;   // Position of currently selected slice on wheel

  /* Returns string representation of GameWheel with each numbered slice
   * on a new line
   */
   public String toString(){
    //Implement the toString method here
    String gameWheelStr = "";
    for(int i = 0; i < slices.size(); i++){
      gameWheelStr += i + " - " + slices.get(i) + "\n";
    }
    return gameWheelStr;
  }


  /* Randomizes the positions of the slices that are in the wheel, but without
   * changing the pattern of the colors
   */
  public void scramble()
  {
    ArrayList<Slice> red = new ArrayList();
    ArrayList<Slice> blu = new ArrayList();
    ArrayList<Slice> blk = new ArrayList();
    for(int i = 0; i < slices.size(); i++){
      if (i == 0 || i%5 == 0){
        blk.add(slices.get(i));
      }
      else if (i%2 == 0){
        blu.add(slices.get(i));
      }
      else{
        red.add(slices.get(i));
      }
    }

    int d = 0;
    int e = 0;
    int f = 0;
    for(int i = 0; i < slices.size(); i++){
      if ((i == 0 || i%5 == 0)){
        //black
        d = (int)((Math.random()*(blk.size())));
        slices.set(i, blk.get(d));
        blk.remove(d);
      }
      else if ((i%2 == 0)){
        //blue
        e = (int)((Math.random()*(blu.size())));
        slices.set(i, blu.get(e));
        blu.remove(e);
      }
      else {
        //red
        f = (int)((Math.random()*(red.size())));
        slices.set(i, red.get(f));
        red.remove(f);
      }
    }
  
  }



  /* Sorts the positions of the slices that are in the wheel by prize amount,
   * but without changing the pattern of the colors.
   */
   public void sort(){
    ArrayList<Slice> red = new ArrayList();
    ArrayList<Slice> blu = new ArrayList();
    ArrayList<Slice> blk = new ArrayList();
    for(int i = 0; i < slices.size(); i++){
      
      if (i%5 == 0){
        if (blk.size() != 0) { 
          for(int j = 0; j < blk.size(); j++){
             if (slices.get(i).getPrizeAmount() <= blk.get(j).getPrizeAmount()) {
              blk.add(j, slices.get(i));
             }
             else if (slices.get(i).getPrizeAmount() >= blk.get(blk.size()-1).getPrizeAmount()) {
                blk.add(slices.get(i));
              }
            }
          }
        else { 
          blk.add(slices.get(i));
         }
      }
      else if (i%2 == 0){
        if (blu.size() != 0 ) { 
          for(int j = 0; j < blu.size(); j++){
             if (slices.get(i).getPrizeAmount() <= blu.get(j).getPrizeAmount()) {
              blu.add(j, slices.get(i));
             }
             else if (slices.get(i).getPrizeAmount() >= blu.get(blu.size()-1).getPrizeAmount()) {
                blu.add(slices.get(i));
            }
          }
        }
        else { 
          blu.add(slices.get(i));
         }
      }
      else{
        if (red.size() != 0){ 
          for(int j = 0; j < red.size(); j++){
             if (slices.get(i).getPrizeAmount() <= red.get(j).getPrizeAmount()) {
              red.add(j, slices.get(i));
             }
              else if (slices.get(i).getPrizeAmount() >= red.get(red.size()-1).getPrizeAmount()) {
                red.add(slices.get(i));
            }
          }
        }
        else { 
          red.add(slices.get(i));
         }
      }
    }
    for(int i = 0; i < slices.size(); i++){
     if ((i%5 == 0)){
        //black
        slices.set(i, blk.get(0));
        blk.remove(0);
      }
      else if ((i%2 == 0)){
        //blue
        slices.set(i, blu.get(0));
        blu.remove(0);
      }
      else {
        //red
        slices.set(i, red.get(0));
        red.remove(0);
      }
 
    }
}


  /* COMPLETED METHODS - YOU DO NOT NEED TO CHANGE THESE */

  /* Creates a wheel with 20 preset slices
   */
  public GameWheel()
  {
    this(getStandardPrizes());
  }

  /* Creates a wheel with 20 slices, using values from array parameter
   */
  public GameWheel(int[] prizes)
  {
    currentPos = 0;
    slices = new ArrayList<Slice>();
    for(int i = 0; i < 20; i++){
      int pa = 0;
      String col = "blue";
      if(i < prizes.length)
        pa = prizes[i];
      if (i%5 == 0)
        col = "black";
      else if (i%2 == 1)
        col = "red";
      slices.add(new Slice(col, pa));
    }
  }

  /* Spins the wheel by so that a different slice is selected. Returns that
   * slice (Note: the 10 slices following the current slice are more likely to
   * be returned than the other 10).
   */
  public Slice spinWheel()
  {
    //spin power between range of 1-50 (inclusive)
    int power = (int)(Math.random()*50 + 1);
    int newPos = (currentPos + power) % slices.size();
    currentPos = newPos;
    return slices.get(currentPos);
  }

  public Slice getSlice(int i){
    int sliceNum = i;
    if(i < 0 || i > 19)
      sliceNum = 0;
    return slices.get(sliceNum);
  }

  // Makes an array with a standard list of prizes
  private static int[] getStandardPrizes()
  {
    int[] arr = new int[20];
    for (int i=0; i < 20; i++)
    {
      if (i%5 == 0)
        arr[i] = i*1000;
      else if (i%2 == 1)
        arr[i] = i*100;
      else
        arr[i] = i*200;
    }
    return arr;
  }
}


