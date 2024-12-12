Real time Ticketing System
=========================

Description
------------

This is a real world ticket simulation system where producer consumer pattern has implemented with multi threading and concurrency. When user inputs the configuration settings system will start the simulation and prints the details and save logger details in a separate logger file. The system supports concurrency and ensures thread safety using locks. It includes configurable parameters, logging, and a stop mechanism for termination.

How to Use(CLI)
-----------

Run the program in IntelliJ or any other idle and provide the configuration inputs that are Total Tickets, vendor count , ticket release rate (per seconds),retrieval rate(per seconds)and max capacity.

the configuration settings will save to "configuration.json" file and logging details will be saved to "ticketSystem.log" file.

After run the program according to the user input messages will print.

User should input in integer data type otherwise it will give a alert message and asked the user the input again.

if user wants to terminate the program middle of the run , type "stop" (case sensitive) to terminate the program manually.


How to Use(GUI)
---------------

Run the Spring Boot first and second run the react framework.

input the configurations in frontend and click start. Then program will run according to the configuration and print loggings in console.

If user wants to stop the running program click stop button to terminate the program manually.


Assumption

For CLI,

This is a real world ticket simulation system where producer consumer pattern has implemented with multi threading and concurrency to CLI .

For multiple vendor implementation user should give the vendor count. Multiple Customer
implements was hard coded by default it functioned.

multi vendor and multi Customer pattern used to run the program.

Event name and price hard coded in the Spring Boot and CLI java

retrieval rate and release rate should be in per second manner.

when vendor released the all ticket vendor stops releasing here after and customers continuously buy ticket until ticket pool gets empty.

if ticket pools reach the max capacity vendors wait for until get a space to release ticket.

by typing "stop" manually program can be terminatable.

if the ticket pools empty and customer tries to buy ticket customer wait until the vendor ticket pool gets ticket.



For GUI,


Same as for the CLI configuration but another extra one is "customer Count" for only the mentioned number of customer count able to retrieve the tickets .

user inputs the configuration when clicked "start" the button will sent request to the spring Boot to run the program .when clicking the "stop" it will sent request to backend to terminate the program.

when vendor released the all ticket vendor stops releasing here after and customers continuously buy ticket until ticket pool gets empty.

release rate and retrieval rate are in per millisecond manner


main assumption
---------------

CLI and GUI are separate programs but runs similar configurations.
