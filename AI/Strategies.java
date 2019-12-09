package AI;



import src.Board;
import src.Hexagon;

import java.util.ArrayList;
import java.util.Hashtable;
import static java.lang.Math.abs;



public class Strategies {


    /* class contains methods that define each strategy, like moving to the center etc.

     */

    //not covered here is the possibility of drawing from a database of openings, this would stronlgy mitigate the importance of the closingDistance strategy

    private ArrayList<String> Player = new ArrayList<>();
    private ArrayList<String> Opponent = new ArrayList<>();
    private ArrayList<String> KillMoves = new ArrayList<>();
    private GameState gameState;
    private Strategies old;

    double CenterX = Board.hashBoard.get("E5").centerX;
    double CenterY = Board.hashBoard.get("E5").centerY;


    public Strategies(GameState gameState) {
    	this.gameState = gameState;
    	if (gameState.oldGameState != null) {
    		old = new Strategies(gameState.oldGameState);
    	}
    	Hashtable<String, Hexagon> boardState = gameState.boardState;
        //System.out.println("evalfrom " + gameState.evaluateFrom);
    	
        for (int i = 0; i< boardState.size(); i++) {
        	if (!boardState.get(Board.hash.get(i)).empty) {
        		if (gameState.evaluateFrom == boardState.get(Board.hash.get(i)).marble.playerNumber) {
        			Player.add(Board.hash.get(i));
        		}
        		else {
        			Opponent.add(Board.hash.get(i));
        		}
        	}
        }
    }


    public double closingDistance(GameState boardState) {

        double PlayerDisAv = 0;

        for (int i = 0; i < Player.size(); i++) {


            PlayerDisAv += Math.sqrt(Math.pow(boardState.boardState.get(Player.get(i)).centerX - CenterX, 2) + Math.pow(boardState.boardState.get(Player.get(i)).centerY - CenterY, 2));


            /* Distance to Center, use Euclidean Distance:

            (centerPointX - MarbleX )^2 + (centerPointY - MarbleY )^2 = distance^2 ;sqrt.dist^2 = distance

            */
        }
        PlayerDisAv = ( PlayerDisAv / Player.size() );

        return PlayerDisAv;
    }

    public double closingDistanceTest() {

        double PlayerDisAv = 0;

        for (int i = 0; i < Player.size(); i++) {


            PlayerDisAv += Math.sqrt(Math.pow(Board.hashBoard.get(Player.get(i)).centerX - CenterX, 2) + Math.pow(Board.hashBoard.get(Player.get(i)).centerY - CenterY, 2));


            /* Distance to Center, use Euclidean Distance:

            (centerPointX - MarbleX )^2 + (centerPointY - MarbleY )^2 = distance^2 ;sqrt.dist^2 = distance

            */
        }
        PlayerDisAv = ( PlayerDisAv / Player.size() );

        return PlayerDisAv;
    }


    public double cohesion() {

        //determine the number of neighbouring teammates for each marble for each player, adds them together
        int cohesion = 0;
        for (int i = 0; i < Player.size(); i++) {
            for (int j = 1; j < Player.size(); j++) {
                if (i != j) {
                    if (abs((int) Player.get(i).charAt(0) - (int) Player.get(j).charAt(0)) < 2 && abs((int) Player.get(i).charAt(1) - (int) Player.get(j).charAt(1)) < 2) {
                        cohesion++;
                    }
                }
            }
        }
        return cohesion;
    }


    public double breakGroup() {

        /*In order to determine value for a player each marble (of this player) is checked for an opponent marble at one adjacent side of the marble and an
         opponent marble at the opposing adjacent side. */
        double groupBreaks = 0;
        for (int i = 0; i < Player.size(); i++) {
            for (int j = 0; j < Opponent.size(); j++) {
                if (abs((int) Player.get(i).charAt(0) - (int) Opponent.get(j).charAt(0)) < 2 && abs((int) Player.get(i).charAt(1) - (int) Opponent.get(j).charAt(1)) < 2 && ((int) Player.get(i).charAt(0) - (int) Opponent.get(j).charAt(0)+(int) Player.get(i).charAt(1) - (int) Opponent.get(j).charAt(1))!=0) {
                    a:
                    for (int k = 0; k < Opponent.size(); k++) {
                        if (k != j && ((int) Player.get(i).charAt(0) - (int) Opponent.get(k).charAt(0)) == -((int) Player.get(i).charAt(0) - (int) Opponent.get(j).charAt(0)) && ((int) Player.get(i).charAt(1) - (int) Opponent.get(k).charAt(1)) == -((int) Player.get(i).charAt(1) - (int) Opponent.get(j).charAt(1))) {
                            groupBreaks++;
                            break a;
                        }
                    }
                }
            }
        }
        return groupBreaks / 2;
    }


    public double strengthenGroup() {
        /* Determinates how many possibilities the AI has to push the Opponent.
         */

        double groupStrengh = 0;
        for (int i = 0; i < Player.size(); i++) {
            for (int j = 1; j < Player.size(); j++) {
                //if we have a neighbor (at least 2 marbles are needed to even consider a push)
                if (i!=j && abs((int) Player.get(i).charAt(0) - (int) Player.get(j).charAt(0)) < 2 && abs((int) Player.get(i).charAt(1) - (int) Player.get(j).charAt(1)) < 2 && ((int) Player.get(i).charAt(0) - (int) Player.get(j).charAt(0)+(int) Player.get(i).charAt(1) - (int) Player.get(j).charAt(1))!=0) {
                    for (int k = 0; k < Opponent.size(); k++) {
                        if (((int) Player.get(i).charAt(0) - (int) Opponent.get(k).charAt(0)) == -((int) Player.get(i).charAt(0) - (int) Player.get(j).charAt(0)) && ((int) Player.get(i).charAt(1) - (int) Opponent.get(k).charAt(1)) == -((int) Player.get(i).charAt(1) - (int) Player.get(j).charAt(1))) {
                            //found a marble to potentially push, needs checking maybe Opp has more marbles
                            boolean possible = true;
                            //if AI can kill, pushing is possible and no further checking needed
                            if(((int) Opponent.get(k).charAt(0)-((int) Player.get(i).charAt(0)-(int) Opponent.get(k).charAt(0))) < (int) 'A' || ((int) Opponent.get(k).charAt(0)-((int) Player.get(i).charAt(0)-(int) Opponent.get(k).charAt(0))) > (int) 'I' || ((int) Opponent.get(k).charAt(1)-((int) Player.get(i).charAt(1)-(int) Opponent.get(k).charAt(1))) < (int) '1' || ((int) Opponent.get(k).charAt(1)-((int) Player.get(i).charAt(1)-(int) Opponent.get(k).charAt(1))) > (int) '9'){
                                KillMoves.add(Player.get(i));
                            }else{
                                for (int m = 1; m < Opponent.size(); m++) {
                                    if (m != k && ((int) Opponent.get(k).charAt(0) - (int) Player.get(i).charAt(0)) == -((int) Opponent.get(k).charAt(0) - (int) Opponent.get(m).charAt(0)) && ((int) Opponent.get(k).charAt(1) - (int) Player.get(i).charAt(1)) == -((int) Opponent.get(k).charAt(1) - (int) Opponent.get(m).charAt(1))) {
                                        //can we still push? atm 2v2
                                        possible = false;
                                        for (int n = 1; n < Player.size(); n++) {
                                            if (n != i && n != j && ((int) Player.get(j).charAt(0) - (int) Player.get(i).charAt(0)) == -((int) Player.get(j).charAt(0) - (int) Player.get(n).charAt(0)) && ((int) Player.get(j).charAt(1) - (int) Player.get(i).charAt(1)) == -((int) Player.get(j).charAt(1) - (int) Player.get(n).charAt(1))) {
                                                //we have three marbles. does the Opponent has as well 3?
                                                boolean possible2 = true;
                                                //if AI can kill, pushing is possible and no further checking needed
                                                if(((int) Opponent.get(m).charAt(0)-((int) Opponent.get(k).charAt(0)-(int) Opponent.get(m).charAt(0))) < (int) 'A' || ((int) Opponent.get(m).charAt(0)-((int) Opponent.get(k).charAt(0)-(int) Opponent.get(m).charAt(0))) > (int) 'I' || ((int) Opponent.get(m).charAt(1)-((int) Opponent.get(k).charAt(1)-(int) Opponent.get(m).charAt(1))) < (int) '1' || ((int) Opponent.get(m).charAt(1)-((int) Opponent.get(k).charAt(1)-(int) Opponent.get(m).charAt(1))) > (int) '9'){
                                                    KillMoves.add(Player.get(i));
                                                }else{
                                                    for (int p = 1; p < Opponent.size(); p++) {
                                                        if (p != m && p != k && ((int) Opponent.get(m).charAt(0) - (int) Opponent.get(k).charAt(0)) == -((int) Opponent.get(m).charAt(0) - (int) Opponent.get(p).charAt(0)) && ((int) Opponent.get(m).charAt(1) - (int) Opponent.get(k).charAt(1)) == -((int) Opponent.get(m).charAt(1) - (int) Opponent.get(p).charAt(1))) {
                                                            //no pushing here possible
                                                            possible2 = false;
                                                        }
                                                    }
                                                }
                                                if (possible2) {
                                                    groupStrengh++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (possible){
                                groupStrengh++;
                            }
                        }
                    }
                }
            }
        }
        //System.out.println(Test1+Test2);
        return groupStrengh;
    }


    public int amountOppMarbles() {
        return Opponent.size();
    }

    public int amountOwnMarbles() {
        return Player.size();

    }
    public int compareMarblesWon() {
        if (old != null) {
            return old.amountOppMarbles() - this.amountOppMarbles();
        }
        return 0;
    }
    
    public int compareMarblesLost() {
        if (old != null) {
            return old.amountOwnMarbles() - this.amountOwnMarbles();
        }
        return 0;
    }

    //additional Strategy: checkKillMove, checks whether pushing out one opponent marble is possible without loosing own marble in subsequent Move,
    //I added this based on the findings of the paper, ie. the agent often had trouble to find that it is already in an position to score

    public double checkKillMove() {
    //brings AI close to killing Opponent in the next turn
        return KillMoves.size();
    }
}
