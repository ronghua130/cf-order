package coffeeshop;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long  orderId;
    private Long coffeeId;
    private Integer qty;
    private String coffeeName;
    private Float price;

    @PostPersist
    public void onPostPersist() throws InterruptedException {
        OrderPlaced orderPlaced = new OrderPlaced();
        orderPlaced.setOrderId(this.getOrderId());
        BeanUtils.copyProperties(this, orderPlaced);
        orderPlaced.setCoffeeId(this.getCoffeeId());
        orderPlaced.setCoffeeName(this.getCoffeeName());
        orderPlaced.setPrice(this.getPrice());
        orderPlaced.setQty(this.getQty());
        orderPlaced.setOrderStatus("Order Placed");
        orderPlaced.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
        //Thread.sleep(1000);

        coffeeshop.external.Payment payment = new coffeeshop.external.Payment();
        payment.setOrderId(this.getOrderId());
        payment.setCoffeeId(this.getCoffeeId());
        payment.setCoffeeName(this.getCoffeeName());
        payment.setPrice(this.getPrice());
        payment.setQty(this.getQty());
        payment.setTotalAmount(this.getPrice() * this.getQty());
        payment.setOrderStatus(orderPlaced.getOrderStatus());
        payment.setPaymentStatus("Paid");

        Application.applicationContext.getBean(coffeeshop.external.PaymentService.class)
            .pay(payment);

    }

    @PreRemove
    public void onPreRemove(){
        OrderCanceled orderCanceled = new OrderCanceled();
        orderCanceled.setOrderId(this.getOrderId());
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.setStatus("Order Canceled");
        orderCanceled.publishAfterCommit();


    }


    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCoffeeId() {
        return coffeeId;
    }
    public void setCoffeeId(Long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getCoffeeName() {
        return coffeeName;
    }
    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

}
