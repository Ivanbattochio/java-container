package org.example;

import java.lang.reflect.*;
import java.util.*;

import static java.util.Objects.isNull;

public class Container implements IContainer {

    Map<String, Map<String, Object>> services = new HashMap<>();

    @Override
    public Object get(String service) throws Exception {
        if (!this.services.containsKey(service)) {
            throw new Exception(service + " was never setted");

        }
        Map<String, Object> serviceMap = this.services.get(service);
        if (!isNull(serviceMap.get(service))) {
            return serviceMap.get("class");
        }

        Class<?> serviceClass = Class.forName(service);

        List<Object> dependencies = new ArrayList<>();
        for (Parameter param : Arrays.stream(serviceClass.getConstructors()).findFirst().get().getParameters()) {
            if (param.getClass().isPrimitive()) {
                continue;
            }

            if (isNull(this.services.get(param.getType().getName())) || isNull(this.services.get(param.getType().getName()).get("class"))) {
                this.get(param.getType().getName());
            }
            dependencies.add(this.services.get(param.getType().getName()).get("class"));
        }

        serviceMap.put("class", Arrays.stream(serviceClass.getConstructors()).findFirst().get().newInstance(dependencies.toArray()));

        return serviceMap.get("class");
    }

    @Override
    public void set(String service) throws Exception {
        if (this.services.containsKey(service)) {
            throw new Exception("already setted " + service + " service");
        }

        Map<String, Object> serviceMap = new HashMap<>();
        serviceMap.put("class", null);

        this.services.put(service, serviceMap);
    }
}
