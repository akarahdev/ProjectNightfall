package me.endistic.skyblock.utils;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Range implements Iterator<Integer>, Iterable<Integer> {
    int min = 0;
    int max = 0;
    int index = 0;

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
        this.index = min;
    }

    @Override
    public boolean hasNext() {
        return index <= max;
    }

    @Override
    public Integer next() {
        return index++;
    }

    @Override
    public Iterator<Integer> iterator() {
        return this;
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        for(this.index = this.min; this.index<this.max; this.index++) {
            action.accept(this.index);
        }
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Iterable.super.spliterator();
    }
}
