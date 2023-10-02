public class Dog{
  
  private String name;
  
  private String breed; 
  
  //constructs the dog
  public Dog(String nameParam, String breedParam){
    
    name = nameParam;
    breed = breedParam;
    
    
  }
  //returns the name of a dog
  public String getName(){
    
    return name;
    
  }
  //returns how many sheep the dog can herd based on its breed
  public int herd(){
    
    String lowerCaseBreed = breed.toLowerCase();
    
    if(lowerCaseBreed.contains("shepard")){
      
      return 25;
      
    }
    else if(lowerCaseBreed.contains("collie")){
      
      return 20;
      
    }
    else if(lowerCaseBreed.contains("kelpie") || lowerCaseBreed.contains("teruven")){
      
      return 30;
      
    }
    else{
      //anyother breed can herd 10 Sheep
      return 10;
      
    }
  }
  
}

