FlexForumServer
===============

RESTful backend forum service using Dropwizard. 

--------------------------------- Phase 1 ---------------------------------

First phase of the project aims to acheive the following objectives:
  - Flesh out core data/class models and define relationships.
  - Allow basic CRUD functions where required.
  - Introduce security layer with login/register.
  - 
  

Build + Run Instructions:
- Clone repo
- "mvn package"
- "java -jar target/flex-server-1.0-SNAPSHOT.jar server config.yaml"


Example POST:
    curl --verbose --header "Content-Type: application/json" -X POST -d '{"firstName": "FOO", "lastName":"BAR", "phone":"324234234"}' http://localhost:8080/members/create
  
Database migrations:
- Set up database 'flex' as outlined in config.yaml
- run 'mvn package' in project folder
- Use command 'java -jar target/flex-server-1.0-SNAPSHOT.jar db migrate config.yaml'