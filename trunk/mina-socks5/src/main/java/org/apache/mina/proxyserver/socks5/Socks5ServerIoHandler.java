package org.apache.mina.proxyserver.socks5;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.proxyserver.socks5.codec.IdentifierMethod;
import org.apache.mina.proxyserver.socks5.codec.MessageType;
import org.apache.mina.proxyserver.socks5.codec.Socks5Message;

/**
 * Created with IntelliJ IDEA.
 * User: songjiao
 * Date: 13-3-27
 * Time: 上午1:44
 * To change this template use File | Settings | File Templates.
 */
public class Socks5ServerIoHandler extends IoHandlerAdapter{
    static final AttributeKey STAGE=new AttributeKey(Socks5ServerIoHandler.class,"STAGE");
    static final int START_MODE	= 0;
    static final int ACCEPT_MODE	= 1;
    static final int PIPE_MODE	= 2;
    static final int ABORT_MODE	= 3;

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        session.setAttribute(STAGE, START_MODE);//已经接受连接了，但是连接并没有真正建立起来，只是创建完了IoSession对象
    }

    /**
     * 。如果你每 隔一段时间，发送一些数据，那么 sessionCreated()方法只会在第一次调用，但是 sessionOpened()方法每次都会调用。
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        session.setAttribute(STAGE,ACCEPT_MODE);    //连接已经打开，可以开始收发数据了
    }

    /**
     * 对于 TCP 来说，连接被关闭时，调用这个方法。 对于 UDP 来说，IoSession 的 close()方法被调用时才会毁掉这个方法。
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        session.setAttribute(STAGE,ABORT_MODE);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        Socks5Message socks5Message=(Socks5Message)message;
        if(socks5Message.getMessageType()== MessageType.IdentifierRequest ){
            IdentifierMethod method=socks5Message.getIdentifierMethod();
                if(method==IdentifierMethod.NO_AUTHENICATION_REQUIRED){
                    byte [] data=new byte[]{05,00};
                    session.write(data);
                }else if(method==IdentifierMethod.USERNAME_PASSWORD){

                }else if(method==IdentifierMethod.GSSAPI){

                }else if(method==IdentifierMethod.RESERVED_FOR_PRIVATE_METHODS){

                }else {

                }
        }
    }

    /**
     * 当发送消息成功时调用这个方法，注意这里的措辞，发送成功之后，也就是说发送消息是不 能用这个方法的。
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
