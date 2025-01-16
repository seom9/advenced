package hello.advanced.app.v1;


import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderControllerV1.request");
            orderService.orderItem(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e); // 얘가 예외를 먹어버리고 정상 흐름으로 가져감
            throw e;    // 예외를 꼭 던져 주어야 한다
            // 로그는 애플리케이션 흐름에 영향을 주면 안 된다
        }
        return "ok";
    }
}

    // 만약 exception 이 터졌을 때 그냥 예외처리 해 버리는 것이 아니라
    // trace.end 까지 호출될 수 있도록 구축
    // 코드가 지저분해지고 있는 상황