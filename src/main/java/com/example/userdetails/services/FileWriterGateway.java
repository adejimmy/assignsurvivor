package com.example.userdetails.services;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

//Declares a
//message gateway
// This
//annotation tells Spring Integration to generate an implementation of this interface
//at runtime
//The defaultRequestChannel attribute of @MessagingGateway indicates that any
//messages resulting from a call to the interface methods should be sent to the given
//message channel. In this case, you state that any messages that result from a call to
//writeToFile() should be sent to the channel whose name is textInChannel
@MessagingGateway(defaultRequestChannel="textInChannel")
public interface FileWriterGateway {

  //  Writes to a file
    //As for the writeToFile() method, it accepts a filename as a String and another
  //String that is to contain the text that should be written to a file. What’s notable about
  //this method signature is that the filename parameter is annotated with @Header. In
  //this case, the @Header annotation indicates that the value passed to filename should
  //be placed in a message header (specified as FileHeaders.FILENAME, which resolves
  //to file_name) rather than in the message payload. The data parameter value, on the
  //other hand, is carried in the message payload
    void writeToFile(
            @Header(FileHeaders.FILENAME) String filename,
            String data);


}
