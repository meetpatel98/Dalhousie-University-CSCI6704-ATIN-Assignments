package question_1_crc;

public class CRCSender {

    private String Mx;
    private String Gx;
    private String MDashX;
    private String quotient;
    private String remainder;
    private String Px;

    public CRCSender(String mx, String gx, String MDashX, String quotient, String remainder, String px) {
        Mx = mx;
        Gx = gx;
        this.MDashX = MDashX;
        this.quotient = quotient;
        this.remainder = remainder;
        Px = px;
    }

    public String getMx() {
        return Mx;
    }

    public void setMx(String mx) {
        Mx = mx;
    }

    public String getGx() {
        return Gx;
    }

    public void setGx(String gx) {
        Gx = gx;
    }

    public String getMDashX() {
        return MDashX;
    }

    public void setMDashX(String MDashX) {
        this.MDashX = MDashX;
    }

    public String getQuotient() {
        return quotient;
    }

    public void setQuotient(String quotient) {
        this.quotient = quotient;
    }

    public String getRemainder() {
        return remainder;
    }

    public void setRemainder(String remainder) {
        this.remainder = remainder;
    }

    public String getPx() {
        return Px;
    }

    public void setPx(String px) {
        Px = px;
    }
}
