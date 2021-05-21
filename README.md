# Security System
## An application in which one can manage and set up different security measures in their home or workplace.

 This application serves as a console where a user can manage the various security features to 
 protect their homes or businesses.
 The console will be able do the following and more:
 
 - Set **security cameras** to on or off and assign them to different areas
 - Manage the position of **security cameras** 
 - Set **lights** of a facility to be on or off as well as managing their brightness levels 
 
 This application is able to be used by any home, business, or facility owner. The more options that an individual 
 has access to, will overall increase the value of this application. 
 Therefore, although anyone can use this application, those that require high levels of security, 
 such as museums, banks, or large property owners would be most interested in utilizing this application.
 
 I was interested in creating a security system control console as I believe it would be relevant and useful in
 current society. I also felt that such creating such an application would be a good chance for me to apply what I have
 learned, as I had several different ideas for different features that a security console could possess.
 
 ## User Stories
 - As a user, I want to be able to create a new section and add it to a list of sections that are under surveillance*
 - As a user, I want to be able to select a section and add new features such as cameras or lights to it*
 - As a user, I want to be able to view the list of all sections that are under surveillance*
 - As a user, I want to be able to select a section and view a list of features that operate in that section* 

 
 - As a user, I want to be able to save my monitoring list with all of its sections within it from the menu
 - As a user, I want to be able to load my monitoring list with all of its sections within it from the menu 
 
 ## Phase 4: Task 2
 I picked to make my Section class robust, specifically the addLight and addCamera methods. The methods now prevents 
 duplication (lights/cameras with the same ID), and if adding such a light/camera with the given ID would create a 
 duplicate, an exception would be thrown instead.
 
 ## Phase 4: Task 3
 When looking at my UML class design diagram, I think something that I would try to refactor if I had more time, 
 would be to make Camera and Light extend a common supertype, most likely an abstract class that I would call 
 SecurityFeature. As in my current implementation, both are added to the Section class in two separate lists, but
 by having them extend a common supertype, I would have only needed to design one add method as well as potentially
 only use one list to store both of them as opposed to one list for each. This may overall reduce duplication 
 that is present with the addLight and addCamera methods. 
 Another possibility, is refactoring the Section class as a whole to replace the MonitoringList class. When looking at
 my UML class design paragraph, as well as my implementation of the MonitoringList class, there isn't really anything 
 unique that it does that could not be accomplished by using methods related to a list, and perhaps I could have had
 the ControlFrame class simply have a field of a ListOfSections and operate on that instead. 
 
 
 
 