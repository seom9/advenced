package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId) {

        TraceStatus status = null;

        try {
            status = trace.begin("OrderRepositoryV1.orderItem()");

            // 저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("exception"); // 표준 예외
            }

            sleep(1000); // 저장하는데 1초
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);

            } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
