# Sushibar

This program simulates a sushibar. The door adds new customer inside waiting area using monitor. 
Waitresses fetches customers from the waiting area and serves them.
When the sushibar's door closes the door class stops adding new customer and when all the customers are 
served by waitresses, the sushibar closes. In the end a final statistics of number of orders are logged.

The implementation manages several threads that are using shared resources. 
These resources are protected using the monitor concept. 
The implementation use the producer/consumer model to solve the problem.

The Producer and Consumers synchronize using the 𝑤𝑎𝑖𝑡(), 𝑛𝑜𝑡𝑖𝑓𝑦() and 𝑛𝑜𝑡𝑖𝑓𝑦𝐴𝑙𝑙(), and mutual exclusion 
is implemented using the Java monitor. 
