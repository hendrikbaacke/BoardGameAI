package AI;


public class Strategies {

    /* class contains methods that define each strategy, like moving to the center etc.

     */

    //not covered here is the possibility of drawing from a database of openings, this would stronlgy mitigate the importance of the closingDistance strategy




    public void closingDistance(GameState boardState){

        //calculate Manhattan distance of each Players marble to center, high distance should return a high value, since closing in has greater priority

    }



    public void cohesion(GameState boardState){

        //determine the number of neighbouring teammates for each marble for each player, take the difference

    }


    public void breakGroup(GameState boardState){

        /*In order to determine value for a player each marble (of this player) is checked for an opponent marble at one adjacent side of the marble and an
         opponent marble at the opposing adjacent side. Again the difference between the values for both players is calculated.*/
    }


    public void strengthenGroup(GameState boardState){


    }

    public void amountOppMarbles(GameState boardState){


    }

    public void amountOwnMarbles(GameState boardState){


    }


    //additional Strategy: checkKillMove, checks whether pushing out one opponent marble is possible without loosing own marble in subsequent Move,
    //I added this based on the findings of the paper, ie. the agent often had trouble to find that it is already in an position to score

    public void checkKillMove(GameState boardState){

    /* solve by giving a very high weight parameter and binary value for check killMove 0/1, if a kill move is available without consequences for the AI,
    checkKillMove should overvote most other possible moves
   */
    }
}
