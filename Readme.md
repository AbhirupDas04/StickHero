Group no.: 140
Members: Abhirup Das (Roll no: 2022019) , Het Riteshkumar Shah (Roll no: 2022213)


Flow of code:
The code is run from the FlowControl class which sets up the game and home screens. Then we jump to the HomeScreen class which displays the features for playing the game or reloading the game. When the user clicks play button , we move to the GameScreen class. The GameScreen class handles the main gameplay. It allows the player to play the game (extend stick, move hero, etc.). When the hero dies, we move to the EndScreen class. The endscreen class displays the scores and gives the options to the player to either return to the home screen or restart the game.


Threading:
We have used threading in the GameScreen class to 

Design Patterns used:
-Flyweight : We have used single instance of the Hero to demonstrate flyweight.


Game Features:
- There are two modes to play the game - reload a saved game or start a new game.
- The game lets the player save the game and exit whenever he wants.
- The game has two options for the user when the hero dies- first, end the game or second, revive the hero by using certain numebr of cherries.
- The game saves the data for saved games and highest scores.
- The hero is able to smootly move across the pillars with the stick. There are animations for the hero falling down, out of the frame if he dies.
- The game generates random cherries between the pillars for the hero to collect.
- The game has several backgrounds, out of which one is randomly selected each time we play the game.
- There is a bonus score awarded if the stick hits the red region at the center of the pillar.


Instructions to run the code in Maven:



