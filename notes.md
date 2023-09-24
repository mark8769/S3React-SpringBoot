## Installing docker

(docker)[https://www.docker.com/]

Run this command to get started:

```
docker run -d -p 80:80 docker/getting-started
```

This page will be open once you run container: http://localhost/tutorial/

http://localhost/tutorial/

Learn more about the docker command you ran here.


## Running POSTGRES inside docker container

cd into root of project repo(wherever this readme is):

http://localhost/tutorial/

```
docker compose up -d
```

-d: Run container in detached mode

This command will show all your running containers.

```
docker ps --format
```

Set this environment variable to get docker container summary's in a nicer format
https://docs.docker.com/engine/reference/commandline/ps/
It looks like this command sets a format for output to be displayed as
Notice the \n "newlines"
Notice the \t "tabs" to separate key:value pairs
Notice the {{ }} to access object variables, presumably exposed through DOCKER.
https://devdojo.com/bobbyiliev/how-to-change-the-docker-ps-output-format#:~:text=You%20can%20format%20the%20output,you%20would%20like%20to%20see.&text=Notice%20the%20%5Ct%20part%2C%20this,you%20want%20to%20use%20tabs.&text=The%20%2D%2Dformat%20is%20very%20handy%20in%20most%20cases.
```
export FORMAT="ID\t{{.ID}}\nNAME\t{{.Names}}\nIMAGE\t{{.Image}}\nPORTS\t{{.Ports}}\nCOMMAND\t{{.Command}}\nCREATED\t{{.CreatedAt}}\nSTATUS\t{{.Status}}\n"
```

Now that FORMAT is set in our environment variables, we can run the following command.
Alternatively, you can set this up inside a config file that docker provides.
Use the link above to follow along with that, won't do here.
```
docker ps --format="$FORMAT"
```

Use following command o confirm container was booted up.
```
docker logs postgres
```

Running Spring Boot Backend

- We have POSTGRES container up and running, but haven't set up our database.
- Project will therefore fail

Creating our Customer Database
```
docker exec -it postgres sh
```

I am assuming the -it flag means to run the container in "interactive mode" like the python interpreter,
node.js interpreter, and so on...

Once you run the command, you'll be taken inside a Linux Box(mini linux ran on Macs(since there is no Mac Containers like Windows/Linux))

Run the following command to login to POSTGRES inside the docker container(linux container for macs)
```
psql -U amigoscode -d postgres
```

List the databases
```
\l
```

Create the database
```
CREATE DATABASE customer;
```

Connect to the customer database
```
\c cusomter
```

Once connected, run this command to see what relations there are in the database.
```
\d
```

### Spring Backend should run successfully now...

Once you run the application, the command line runner inside Spring Boot should initialize some information,
along with some relations.

Run again

```
\d
```

Cautionary Notes:

For some reason, opening the whole project in IntelliJ(including the frontend) causes IntelliJ not being able to find the main class file. Without being able to run this, I cannot start the application.

- Fixed issue by reopening project, but only the spring backend.

TODO: Figure out how to run Spring Boot applications from the command line instead of relying on the IDE to do it for me....


## To disconnect from PSQL / and docker container

Run this command twice

```
ctrl + d
```

# TO Start React-frontend

1. Head into frontend/react/
2. run ```npm install```
3. run ```npm dev run```
4. Grab credentials from startup of java backend (from database)

-------------------------------------------------------------------------------------
# Using Amazon S3

## Setting S3 maven dependency in maven (pom.xml) file
- (Maven Docs)[https://maven.apache.org/guides/getting-started/index.html]
- (Amazon SDK docs for java)[https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/setup-project-maven.html]

## Using temporary credential (local development)
- (S3 local/temp credentials)[https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials-temporary.html]

