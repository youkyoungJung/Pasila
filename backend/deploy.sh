docker build -t springbootapp .
docker stop springbootapp
docker rm springbootapp
docker run -p 5656:80 --name springbootapp --network ubuntu_default -d springbootapp