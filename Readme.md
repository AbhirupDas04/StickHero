Flow of code:
The code is run from the FlowControl class which sets up the game and home screens. Then we jump to the HomeScreen class which displays the features for playing the game or reloading the game. When the user clicks play button , we move to the GameScreen class. The GameScreen class handles the main gameplay. It allows the player to play the game (extend stick, move hero, etc.). When the hero dies, we move to the EndScreen class. The endscreen class displays the scores and gives the options to the player to either return to the home screen or restart the game.


Threading:
We have used threading in the GameScreen class to 

Design Patterns used:
-Flyweight : We have used single instance of the Hero to demonstrate flyweight.


GameFeatures:


Instructions to run the code in Maven:



