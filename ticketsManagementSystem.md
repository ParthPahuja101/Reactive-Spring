## Capstone Project

```
Movie Tickets Management->
Requirements-
1) We need to have a table for Movies
        Movies(
            Int id;
            String genre: [Comedy, Thriller, Horror]
            String movieName;
            Int maxCapacity;
            Int availableCapacity
        )
    We will expose an api to add movies, RequestBody(movieName: String, maxCapacity: int, genre: String)
    POST /movies
2) We need to have a table for Tickets
        Tickets(
            Int id;
            String movieName;
            String userName;
        )
    We will expose an api to add tickets, RequestBody(movieName: String, userName: String)
    We need to validate that the movie we are booking ticket for exists, should have available capacity 
    and decrease the availabla capacity by 1 once the ticket is booked
    POST /tickets
3) An API to get list of all movies with available capacity.
    GET /movies
    
4) An API to get list of movies with available capacity.
    GET /available-movies
    
5) An API to get list of all users who have booked a ticket
   GET /tickets
    
6) An API to get list of all users who have booked a ticket of movie X
   GET /tickets?movieName=x

Using Functional Web, R2DBC, Global Exception Handling and Test cases.
 




```
