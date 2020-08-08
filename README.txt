A Gradle project for the following problem statemet :
Each kingdom has an animal emblem and Shan needs to send a message with the animal in the message to win them over.
SPACE emblem - Gorilla, LAND emblem - Panda, WATER emblem - Octopus,
ICE emblem - Mammoth, AIR emblem - Owl, FIRE emblem - Dragon.
Your coding challenge is to have King Shan send secret message to each kingdom and win them over.
Once he wins 3 more kingdoms, he is the ruler! The secret message needs to contain the letters of the animal in their emblem.
For example, secret message to the Land kingdom (emblem: Panda) needs to have the letter 'p','n','d' at-least once and 'a' atleast
twice. If he sends "ahdvvnxxxautup" to the Land kingdom, he will win them over.
King Shan wants to make sure his secret message is not found by his enemies easily. So he decides to use the oldest of the
ciphers 'Seasar cipher�. A cipher is a type of secret code, where you swap the letters around so that no-one can read your
message.

How to run :
1. You can build the project using :
    (a). ./gradlew build for linux based os
    (b). gradlew build for windows based os
2. You can run test using :
    (a). ./gradlew test for linux based os
    (b). gradlew test for windows based os
3. After build the solution can be executed using :
    java -jar geektrust.jar <path_to_input_file>

About the project :
Name : goldencrown
1. The input is provided in the command line via path to the input.txt file
2. GoldenCrownMain.java is the main class for the project.
3. The input.txt file is parsed using FIleParser.java present in parser folder and returns the data as String.
4. Kingdoms and their emblems are present in KingdomsAndEmblems.txt. 
   It is parsed using file parser.
5. Pair is  a container to ease passing around a tuple of two objects defined in pair folder.
6. StringMapper is responsible for mapping the data parsed as String to a Data Structure which inturns allows us to process it.
7. Solution contains the necessary logic to process the data and generate the result.
