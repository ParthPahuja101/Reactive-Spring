## Spring R2DBC

##### Spring R2DBC stands for Spring Reactive Relational Database Connectivity. 

##### It is a part of the Spring ecosystem that allows non-blocking, reactive access to relational databases using the R2DBC specification.

<br />

##### Dependency
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-r2dbc</artifactId>
</dependency>
<dependency>
    <groupId>io.asyncer</groupId>
    <artifactId>r2dbc-mysql</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-relational</artifactId>
</dependency>
```
<br />

##### Setting Up Mysql Database
```
// Create a new user in mysql with password
CREATE USER 'book_management'@'localhost' IDENTIFIED BY 'book_management';


// Create a new database
CREATE DATABASE book_management;


// Grant all privileges to the user on the database
GRANT ALL PRIVILEGES ON book_management.* TO 'book_management'@'localhost';
FLUSH PRIVILEGES;
```
<br />


##### Create tables in the Database
```
CREATE TABLE IF NOT EXISTS books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_name VARCHAR(255),
    author VARCHAR(255),
    pages INT,
    genre VARCHAR(50),
    published_on DATE
);

```
<br />

##### Update application.yml file with spring r2dbc configs
```
spring:
  r2dbc:
    url: r2dbc:mysql://localhost:3306/{{DB_NAME}}
    username: {{DB_USERNAME}}
    password: {{DB_PASSWORD}}
```
<br />

##### We can configure the schema creation as part of the application startup process. 
```
spring:
  sql:
    init:
      mode: always
      schema-locations: classpath:schema.sql
```
<br />
<br />
<br />


##### Reactive Crud Repository
```
@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
    
}
```
##### Out of the box methods
| Method	                       | Return Type    | 	Description            |
|-------------------------------|----------------|-------------------------|
| findAll()	                    | Flux<T>	       | Get all records         |
| findById(ID id)               | 	Mono<T>	      | Find one by ID          |
| save(T entity)                | 	Mono<T>	      | Insert or update one    |
| saveAll(Iterable<T> entities) | 	Flux<T>       | 	Insert/update multiple |
| delete(T entity)              | 	Mono<Void>    | 	Delete one entity      |
| deleteById(ID id)             | 	Mono<Void>    | 	Delete by ID           |
| deleteAll()                   | 	Mono<Void>    | 	Delete everything      |
| existsById(ID id)             | 	Mono<Boolean> | 	Check if entity exists |
| count()                       | 	Mono<Long>    | 	Count total records    |

##### Custom Query Methods
```
@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
        
    Flux<Book> findByAuthor(String author);

    Mono<Book> findByBookName(String name);

    Flux<Book> findByGenre(Genre genre);

    Flux<Book> findByPagesGreaterThan(int pages);
    
}
```
##### Custom Query Methods
```
@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
        
    @Query("SELECT * FROM books WHERE published_on < :date")
    Flux<Book> findBooksPublishedBefore(LocalDate date);
    
    @Query("SELECT * FROM books WHERE pages >= :minPages and pages <= :maxPages")
    Flux<Book> findBooksPublishedBefore(int minPages, int maxPages);
    
}
```