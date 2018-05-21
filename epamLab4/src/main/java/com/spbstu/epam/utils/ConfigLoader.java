package com.spbstu.epam.utils;

import org.aeonbits.owner.ConfigFactory;

import java.util.Objects;

public class ConfigLoader {
    private static TestConfig config;

    public static TestConfig config() {
        if (Objects.isNull(config)) {
            config = ConfigFactory.create(TestConfig.class);
        }
        return config;
    }
}