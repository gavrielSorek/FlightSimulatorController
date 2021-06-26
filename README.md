# FlightSimulatorController

### _Introduction:_

This project is an android application which represents a flight gear controller. 
With this application we can control the aircraft on the FlightGear desktop app.

### _Video demonstration:_

You can find [here](https://www.youtube.com/watch?v=rHIkL8D5Dlk&ab_channel=RoyMechany) the video of the project.
 



### _Application main screen:_

![open screen](https://user-images.githubusercontent.com/58228085/122649520-cf392500-d136-11eb-8ab1-75949bb6cbf2.png)







### _Project Structure:_

![folders structure](https://user-images.githubusercontent.com/58228085/122649596-322abc00-d137-11eb-9eb1-fd2f35b396bc.png)

1)	View – 
The view responsible of the visual representation of each component (Main activity, Joystick ,ErrorWindow and SuccessWindow).
2)	Model –
The main and only model of the project implements the MVVM design pattern and it is responsible of the business logical of the project.
It contains the logic parts of the application which is being used in the view models.
3)	ViewModel-
this folder contains the view models of each logical part as mentioned above. the class connects the model to the view.  

#### _Main calsses in the project:_
*Main activity - contains all the view components.
*Joystick - this class responsable on the joystick view and functinallity of the joystick.
*ErrorWindow - this class responsable on the activity when client failed to connenct to flightGear server.
*SuccessWindow - this class responsable on the activity when client succeed to connenct to flightGear server.

### _Installation requirements:_

•	Frameworks:
1) Android phone -version: android 11 and above.
2) FlightGear server - on seperated machine (the android app will control the simulator)


•	Applications:
1)	FlightGear application 

#### _Installation Instructions:_


1) Open the FlighGear application

2)	Update the FlightGear's settings: 

--telnet=socket,in,10,127.0.0.1,6400,tcp

3) Check your local IP address on the FG server machine:

* open CMD and insert the command "ipconfig" and copy the  "IPv4 Address"  ( 192.168.X.X || 10.0.0.X)

4) You can run the android apllication with this github repositiry from "android studio" or you can install the application on your android phone via the .apk file from [this link](https://github.com/gavrielSorek/FlightSimulatorController/blob/main/install%20file)

5)	Open the android apllication on your android device.

6)	Launch  our app!

After lanuch our app:
1)	insert your public IPV4 address
2)	insert port 6400 
3) press connect
4) controll the simulator with the joystick and the seek bars



### _UML and MVVM design pattern:_

![UML picture](https://user-images.githubusercontent.com/58228085/122650172-0bba5000-d13a-11eb-934f-185519db959e.png)
