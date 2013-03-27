package org.apache.mina.proxyserver.socks5.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.net.Inet4Address;
import java.net.InetAddress;

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
        Socks5Message message = new Socks5Message();
        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);

        while (in.hasRemaining()) {
            buffer.put(in.get());
        }

        if (buffer.get(0) == 5) {//socks5协议
            if (buffer.position() < 4) {//IdentifierMethod
                message.setMessageType(MessageType.IdentifierRequest);
                switch (buffer.get(2)) {
                    /**
                     o  X'00' NO AUTHENTICATION REQUIRED
                     o  X'01' GSSAPI
                     o  X'02' USERNAME/PASSWORD
                     o  X'03' to X'7F' IANA ASSIGNED
                     o  X'80' to X'FE' RESERVED FOR PRIVATE METHODS
                     o  X'FF' NO ACCEPTABLE METHODS
                     */
                    case 0:
                        message.setIdentifierMethod(IdentifierMethod.NO_AUTHENICATION_REQUIRED);
                        break;
                    case 1:
                        message.setIdentifierMethod(IdentifierMethod.GSSAPI);
                        break;
                    case 2:
                        message.setIdentifierMethod(IdentifierMethod.USERNAME_PASSWORD);
                        break;
                    case 3:
                        message.setIdentifierMethod(IdentifierMethod.IANA_ASSIGNED);
                        break;
                    case 80:
                        message.setIdentifierMethod(IdentifierMethod.RESERVED_FOR_PRIVATE_METHODS);
                        break;

                }

            } else {
                message.setMessageType(MessageType.Socks5Request);
                switch (buffer.get(1)) {
                    /**
                     o  CMD
                     o  CONNECT X'01'
                     o  BIND X'02'
                     o  UDP ASSOCIATE X'03'
                     */
                    case 01:
                        message.setCmd(Cmd.CONNECT);
                        break;
                    case 02:
                        message.setCmd(Cmd.BIND);
                        break;
                    case 03:
                        message.setCmd(Cmd.UDP);
                        break;
                }

                switch (buffer.get(3)){
                    /**
                     o  ATYP   address type of following address
                     o  IP V4 address: X'01'
                     o  DOMAINNAME: X'03'
                     o  IP V6 address: X'04'
                     */
                    case 01:
                        message.setAddressType(AddressType.IP_V4);
                        byte[] ipv4=new byte[4];
                        buffer.get(ipv4,4,4);
                        message.setDstAddr(Inet4Address.getByAddress(ipv4));
                        break;
                    case 03:
                        byte [] domain=new byte[buffer.get(4)];
                        buffer.get(domain,5,buffer.get(4));
                        message.setDstAddr(InetAddress.getByName(domain.toString()));
                        message.setAddressType(AddressType.DOMAIN_NAME);
                        break;
                    case 04:
                        byte [] ipv6 = new byte[16];
                        buffer.get(ipv6,4,16);
                        message.setDstAddr(InetAddress.getByAddress(ipv6));
                        message.setAddressType(AddressType.IP_V6);
                        break;
                }

            }
        } else {
            out.write(message);
            return false;
        }
        out.write(message);
        return false;

    }
}
