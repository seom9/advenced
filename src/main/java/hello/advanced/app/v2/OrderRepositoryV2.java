package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId) {

        TraceStatus status = null;

        try {
            status = trace.beginSync(traceId,"OrderRepositoryV2.orderItem()");

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
