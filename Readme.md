Group no.: 140
Members: Abhirup Das (Roll no: 2022019) , Het Riteshkumar Shah (Roll no: 2022213)


Flow of code:
The code is run from the FlowControl class which sets up the game and home screens. Then we jump to the HomeScreen class which displays the features for playing the game or reloading the game. When the user clicks play button , we move to the GameScreen class. The GameScreen class handles the main gameplay. It allows the player to play the game (extend stick, move hero, etc.). When the hero dies, we move to the EndScreen class. The endscreen class displays the scores and gives the options to the player to either return to the home screen or restart the game.


Threading:
We have used threading in the GameScreen class between lines 705 to 893. The main purpose of these threads was simply to be a constant checker for the character's position, and also, to collect cherries.


Design Patterns used:
-Singleton : We have used single instance of the Hero to demonstrate the singleton design pattern. You can see this in the 'Hero' class.
-Flyweight : We have used it while generating pillars. You can also visit this in the 'Flyweight' class.


Game Features:
- There are two modes to play the game - reload a saved game or start a new game.
- The game lets the player save the game and exit whenever he wants.
- The game has two options for the user when the hero dies- first, end the game or second, revive the hero by using certain numebr of cherries.
- The game saves the data for saved games and highest scores.
- The hero is able to smootly move across the pillars with the stick. There are animations for the hero falling down, out of the frame if he dies.
- The game generates random cherries between the pillars for the hero to collect.
- The game has several backgrounds, out of which one is randomly selected each time we play the game.
- There is a bonus score awarded if the stick hits the red region at the center of the pillar.


Sounds Effects/Animations:-

- Each scene has its own background music.
- The hero has separate animations for when he hits the pillar while upside down or when the stick is of inappropriate length.
- We have sound effects for the stick landing.
- We also have put effects for the walking of the character as well as that of collecting the cherry.


Instructions to run the code via cmd:

1. Download the zip folder in GC and extract its contents.
2. Open a command prompt of your choice and cd to the src folder.
3. Do 'mvn clean', then 'mvn compile' and then 'mvn package'.
4. Then cd to the 'target' folder.
5. Then run the following command :-

java --module-path <lib_folder of your java_fx installation> --add-modules javafx.media,javafx.controls -jar StickHero-1.0-SNAPSHOT.jar

where the lib folder path has to be the path to the lib folder of wherever you have installed your javafx.

For Example, here's my command :- java --module-path C:\Users\ABHIRUP-ACER\Downloads\openjfx-21.0.1_windows-x64_bin-sdk\javafx-sdk-21.0.1\lib --add-modules javafx.media,javafx.controls -jar StickHero-1.0-SNAPSHOT.jar

6. Finally the game should run. If it doesn't, please contact us as this works every time we have downloaded from gc and ran it via cmd.



Some Key Points about Our Game:-

1. Our code displays the 'Best Score' achieved after one run of the game. This score is permanent even if you terminate the program and run it again as we wanted to make it feel like a game.
2. In the home page, you would see a 'Load Save' icon. If you click it, it will display the 3 best scores that you have achieved till date. You can click on any one of them to continue playing from where you left off.
3. In order to save a game, click the 'Save' icon during the game.