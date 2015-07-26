package com.m91snik.coastlength.model;

/**
 * Created by m91snik on 20.07.15.
 */
public class Movement {
    final Direction prev;
    final Direction next;

    public Movement(Direction prev, Direction next) {
        this.prev = prev;
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movement movement = (Movement) o;

        if (next != movement.next) return false;
        if (prev != movement.prev) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prev != null ? prev.hashCode() : 0;
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }
}


