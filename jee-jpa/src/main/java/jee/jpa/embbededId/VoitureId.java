package jee.jpa.embbededId;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VoitureId implements Serializable {
    @GeneratedValue
    private long foo;

    @GeneratedValue
    private long bar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoitureId voitureId = (VoitureId) o;
        return foo == voitureId.foo &&
                bar == voitureId.bar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(foo, bar);
    }

    public long getFoo() {
        return foo;
    }

    public void setFoo(long foo) {
        this.foo = foo;
    }

    public long getBar() {
        return bar;
    }

    public void setBar(long bar) {
        this.bar = bar;
    }
}
