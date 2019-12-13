package Day12;

import java.util.Objects;

class Quadruple<A extends Object> {
    private final A m1;
    private final A m2;
    private final A m3;
    private final A m4;

    public Quadruple(A m1, A m2, A m3, A m4) {
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.m4 = m4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quadruple<?> quadruple = (Quadruple<?>) o;
        return Objects.equals(m1, quadruple.m1) &&
                Objects.equals(m2, quadruple.m2) &&
                Objects.equals(m3, quadruple.m3) &&
                Objects.equals(m4, quadruple.m4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m1, m2, m3, m4);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\t").append(m1);
        sb.append("\t").append(m2);
        sb.append("\t").append(m3);
        sb.append("\t").append(m4);
        sb.append('}');
        return sb.toString();
    }
}
