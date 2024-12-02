package backend.oop_cw.Model;

public class Customer {
    private int CusId;
    private String CusName;

    Customer(int CusId, String CusName) {
        this.CusId = CusId;
        this.CusName = CusName;
    }

    public int getCusId() {
        return CusId;
    }

    public void setCusId(int cusId) {
        CusId = cusId;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CusId=" + CusId +
                ", CusName='" + CusName + '\'' +
                '}';
    }
}
