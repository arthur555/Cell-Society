cell society
====

This project implements a cellular automata simulator.

Names: Xi Pu, Arthur Zhao, Jason Zhou

### Timeline

Start Date: September 16, 2018

Finish Date: September 30, 2018

Hours Spent: around 120 hours

### Primary Roles


### Resources Used


### Running the Program

* View.Main class: used as the main class

* Data files needed:
  * An xml for initial configuration
    
* Interesting data files:
    * fire.xml: a fire simulation setup without an initial map
    * fireDefault.xml: a fire simulation setup with an invalid initial map
    
Features implemented: 

* Configuration
    1. Implement error checking for incorrect file data
    2. Allow simulations initial configuration to be set by randomly based on probability/concentration distributions
    3. Allow users to save the current state of the simulation as an XML configuration file


* Visualization
    1. Display a graph of the populations of all of the "kinds" of cells over the time of the simulation
    2. Allow users to interact with the simulation dynamically to change the values of its parameters
    3. Allow users to run multiple simulations at the same time so they can compare the results side by side
    (We made the decision to make each simulation independent from each other. So, users can add different
    types of simulations and control each simulation individually. Each individual simulation will have its own 
    UI control. We made this decision because it gives users maximum amount of flexibility over what they 
    want to do with simulations. We also )

* Assumptions or Simplifications:
    * The user will start with an XML file, or knows how to create an XML file.
    * The user knows what each simulation mode means
    * The user can understand initial XML configurations easily
Known Bugs:
    * If the size of the grid is only 1*1 (which is a very unlikely case), the program will output a bug.

Extra credit:

1. Our program supports multiple languages: english and simplified chinese
    
2. Warning dialogs will appear if user enters invalid output or if the data file they choose 
    does not follow the specified format
    
3. You can change the size of the grid dynamically through the width and height input field;

4. Not only can you add new simulation, you can also remove the added simulation if necessary;


   Text input fields only 

### Notes

* Note that the program supports adding as many simulation as you like. However,


* When you first run the application, the simulation area may be empty or already completed. 
This is because the simulation has already occurred before it got rendered on the screen before
Just press reset button to see its simulation.

* You have to press apply button in order for the change you enter in the input fields
to be effective

* Pressing the reset button will reset the simulation to its 
initial state and pause it. So, you have to either press start 
or step to continue the simulation again. 


### Impressions

