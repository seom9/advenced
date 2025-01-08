package hello.advanced.trace;

import java.util.UUID;

// 기반 데이터 트랜잭션 아이디랑 level 를 가지고 잇는 것
public class TraceId {

    private String id;
    private int level; // 얼마나 깊은 트랜잭션인가 구분

    public TraceId(String id, int level) {
        this.id = createId();
        this.level = 0;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
        // 유효 아이디
    }

    private TraceId createNextId() {
        return new TraceId(id, level + 1);
        // traceId 는 똑같고 레벨은 하나 증가
    }

    private TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public int getLevel() {
        return level;
    }

    public String getId() {
        return id;
    }
}
