## Virtuat disk manager Project
Technologies:
    Spring boot
    React js
    Mysql
    HTML,CSS
## Documentation
Pour conteneuriser l'application on aura besoin de quelques fichier Dockerfile et un fichier DockerCompose
 Backend
Pour conteneuriser l'application backend on ajoute un fichier Dockerfile dans /BackEnd/demo il contient :
-fichier dockerfile:
        FROM maven:3.8.2-jdk-8
        WORKDIR /app
        COPY . .
        RUN mvn clean install
        CMD mvn spring-boot:run
        
 FrontEnd
Pour conteneuriser l'application React en front end on ajoute un fichier Dockerfile dans /frontEnd il contient :
-fichier dockerfile:
<code>
        RUN mkdir / frontEnd
        WORKDIR / frontEnd
        COPY / src / app / src
        COPIE ["package.json", "package-lock.json*", "./"]
        RUN npm install --production --silent && mv node_modules ../
        # Exposer PORT 3000 sur notre machine virtuelle afin que nous puissions exécuter notre serveur
        EXPOSE 3000 
  </code>
 base de donnees
    Premierement on doit faire un pull de l'image de mysql de docker registry puis on crée un fichier DockerCompose qui contient le code en dessous et on excute la commande "dockercompose up".
-fichier dockerCompose:

        version: '3.1'
        services:
          db:
            image: mysql
            command: --default-authentication-plugin=mysql_native_password
            restart: always
            environment:
              MYSQL_ROOT_PASSWORD: root
          adminer:
            image: adminer
            restart: always
            ports:
              - 8080:
