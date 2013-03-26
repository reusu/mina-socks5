package org.apache.mina.proxyserver.socks5.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * Created with IntelliJ IDEA.
 * User: songjiao
 * Date: 13-3-27
 * Time: 上午2:26
 * To change this template use File | Settings | File Templates.
 */
public class Socks5Decoder extends CumulativeProtocolDecoder {
    /**
     * Implement this method to consume the specified cumulative buffer and
     * decode its content into message(s).
     *
     * @param in the cumulative buffer
     * @return <tt>true</tt> if and only if there's more to decode in the buffer
     *         and you want to have <tt>doDecode</tt> method invoked again.
     *         Return <tt>false</tt> if remaining data is not enough to decode,
     *         then this method will be invoked again when more data is cumulated.
     * @throws Exception if cannot decode <tt>in</tt>.
     */
    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        IoBuffer buffer = IoBuffer. allocate (100).setAutoExpand( true );
        while (in.hasRemaining()) {
            byte b = in.get();
            buffer.put(b);

        }
        SmsObject smsObject = new SmsObject();
        smsObject.setSender(sender.split( ": " )[1]);
        smsObject.setReceiver(receiver.split( ": " )[1]);
        smsObject.setMessage(sms);
        out.write(smsObject);
        return false ;
    }
}
