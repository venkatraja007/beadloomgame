BeadLoom Project Notes - Clayton Kotulak

Foreword:

The tool sets used in the following description are Eclipse IDE Version: 3.3.2 
and tortoiseSVN. The files are stored and modified from the hardrive and updated 
periodically to the eclipse server using the "commit" command.

------------ Compiling and runnning the BeadLoom program in eclipse ------------

In order to compile the Java implementation of the BeadLoom start by downloading 
a fresh copy of the program files (under the BeadFinalWindows\project\project in 
our particular version) and do the following:

1. After booting up eclipse create a new java project (file --> new --> java 
project)

2. When the "New java project" dialogue pops up, click "Create project from 
existing source"

3. In the text box enter the directory of the source files (this will bethe last 
"project" folder if you have just downloaded the program)

4. Click "next" and then "finish" to load the project to your current workspace.

5. To compile the project right click BeadLoom.java under "(defult package)"
Once the options window pops up click "Run as" and run it as a java application.

Now, this should compile and run the application, however I never promised that 
the application would be fully functional. It seems that in its current state it 
is not loading the beads when the draw function is called. This is most likely
because we are missing a bead image file, but it seems that all of the image files
were properly loaded when the project was created. I guess some debugging is in 
order.

------------ Changing the hard coded URLs to a different location ------------

Beforehand:

In order to upload the image files to a server, you may need a SFTP client  to 
transfer the files, professor Krishnamoorthy has suggested WinSCP and so far it has
 worked rather well.  If you are using this program you enter the name of your host 
server (I used rcs-sun1.rpi.edu), port number (22), user name (kotulc in my case), 
and select the SFTP protocol. If you are using the school's server you should place 
the image files under the public_html folder (I just copied the entire image folder 
int public_html), my URL path looks like this "http://www.rpi.edu/ ~kotulc/Images/".  
Now that you have the image files in a publicly accessible location you can change 
the URLs in the source code to match.

Note: To make finding the URLs easier hit control-f to use the search function and 
search for the string "http".

In the file BeadLoom.java

1. The first URL  (aptly labeled "url") is on line 60 and  needs to be changed,  my 
version of url is in the form
"URL url = new URL("http://www.rpi.edu/~kotulc/Images/bead.JPG");"

2. Just a little further down on line 69 is BEAD_ADDRESS, again, this needs to be 
swapped with our new url.
"String BEAD_ADDRESS = "http://www.rpi.edu/~kotulc/Images/bead.JPG";"

3. On line 551 there is another url variable, change it.
"url = new URL("http://www.rpi.edu/~kotulc/Images/bead.JPG");"

4. On line 675 there is yet another URL, this time it has a variable appended, do 
the same but substitute with your url.
"url = new URL("http://www.rpi.edu/~kotulc/Images/"+name);"

5. Finally, on line 760 there is the last URL that needs to be changed
"URL url = new URL("http://www.rpi.edu/~kotulc/Images/RAMROD.jpg");"

6. Now the last change we need to make involves the URL that had the appended 
variable, simply go to line 209 and comment out the two sections of code that 
contain the bear.xml and pattern.xml files since these are not located in the image 
folder that we have been supplied. it should look like this:

/*Top.getDrawBearMenuItem().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DrawExampleMenuItemActionPerformed(e, "bear.xml");
            }
        });
        Top.getDrawPatternMenuItem().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DrawExampleMenuItemActionPerformed(e, "pattern.xml");
            }
        });*/

These are just example image files that are not nessassary to run the program.
Now the last modification is made to the GUIGoalImages.java file

1. On line 47 is the last url
"URL url = new URL("http://www.rpi.edu/~kotulc/Images/image2.jpg");"

After these modifications have been made you should be able to compile and run the
program with full functionality. this process has been added to my readme text file.

------------ Passing URL stings to the Applet with HTML parameters ------------

Beforehand:

To enable the use of alternate server locations for the image files which are used in the 
Beadloom applet, traditionally it would be necassary to recompile the project after 
any and all changes to the hardcoded URLs, thus to enable a change in image locality 
the program has been altered to accept html parameters which alleviates the dependency 
on recompilation. The image URL parameter has the tag 'server' which is used by the 
getParameter() function to retrive the value being passed through the html page which it 
is being launched from. The parameter value in the temporary html file (temp.html) is 
set to 'http://www.rpi.edu/~kotulc/Images/' which is the folder that contains the image
files which the applet links to. this value is passed as the 'baseURL' variable and is 
used in all image based functionality in the program by simply appending the file name to
the supplied string. The html file can be as simple as a couple of lines supplying the
dimensions, the jar file and of course the URL parameter. A functional example is as 
follows:

<html>
	<applet code = "BeadLoom.class" archive = "xom-1.1.jar"
		width = 1200 height = 800>
		<param name="server" value="http://www.rpi.edu/~kotulc/Images/">
	</applet>
</html>

This file should be named with an html extension and coupled with the source to enable it 
to be launched in an applet form.

Now with the basics covered we now have the ability to insert our own URL value in place
of the supplied value. It's as simple as changeing the line which follows the 'value=' 
statment. However, if we were to have a need to exchange one of the implemented images with
one of our own we would need to name the file exactly as it has been named in the compiled
source code.  We use a single base URL and then append the file names to simplify the 
process, but if there is a need to adapt a file name which differs from the given names 
then follow the section titled 'Changing the hard coded URLs to a different location' and 
just change the appended file name, this of course will require recompilation.

-------------- Singning a java application with a custom keyset ---------------

Beforehand:
In order to sing a JAR file you should have the JRE 1.2 or better along with the keytool
and jarsigner applications (generally included with most modern JRE versions)

If intended uses require permissions that extend beyond the sandbox restrictions of an
unsigned java applet, then the JAR file must be signed with a digital signature to grant 
rights such as executing an outside file, reading and or writing to the hard disk.  There
are several informative tutorials on this particual subject, but one more user friendly 
guids can be found at http://www.cs.princeton.edu/introcs/85application/jar/sign.html. 
This guid will walk you through the process but for to gain a thurough understanding of
digital signatures in general the sun site is a must, 
http://java.sun.com/docs/books/tutorial/deployment/jar if these links are invalid google 
is always a sure bet.

The condensed version is as follows:

1. Obtain a copy of the projects jar file, in this case I will be using 'xom-1.1.jar'. 

2. To generate the keys with which you will be using to sign the JAR enter the following
command:

	'keytool -genkey -keystore' followed by the desired name of the keystore file.

3. Fill out the following prompts to the best of your ability and hit 'return'

4. Now, in order to sign the jar use the keystore file we created earlier, enter:

	'jarsigner -keystore KEYSTORENAME xom-1.1.jar ALIAS'

To which you should be prompted to enter a password.
	 