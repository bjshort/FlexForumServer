<h1>FlexForumServer</h1>
===============

<p>RESTful backend forum service using Dropwizard. </p>

--------------------------------- Phase 1 ---------------------------------

First phase of the project aims to acheive the following objectives:
  - Flesh out core data/class models and define relationships.
  - Allow basic CRUD functions where required.
  - Introduce security layer with login/register.
  
  

<h2>Build + Run Instructions:</h2>
- Clone repo
- "mvn package"
- "java -jar target/flex-server-1.0-SNAPSHOT.jar server config.yaml"

<h2>Database migrations:</h2>
- Set up database 'flex' as outlined in config.yaml
- run 'mvn package' in project folder
- Use command 'java -jar target/flex-server-1.0-SNAPSHOT.jar db migrate config.yaml'



<h3>Useful commands</h3>

<h5>Example POST:</h5>
   - curl --verbose --header "Content-Type: application/json" -X POST -d '{"firstName": "FOOBARRR", "lastName":"BAR", "phone":"324234234", "email":"blah@blah.com", "password":"goatscheese"}' http://localhost:8080/members/create
  