## Installation instructions
 
### Step 1: Install Java
     
#### Java installation verification
You will need to open a Terminal.
  - Windows: go to the search (at the bottom left in Windows 10) and type `cmd`. 
You will see a program in the list, select it.
  - Linux: ctr + alt + T or look for the app Terminal
  - Mac: look for the app Terminal
Check if you have `java` installed, type in the command line (Terminal):
 
 `java -version`
      
If you get a message displaying a version of java 8 or later (for example javac 1.8.0_201), you can skip to step 2 because it means that you already have java installed. if you get javac 1.6 or 1.7 you still need to follow these steps as these versions are too old
   
#### Java 8 installation 
* Linux (Ubuntu or Mint):
        
     ```        
            sudo add-apt-repository ppa:webupd8team/java
         
            sudo apt-get update
         
            sudo apt-get install oracle-java8-installer
         
            sudo apt-get install oracle-java8-set-default       
         
    ```
* Mac and Windows:     
  * Go to the [Oracle website](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 
  * On the first box, tick the __Accept License Agreement__ checkbox and select the right build for your operating system. 
    * Mac: lucky you, there is only one in the list to chose from.
    * Windows: 
                    You will need to find out if you have a 64 or 32 bits system. If you have windows 10 or 8 type 
                    ```
                    control /name Microsoft.System
                    ``` 
                    in the command line or open the `Control Panel` manually. 
                    For older versions, just open the `Control Panel` manually.
                    There should be a system section and it will say __64 bits system__ or __32 bits system__. 
                    Select the right file to download either 64bits or 32 bits.
 * Once the download completes, run the installer and follow the instructions.
      
      
### Step 2: Setup JAVA_HOME
    
* Check if you have it already setup, in the command line type:
 
  * Linux and Mac:
    ```
        echo $JAVA_HOME
    ```
  * Windows:
    ```
       echo %JAVA_HOME%
     ```    
    If you get a file path, for example `/usr/lib/jvm/java-8-oracle`, then you can go directly to step 3.
 
* Setup
   - Windows:
   
        - Start > Control Panel > System > Advanced System Settings > Environment Variables System Variables > New
        - Variable name: JAVA_HOME
        - Variable value: C:\Program Files\Java\jdk1.8.0_171 (or whatever version your installation is!)
        - Don't forget to accept.
        - Open a new command line and type: `echo %JAVA_HOME%` again to make sure the path is set.
      
   - Mac:
   
        - Open Terminal.
        - Navigate to your home directory using `cd`
        - Open the file `~/.bash_profile` which is a configuration file for your terminal
        - Add the following command to the file: 
        `export JAVA_HOME=$(/usr/libexec/java_home)`
        - Reload the configuration file using:
        `source ~/.bash_profile`
        - Enter `echo $JAVA_HOME` to confirm the path.
   - Linux:
        - Open `.bashrc` file. Add `export JAVA_HOME=/usr/lib/jvm/java-8-oracle` at the end of the file.
        - Open a new Terminal and check with `echo $JAVA_HOME`
   
        
### Step 3: Install Android Studio
    
   Android Studio is the IDE that we will use to develop Android apps. 
    
   Follow [these](https://developer.android.com/studio/install) instructions and if you have __Linux 64 bit do not forget the extra libraries__.
   To make it simple, just choose the standard installation (selected by default), you will get all the tools needed for this workshop.
   
### Step 4: Install git
    
   We use git as a source control tool to commit all the code we write to github. You will need it to clone the project and get all the sources on your machine.
   Check if you have git installed, in the command line type:
   `git --version`
   If you get a message saying that the git program is not recognised, you will need to install it, otherwise skip to section 5.
   * Windows:
   
     [Download](https://git-scm.com/download/win) the file and run it to install. I just left the default settings and it all worked.
     
   * Linux (Ubuntu or Mint):
   ```
   sudo apt-get update
   sudo apt-get install git-core
   ```
   
### Step 5: Clone the project
    
  Choose a folder where you will put your code. I use a folder called `src` but it is totally up to you.
  Navigate to that folder in the command line and clone the project.
  ```
  cd src
  git clone https://github.com/WomenWhoCode/WWCodeBarcelona.git
  ```
  A folder called `AndroidWorkshops_BCN` will be created.
  Wait until the cloning is complete.
  Open Android studio.
  Select `Import an existing Android Studio project`.
  Chose the project using the path you set, in my case, `src/AndroidWorkshops_BCN/StartingProject`.
  Press OK.
  The project will be imported and you are all set!


### Step 6: Run the project
If you are curious after the project finishes building and downloading dependencies you can look for the green run button and try running it. You will be asked to chose the emulator to use or create one if there are none.
