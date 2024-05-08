## Run in intellij using testcontainers
These don't persist data. Just run **ComposerLocal** compound configuration to start all instances.

## Running via Intellij using external docker containers
Start/Stop the db.
````
docker run --rm --name composition_database -v .\DbData:/var/lib/mysql -e MYSQL_DATABASE=db_composition -e MYSQL_USER=test_user -e MYSQL_PASSWORD=password -e MYSQL_ROOT_PASSWORD=admin -d -p 3306:3306 mysql:8.0.37

docker stop composition_database
````
Run the **CompositionServiceApplication** run configuration.

Note that it will connect using the "localhost" hostname.

To clear existing data.
````
rmdir .\DbData
````

## Running via docker container
Note that in this case the hostname of "composition-database" is used for the connection.

### Build
````
docker image rm lreynolds/composer-v1-composition
mvn clean install -DskipTests
docker build -t lreynolds/composer-v1-composition:latest .
````

### Run
To clear existing data.
````
rmdir .\DbData 
````
Create the network
````
docker network create -d bridge composition_network
````
Start/stop the mongo db (now with the new hostname, on the network and no exposed ports).
````
docker run --rm --name composition_database --hostname composition-database -v .\DbData:/var/lib/mysql -e MYSQL_DATABASE=db_composition -e MYSQL_USER=test_user -e MYSQL_PASSWORD=password -e MYSQL_ROOT_PASSWORD=admin -d --network=composition_network mysql:8.0.37

docker stop composition_database
````
Start/stop the application (using the new hostname and on the network).
````
docker run --rm --name composer_composition -e SPRING_PROFILES_ACTIVE=docker -e MYSQL_HOST=composition-database -p 8080:8080 --network=composition_network lreynolds/composer-v1-composition:latest

docker stop composer_composition
````