package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemId) {
        // 저장 로직
        if (itemId.equals("ex")) {
            throw new IllegalStateException("exception"); // 표준 예외
        }
        sleep(1000); // 저장하는데 1초
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);

            } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
