package question_1_crc;

public class CRCReceiver {

    private String Px;
    private String Gx;
    private String Rx;
    private String Qx;
    private String message;

    public CRCReceiver(String px, String gx, String rx, String qx, String message) {
        Px = px;
        Gx = gx;
        Rx = rx;
        Qx = qx;
        this.message = message;
    }

    public String getPx() {
        return Px;
    }

    public void setPx(String px) {
        Px = px;
    }

    public String getGx() {
        return Gx;
    }

    public void setGx(String gx) {
        Gx = gx;
    }

    public String getRx() {
        return Rx;
    }

    public void setRx(String rx) {
        Rx = rx;
    }

    public String getQx() {
        return Qx;
    }

    public void setQx(String qx) {
        Qx = qx;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
