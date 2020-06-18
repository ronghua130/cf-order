package coffeeshop.external;

import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFallback implements PaymentService {

    @Override
    public void pay(Payment payment) {
        System.out.println("주문은 정상적으로 진행되었습니다. 결제는 잠깐만 기다려 주세요.");
    }

}