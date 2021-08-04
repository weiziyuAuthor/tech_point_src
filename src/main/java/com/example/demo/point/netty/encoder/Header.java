package com.example.demo.point.netty.encoder;

/**
 * @Auther ziyu.wei
 * @date 2021/7/29 10:14 上午
 */
public class Header {

    private byte tag;
    private byte encode;
    private byte encrypt;
    private byte extend1;
    private byte extend2;
    private String sessionId;
    private int length = 1024;

    private int command;

    public Header() {

    }

    public Header(String sessionId) {
        this.encode = 0;
        this.encrypt = 0;
        this.sessionId = sessionId;
    }

    public Header(byte tag, byte encode, byte encrypt, byte extend1, byte extend2,
                  String sessionId, int length, int command) {
        this.tag = tag;
        this.encode = encode;
        this.encrypt = encrypt;
        this.extend1 = extend1;
        this.extend2 = extend2;
        this.sessionId = sessionId;
        this.length = length;
        this.command = command;
    }

    public byte getTag() {
        return tag;
    }

    public void setTag(byte tag) {
        this.tag = tag;
    }

    public byte getEncode() {
        return encode;
    }

    public void setEncode(byte encode) {
        this.encode = encode;
    }

    public byte getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(byte encrypt) {
        this.encrypt = encrypt;
    }

    public byte getExtend1() {
        return extend1;
    }

    public void setExtend1(byte extend1) {
        this.extend1 = extend1;
    }

    public byte getExtend2() {
        return extend2;
    }

    public void setExtend2(byte extend2) {
        this.extend2 = extend2;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "Header{" +
                "tag=" + tag +
                ", encode=" + encode +
                ", encrypt=" + encrypt +
                ", extend1=" + extend1 +
                ", extend2=" + extend2 +
                ", sessionId='" + sessionId + '\'' +
                ", length=" + length +
                ", command=" + command +
                '}';
    }
}
