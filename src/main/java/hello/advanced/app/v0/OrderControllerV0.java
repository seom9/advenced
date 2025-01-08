package hello.advanced.app.v0;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {

    private final OrderServiceV0 orderService;

    @GetMapping("v0/request")
    public String request(String itemId){
        orderService.orderItem(itemId);
        return "ok";
    }
    // 내가 왜 로그추적기를 만들고 있는 거지
    // 병목 및 예외 발생을 로그 추적으로 관리
    // 트랜잭션ID 와 깊이를 표현하는 방법은 기존 정보를 이어 받아야 함
