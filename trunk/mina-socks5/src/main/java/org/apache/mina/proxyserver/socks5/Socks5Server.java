package org.apache.mina.proxyserver.socks5;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.proxyserver.socks5.codec.Socks5CodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 * User: songjiao
 * Date: 13-3-27
 * Time: 上午1:23
 *
 */
public class Socks5Server {
    private static final int PORT=1080;//监听1080端口
    public static void main(String args[]) throws IOException {

        NioSocketAcceptor acceptor=new NioSocketAcceptor(); //新建accptor来接受TCP/IP连接


        //添加socks5编码解码过滤器
        acceptor.getFilterChain().addLast("socks5Codec",new ProtocolCodecFilter(new Socks5CodecFactory(encoder)));

        //设置socks5业务处理Handler
        Socks5ServerIoHandler handler=new Socks5ServerIoHandler();
        acceptor.setHandler(handler);

        acceptor.bind(new InetSocketAddress(PORT));   //绑定端口
    }
}
