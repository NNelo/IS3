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
