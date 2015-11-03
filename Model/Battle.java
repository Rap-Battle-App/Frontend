package Model
 /**
 * Created by Dennis on 03.11.2015.
 */
public class Battle {
    private Rapper rapper[];
    private Beat beats[];
    private int phase;
    private int votes[];
    private int id;

    public Rapper[] getRapper(){
        return  this.rapper;
    }

    public Rapper getRapper(int number) { //number: 0 oder 1
        if(number >= 0 && number <=1){
            return this.rapper[number];
        }
        throw new IllegalArgumentException("Number muss zwischen 0 und 1 sein");
    }

    public Beat getBeat (int number) { //number: 0 oder 1
        if(number >= 0 && number <=1){
            return this.beats[number];
        }
        throw new IllegalArgumentException("Number muss zwischen 0 und 1 sein");
    }

    public Beat[] getBeats(){
        return  this.beats;
    }

    public int getVotes (int number) { //number: 0 oder 1
        if(number >= 0 && number <=1){
            return this.votes[number];
        }
        throw new IllegalArgumentException("Number muss zwischen 0 und 1 sein");
    }

    public int[] getVotes(){
        return this.votes;
    }

    public int getPhase(){
        return this.phase;
    }

    public void increasePhase(){
        this.phase++;
    }

    public int getId(){
        return this.id;
    }

    public Battle(int id, int phase, int[] votes, Rapper[] rapper, Beat[] beats){
        this.id = id;
        this.phase = phase;
        this.votes = votes;
        this.rapper = rapper;
        this.beats = beats;
    }
}
