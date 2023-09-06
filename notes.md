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