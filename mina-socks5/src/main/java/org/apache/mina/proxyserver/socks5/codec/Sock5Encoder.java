package org.apache.mina.proxyserver.socks5.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * Created with IntelliJ IDEA.
 * User: songjiao
 * Date: 13-3-27
 * Time: 上午2:26
 * To change this template use File | Settings | File Templates.
 */
public class Sock5Encoder extends ProtocolEncoderAdapter {

    /**
     * Encodes higher-level message objects into binary or protocol-specific data.
     * MINA invokes {@link #encode(org.apache.mina.core.session.IoSession, Object, org.apache.mina.filter.codec.ProtocolEncoderOutput)}
     * method with message which is popped from the session write queue, and then
     * the encoder implementation puts encoded messages (typically {@link org.apache.mina.core.buffer.IoBuffer}s)
     * into {@link org.apache.mina.filter.codec.ProtocolEncoderOutput}.
     *
     * @throws Exception if the message violated protocol specification
     */
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        System.out.println(message);
    }
}
