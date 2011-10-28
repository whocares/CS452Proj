package HTMLserver;

import java.io.*;
import java.net.*;

//file: server.java
//the real (http) serverclass
//it extends thread so the server is run in a different
//thread than the gui, that is to make it responsive.
//it's really just a macho coding thing.
public class server
  extends Thread {

//the constructor method
//the parameters it takes is what port to bind to, the default tcp port
//for a httpserver is port 80. the other parameter is a reference to
//the gui, this is to pass messages to our nice interface
public server(int listen_port, webserver_starter to_send_message_to) {
  message_to = to_send_message_to;
  port = listen_port;

//this makes a new thread, as mentioned before,it's to keep gui in
//one thread, server in another. You may argue that this is totally
//unnecessary, but we are gonna have this on the web so it needs to
//be a bit macho! Another thing is that real pro webservers handles
//each request in a new thread. This server dosen't, it handles each
//request one after another in the same thread. This can be a good
//assignment!! To redo this code so that each request to the server
//is handled in its own thread. The way it is now it blocks while
//one client access the server, ex if it transferres a big file the
//client have to wait real long before it gets any response.
  this.start();
}

private void s(String s2) { //an alias to avoid typing so much!
  message_to.send_message_to_window(s2);
}

private webserver_starter message_to; //the starter class, needed for gui
private int port; //port we are going to listen to

//this is a overridden method from the Thread class we extended from
public void run() {
  //we are now inside our own thread separated from the gui.
  ServerSocket serversocket = null;
  //To easily pick up lots of girls, change this to your name!!!
  s("The simple httpserver v. 0000000000\nCoded by Jon Berg" +
    "<jon.berg[on server]turtlemeat.com>\n\n");
  //Pay attention, this is where things starts to cook!
  try {
    //print/send message to the guiwindow
    s("Trying to bind to localhost on port " + Integer.toString(port) + "...");
    //make a ServerSocket and bind it to given port,
    serversocket = new ServerSocket(port);
  }
  catch (Exception e) { //catch any errors and print errors to gui
    s("\nFatal Error:" + e.getMessage());
    return;
  }
  s("OK!\n");
  //go in a infinite loop, wait for connections, process request, send response
  while (true) {
    s("\nReady, Waiting for requests...\n");
    try {
      //this call waits/blocks until someone connects to the port we
      //are listening to
      Socket connectionsocket = serversocket.accept();
      //figure out what ipaddress the client commes from, just for show!
      InetAddress client = connectionsocket.getInetAddress();
      //and print it to gui
      s(client.getHostName() + " connected to server.\n");
      //Read the http request from the client from the socket interface
      //into a buffer.
      BufferedReader input =
          new BufferedReader(new InputStreamReader(connectionsocket.
          getInputStream()));
      //Prepare a outputstream from us to the client,
      //this will be used sending back our response
      //(header + requested file) to the client.
      DataOutputStream output =
          new DataOutputStream(connectionsocket.getOutputStream());

//as the name suggest this method handles the http request, see further down.
//abstraction rules
      http_handler(input, output);
    }
    catch (Exception e) { //catch any errors, and print them
      s("\nError:" + e.getMessage());
    }

  } //go back in loop, wait for next request
}

//our implementation of the hypertext transfer protocol
//its very basic and stripped down
private void http_handler(BufferedReader input, DataOutputStream output) {
  int method = 0; //1 get, 2 head, 0 not supported
  String http = new String(); //a bunch of strings to hold
  String path = new String(); //the various things, what http v, what path,
  String file = new String(); //what file
  String user_agent = new String(); //what user_agent
  try {
    String tmp = input.readLine();
    String tmp2 = new String(tmp);
    tmp.toUpperCase(); 
    if (tmp.startsWith("GET")) { 
      method = 1;
    } //if we set it to method 1
    if (tmp.startsWith("HEAD")) { 
      method = 2;
    } 

    if (method == 0) { 
      try {
        output.writeBytes(construct_http_header(501, 0));
        output.close();
        return;
      }
      catch (Exception e3) { 
        s("error:" + e3.getMessage());
      }
    }

 
    int start = 0;
    int end = 0;
    for (int a = 0; a < tmp2.length(); a++) {
      if (tmp2.charAt(a) == ' ' && start != 0) {
        end = a;
        break;
      }
      if (tmp2.charAt(a) == ' ' && start == 0) {
        start = a;
      }
    }
    path = tmp2.substring(start + 2, end); //fill in the path
  }
  catch (Exception e) {
    s("errorr" + e.getMessage());
  } 

  
  s("\nClient requested:" + new File(path).getAbsolutePath() + "\n");
  FileInputStream requestedfile = null;

  try {
    
    requestedfile = new FileInputStream(path);
  }
  catch (Exception e) {
    try {
     output.writeBytes(construct_http_header(404, 0));
     
      output.close();
    }
    catch (Exception e2) {}
    ;
    s("error" + e.getMessage());
  } 

  try {
    int type_is = 0;
   if (path.endsWith(".zip") || path.endsWith(".exe")
        || path.endsWith(".tar")) {
      type_is = 3;
    }
    if (path.endsWith(".jpg") || path.endsWith(".jpeg")) {
      type_is = 1;
    }
    if (path.endsWith(".gif")) {
      type_is = 2;
    }
    output.writeBytes(construct_http_header(200, 5));

   if (method == 1) { //1 is GET 2 is head and skips the body
      while (true) {
       int b = requestedfile.read();
        if (b == -1) {
          break; //end of file
        }
        output.write(b);
      }
      
    }
   output.close();
    requestedfile.close();
  }

  catch (Exception e) {}

}

private String construct_http_header(int return_code, int file_type) {
  String s = "HTTP/1.0 ";

  switch (return_code) {
    case 200:
      s = s + "200 OK";
      break;
    case 400:
      s = s + "400 Bad Request";
      break;
    case 403:
      s = s + "403 Forbidden";
      break;
    case 404:
      s = s + "404 Not Found";
      break;
    case 500:
      s = s + "500 Internal Server Error";
      break;
    case 501:
      s = s + "501 Not Implemented";
      break;
  }

  s = s + "\r\n"; //other header fields,
  s = s + "Connection: close\r\n"; //we can't handle persistent connections
  s = s + "Server: SimpleHTTPtutorial v0\r\n"; //server name

 switch (file_type) {
    //plenty of types for you to fill in
    case 0:
      break;
    case 1:
      s = s + "Content-Type: image/jpeg\r\n";
      break;
    case 2:
      s = s + "Content-Type: image/gif\r\n";
    case 3:
      s = s + "Content-Type: application/x-zip-compressed\r\n";
    default:
      s = s + "Content-Type: text/html\r\n";
      break;
  }

  s = s + "\r\n"; 
  return s;
}

}