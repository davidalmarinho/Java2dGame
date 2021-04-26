# Java2DGame project

Hello, this is my first Open-Source Game, like I said before :D 
Here, I am doing a 2D Java Game where I am putting all my skills. 
First, of all, I recommend you to add this extension to your browser: [Markdown Diagrams](https://github.com/marcozaccari/markdown-diagrams-browser-extension). Because I have some explanations for you prepared in Subjects' directory. The problem is, since I use mermaid to do diagrams and graphics, if you don't have something that converts mermaid's diagrams codes into diagrams (that can be a text editor, an extension...), you will lose some explanations. 

This is why I recommend this extension.

---

## Now, let's take a look at this amazing game! (cof cof)

---

### *Requirements*

- Just the [jdk 8](https://www.oracle.com/pt/java/technologies/javase/javase-jdk8-downloads.html)'s toolkit

---

### *How to run it?* (NOT WORKING THIS RUNNING METHOD)

- [Windows](#windows)

- [Linux](#linux)

- P.S.: Sorry, I don't have a MacBook, so... 

---

### *Thank you!*

I just want to thank you due to giving me a chance to my project!

I hope you enjoy it :D

---

##### Windows:

The game is already compiled (in the output directory) which means that you can execute.

But, it needs a special way to be run:

- Copy the assets' folder to out/production/Java2dGame
  
  ```cpp
  xcopy /E /I "C:\Users\username\projectpath\Java2dGame\assets" "C:\Users\David\Downloads\Java2dGame\out\production\Java2dGame\assets"
  ```

- After, we will just go into the package's folder and run the project using Java
  
  ```cpp
  cd "C:\Users\username\projectpath\Java2dGame\out\production\Java2dGame"
  ```
  
  ```cpp
  "C:\Program Files\Java\jdk1.8.0_281\bin\java.exe" com.davidalmarinho.main.Main
  ```
  
  If you don't know much about how cmd works, I will give you an example.
  Let's suppose that you have downloaded the project and, now, the project is in Download's folder.
  So, the commands will be:
  
  ```cpp
  xcopy /E /I "C:\Users\David\Downloads\Java2dGame\assets" "C:\Users\David\Downloads\Java2dGame\out\production\Java2dGame\assets"
  ```
  
  ```cpp
  cd "C:\Users\David\Downloads\Java2dGame\out\production\Java2dGame"
  ```
  
  ```cpp
  "C:\Program Files\Java\jdk1.8.0_281\bin\java.exe" com.davidalmarinho.main.Main
  ```

#### Linux:

The game is already compiled (in the output directory) which means that you can execute.

But, it needs a special way to be run:

- Copy the assets' folder to out/production/Java2dGame
  
  ```bash
  cp ~/projectPath/assets ~/projectPath/out/production/Java2dGame/
  ```

- After, we will just go into the package's folder and run the project using Java
  
  ```bash
  cd ~/projectPath/out/production/Java2dGame
  java com.davidalmarinho.main.Main
  ```
  
  If you don't know much about bash, I will give you an example.
  Let's suppose that you have downloaded the project and, now, the project is in Download's folder.
  So, the commands will be:
  
  ```bash
  cp ~/Downloads/Java2dGame/assets ~/Downloads/Java2dGame/out/production/Java2dGame/
  cp ~/Downloads/Java2dGame/levels ~/Downloads/Java2dGame/out/production/Java2dGame/
  cd ~/Downloads/Java2dGame/out/production/Java2dGame/
  java com.davidalmarinho.main.Main
  ```

---
