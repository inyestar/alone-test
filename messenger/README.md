# Alone Messenger Microservice

### Featurs

### Background
** WebSocket
  - bidirectional data transmission on the web
  - HTML5
  - full-duplex communication channels over a single TCP connection 
  - at layer 7 in the OSI model and depend on TCP at layer 4. 
  - HTTP is used only for the initial handshake
  - the HTTP Upgrade header[1] to change from the HTTP protocol to the WebSocket protocol. 
  	<pre>
	  	<code>
		    GET /chat HTTP/1.1
		    Host: server.example.com
		    Upgrade: websocket
	    </code>
    </pre>

** STOMP
  - WebSocket protocol there is simply not enough information in an incoming message for a framework or container to know how to route it or process it.
    For this reason the WebSocket RFC defines the use of sub-protocols. During the handshake, the client and server can use the header Sec-WebSocket-Protocol to agree on a sub-protocol, i.e. a higher, application-level protocol to use.
    The Spring Framework provides support for using STOMP
  - Simple (or Streaming) Text Orientated Messaging Protocol
  - STOMP clients can communicate with any STOMP message broker
  - default encoding for STOMP is UTF-8
 
** references
  - [WebSocket](https://en.wikipedia.org/wiki/WebSocket)
  - [Spring WebSocket](https://docs.spring.io/spring-framework/docs/4.3.x/spring-framework-reference/html/websocket.html#websocket-intro)
  - [STOMP](https://stomp.github.io/stomp-specification-1.2.html#Background)
	