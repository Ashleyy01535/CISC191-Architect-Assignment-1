# CISC191
Intermediate Java Programming Project
## Purpose
Client server host of a JavaFX tableview application that lists all capital and select major cities of the United States
and allows user to delete any city listed. Will select a random city and print to console.
## Prerequisites
1. Maven
2. Git
3. JDK 1.8
## Building
mvn clean install
## Running
java -jar Server/target/Server-1.0.0.jar  
java -jar Client/target/Client-1.0.0.jar
## Common Module
Shared classes between client and server modules.
## Server Module
The server application that handles multiple clients.
## Client Module
The client application used to connect to the server.

## Modules Implemented
1, 2, 5: Used a JavaFX application to have an interactive console menu that performs delete and find on a Map.Entry(HashMap).<br>
3: Separated tasks in Table.java into several classes and have CitiesStates object<br>
4: Used networking with client and server <br>
9, 10: Converted CSV into Map through parallel streams/concurrency