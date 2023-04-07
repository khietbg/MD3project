package ra.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

public class Invoice implements Serializable {
    private User invoiceUser;
    private List<CartItem> invoiceCart;
    private String invoiceStatus;
    private String Feedback;

    public Invoice() {
    }

    public Invoice(User invoiceUser, List<CartItem> invoiceCart, String invoiceStatus, String feedback) {
        this.invoiceUser = invoiceUser;
        this.invoiceCart = invoiceCart;
        this.invoiceStatus = invoiceStatus;
        Feedback = feedback;
    }

    public User getInvoiceUser() {
        return invoiceUser;
    }

    public void setInvoiceUser(User invoiceUser) {
        this.invoiceUser = invoiceUser;
    }

    public List<CartItem> getInvoiceCart() {
        return invoiceCart;
    }

    public void setInvoiceCart(List<CartItem> invoiceCart) {
        this.invoiceCart = invoiceCart;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Invoice.class.getSimpleName() + "[", "]")
                .add("invoiceUser=" + invoiceUser)
                .add("invoiceCart=" + invoiceCart)
                .add("invoiceStatus='" + invoiceStatus + "'")
                .add("Feedback='" + Feedback + "'")
                .toString();
    }
}
