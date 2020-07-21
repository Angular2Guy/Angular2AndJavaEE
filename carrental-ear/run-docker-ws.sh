docker build -t angular2guy/angular2andjavaee-ws -f Dockerfile-ws .
docker run -p 9080:9080 -p 9443:9443 angular2guy/angular2andjavaee-ws:latest