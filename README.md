# cs_space-invaders
#### Final programming assignment for the Introduction to Object Oriented Design course

This repository contains the final programming assignment for a course I had taken at the ***University of Rhode Island***.
My initial submission was enough to earn a perfect grade rubric-wise, but the game was far from complete.

**Note: This was an individual assignment.**

There was initially only **1** row of **10** aliens moving back and forth as I was not able to complete the remaining logic on time.
Since I wasn't developing an ***ACTUAL*** game, I guess that was okay!

However, I began working on the game here and there on my free time during the past summer.
Unfortunately I ran into serious bugs attempting to improve the game.

### What's changed since

It wasn't until recently (in order to have something presentable) that I have finally brought the project closer to a more complete game.
Below I will list the improvements in the order they were implemented:

* Refactored ***Entity*** child classes to take better advantage of inheritance and polymorphic design
* **10** columns of **4** aliens for a more challenging experience (I finally nailed it!)
* Enemy aliens are stored in a ***doubly linked list*** instead of an ***array*** -- not necessary, but nice practice
* Insertion and deletion of aliens are now ***O(n)***

I decided to implement a **doubly linked list** because it allowed me to create a modular data structure for the game.
I might reuse the ***DDList.java*** source to segregate **missile** objects from **SpaceShip** child classes.
This way, an alien can be eliminated, but the **missile** it fired will not be destroyed as a result.
