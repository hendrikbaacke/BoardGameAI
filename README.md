# BoardGameAI
## The aim of this project:
Developing AI strategies in multiplayer boardgame 'Abalone'.
Moreover, try to increase the performance of these AI's.
On top of that, also compare these AI's, using experiments, and research which one is better.

## The way the code is built up:
There are in total two packages: one called src, and one called AI.

The src package includes the classes which contain the way the game itself is built, including the board, its marbles and hexagons and the way how to perform a move. Moreover, this package contains the main class and the GUI, which are needed to perform experiments and to perform the AI's.

The AI package contains classes for the game tree, game state, evaluation function and search algorithms such as greedy, alpha beta and Monte Carlo tree search. Besides that, there are also folders which contain both the results (if the genetic algorithm has been run) and the framework and implementation of the genetic algorithm.

## How to use the GUI:
Firstly, the class Main.java needs to be run. Then, there are multiple buttons which could be chosen from. It is possible to look at the rules, credits and the 'how to', which shows how to play this game. 

When the player is ready to choose one of the options, there are three modes to choose from. These are: Player vs. Player, Player vs. Computer and Computer vs. Computer.

In the PvP mode, a game can be played between two human players. The players alternately perform a move. How this move can be performed can be found in the 'how to' of the GUI.

In the PvC mode, a game is played between a human player and an AI. The AI that is playing against the human player is the greedy algorithm, using an evaluation function that is found by the genetic algorithm. The AI automatically performs a move after the player performed a move.

In the CvC mode, the player can do two things: run the genetic algorithm, and perform a game between two AI's of the user's own choosing. The AI's can be chosen by selecting it in the menu on the left side of the screen. When this game is finished, the result and resulting board state gets displayed.

The genetic algorithm is ran by clicking a button twice, firstly creating the initial state, and after that running the whole algorithm. The fact this needs to be pressed twice is indicated on the button. The results for every game in the genetic algorithm is printed in the command line.
