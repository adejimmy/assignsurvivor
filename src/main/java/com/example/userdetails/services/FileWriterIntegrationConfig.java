package com.example.userdetails.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.integration.file.support.FileExistsMode;
import java.io.File;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileWritingMessageHandler;

@Configuration
public class FileWriterIntegrationConfig {
    //The transformer bean is annotated with
    //@Transformer designating it as a transformer in the integration
    // flow that receives messages on a channel named textInChannel and writes messages to the channel named
    //fileWriterChannel
    @Bean
    @Transformer(inputChannel = "textInChannel",
            outputChannel = "fileWriterChannel")
    public GenericTransformer<String, String> upperCaseTransformer() {
        return text -> text.toUpperCase();
    }

   // As for the file-writing bean, it’s annotated with @ServiceActivator to indicate that
   //it’ll accept messages from fileWriterChannel and hand those messages over to the
   //service defined by an instance of FileWritingMessageHandler. FileWritingMessageHandler
   //
   // is a message handler that writes a message payload to a file in a specified
   //directory using a filename specified in the message’s file_name header
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler fileWriter() {
        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(new File("/tmp/sia5/files"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;

    }

}
// Channels—Pass messages from one element to another.
// Filters—Conditionally allow messages to pass through the flow based on some
//criteria.
// Transformers—Change message values and/or convert message payloads from
//one type to another.
// Routers—Direct messages to one of several channels, typically based on message headers.
// Splitters—Split incoming messages into two or more messages, each sent to different channels.
// Aggregators—The opposite of splitters, combining multiple messages coming in
//from separate channels into a single message.
// Service activators—Hand a message off to some Java method for processing, and
//then publish the return value on an output channel.
// Channel adapters—Connect a channel to some external system or transport. Can
//either accept input or write to the external system.
// Gateways—Pass data into an integration flow via an interface


//Message channels
//Message channels are the means by which messages move through an integration
//pipeline (figure 9.2).
//Spring Integration provides several channel implementations, including these:
// PublishSubscribeChannel—Messages published into a PublishSubscribeChannel
// are passed on to one or more consumers. If there are multiple consumers, all of them receive the message.
//Channel
//Figure 9.2 Message channels are conduits
//through which data flows between other
//components in an integration flow.
//218 CHAPTER 9 Integrating Spring
// QueueChannel—Messages published into a QueueChannel are stored in a queue
//until pulled by a consumer in a first in, first out (FIFO) fashion. If there are
//multiple consumers, only one of them receives the message.
// PriorityChannel—Like QueueChannel but, rather than FIFO behavior,
// messages are pulled by consumers based on the message priority header.
// RendezvousChannel—Like QueueChannel except that the sender blocks the
//channel until a consumer receives the message, effectively synchronizing the
//sender with the consumer.
// DirectChannel—Like PublishSubscribeChannel but sends a message to a single
// consumer by invoking the consumer in the same thread as the sender. This
//allows for transactions to span across the channel.
// ExecutorChannel—Similar to DirectChannel but the message dispatch occurs
//via a TaskExecutor, taking place in a separate thread from the sender. This
//channel type doesn’t support transactions that span the channel.
// FluxMessageChannel—A Reactive Streams Publisher message channel based on
//Project Reactor’s Flux. (We’ll talk more about Reactive Streams, Reactor, and
//Flux in chapter 10.)


//FILTER
//Filters
//Filters can be placed in the midst of an integration pipeline to allow or disallow messages
// from proceeding to the next step in the flow (figure 9.3).
//For example, suppose that messages containing integer values are published through
//a channel named numberChannel, but you only want even numbers to pass on to the
//channel named evenNumberChannel. In that case, you could declare a filter with the
//@Filter annotation like this:
//@Filter(inputChannel="numberChannel",
// outputChannel="evenNumberChannel")
//public boolean evenNumberFilter(Integer number) {
// return number % 2 == 0;


//TRANSFORMER
//Transformers
//Transformers perform some operation on messages, typically resulting in a different
//message and, possibly, with a different payload type (see figure 9.4).
// The transformation can be something simple, such as performing mathematic operations on
// a number or manipulating a String value. Or the transformation can be more complex,
//such as using a String value representing an ISBN to look up and return details of the
//corresponding book.


//ROUTER
// Routers
//Routers, based on some routing criteria, allow for branching in an integration flow,
//For example, suppose that you have a channel named numberChannel through which
//integer values flow. And let’s say that you want to direct all messages with
// even numbers to a channel named evenChannel, while messages with odd numbers are routed
//to a channel named oddChannel. To create such a routing in your integration flow,
//you can declare a bean of type AbstractMessageRouter and annotate the bean with
//The AbstractMessageRouter bean declared here accepts messages from an input
//channel named numberChannel. The implementation, defined as an anonymous
//inner class, examines the message payload and, if it’s an even number, returns the
//channel named evenChannel (declared as a bean after the router bean). Otherwise,
//the number in the channel payload must be odd; in which case, the channel named
//oddChannel is returned (also declared in a bean declaration method).


//Splitters
//At times, in an integration flow it can be useful to split a message into multiple messages
// to be handled independently. Splitters, as illustrated in figure 9.6, will split and
//handle those messages for you.
//Splitters are useful in many circumstances, but there are two essential use cases for
//which you might use a splitter:
// A message payload contains a collection of items of the same type that you’d like to process
//as individual message payloads. For example, a message carrying a list of products
// might be split into multiple messages with payloads of one product each.
// A message payload carries information that, although related, can be split into two or
//more messages of different types. For example, a purchase order might carry delivery,
// billing, and line-item information. The delivery details might be processed
//by one subflow, billing by another, and line items in yet another. In this use
//case, the splitter is typically followed by a router that routes messages by payload
//type to ensure that the data gets handled by the right subflow

// Service activators
//Service activators receive messages from an input channel and send those messages to
//an implementation of MessageHandler

//Gateways
//Gateways are the means by which an application can submit data into an integration
//flow and, optionally, receive a response that’s the result of the flow. Implemented by
//Spring Integration, gateways are realized as interfaces that the application can call to
//send messages to the integration flow


// Channel adapters
//Channel adapters represent the entry and exit points of an integration flow. Data
//enters an integration flow by way of an inbound channel adapter and
// exits an integration flow by way of an outbound channel adapter.

