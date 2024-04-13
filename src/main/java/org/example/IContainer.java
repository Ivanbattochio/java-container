package org.example;

public interface IContainer {
    <T> T get(String service) throws Exception;

    void set(String service) throws Exception;
}
