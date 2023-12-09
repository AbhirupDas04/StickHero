1) We have attached screenshots of 3 different screens of our UI. While these don't represent
all the graphical elements that we have, they ought to represent most.


2) Also, some classes do have code in them. They represent the code
for the UI creation and nothing else.


3) Most of our classes are self explanatory. That's why no comments have been
added to them.


4) All the java classes can be found in the .src folder.


5) If you want to compile with maven that's also possible but please
remember to add the javafx lib path to the module path while running
if you want to run the pom.xml.

Flow of code:
The code is run from the FlowControl class which sets up the game and home screens. Then we jump to the HomeScreen class which displays the features for playing the game or reloading the game. When the user clicks play button , we move to the GameScreen class. The GameScreen class handles the main gameplay. It allows the player to play the game (extend stick, move hero, etc.). When the hero dies, we move to the EndScreen class. The endscreen class displays the scores and gives the options to the player to either return to the home screen or restart the game.


Threading:
We have used threading in the GameScreen class to 

Design Patterns used:
-Flyweight : We have used single instance of the Hero to demonstrate flyweight.


GameFeatures:


Instructions to run the code in Maven:



