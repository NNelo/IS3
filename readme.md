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
