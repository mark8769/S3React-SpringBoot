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

## Logging into Amazon Console

- Head to Identity and Access Management (IAM)
- Head to Access Management dropdown menu on far left sidebar
- Expand dropdown
- Click on "Users" (An IAM user is an identity with long-term credentials that is used to interact with AWS in an account.)
- Click on "Create User"
- Give new user a username
- Under "Permissions Options" (h2), click on "Add user to group"
- Under "User Groups" (h2) select "admin", this gives our user admin priviledges
- If that user group is NOT under user groups yet. Click on "create group" -> this will bring up an overlayed page -> under "Create user group" (h2) give the user group a name -> under "Permissions Policies" (h2) find the policy name "Administrator Access" that provides "Full Access" - > Done
- Click "next"
- Finish by clicking on "create user"

- Click on your new user -> head to "security credentials" tab -> head to "Access Keys" tab -> click on "create access key" if you don't already have one -> when creating new access key, under "use case" click on the local code option (since this is for local development)

## Using new credentials on machine....
[Setting up aws credentials file](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials-temporary.html)

- type in "cd", this will take you to the root of your computer/user
- issue the following command ```mkdir .aws```, inside the folder create a "credentials" file.

```
[default]
[default]
aws_access_key_id=
aws_secret_access_key=
```
- Inside use the user we created in the previous steps and input the username(id) and secret access key (password)

## Working with AWS S3

https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-s3.html

Amazon S3: Object storage built to retrieve any amount of data.

- To enable S3 client, paste this information into 

```
S3Client client = S3Client.builder()
                          .region(Region.US_WEST_2)
                          .endpointOverride(URI.create("https://s3.us-west-2.amazonaws.com"))
                          .forcePathStyle(true)
                          .build();
```
