# AuthServer


 
 1. `docker build -t auth_server .` (build image named backend)
 2. `docker-compose up --build --force-recreate -d` (run auth server + mysqldb in detached instance)
 3. Wait until all images are healthy/started.
 4. When done, `docker-compose down`

