Library Management API Documentation
How to Run the Application

1. Prerequisites:
   - Ensure you have Java 17 or later installed.
   - Verify that Maven is installed for building the project.
   - Make sure a compatible database (sql server) is configured.

2. Running the Application:
   - Clone the repository using git clone <repository-url>.
   - Build the project: `mvn clean install`.
   - Run the application: `mvn spring-boot:run`.
   - The API will be available at `http://localhost:8080`.
  
3.Configure the Database
    -spring.application.name=library
    -server.port=8080
    -spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=library;encrypt=true;trustServerCertificate=true;
    -spring.datasource.username=root
    -spring.datasource.password=root
    -spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
    -spring.jpa.hibernate.ddl-auto=update
    -spring.jpa.show-sql=true
    -spring.jpa.properties.hibernate.format_sql=true
    -spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect

API Endpoints

1. Book Management Endpoints
- GET /api/books: Retrieve a list of all books.
- GET /api/books/{id}: Retrieve details of a specific book by ID.
- POST /api/books: Add a new book to the library.
  - Request Body Example:
    ```json
   {
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "publicationYear": 1925,
    "isbn": "9780743273565",
    "genre": "Fiction",
    "publisher": "Charles Scribner's Sons",
    "language": "English",
    "totalCopies": 5,
    "availableCopies": 5,
    "shelfLocation": "A1",
    "summary": "A novel set in the Jazz Age that tells the story of Jay Gatsby and his unrequited love for Daisy Buchanan.",
    "keywords": [
        "classic",
        "American",
        "Jazz Age"
    ]
}
    ```
- PUT /api/books/{id}: Update an existing book's information.
  - Request Body Example:
    ```json
    {
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "publicationYear": 1925,
    "isbn": "9780743273565",
    "genre": "Fiction",
    "publisher": "Charles Scribner's Sons",
    "language": "English",
    "totalCopies": 5,
    "availableCopies": 5,
    "shelfLocation": "A1",
    "summary": "A novel set in the Jazz Age that tells the story of Jay Gatsby and his unrequited love for Daisy Buchanan.",
    "keywords": [
        "classic",
       
    ]
}
    ```
- DELETE /api/books/{id}: Remove a book from the library.

 2. Patron Management Endpoints
- GET /api/patrons: Retrieve a list of all patrons.
- GET /api/patrons/{id}: Retrieve details of a specific patron by ID.
- POST /api/patrons: Add a new patron to the system.
  - Request Body Example:
    ```json
    {
    "name": "John Doe",
    "email": "johndoe@example.com",
    "phoneNumber": "+12345678901",
    "address": "123 Main St, Anytown, USA",
    "membershipType": "Regular",
    "registrationDate": "2024-11-01T10:00:00",
    "membershipExpirationDate": "2025-11-01T10:00:00",
    "dateOfBirth": "1990-01-01",
    "fines": 0.0,
    "borrowingLimit": 5,
    "favoriteGenres": ["Fiction", "Science Fiction"],
    "activeStatus": true
}
    ```
- PUT /api/patrons/{id}: Update an existing patron's information.
  - Request Body Example:
    ```json
    {
    "name": "John Doe",
    "email": "johndoe@example.com",
    "phoneNumber": "+123456789044",
    "address": "123 Main St, Anytown, USA",
    "membershipType": "Regular",
    "registrationDate": "2024-11-01T10:00:00",
    "membershipExpirationDate": "2025-11-01T10:00:00",
    "dateOfBirth": "1990-01-01",
    "fines": 0.0,
    "borrowingLimit": 5,
    "favoriteGenres": ["Fiction"],
    "activeStatus": true
}
    ```
- DELETE /api/patrons/{id}: Remove a patron from the system.

 3. Borrowing Endpoints
- POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to borrow a book.
- PUT /api/return/{bookId}/patron/{patronId}: Return a borrowed book.

 Authentication 
- JWT-based Authentication:
  - Include an `Authorization` header with `Bearer <token>` for endpoints requiring authentication.
  -i use  for verify HMAC256  with scecret key:7cd89a4694a1b7bba3e8dbf9af967f4f172d373b3c5c0a4e1a78f72f263a52366d8c7e8c378804df23d7aa0f914ecef6bb942d7ba9c012f00709baefe0b49f89
  - Example header:
    ```
    Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
    ```
  
 Running Tests
1. Unit Tests:
   - Run tests using `mvn test` to validate the functionality of the API endpoints.
2. Frameworks Used:
   - JUnit 5: For writing test cases.
   - Mockito: For mocking dependencies.

 Example Unit Test Structure
- BookControllerTest:
  - Validate `GET /api/books`.
  - Validate `POST /api/books`.

- PatronControllerTest:
  - Validate `GET /api/patrons`.
  - Validate `POST /api/patrons`.
