# Hotel
PAO project in Java
```
This is a project for Object Orientated Programming Course using Java, which is made 
to manage the activity of a hotel. The app allows you to see and make changes (such as add, delete, sort) 
for employees, clients, rooms and finally, it allows you to do check in/check out.
```

## OOP concepts & others
```
# private methods and attributes
# collections (List, HashMap)
# inheritance
# service class for a better manage
# connection to database 
# CRUD operations
# audit service (command_name, timestamp)
# cvs service (writing and reading)
```

## Objects
```
PERSON (abstract)
		(inheritance) —---> CLIENT
		(inheritance) —---> EMPLOYEE
ROOM (abstract)
		(inheritance) —---> SINGLEROOM
			(extends) —----> DOUBLEROOM
```

## Commands
```
______________________________________________________________________
|                              COMMANDS                              |
|- add employee                                                      |
|- update employee                                                   |
|- list employees                                                    |
|- sort employees                                                    |
|- delete employee                                                   |
|- add client                                                        |
|- update client                                                     |
|- list clients                                                      |
|- delete client                                                     |
|- add room (single/double)                                          |
|- update room price (single/double)                                 |
|- delete room (single/double)                                       |
|- list rooms (single/double)                                        |
|- sort rooms (asc/desc)                                             |
|- how many = how many single rooms and double rooms                 |
|- list all rooms (a/u) = available or unavailable                   |
|- check in                                                          |
|- check out                                                         |
|- exit = exiting the program                                        |
|____________________________________________________________________|
```
## CERINTE:
### ETAPA 1
``` 
1) Definirea sistemului  
Să se creeze o lista pe baza temei alese cu cel puțin 10 acțiuni/interogări care 
se pot face în cadrul  sistemului și o lista cu cel puțin 8 tipuri de obiecte. 

2) Implementare  
Sa se implementeze în limbajul Java o aplicație pe baza celor definite la primul punct.  Aplicația va conține:  
• clase simple cu atribute private / protected și metode de acces  
• cel puțin 2 colecții diferite capabile să gestioneze obiectele definiteanterior (eg: List, Set, Map,  etc.)
dintre care cel puțin una sa fie sortata – se vor folosi array-uri uni- /bidimensionale în cazul în care  
nu se parcurg colectiile pana la data checkpoint-ului.  
• utilizare moștenire pentru crearea de clase adiționale și utilizarea lor încadrul colecțiilor;  
• cel puțin o clasă serviciu care sa expună operațiile sistemului  
• o clasa Main din care sunt făcute apeluri către servicii  
```
### ETAPA 2
```
1) Extindeți proiectul din prima etapa prin realizarea persistentei utilizând o baza de date relationala  si JDBC.  
Să se realizeze servicii care sa expună operații de tip create, read, update si delete pentru cel puțin 4  
dintre clasele definite. Se vor realiza servicii singleton generice pentru scrierea și citirea din baza de  date. 

2) Realizarea unui serviciu de audit  
Se va realiza un serviciu care sa scrie într-un fișier de tip CSV de fiecare data când este executată una 
dintre acțiunile descrise în prima etapa. Structura fișierului: nume_actiune, timestamp 
```
