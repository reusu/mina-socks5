package org.apache.mina.proxyserver.socks5.codec;

import java.net.InetAddress;

/**
 * Created with IntelliJ IDEA.
 * User: SongJiao
 * Date: 13-3-27
 * Time: 下午1:41
 * The client connects to the server, and sends a version
 * identifier/method selection message:
 * <p/>
 * +----+----------+----------+
 * |VER | NMETHODS | METHODS  |
 * +----+----------+----------+
 * | 1  |    1     | 1 to 255 |
 * +----+----------+----------+
 * <p/>
 * The VER field is set to X'05' for this version of the protocol.  The
 * NMETHODS field contains the number of method identifier octets that
 * appear in the METHODS field.
 * <p/>
 * The server selects from one of the methods given in METHODS, and
 * sends a METHOD selection message:
 * <p/>
 * +----+--------+
 * |VER | METHOD |
 * +----+--------+
 * | 1  |   1    |
 * +----+--------+
 * <p/>
 * If the selected METHOD is X'FF', none of the methods listed by the
 * client are acceptable, and the client MUST close the connection.
 * <p/>
 * The values currently defined for METHOD are:
 * <p/>
 * o  X'00' NO AUTHENTICATION REQUIRED
 * o  X'01' GSSAPI
 * o  X'02' USERNAME/PASSWORD
 * o  X'03' to X'7F' IANA ASSIGNED
 * o  X'80' to X'FE' RESERVED FOR PRIVATE METHODS
 * o  X'FF' NO ACCEPTABLE METHODS
 * <p/>
 * The client and server then enter a method-specific sub-negotiation.
 * <p/>
 * <p/>
 * <p/>
 * The SOCKS request is formed as follows:
 * <p/>
 * +----+-----+-------+------+----------+----------+
 * |VER | CMD |  RSV  | ATYP | DST.ADDR | DST.PORT |
 * +----+-----+-------+------+----------+----------+
 * | 1  |  1  | X'00' |  1   | Variable |    2     |
 * +----+-----+-------+------+----------+----------+
 * <p/>
 * Where:
 * <p/>
 * o  VER    protocol version: X'05'
 * o  CMD
 * o  CONNECT X'01'
 * o  BIND X'02'
 * o  UDP ASSOCIATE X'03'
 * o  RSV    RESERVED
 * o  ATYP   address type of following address
 * o  IP V4 address: X'01'
 * o  DOMAINNAME: X'03'
 * o  IP V6 address: X'04'
 * o  DST.ADDR       desired destination address
 * o  DST.PORT desired destination port in network octet
 * order
 */
public class Socks5Message {
    private Cmd cmd;
    private AddressType addressType;
    private InetAddress DstAddr;

    public Cmd getCmd() {
        return cmd;
    }

    public void setCmd(Cmd cmd) {
        this.cmd = cmd;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public InetAddress getDstAddr() {
        return DstAddr;
    }

    public void setDstAddr(InetAddress dstAddr) {
        DstAddr = dstAddr;
    }

    public int getDstPort() {
        return DstPort;
    }

    public void setDstPort(int dstPort) {
        DstPort = dstPort;
    }

    private int DstPort;


    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    private MessageType messageType;

    public IdentifierMethod getIdentifierMethod() {
        return identifierMethod;
    }

    public void setIdentifierMethod(IdentifierMethod identifierMethod) {
        this.identifierMethod = identifierMethod;
    }

    private IdentifierMethod identifierMethod;


}

