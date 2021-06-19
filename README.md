# FlightSimulatorController

__Introduction:__

This project is a android application which represents a flight gear controller. 
With this application we can control the plan on the FlightGear desktop app.

__Video demonstration:__

You can find [here](TODO ADD VIDRIO) the video of the projec.
 



__Application main screen:__
![openScreen](TODO ADD photo)






__Application features:__





__Project Structure:__

![folders picture](ADDPHOTO)

1)	View – 
responsible of the view of each component (Main activity, Joystick ,ErrorWindow and SuccessWindow).
Each logical part has a view model of its own.
2)	Model –
the main and only model of the project, implements the MVVM design pattern and its responsible of the business logic of the project.
Contains the logic parts of the application that is being used at the view models.
3)	ViewModel-
this folder contains the view models of each logical part as mentioned above. the class connects the model to the view.  

__Installation requirements:__

•	Frameworks:
1) Android phone -version: android 11 and above.
2) FlightGear server - on seperated machine (the android app will control the simulator)


•	Applications:
1)	FlightGear application 

__Installation Instructions:__


1) Open the FlighGear application

2)	Update the FlightGear's settings: 



3) Check your local IP address on the FG server machine:

*open CMD and insert the command "ipconfig" and copy the  "IPv4 Address"  ( 192.168.X.X || 10.0.0.X)

4)You can run the android apllication with this github repositiry from "android studio" or you can install the application on your android phone via the .apk file from [this link](https://github.com/gavrielSorek/FlightSimulatorController/blob/main/install%20file)

5)	Open the android apllication on your android device.

6)	Launch  our app!



__UML and MVVM design pattern:__

![uml picture](TODO ADD)


 














