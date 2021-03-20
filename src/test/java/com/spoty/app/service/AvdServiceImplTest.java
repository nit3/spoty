package com.spoty.app.service;

import com.spoty.app.domain.Device;
import com.spoty.app.domain.generic.OperationResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AvdServiceImplTest {

    @Autowired
    AvdService avdService;

    @Test
    void create() {
        Device device = new Device();
        device.setName("Work mobile");
        device.setPortNumber(7667);
        device.setEmulatorName("test-6666");
        device.setModel("system-images;android-29;google_apis_playstore;x86");
        OperationResult<Device> result = avdService.create(device);
        assertTrue(result.isSuccessful());
    }

    @Test
    void start() {
        Device device = new Device();
        device.setName("Work mobile");
        device.setPortNumber(7667);
        device.setEmulatorName("test-9999");
        device.setModel("system-images;android-29;google_apis_playstore;x86");
        OperationResult<Device> result = avdService.create(device);
        assertTrue(result.isSuccessful());
        OperationResult<Device> startResult = avdService.start(device);
        assertTrue(startResult.isSuccessful());
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void stop() {
    }

    @Test
    void installApp() {
    }

    @Test
    void uninstallApp() {
    }

    @Test
    void takeScreenshot() {
    }

    @Test
    void recordScreen() {
    }
}
