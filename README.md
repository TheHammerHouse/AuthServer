# AuthServer


 
 1. `mvn clean package`
 2. `docker-compose up --build --force-recreate -d` (run auth server + mysqldb in detached instance)
 3. Wait until all images are healthy/started.
 4. When done, `docker-compose down`

