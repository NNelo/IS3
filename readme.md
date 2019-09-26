## Trabajo Práctico 1 - Git Básico

#### 1- Instalar Git

#### 2- Crear un repositorio local y agregar archivos
	git init
	touch readme.md
	git add .
	git commit -m "agregando readme"

#### 3- Crear un repositorio remoto
	git remote add origin https://github.com/NNelo/IS3.git
	git push -u origin master

#### 4- Familiarizarse con el concepto de Pull Request
	git push origin rama1
	pull request
	merge on github

	Refinición de "pull request"
		En un contexto de un repositorio con varias ramas, se puede querer que cierto código en alguna de las ramas (ej: RAMA A) en otra rama (llamemos RAMA B). Para cumplir con tal comedido, se puede hacer un pull request. Esto consiste en que la rama A solicita enviar código a la rama B, quien la unirá luego con un Merge

https://help.github.com/en/articles/about-pull-requests#about-pull-requests


#### 5- Mergear código con conflictos

```bash
$>Segundo clon\IS3>git commit -m "Se agregan lineas 1 y 3 a readme en segundo clon"
[master 40b28bb] Se agregan lineas 1 y 3 a readme en segundo clon
 1 file changed, 3 insertions(+)

ERROR

$>Segundo clon\IS3>git push
To https://github.com/NNelo/IS3.git
 ! [rejected]        master -> master (fetch first)
error: failed to push some refs to 'https://github.com/NNelo/IS3.git'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.

WinMerge
[diff]
    tool = winmerge
[difftool "winmerge"]
	cmd = "'C:/Program Files (x86)/WinMerge/WinMergeU.exe'" -e "$LOCAL" "$REMOTE"

C:\Users\nelon\Desktop\Segundo clon\IS3>git difftool HEAD HEAD~1

Viewing (1/1): 'readme.md'
Launch 'winmerge' [Y/n]? y

![Alt text](winmerge_mod_readme.png)

C:\Users\nelon\Desktop\Segundo clon\IS3>git status
On branch master
Your branch and 'origin/master' have diverged,
and have 2 and 1 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)

You have unmerged paths.
  (fix conflicts and run "git commit")
  (use "git merge --abort" to abort the merge)

Unmerged paths:
  (use "git add <file>..." to mark resolution)

        both modified:   readme.md

no changes added to commit (use "git add" and/or "git commit -a")


C:\Users\nelon\Desktop\Segundo clon\IS3>git add .

C:\Users\nelon\Desktop\Segundo clon\IS3>git commit -m "solucionando conflictos"
[master 5c06d07] solucionando conflictos
```

Segundo intento usando p4merge + tortoise en segundo clon

```bash
C:\Users\nelon\Desktop\Segundo clon\IS3>git commit -m "modificando readme desde clon 2"
[master 16204ba] modificando readme desde clon 2
 1 file changed, 3 insertions(+)

C:\Users\nelon\Desktop\Segundo clon\IS3>git push
To https://github.com/NNelo/IS3.git
 ! [rejected]        master -> master (fetch first)
error: failed to push some refs to 'https://github.com/NNelo/IS3.git'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
```

tortoise pull 

![Alt text](tortoise_conflict.png)

solving conflicts
![Alt text](p4merge.png)


LOCAL - the head for the file(s) from the current branch on the machine that you are using.
REMOTE - the head for files(s) from a remote location that you are trying to merge into your LOCAL branch.
BASE - the common ancestor(s) of LOCAL and BASE.
MERGED - the tag / HEAD object after the merge - this is saved as a new commit.

## Trabajo Práctico 2 - Herramientas de construcción de software

#### 1- Instalar Java JDK si no dispone del mismo. 

```bash
sudo apt install && sudo apt upgrade
sudo apt install default-jre
sudo apt install default-jdk
```

#### 2- Instalar Maven

```bash
sudo apt install maven 
```

.profile
```bash
export PATH=/opt/apache-maven-3.6.1/bin:$PAT
```

#### 3- Introducción a Maven

- Qué es Maven?
Maven es una evolución de un par de herramientas para construir aplicaciones java. Sirve para, entre otras cosas, compilar programas. Esencialmente: construir aplicaciones java. Standalone, web. 
El objetivo de Maven es simplificar los procesos de build (compilar y generar ejecutables a partir del código fuente). Antes de su presencia cada proyecto solía tener alguna persona dedicada exclusivamente a configurar el proceso de build que debía analizar qué partes de código se debían compilar, qué librerías utilizaba el código, dónde incluirlas, qué dependencias de compilación había en el proyecto

- Qué es el archivo POM?
Receta de lo que quiero construir. Define los pasos de la ejecución de un	build. Define qué hacer y no cómo hacerlo (no es procedural).
Por otra parte, con Maven la gestión de dependencias entre módulos y distintas versiones de librerías se hace muy sencilla. En este caso, solo tenemos que indicar los módulos que componen el proyecto, o qué librerías utiliza el software que estamos desarrollando en un fichero de configuración de Maven del proyecto llamado POM.

	- modelVersion: determina qué versión de POM se está utilizando.
	- groupId: un identificador único dentro de una misma organización o proyecto.
	- artifactId: es el nombre con el que se conoce el proyecto.
	- versionId: denota qué pieza de software se utiliza respecto del tiempo.

- Repositorios Local, Central y Remotos 
http://maven.apache.org/guides/introduction/introduction-to-repositories.html
De donde maven saca las dependencias.
	- Central: todo lo abierto.
	- Local: cache de versiones locales en la maquina (mismo checksum, mismo jar). Evita tener que descargar reiteradas veces
	- Remoto: un servidor maven privado. Por compañía

- Entender Ciclos de vida de build
Son definiciones de pasos que hace maven, se ven como comandos. 
 
Maven se basa en los conceptos de ciclos de vida de build, lo que permite definir claramente los procesos de build y distribución de cada proyecto particularmente. En este sentido, sólo es necesario establecer algún conjunto de comandos (en el POM) para que Maven buildee cualquier proyecto
Existen tres tipos de ciclos de vida de build:
	- default: se encarga del deploy del proyecto.
 	- clean: se encarga de la limpieza del proyecto
	- site: se encarga de la creación de la documentación del proyecto

	- Referencia: http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Build_Lifecycle_Basics

- Comprender las fases de un ciclo de vida, por ejemplo, default:

| Fase de build | Descripción                                                                                                                            |
|---------------|----------------------------------------------------------------------------------------------------------------------------------------|
| validate      | valida si el proyecto está correcto y toda la información está disponible                                                             |
| compile       | compila el código fuente del proyecto                                                                                 |
| test          | prueba el código fuente compilado utilizando un marco de prueba de unidad adecuado. Estas pruebas no deberían requerir que el código se empaquete o implemente |
| package       | toma el código compilado y lo empaquéta en su formato distribuible, como un JAR.                                                     |
| verify        | ejecuta cualquier verificación de los resultados de las pruebas de integración para garantizar que se cumplan los criterios de calidad                                                      |
| install       | instal1 el paquete en el repositorio local, para usarlo como dependencia en otros proyectos localmente                                       |
| deploy        | hecho en el entorno de compilación, copia el paquete final en el repositorio remoto para compartirlo con otros desarrolladores y proyectos.      |

- Copiar el siguiente contenido a un archivo, por ejemplo ./trabajo-practico-02/maven/vacio/pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>ar.edu.ucc</groupId>
    <artifactId>proyecto-01</artifactId>
    <version>0.1-SNAPSHOT</version>
</project>
```

- Ejecutar el siguiente comando en el directorio donde se encuentra el archivo pom.xml
```
mvn clean install
```

```bash

C:\Users\nelon\Desktop\TP2-maven\maven\vacio>mvn clean install
[INFO] Scanning for projects...
[INFO]
[INFO] -----------------------< ar.edu.ucc:proyecto-01 >-----------------------
[INFO] Building proyecto-01 0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ proyecto-01 ---
[INFO] Deleting C:\Users\nelon\Desktop\TP2-maven\maven\vacio\target
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ proyecto-01 ---
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory C:\Users\nelon\Desktop\TP2-maven\maven\vacio\src\main\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ proyecto-01 ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ proyecto-01 ---
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory C:\Users\nelon\Desktop\TP2-maven\maven\vacio\src\test\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ proyecto-01 ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ proyecto-01 ---
[INFO] No tests to run.
[INFO]
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ proyecto-01 ---
[WARNING] JAR will be empty - no content was marked for inclusion!
[INFO] Building jar: C:\Users\nelon\Desktop\TP2-maven\maven\vacio\target\proyecto-01-0.1-SNAPSHOT.jar
[INFO]
[INFO] --- maven-install-plugin:2.4:install (default-install) @ proyecto-01 ---
[INFO] Installing C:\Users\nelon\Desktop\TP2-maven\maven\vacio\target\proyecto-01-0.1-SNAPSHOT.jar to C:\Users\nelon\.m2\repository\ar\edu\ucc\proyecto-01\0.1-SNAPSHOT\proyecto-01-0.1-SNAPSHOT.jar
[INFO] Installing C:\Users\nelon\Desktop\TP2-maven\maven\vacio\pom.xml to C:\Users\nelon\.m2\repository\ar\edu\ucc\proyecto-01\0.1-SNAPSHOT\proyecto-01-0.1-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  20.488 s
[INFO] Finished at: 2019-08-17T21:30:55-03:00
[INFO] ------------------------------------------------------------------------
```

- Sacar conclusiones del resultado

Analicemos el comando ejecutado, la instrucción install determina que debe ejecutarse el ciclo de vida default en orden (validate, compile, test, package, verify, install), el resultado será el proyecto compilado y empaquetado para ser usado a nivel local, directamente o como dependencia de otros proyectos. 
El comando clean que lo precede indica que el proyecto debe ser limpiado antes de comenzar las etapas mencionadas anteriormente. Esta limpieza implica remover todos los archivos generados por el build que se haya hecho previamente. En conclusión, el proyecto pasa previamente por el ciclo de clean antes del default.

Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-utils/3.0.5/plexus-utils-3.0.5.pom



## Trabajo Práctico 3 -

## Trabajo Práctico 4 - Introducción a Docker

#### 1- Instalar Docker Community Edition

```
netbook@netbook-pc:~$ sudo docker version
Client: Docker Engine - Community
 Version:           19.03.2
 API version:       1.40
 Go version:        go1.12.8
 Git commit:        6a30dfca03
 Built:             Thu Aug 29 05:29:17 2019
 OS/Arch:           linux/amd64
 Experimental:      false

Server: Docker Engine - Community
 Engine:
  Version:          19.03.2
  API version:      1.40 (minimum version 1.12)
  Go version:       go1.12.8
  Git commit:       6a30dfca03
  Built:            Thu Aug 29 05:27:52 2019
  OS/Arch:          linux/amd64
  Experimental:     false
 containerd:
  Version:          1.2.6
  GitCommit:        894b81a4b802e4eb2a91d1ce216b8817763c29fb
 runc:
  Version:          1.0.0-rc8
  GitCommit:        425e105d5a03fabd737a126ad93d62a9eeede87f
 docker-init:
  Version:          0.18.0
  GitCommit:        fec3683
```

#### 2- Explorar DockerHub

User: ?????
Password: ???

#### 3- Obtener la imagen BusyBox

- Ejecutar el siguiente comando, para bajar una imagen de DockerHub

```
netbook@netbook-pc:~$ sudo docker pull busybox
[sudo] password for netbook: 
Using default tag: latest
latest: Pulling from library/busybox
7c9d20b9b6cd: Pull complete 
Digest: sha256:fe301db49df08c384001ed752dff6d52b4305a73a7f608f21528048e8a08b51e
Status: Downloaded newer image for busybox:latest
docker.io/library/busybox:latest
``` 

- Verificar qué versión y tamaño tiene la imagen bajada, obtener una lista de imagenes locales:

```
netbook@netbook-pc:~$ sudo docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
busybox             latest              19485c79a9bb        8 days ago          1.22MB
hello-world         latest              fce289e99eb9        8 months ago        1.84kB

``` 

Comentario sobre TAG: latest

Puede haber problemas con latest

En Julio:
	latest -> 1.13.0
En Junio
	latest -> 1.12.0
En Mayo
	latest -> 1.11.0

Si hubo pull con latest = 1.11.0
En Julio al hacer pull a latest conserva 1.11.0 porque localmente fue tageada como latest. No trae 1.13.0

#### 4- Ejecutando contenedores

- Ejecutar un contenedor utilizando el comando run de docker:

```
docker run busybox
```

- Explicar porque no se obtuvo ningún resultado
Por que no hace nada porque el so levanta pero no se indicó que ordenes tiene que hacer. No tiene entry points. 
Y, a diferencia de las VM, Docker mata instancias si no hacen nada

- Especificamos algún comando a correr dentro del contendor, ejecutar por ejemplo:

```
netbook@netbook-pc:~$ sudo docker run busybox echo "Hello world"
Hello world
```

- Ver los contendores ejecutados utilizando el comando ps:

```
netbook@netbook-pc:~$ sudo docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES

```

No existen contenedores ejecutándose en ese momento

- Vemos que no existe nada en ejecución, correr entonces:

```
netbook@netbook-pc:~$ sudo docker ps -a
CONTAINER ID        IMAGE               COMMAND                CREATED             STATUS                     PORTS               NAMES       
7666cafbf889        busybox             "echo 'Hello world'"   3 minutes ago       Exited (0) 3 minutes ago                       goofy_clarke
1f333a0dde95        busybox             "sh"                   7 minutes ago       Exited (0) 7 minutes ago                       dreamy_tu   
9b67c5951292        hello-world         "/hello"               6 days ago          Exited (0) 6 days ago                          funny_yalow 
ef74686cb7b0        hello-world         "/hello"               7 days ago          Exited (0) 7 days ago                          hopeful_mendel
fd70f9eb3182        hello-world         "/hello"               7 days ago          Exited (0) 7 days ago                          agitated_burnell
```

```
netbook@netbook-pc:~$ sudo docker ps -a
CONTAINER ID        IMAGE                 COMMAND                  CREATED             STATUS                     PORTS               NAMES
2c51a9c89041        daviey/nyan-cat-web   "nginx -g 'daemon of…"   13 days ago         Exited (0) 13 days ago                         reverent_burnell
67bc3bdb9be5        daviey/nyan-cat-web   "nginx -g 'daemon of…"   13 days ago         Exited (0) 13 days ago                         laughing_bartik
90ab280fd5fd        daviey/nyan-cat-web   "nginx -g 'daemon of…"   13 days ago         Exited (137) 13 days ago                       priceless_davinci
82fc1c26f696        busybox               "sh"                     13 days ago         Exited (0) 13 days ago                         quizzical_jackson
622ef17c6ed1        busybox               "sh"                     13 days ago         Exited (0) 13 days ago                         friendly_mestorf
9e36247ef8ae        busybox               "sh"                     13 days ago         Exited (127) 13 days ago                       admiring_johnson
7666cafbf889        busybox               "echo 'Hello world'"     13 days ago         Exited (0) 13 days ago                         goofy_clarke
1f333a0dde95        busybox               "sh"                     13 days ago         Exited (0) 13 days ago                         dreamy_tu
9b67c5951292        hello-world           "/hello"                 2 weeks ago         Exited (0) 2 weeks ago                         funny_yalow
ef74686cb7b0        hello-world           "/hello"                 2 weeks ago         Exited (0) 2 weeks ago                         hopeful_mendel
fd70f9eb3182        hello-world           "/hello"                 2 weeks ago         Exited (0) 2 weeks ago                         agitated_burnell
```

- Mostrar el resultado y explicar que se obtuvo como salida del comando anterior:
El comando anterior muestra los contenedores que se ejecutaron especificando el momento en que lo hicieron y con qué comandos se iniciaron.
Historia de los comandos con los que se levantaron/ejecutaron los contenedores
No contiene los comandos dentro del contenedor

Según la documentación de Docker
Show all containers (default shows just running)


#### 5- Ejecutando en modo interactivo

- Ejecutar el siguiente comando

```
netbook@netbook-pc:~$ sudo docker run -it busybox sh
/ # 


```

El comando devuelve un contenedor con una prompt (un # para enviar comandos)
El parámetro -i (--interactive) hace interactivo
	Según DockerDocs: Keep STDIN open even if not attached
El parámetro -t (--tty) simula una conexión para control remoto 
	Según DockerDocs: Allocate a pseudo-TTY 
	
	
- Para cada uno de los siguientes comandos dentro de contenedor, mostrar los resultados:	

```
netbook@netbook-pc:~$ sudo docker run -it busybox sh
[sudo] password for netbook: 
/ # ps
PID   USER     TIME  COMMAND
    1 root      0:01 sh
    7 root      0:00 ps
/ # uptime
 19:18:33 up 6 min,  0 users,  load average: 1.63, 1.60, 0.87
/ # free
              total        used        free      shared  buff/cache   available
Mem:        2027920      722440      460828       89812      844652     1107792
Swap:             0           0           0
/ # ls -l /
total 36
drwxr-xr-x    2 root     root         12288 Sep  4 17:26 bin
drwxr-xr-x    5 root     root           360 Sep 26 19:17 dev
drwxr-xr-x    1 root     root          4096 Sep 26 19:17 etc
drwxr-xr-x    2 nobody   nogroup       4096 Sep  4 17:26 home
dr-xr-xr-x  173 root     root             0 Sep 26 19:17 proc
drwx------    1 root     root          4096 Sep 26 19:18 root
dr-xr-xr-x   13 root     root             0 Sep 26 19:17 sys
drwxrwxrwt    2 root     root          4096 Sep  4 17:26 tmp
drwxr-xr-x    3 root     root          4096 Sep  4 17:26 usr
drwxr-xr-x    4 root     root          4096 Sep  4 17:26 var
/ # 
```

Observaciones
En comando ps: El proceso id 1: entry point, el comando con el que iniciamos el contenedor (desde docker run)

Los diferentes comandos utilizados muestran informacion sobre el sistema, pero el sistema del contenedor, por lo tanto los resultados difieren de los de la computadora física.

#### 6- Borrando contendores terminados

- Obtener la lista de contendores

```
netbook@netbook-pc:~$ sudo docker ps -a
CONTAINER ID        IMAGE                 COMMAND                  CREATED             STATUS                           PORTS               NAMES
88a893c0e1be        busybox               "sh"                     6 minutes ago       Exited (0) 2 minutes ago                             vigilant_fermi
95f31d88aa15        busybox               "sh"                     3 hours ago         Exited (137) About an hour ago                       sleepy_cray
2c51a9c89041        daviey/nyan-cat-web   "nginx -g 'daemon of…"   13 days ago         Exited (0) 13 days ago                               reverent_burnell
67bc3bdb9be5        daviey/nyan-cat-web   "nginx -g 'daemon of…"   13 days ago         Exited (0) 13 days ago                               laughing_bartik
90ab280fd5fd        daviey/nyan-cat-web   "nginx -g 'daemon of…"   13 days ago         Exited (137) 13 days ago                             priceless_davinci
82fc1c26f696        busybox               "sh"                     13 days ago         Exited (0) 13 days ago                               quizzical_jackson
622ef17c6ed1        busybox               "sh"                     13 days ago         Exited (0) 13 days ago                               friendly_mestorf
9e36247ef8ae        busybox               "sh"                     13 days ago         Exited (127) 13 days ago                             admiring_johnson
7666cafbf889        busybox               "echo 'Hello world'"     13 days ago         Exited (0) 13 days ago                               goofy_clarke
1f333a0dde95        busybox               "sh"                     13 days ago         Exited (0) 13 days ago                               dreamy_tu
9b67c5951292        hello-world           "/hello"                 2 weeks ago         Exited (0) 2 weeks ago                               funny_yalow
ef74686cb7b0        hello-world           "/hello"                 2 weeks ago         Exited (0) 2 weeks ago                               hopeful_mendel
fd70f9eb3182        hello-world           "/hello"                 2 weeks ago         Exited (0) 2 weeks ago                               agitated_burnell
```

Observación: El "command" es equivalente al "entry point"

- Para borrar podemos utilizar el id o el nombre (autogenerado si no se especifica) de contendor que se desee, por ejemplo:

```
netbook@netbook-pc:~$ sudo docker rm sleepy_cray
sleepy_cray
```

- Para borrar todos los contendores que no estén corriendo, ejecutar cualquiera de los siguientes comandos:

```
netbook@netbook-pc:~$ sudo docker rm $(sudo docker ps -a -q -f status=exited)
88a893c0e1be
2c51a9c89041
67bc3bdb9be5
90ab280fd5fd
82fc1c26f696
622ef17c6ed1
9e36247ef8ae
7666cafbf889
1f333a0dde95
9b67c5951292
ef74686cb7b0
fd70f9eb3182

netbook@netbook-pc:~$ sudo docker ps -a
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
```

Usando el nuevo comando: container prune

```
netbook@netbook-pc:~$ sudo docker container prune
WARNING! This will remove all stopped containers.
Are you sure you want to continue? [y/N] y
Deleted Containers:
7b968b3dc45980a4df3fb4742f874a939eef7dfa4ce2c473db25b1474e3fab91
992810a40052ad12ddf853b0e8b16ada79591a57f9dfee7cf3dfe5de2a8c4f12
f1199a0e4adc478f2ded9c69e3ec6b5b18fcd5bab7cb9a913f0b83dd191f5c12

Total reclaimed space: 5B
netbook@netbook-pc:~$ sudo docker ps -a
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
```


Observacion:
Los comandos
	docker rm $(docker ps -a -q -f status=exited)
	docker container prune

cumplen la misma función: borra los contenedores, no las imagenes. Por lo tanto se puede volver a instanciar la imagen.


#### 7- Montando volúmenes

Observación
Corriendo los comandos siguientes dentro del contenedor
	ls -l /var/escritorio
	touch /var/escritorio/hola.txt

El archivo queda en el contenedor. Es efimero.
Para persistirlo se utilizan volumenes (montar a carpetas de la máquina física)


- Ejecutar el siguiente comando, cambiar myusuario por el usuario que corresponda. En linux/Mac puede utilizarse /home/miusuario):
	docker run -it -v C:\Users\misuario\Desktop:/var/escritorio busybox /bin/sh

Observacion:
La interpretación del comando es la siguiente
	El path antes de ":" (C:\Users\misuario\Desktop) refiere a la carpeta fisica
	El path luego de ":" (/var/escritorio) refiere a la carpeta dentro del contenedor


```
netbook@netbook-pc:~$ sudo docker run -it -v /home/:/var/escritorio busybox sh
/ #
```
Se inicia un contenedor que tendrá la carpeta home (física) ubicada en el escritorio (del contenedor)

- Dentro del contenedor correr

```
netbook@netbook-pc:~$ sudo docker run -it -v /home/:/var/escritorio busybox sh
/ # ls
bin   dev   etc   home  proc  root  sys   tmp   usr   var
/ # cd var/
/var # ls
escritorio  spool       www
/var # cd escritorio/
/var/escritorio # ls
netbook
/var/escritorio # cd netbook/
/var/escritorio/netbook # ls
Desktop            Downloads          Pictures           Templates          eclipse-workspace
Documents          Music              Public             Videos             pt
/var/escritorio/netbook #
```

Observación:
	Desde el escritorio del contenedor se puede listar el contenido del home de la maquina física
	

```
/var/escritorio/netbook/Desktop # touch text_desde_container.txt
```

El comando crea un archivo dentro del contenedor, el cual persiste en la máquina física

- Verificar que el Archivo se ha creado en el escritorio o en el directorio home según corresponda.

```
netbook@netbook-pc:~/Desktop$ ls -l
total 68
-rw-rw-r-- 1 netbook netbook    90 sep 26 16:13 computer.desktop
-rw-rw-r-- 1 netbook netbook 17388 sep 25 17:42 IS3-0909.txt
drwxrwxr-x 4 netbook netbook  4096 sep 19 19:59 IS3bis
-rw-rw-r-- 1 netbook netbook  8225 sep 26 15:23 IS3P1209.txt
-rw-rw-r-- 1 netbook netbook    94 sep 26 16:13 network.desktop
drwxrwxr-x 4 netbook netbook  4096 sep  5 18:36 payroll
****************************************************************************
* -rw-r--r-- 1 root    root        0 sep 12 19:44 text_desde_container.txt *
****************************************************************************
-rw-rw-r-- 1 netbook netbook   101 sep 26 16:13 trash-can.desktop
-rw-rw-r-- 1 netbook netbook    91 sep 26 16:13 user-home.desktop

```


Observación: mirando informacion del disco dentro del contenedor

```
/ # df -h
Filesystem                Size      Used Available Use% Mounted on
overlay                  69.5G      9.7G     56.2G  15% /
tmpfs                    64.0M         0     64.0M   0% /dev
tmpfs                   990.2M         0    990.2M   0% /sys/fs/cgroup
shm                      64.0M         0     64.0M   0% /dev/shm
****************************************************************************
* /dev/sda4                69.5G      9.7G     56.2G  15% /var/escritorio  *
****************************************************************************
/dev/sda4                69.5G      9.7G     56.2G  15% /etc/resolv.conf
/dev/sda4                69.5G      9.7G     56.2G  15% /etc/hostname
/dev/sda4                69.5G      9.7G     56.2G  15% /etc/hosts
tmpfs                   990.2M         0    990.2M   0% /proc/asound
tmpfs                   990.2M         0    990.2M   0% /proc/acpi
tmpfs                    64.0M         0     64.0M   0% /proc/kcore
tmpfs                    64.0M         0     64.0M   0% /proc/keys
tmpfs                    64.0M         0     64.0M   0% /proc/timer_list
tmpfs                    64.0M         0     64.0M   0% /proc/sched_debug
tmpfs                   990.2M         0    990.2M   0% /proc/scsi
tmpfs                   990.2M         0    990.2M   0% /sys/firmware
```

El comando muestra que el path /var/escritorio se encuentra ubicado físicamente en el sda4, perteneciente a la maquina física

#### 8- Publicando puertos

- Ejecutar la siguiente imagen, en este caso utilizamos la bandera -d (detach) para que nos devuelva el control de la consola

Observación: el parámetro "-d" (--detach) devuelve el prompt a la maquina física, es como ejecutar el container en segundo plano
	Según DockerDocs: Run container in background and print container ID.


```
netbook@netbook-pc:~$ sudo docker run -d daviey/nyan-cat-web
Unable to find image 'daviey/nyan-cat-web:latest' locally
latest: Pulling from daviey/nyan-cat-web
b7f33cc0b48e: Pull complete 
5f9b58fd6dd4: Pull complete 
1adeef8edfca: Pull complete 
cc8a2986b124: Pull complete 
7220539c61d6: Pull complete 
Digest: sha256:57ac8fd383ada137e22a2894e92f74287f4566be0ae21ca97828b34a93a646c6
Status: Downloaded newer image for daviey/nyan-cat-web:latest
90ab280fd5fdf7112d6e89d17567ee4c2506d63949305a733964913bff21122c
```

Observación: al no encontrar la imagen localmente, por ser la primera vez, la trae de repositorios remotos
En las instancias futuras no ocurre

```
netbook@netbook-pc:~/Desktop$ sudo docker run -d daviey/nyan-cat-web
[sudo] password for netbook: 
662a808246d3cb5b01120fa6bc6d2e2fc455595558d72b92f3a18c5df797f83c

netbook@netbook-pc:~/Desktop$ sudo docker run -d daviey/nyan-cat-web
1417949ff92e087c85be71430c9b32c40877d6e3450b2f4e750665aabca46e41
```

- Si ejecutamos un comando ps:
```
netbook@netbook-pc:~/Desktop$ sudo docker run -d daviey/nyan-cat-web
3a576782352f6bee87d51b2f271756b0f3c791a3ab173ed3b90df6cdca05b56d

netbook@netbook-pc:~/Desktop$ sudo docker ps
CONTAINER ID        IMAGE                 COMMAND                  CREATED             STATUS              PORTS               NAMES
3a576782352f        daviey/nyan-cat-web   "nginx -g 'daemon of…"   10 seconds ago      Up 4 seconds        80/tcp, 443/tcp     focused_liskov
```

Observación: queda corriendo nginx en puertos 80 y 443, y gracias a -d queda en segundo plano

- Vemos que el contendor expone 2 puertos el 80 y el 443, pero si intentamos en un navegador acceder a http://localhost no sucede nada.

Observación:
Al consultar desde un navegador por "http://localhost:80", no funciona.
Esto se debe a que el puerto 80 de la maquina no es el mismo del contenedor. Porque no hay mapeo de puertos.

- Procedemos entonces a parar y remover este contenedor:
```
netbook@netbook-pc:~/Desktop$ sudo docker kill focused_liskov
focused_liskov
netbook@netbook-pc:~/Desktop$ sudo docker rm focused_liskov
focused_liskov
```

- Vamos a volver a correrlo otra vez, pero publicando uno de los puertos solamente, el este caso el 80
	docker run -d -p 80:80 daviey/nyan-cat-web

Observación:
La interpretación del comando es la siguiente
	El parámetro -p sirve para mapear puertos
	El puerto antes de ":" (80) refiere al puerto de la máquina física
	El puerto luego de ":" (80) refiere al puerto interno del contenedor
	Es similar a cómo funcionan los volumenes (puerto de la maquina:puerto del container)

```
netbook@netbook-pc:~$ sudo docker run -d -p 80:80 daviey/nyan-cat-web
67bc3bdb9be5b58fd927da7ce358489d68978cf9f2d3fc28e8aee10d9e7b4165
```

- Accedamos nuevamente a http://localhost y expliquemos que sucede.

Al consultar desde un navegador por "http://localhost:80", sí funciona.

Observación:
Se ejecuta otro contenedor, pero mapeado a otro puerto local

```
netbook@netbook-pc:~$ sudo docker run -d -p 3000:80 daviey/nyan-cat-web
67bc3bdb9be5b58fd927da7ce358489d68978cf9f2d3fc28e8aee10d9e7b4165
```


Al consultar desde un navegador por "http://localhost:3000", sí funciona.

Esto de abrir muchos puertos para hacer balanceo de cargas
Tambien para tener diferentes versiones en distintos puertos (canary)
	Usa a cierta gente (usuarios) en esa version de la aplicacion

