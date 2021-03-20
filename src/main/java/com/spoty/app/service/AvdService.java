package com.spoty.app.service;

import com.spoty.app.domain.Device;
import com.spoty.app.domain.generic.OperationResult;
import org.springframework.http.ResponseEntity;

public interface AvdService {


    OperationResult<Device> create(Device device);

    OperationResult<Device> start(Device device);

    OperationResult<Device> delete(Device device);

    OperationResult<Device> get(Device device);

    OperationResult<Device> stop(Device device);

    OperationResult<Device> installApp(Device device, String app);

    OperationResult<Device> uninstallApp(Device device, String packageName);

    ResponseEntity<byte[]> takeScreenshot(Device device);

    OperationResult<Device> recordScreen(Device device);
}
