/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA5 Write-up
   
  This file is for CSE 12 PA5 in Winter 2023,
  This file is to implement a data structure similar to 
  HashMap. The file is to do the hashMap application. It’s 
  designed for a wildlife sanctuary that can efficiently track
  the animals that are in its care.
*/
import java.util.HashMap;
import java.util.Map;

/**
* This class is called Sanctuary that stores some methods,
* and do a data structure similar to HashMap. The sanctuary 
* efficiently track the animals that are in its care. The sanctuary
* wants to keep track of how many of each species are currently 
* located on its grounds.
*/
public class Sanctuary{

    /**
     * There are the instance variables
     */
    HashMap<String, Integer> sanctuary;
    private final int maxAnimals;
    private final int maxSpecies;

    /** 
     * Constructor to initialize the sanctuary’s information with
     * the maxAnimals and the maxSpecies
     * 
     * @param maxAnimals max number of animals that sanctuary support
     * @param maxSpecies max number of animals that sanctuary support
     */
    public Sanctuary(int maxAnimals, int maxSpecies){
        if(maxAnimals<=0||maxSpecies<=0||maxSpecies>maxAnimals){
            throw new IllegalArgumentException();
        }
        sanctuary = new HashMap<>();
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    }

    /** 
     * The method is to get the specific number of the species in 
     * the sanctuary.
     * 
     * @param species the specific species 
     * @return the number of animals with the specific species
     */
    public int countForSpecies(String species){
        if(species == null){
            throw new IllegalArgumentException();
        }
        return sanctuary.getOrDefault(species, 0);
    }
    

    /** 
     * The method is to get the total number of the animals in 
     * the sanctuary.
     * 
     * @return the total number of the animals in sanctuary
     */
    public int getTotalAnimals(){
        int total = 0;
        for(Integer count: sanctuary.values()){
            total += count;
        }
        return total;
    }

    /** 
     * The method is to get the total number of the species in 
     * the sanctuary.
     * 
     * @return the total number of the species in sanctuary
     */
    public int getTotalSpecies(){
        return sanctuary.size();
    }

    /** 
     * The method is to get max number of the animals that the 
     * the sanctuary support
     * 
     * @return the max number of the animals in sanctuary
     */
    public int getMaxAnimals(){
        return maxAnimals;
    }

    /** 
     * The method is to get max number of the species that the 
     * the sanctuary support
     * 
     * @return the max number of the species in sanctuary
     */
    public int getMaxSpecies(){
        return maxSpecies;
    }

    /** 
     * The method is to return the number of animals that 
     * could not be rescued.
     * 
     * @param species the specific species 
     * @param num the rescued animals of the specific species 
     * @return the number of animals can not be rescued 
     */
    public int rescue(String species, int num){
        if (num<=0){
            throw new IllegalArgumentException();
        }
        if (species ==null){
            throw new IllegalArgumentException();
        }
        if (!sanctuary.containsKey(species)&& 
            getTotalSpecies()+1>getMaxSpecies()){
            return num;
        }
        if (!sanctuary.containsKey(species)&& 
            getTotalAnimals()+1>getMaxAnimals()){
            return num;
        }
        int remainAnimals = getMaxAnimals()-getTotalAnimals(); 
        int remainSpecies = getMaxSpecies()-getTotalSpecies();
        if (remainAnimals>=0 ||remainSpecies>=0){
            int rescureNum = Math.min(num, remainAnimals);
            sanctuary.put(species, 
                sanctuary.getOrDefault(species, 0) + rescureNum);
            return num-rescureNum;
        }else{
            return num;
        }
    }

    /** 
     * The method is to remove the num animals of species 
     * from the sanctuary. 
     * 
     * @param species the specific species 
     * @param num the release animals of the specific species 
     */
    public void release(String species, int num){
        if (num<=0){
            throw new IllegalArgumentException();
        }
        if (num>countForSpecies(species)){
            throw new IllegalArgumentException();
        }
        if (species == null){
            throw new IllegalArgumentException();
        }
        if (!(sanctuary.containsKey(species))){
            throw new IllegalArgumentException();
        }
        int animalsLeft = countForSpecies(species)-num;
        if (animalsLeft == 0) {
            sanctuary.remove(species);
        } else {
            sanctuary.put(species, animalsLeft);
        }
    }
}

