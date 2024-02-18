### How to deploy the containers ?

1. Make sure you have the `docker.sock` in `/var/run/docker.sock`
2. Run : `docker-compose up -d`
3. when jenkins starts ,Make sure to install the suggested plugins (we will need Git and maven plugins)
4. In jenkins dashboard install `Docker` Plugin
5. Make sure you have the following tools configuration `http://localhost:8080/manage/configureTools/` :

<img width="609" alt="Screenshot 2024-02-18 at 03 33 16" src="https://github.com/karami4yasser/spring-ci-cd-sonar/assets/83478271/2a010e91-9eec-4265-8e1e-c64069d6e98a">


6. Create a new jenkins Pipeline , and Make sure to have the following Git configuration

<img width="1016" alt="Screenshot 2024-02-18 at 03 32 38" src="https://github.com/karami4yasser/spring-ci-cd-sonar/assets/83478271/58f244d2-40aa-430c-a247-71937130f1cc">
