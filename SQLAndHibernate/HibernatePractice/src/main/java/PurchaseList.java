import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Purchaselist")
public class PurchaseList {

    @EmbeddedId
    private PurchaseListKey purchaseListKey;

    @Column(name = "student_name",insertable=false, updatable=false, nullable = true, length = 500)
    private String studentName;

    @Column(name = "course_name",insertable=false, updatable=false, nullable = true, length = 500)
    private String courseName;

    @Column(name = "price", nullable = true)
    private int price;

    @Column(name = "subscription_date", nullable = true)
    private LocalDateTime subscriptionDate;

    public PurchaseListKey getPurchaseListKey() {
        return purchaseListKey;
    }

    public void setPurchaseListKey(PurchaseListKey purchaseListKey) {
        this.purchaseListKey = purchaseListKey;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PurchaseList that = (PurchaseList) object;
        return price == that.price && Objects.equals(purchaseListKey, that.purchaseListKey) && Objects.equals(studentName, that.studentName) && Objects.equals(courseName, that.courseName) && Objects.equals(subscriptionDate, that.subscriptionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseListKey, studentName, courseName, price, subscriptionDate);
    }

    @Override
    public String toString() {
        return "PurchaseList{" +
                "purchaseListKey=" + purchaseListKey +
                ", studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", price=" + price +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }
}
