package com.cheatbreaker.common.generic;

public interface Visitor<T> {
    void accept(T t);
}
