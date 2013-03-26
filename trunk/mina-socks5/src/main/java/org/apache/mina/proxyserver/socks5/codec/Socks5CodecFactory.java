package org.apache.mina.proxyserver.socks5.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: songjiao
 * Date: 13-3-27
 * Time: 上午2:24
 * To change this template use File | Settings | File Templates.
 */
public class Socks5CodecFactory implements ProtocolCodecFactory {
    private final Sock5Encoder encoder;
    private final Socks5Decoder decoder;

    public Socks5CodecFactory() {
        this.encoder=new Sock5Encoder();
        this.decoder=new Socks5Decoder();
    }

    /**
     * Returns a new (or reusable) instance of {@link org.apache.mina.filter.codec.ProtocolEncoder} which
     * encodes message objects into binary or protocol-specific data.
     */
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Returns a new (or reusable) instance of {@link org.apache.mina.filter.codec.ProtocolDecoder} which
     * decodes binary or protocol-specific data into message objects.
     */
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
