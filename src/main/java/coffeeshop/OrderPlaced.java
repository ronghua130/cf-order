package coffeeshop;

public class OrderPlaced extends AbstractEvent {

    private Long orderId;
    private Long coffeeId;
    private Integer qty;
    private String coffeeName;
    private Float price;
    private String orderStatus;


    public OrderPlaced(){
        super();
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

    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
