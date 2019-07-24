package com.flock.app.serializer;

public interface ISerializer<T> {
    String serialize(T t);
}
