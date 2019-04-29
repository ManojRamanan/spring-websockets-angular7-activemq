This is a spring websocket template with active mq congifuration
and angular 7 support 

#prerequistes 
java version 8<br>
angular version 7.1.4<br>
gradle version 4.10.2<br>
active mq version 5 <br>
node version v10.13.0 

#start the active mq broker 
cd [activemq_install_dir]<br>
bin\activemq start

# To package and start  websocket server 
gradle clean build && gradle bootRun

#To package and start client 
npm install <br>
ng serve --port 4200




If you have any questions or suggestions feel free to contact me:

manojramanann@gmail.com