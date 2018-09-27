# Duplication Refactoring

# Checklist Refactoring
* We refactored the name of Controller_API to Controller to make it more friendly
# General Refactoring
* There are times when we import Model.* while we don't really need every class in Model. We fixed this by importing more specifically 
* There are times when we do something like if(...) i+=1; without adding braces around the i+=1. This isn't very dangerous but we refactored it for better extendability.
# Longest Method Refactoring
* We refactored the setup method in Controller because it is indeed too long (28 lines). Lots of operations are very simple and can be easily
captured with a method. So we broke the setup method up into a smaller one by adding an extra method.
* We didn't refactored the getSimulation method in Controller (17 lines)because it's not too long and it's hard to refactor. The major body of the method is a switch and that is really makes
it long. Fixing it would be possible, but wouldn't be very helpful.
