package AI;



import src.Board;
import src.BoardRows;
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
        PlayerDisAv = ( PlayerDisAv / Player.size());

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

    public int amountOwnMarbles() { return Player.size(); }

    public int compareMarblesWon() {
        if (old != null) {
            System.out.println("+++++++++++"+ (old.amountOppMarbles() - this.amountOppMarbles()));
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

    public double closingDistanceOpp(GameState boardState) {

        double PlayerDisAv = 0;

        for (int i = 0; i < Opponent.size(); i++) {
            PlayerDisAv += Math.sqrt(Math.pow(boardState.boardState.get(Opponent.get(i)).centerX - CenterX, 2) + Math.pow(boardState.boardState.get(Opponent.get(i)).centerY - CenterY, 2));
        }
        PlayerDisAv = ( PlayerDisAv / Opponent.size());

        return PlayerDisAv;
    }
    
    //return 1 if a marble is in danger, return 0 if there is no marble in danger
    public int danger() {
    	Hashtable<String, Hexagon> board = this.gameState.boardState;
    	BoardRows rows = src.GameData.rows;
    	if (dangerRow(rows.horizontal) ==1) {
    		return 1;
    	}
    	if (dangerRow(rows.topLeft) ==1) {
    		return 1;
    	}
    	if (dangerRow(rows.topRight) ==1) {
    		return 1;
    	}
    	return 0;
    }

    public int dangerRow(ArrayList<ArrayList<String>> row) {
    	//at the "start" of a row -> so spot 0, 1, 2, 3
    	//these are the playernumbers!!!
    	int bfirst = 0;
    	int bsecond = 0;
    	int bthird = 0;
    	int bfourth = 0;
    	int bfifth = 0;
    	
    	//at the "end" of a row -> so spot size -4, size - 3, size -2, size -1
    	int endfirst = 0;
    	int endsecond = 0;
    	int endthird = 0;
    	int endfourth = 0;
    	int endfifth = 0;
    	
    	Hashtable<String, Hexagon> board = this.gameState.boardState;
    	
    	for (int i = 0; i < row.size(); i++) {
    		if (row.get(i).size()>4) {
	    		if (!board.get(row.get(i).get(0)).empty) {
	    			bfirst = board.get(row.get(i).get(0)).marble.playerNumber;
	    		}
	    		if (!board.get(row.get(i).get(1)).empty) {
	    			bsecond = board.get(row.get(i).get(1)).marble.playerNumber;;
	    		}
	    		if (!board.get(row.get(i).get(2)).empty) {
	    			bthird = board.get(row.get(i).get(2)).marble.playerNumber;;
	    		}
	    		if (!board.get(row.get(i).get(3)).empty) {
	    			bfourth = board.get(row.get(i).get(3)).marble.playerNumber;;
	    		}
	    		if (!board.get(row.get(i).get(4)).empty) {
	    			bfifth = board.get(row.get(i).get(4)).marble.playerNumber;;
	    		}
	    		if (!board.get(row.get(i).get(row.get(i).size()-1)).empty) {
	    			endfirst = board.get(row.get(i).get(row.get(i).size()-1)).marble.playerNumber;
	    		}
	    		if (!board.get(row.get(i).get((row.get(i).size()-2))).empty) {
	    			endsecond = board.get(row.get(i).get(row.get(i).size()-2)).marble.playerNumber;
	    		}
	    		if (!board.get(row.get(i).get(row.get(i).size()-3)).empty) {
	    			endthird = board.get(row.get(i).get(row.get(i).size()-3)).marble.playerNumber;
	    		}
	    		if (!board.get(row.get(i).get(row.get(i).size()-4)).empty) {
	    			endfourth = board.get(row.get(i).get(row.get(i).size()-4)).marble.playerNumber;
	    		}
	    		if (!board.get(row.get(i).get(row.get(i).size()-5)).empty) {
	    			endfifth = board.get(row.get(i).get(row.get(i).size()-5)).marble.playerNumber;
	    		}
    		}
    	
    		//now check whether there is an endangered marble
    		if (bfirst == gameState.evaluateFrom) {
    			if (bsecond == gameState.evaluateFrom) {
    				if (bthird > 0 && bthird != gameState.evaluateFrom && bthird ==bfourth && bthird ==bfifth) {
    					System.out.println("case 1");
    					return 1;
    				}
    			}
    			else if(bsecond > 0 && bsecond != gameState.evaluateFrom && bsecond == bthird) {
    				System.out.println("case 2");
    				return 1;
    			}
    		}
    		
    		if (endfirst == gameState.evaluateFrom) {
    			if (endsecond == gameState.evaluateFrom) {
    				if (endthird > 0 && endthird != gameState.evaluateFrom && endthird ==endfourth && endthird ==endfifth) {
    					System.out.println("case 3");
    					return 1;
    				}
    			}
    			else if(endsecond > 0 && endsecond != gameState.evaluateFrom && endsecond == endthird) {
    				System.out.println("case 4");
    				return 1;
    			}
    		}
    	}
    	System.out.println("no danger");
    	return 0;
    }

}
