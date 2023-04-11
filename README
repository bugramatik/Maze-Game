# Maze Game - Pac-Man Style
This repository contains a Pac-Man style Maze game implemented using Java and object-oriented programming principles, including design patterns such as Strategy Pattern, Singleton Pattern, Observer Pattern, and Decorator Pattern. The game is a fun and engaging experience for users who enjoy solving puzzles and exploring mazes, featuring various actors such as the player, enemies, bullets, power-ups, and walls.
## Features
- User-controlled player navigating the maze to collect all power-ups to win the game.
- Enemies patrolling the maze, capable of killing the player upon collision.
- Walls defining impassable locations on the game map.
- Bullets created by the player to damage enemies upon collision.
- Power-ups spread around the map for the player to collect.
- Win condition: collect all power-ups; lose condition: killed by an enemy.
- UML class diagram and Javadoc documentation provided.

## Design Patterns and OOP Principles
The Maze Game is designed using various design patterns and OOP principles to ensure a clean, maintainable, and scalable codebase. Some of the key concepts used in the project are:

1. **Strategy Pattern**: Used for implementing different enemy patrol strategies (horizontal, vertical, or stationary).
1. **Singleton Pattern**: Applied to the GameDisplay class to provide a global access point for the display window.
1. **Observer Pattern**: Implemented for handling collisions between actors, with the CollisionComponent class notifying ICollisionListener objects of collisions.
1.  **Decorator Pattern**: Used in the AbstractActor class, with IRealTimeComponent acting as the decorator.

## Class Hierarchy
The provided class hierarchy consists of various classes and interfaces, including Main, GameDisplay, GameEngine, GameMapLoader, Position2D, AABB, AbstractActor, IRealTimeComponent, IDrawable, SpriteComponent, PlayerInputComponent, CollisionComponent, ICollisionListener, AbstractPatrolStrategy, and various game actors (Player, PowerUp, Bullet, Wall, Enemy). The hierarchy is extensible and modifiable to accommodate additional features and improvements.

## Game Details
The game includes various actors with specific properties and functionalities:

1. **Enemy**: Health: 100 units, Movement Speed: 120 pixels per second, Sprite Path: "./data/img/enemy.png".
1. **Player**: Movement Speed: 110 pixels per second, Sprite Path: "./data/img/player.png".
1. **Wall**: Sprite Path: "./data/img/wall.png".
1. **Bullet**: Bullet Life: 0.7 seconds, Bullet Speed: 300 pixels per second, Sprite Path: "./data/img/bullet.png".
1. **Power-Up**: Sprite Path: "./data/img/scroll.png".
UML Class Diagram
A UML class diagram is provided to document the code, showing important fields, methods, and relations between classes. The diagram should include composition, aggregation, dependency, and other relationships. StarUML is a recommended tool for creating the class diagram.

FAQ
Refer to the original problem statement for a list of frequently asked questions and their answers.
## Build and Run
This project can be compiled and run via Intellij Idea's Java Compiler API.
From Run/Edit Configurations/Program Arguments, path of map (data/map1.dat) can be given.
