package hello.advanced.app.v2;


import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(TraceId traceId, String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderControllerV2.request");
            orderService.orderItem(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e); // 얘가 예외를 먹어버리고 정상 흐름으로 가져감
            throw e;    // 예외를 꼭 던져 주어야 한다
            // 로그는 애플리케이션 흐름에 영향을 주면 안 된다
        }
        return "ok";
    }
}

