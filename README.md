# Arkanoid-Game

This Arkanoid game was developed as a project for an Object-Oriented Programming (OOP) course at Bar Ilan University.

![collage](https://github.com/user-attachments/assets/80235a9c-a90e-49fc-9fd3-731e865e2788)

## Overview
This game was created as part of an OOP course during my undergraduate studies. It features multiple levels with varying degrees of difficulty, presented in a user-friendly graphical interface. The game is developed in Java using the IntelliJ IDEA Ultimate IDE, adhering to core OOP concepts such as:

* Polymorphism and Inheritance
* Implementation of essential design patterns in OOP, including Observer, Factory, etc.
* Utilization of various generic collections and data structures like linked lists and array lists.
* Working with graphical user interfaces (GUI).

## Design Patterns
#### Decorator Design Pattern
The Decorator design pattern was applied to add extra functionality to existing Animation objects. For instance, the Background class acts as the base component, while 'Bird' , 'Building' , 'Night' , 'Stars' , 'Sun' , and 'Target' classes serve as decorators that extend the base Background class. These decorator classes introduce specific background drawing capabilities by enhancing the drawOn method of the base class. This pattern allows for the composition of different backgrounds with various features, all while keeping them interchangeable and encapsulated. 

#### Observer Design Pattern
The Observer design pattern was effectively utilized to handle events triggered by various game objects. These objects are structured as Observables, with listener classes acting as Observers. Listener classes subscribe to events generated by game objects and react accordingly, performing actions such as removing blocks or balls from the game or updating the player's score. By using the Observer pattern, the code remains highly flexible and modular. New listeners can be easily integrated without needing to alter the existing game objects, resulting in a more maintainable and extensible system.

#### Builder Design Pattern
The Builder design pattern was fundamental in this project, enabling the systematic creation of various game elements. This method not only improves flexibility but also enhances usability by delegating the construction of specific objects to dedicated builder classes. In this project, builders were employed to create different components like balls, blocks, and paddles, ensuring a clear and well-organized development process. This approach results in code that is more understandable and easier to maintain. Moreover, it allows for easier adjustments of object properties during their construction, ensuring adaptability and efficiency throughout the project.

#### Factory Design Pattern
In this pattern, the 'LevelInformation' interface serves as a factory for generating different levels of the game. Concrete classes implement this interface to create specific level instances. This approach allows for the creation of various level objects without altering existing code, aligning with the principles of the Factory design pattern.

## Instructions
* Use the 'Left' and 'Right' arrow keys to move the paddle.
* Press 'P' to pause the game.
* Press 'Space' to resume play.
* Clear all the blocks and advance through the levels until you win, or lose and try again!

## Installation and Execution






