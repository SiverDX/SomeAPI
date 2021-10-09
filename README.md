# Docker / Kubernets Playground Project
The application is set up to run as .war on a tomcat - you can run it with an embedded tomcat as well though

**Important**: As of now (2021-10-10) this does not work with Tomcat 10

So if your application does not start even if everything else is supposedly correctly set up - this might be the problem

## Set Up
Build a Docker image based on the project `Dockerfile`

Execute the following command to build an image:

`docker build .`

To tag it first use `docker images` to get the `IMAGE ID`

Then use this command to tag it:

`docker tag <image-id> <repository>/<image>:<version>`

You can also just do this:

`docker build . -t <repository>/<image>:<version>`

### Example:
**Assumption**: `IMAGE ID` is `28ff55432f65`

`docker tag 28ff cadentem/some-api:1.0`

Register the tagged image on DockerHub: `docker push cadentem/some-api:1.0`

## Start and Configuration
Start a Docker container with the built image

**Also**: You can bind the port of the machine that is running the container to the container port (`-p <host-port>:<container-port>`)

`docker run -p 1234:8080/tcp <image>`

This allows you to access the application with `localhost:1234`

Once the container is running you can check the ports with `docker ps`

## Interact with the Container
You can use the following command to connect to an interactive shell on the container:

`docker exec -it <container-name> /bin/bash`

(You get the container name via `docker ps`)

If you want to mess with the Tomcat ROOT, do the following in the interactive shell:

```
cd /usr/local/tomcat
mv webapps webapps2
mv webapps.dist webapps
```

### Installing packages
To be able to find packages in the first place:

`apt-get update`

Install the preferred package (like `nano` or `vim`):

`apt-get install <package>`

### Keep in mind
All of these changes are temporary and will be lost once the container is stopped

**Also**: Stopping the server (with `catalina.sh stop` for example) stops the container as well

## Helpful commands
### Remove all inactive pods

`docker rm  $(docker ps -q -a)`

`-q` => list the id

`-a` => list all containers (incl. stopped)

`docker rm` does not remove active containers

### Other
...

# Kubernetes
Connect to a pod, creating an interactive shell:

`kubectl exec -n <namespace> -it <pod> -- /bin/bash`