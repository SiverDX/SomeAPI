# Docker / Kubernets Playground Project
The application is set up to run as .war on a tomcat - you can run it with an embedded tomcat as well though

**Important**: As of now (2021-10-10) this does not work with Tomcat 10

So if your application does not start even if everything else is supposedly correctly set up - this might be the problem

## Set Up
Build a Docker image based on the project `Dockerfile` by execute the following command:

`docker build .`

To tag it we need the id: `docker images` to get the `IMAGE ID`

Then use this command to tag it:

`docker tag <image-id> <repository>/<image>:<version>`

You can also just do this:

`docker build . -t <repository>/<image>:<version>`

The repository is mainly used to later push it to DockerHub - you can run the image even without tagging it, you just need the id

### Example:
**Assumption**: `IMAGE ID` is `28ff55432f65`

`docker tag 28ff cadentem/some-api:1.0`

Register the tagged image on DockerHub: `docker push cadentem/some-api:1.0`

## Start and Configuration
Start a Docker container with the built image

**Also**: You can bind the port of the machine that is running the container to the container port (`-p <host-port>:<container-port>`)

`docker run -p 1234:8080 <image>`

This allows you to access the application with `localhost:1234`

If you do not want to specify a port you can also expose all of them using `-P` (no port required)

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

# Helm
...

# Jenkins
## Run Locally
Start the jenkins with a persistent storage and the latest image

(persistent storage = directory on your local machine; without this the configuration will be lost once the container is stopped)

`docker run -v jenkins_home:/var/jenkins_home -p 8080:8080 -p 50000:50000 jenkins/jenkins:lts`

(`lts` means long term support)

## Run in Kubernetes
**Required**: You need to have helm installed in your Kubernetes Cluster

`helm install jenkins stable/jenkins`

Set the Jenkins URL in 'Manage Jenkins' -> 'Configure System'

Check `kubectl get pods` to get the ip - though it would be better to have a `Service` at this point

## Configuration
Install plugins in 'Manage Jenkins' -> 'Manage Plugins '-> 'Available'

### User Management
Install this plugin: `Matrix Authorization Strategy`

Manage 'Jenkins' -> 'Configure Global Security' -> In 'Security Realm' activate:
* Jenkins' own user database
* Allow users to sign up
* Matrix-based security

Create new users with 'Add user or group...' and set permissions

After that the users have to be created in the login page with 'create an account'

### Maven
In 'Manage Jenkins' -> 'Global Tool Configuration' search for 'Maven'

There you will have to add a Maven installation via 'Add Maven'

### Create a new Job
'New Item' -> 'Freestyle project'

### Git Repository Authorization
execute `ssh-keygen` and copy the content of `id_rsa.pub`

For GitHub you will have to place this in your Profile under 'SSH and GPG Keys'

In Jenkins you set the private: 'New Item' -> 'Source Code Management' -> check 'Git'

After that add via 'Add' -> 'SSH Username with private key' the content of `id_rsa`

# Kubernetes
Connect to a pod, creating an interactive shell:

`kubectl exec -n <namespace> -it <pod> -- /bin/bash`

# Interact with the application
### Important 
If you have problems reaching your application think about the root context - it could be set to the name of the .war file

You can change the name of the .war file by using `<finalName/>` in the spring-boot plugin

