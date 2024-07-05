package com.maua.roubit.shared.infra.config;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvConfigListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(EnvConfigListener.class);

    @Override
    public void onApplicationEvent(@Nullable ApplicationEnvironmentPreparedEvent event) {
        File envFile = new File(".env");
        if (!envFile.exists()) {
            logger.warn(".env file not found, skipping environment configuration");
            return;
        }

        try (FileInputStream fis = new FileInputStream(envFile)) {
            Properties properties = new Properties();
            properties.load(fis);
            properties.forEach((key, value) -> System.setProperty(key.toString(), value.toString()));
        } catch (IOException e) {
            logger.error("Could not load .env file", e);
            throw new RuntimeException("Could not load .env file", e);
        }
    }
}
