### 组件
* Bootstrap：netty的组件容器，用于把其他各个部分连接起来；如果是TCP的Server端，则为ServerBootstrap.
* Channel：代表一个Socket的连接
* EventLoopGroup：一个Group包含多个EventLoop，可以理解为线程池
* EventLoop：处理具体的Channel，一个EventLoop可以处理多个Channel
* ChannelPipeline：每个Channel绑定一个pipeline，在上面注册处理逻辑handler
* Handler：具体的对消息或连接的处理，有两种类型，Inbound和Outbound。分别代表消息接收的处理和消息发送的处理。
* ChannelFuture：注解回调方法