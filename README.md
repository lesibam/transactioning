# Transactioning 
### Small spring boot application to store banking transactions

Running Requirements:
- Java 17
- Maven 3.8.X or newer - I used 3.9.6 (Worked) :)
- Postgres DB. The application can be run in incognito mode (H2) i.e. `-Dspring.profiles.active=h2`

Include an .http file to invoke the rest endpoints

## TODO
- add more meaningful unit tests
- add some validation
- Handle Exceptions properly
- Maybe move auth to proper auth provider. (keycloak might a good alternative)
- Add bulk import of transactions.