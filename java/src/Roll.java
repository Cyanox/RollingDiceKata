package src;
import org.junit.Assert;
import src.Dice;
public class Roll {
    public enum RollType {
        NORMAL,
        ADVANTAGE,
        DISADVANTAGE
    }
    private int diceValue;
    private int nbRoll;
    private int modifier;
    private String formula;
    public Roll(String formula) {
        String res;
        this.formula=formula;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        res= this.formula.substring(0,1);
        if ( res.equals("d") ){
            this.formula = this.formula.replace("d", "1d");
            String combi[] = this.formula.split("[d'+']");
            this.diceValue=Integer.parseInt(combi[1]);
            this.nbRoll=Integer.parseInt(combi[0]);
            if (combi.length>2){
                this.modifier=Integer.parseInt(combi[2]);}
        }
        else if(alphabet.contains(res)){
            this.diceValue=-50;
            this.nbRoll=-50;}
        else if(this.formula.contains("/")){
            this.diceValue=-50;
            this.nbRoll=-50;}
        else if(this.formula.contains("d-")){
            this.diceValue=-50;
            this.nbRoll=-50;}
        else if(this.formula.contains("-")){
            String combi[] = this.formula.split("[d-]");
            this.diceValue=Integer.parseInt(combi[1]);
            this.nbRoll=Integer.parseInt(combi[0]);
            if (combi.length>2){
                this.modifier=-Integer.parseInt(combi[2]);
            }}
        else {
            String combi[] = this.formula.split("[d'+']");
            this.diceValue=Integer.parseInt(combi[1]);
            this.nbRoll=Integer.parseInt(combi[0]);
            if (combi.length>2){
                this.modifier=Integer.parseInt(combi[2]);}
        }

    }
    public Roll(int diceValue, int nbRoll, int modifier) {
        this.diceValue=diceValue;
        this.nbRoll=nbRoll;
        this.modifier=modifier;
    }
    public int makeRoll(RollType rollType) {
        int save=0;
        int result;
        int max=0;
        int min=0;
        int add =0;
        if (this.diceValue <= 0 || this.nbRoll <= 0){return -1;}
        switch (rollType){
            case NORMAL:
                for(int i= 0; i < this.nbRoll; i++){
                    Dice abc = new Dice(this.diceValue);
                    save=abc.rollDice();
                    add = add + save;}
                //System.out.print(this.modifier);
                result = add + this.modifier;
                if (result < 0){return 0;}
                else{return result;}

            case ADVANTAGE:
                for(int i= 0; i <= this.nbRoll; i++){
                    Dice abc = new Dice(this.diceValue);
                    save=abc.rollDice();
                    if ( save > max){max = save;}}
                result = max + this.modifier;
                if (result < 0){return 0;}
                else{return result;}

            case DISADVANTAGE:
                for(int i= 0; i <= this.nbRoll; i++){
                    Dice abc = new Dice(this.diceValue);
                    save=abc.rollDice();
                    if (i==0){min = save;}
                    else if ( save < min){min = save;}}
                result = min + this.modifier;
                if (result < 0){return 0;}
                else{return result;}
        }
        return 0;
    }
}