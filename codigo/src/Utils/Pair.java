package Utils;

public class Pair<N, T> {

    N v1;
    T v2;

    public Pair(N v1, T v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public N getV1() {
        return v1;
    }

    public T getV2() {
        return v2;
    }
}
