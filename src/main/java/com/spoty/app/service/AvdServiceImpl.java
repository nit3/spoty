package com.spoty.app.service;

import com.spoty.app.cmd.AdbCmdBuilder;
import com.spoty.app.cmd.AvdManagerCmdBuilder;
import com.spoty.app.cmd.CommandLine;
import com.spoty.app.cmd.EmulatorCmdBuilder;
import com.spoty.app.domain.Device;
import com.spoty.app.domain.generic.OperationCode;
import com.spoty.app.domain.generic.OperationResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvdServiceImpl implements AvdService{
    private final Logger log = LoggerFactory.getLogger(AvdServiceImpl.class);

    @Override
    public OperationResult<Device> create(Device device) {
        log.debug("Running create device job for device: " + device.getName());
        final AvdManagerCmdBuilder builder = new AvdManagerCmdBuilder("create");
        builder.setForce();
        builder.setName(device.getEmulatorName());
        builder.setImage("system-images;android-29;google_apis_playstore;x86");
        boolean executed = CommandLine.run(builder.build());
        String message = "Created device : " + device.getName();
        if(!executed){
            message = "Couldn't create device: " + device.getName();
            log.error(message);
            return new OperationResult<Device>().setCode(OperationCode.GENERIC_ERROR)
                .setMessage(message)
                .setValue(device);
        }
        log.debug(message);
        return new OperationResult<Device>().setCode(OperationCode.SUCCESS)
            .setMessage(message)
            .setValue(device);
    }

    @Override
    public OperationResult<Device> start(Device device) {
        final EmulatorCmdBuilder builder = new EmulatorCmdBuilder();
        builder.setAvd(device.getEmulatorName());
        builder.disableBootAnimation();
        builder.swiftShader();
        builder.setPortRange(device.getPortNumber(), device.getPortNumber() + 1);
        boolean executed = CommandLine.run(builder.build());
        String message = "Created device : " + device.getName();
        this.waitForStatus(device, "stopped");
        if(!executed){
            message = "Couldn't create device: " + device.getName();
            log.error(message);
            return new OperationResult<Device>().setCode(OperationCode.GENERIC_ERROR)
                .setMessage(message)
                .setValue(device);
        }
        log.debug(message);
        return new OperationResult<Device>().setCode(OperationCode.SUCCESS)
            .setMessage(message)
            .setValue(device);
    }

    private boolean waitForStatus(Device device, String status)
    {
        final AdbCmdBuilder builder = new AdbCmdBuilder();
        builder.setEmulator("emulator-"+device.getPortNumber());
        builder.checkBootAnimation();
        List<String> result ;
        boolean found = false;
        do
        {
            result = CommandLine.runWithResult(builder.build());
            for (String s: result) {
                System.out.println(s);
                found = s.contains(status);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!found);
        return true;
    }

    @Override
    public OperationResult<Device> delete(Device device) {
        final AvdManagerCmdBuilder builder = new AvdManagerCmdBuilder("delete");
        builder.setName(device.getEmulatorName());
        boolean executed = CommandLine.run(builder.build());
        waitForStatus(device, "not found");
        return null;
    }

    @Override
    public OperationResult<Device> get(Device device) {
        return null;
    }

    @Override
    public OperationResult<Device> stop(Device device) {
        AdbCmdBuilder builder = new AdbCmdBuilder();
        builder.setEmulator("emulator-"+device.getPortNumber());
        boolean executed = CommandLine.run(builder.stopEmulator());
        if (executed) {
            return new OperationResult<Device>()
                .setCode(OperationCode.SUCCESS)
                .setMessage("Device stopped successfully");
        }
        return new OperationResult<Device>()
            .setCode(OperationCode.GENERIC_ERROR)
            .setMessage("Couldn't stop the device");
    }

    @Override
    public OperationResult<Device> installApp(Device device, String app) {
        return null;
    }

    @Override
    public OperationResult<Device> uninstallApp(Device device, String packageName) {
        return null;
    }

    @Override
    public ResponseEntity<byte[]> takeScreenshot(Device device) {
        return null;
    }

    @Override
    public OperationResult<Device> recordScreen(Device device) {
        return null;
    }
}
