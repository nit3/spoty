package com.spoty.app.cmd;

public class AdbCmdBuilder {
  private final StringBuilder command;

  public AdbCmdBuilder() {
    this.command = new StringBuilder();
    this.command.append("adb");
  }

  /**
   * This is terminal operation. Nothing will be added to command after this option
   *
   * @param app path to APK binary to install on the specified device
   * @return string command to execute directly into shell/cmd
   */
  public String installApp(String app) {
    this.command.append(" ");
    this.command.append("install");
    this.command.append(" ");
    this.command.append(app);
    return this.build();
  }

  /**
   * This is terminal operation. Nothing will be added to command after this option
   *
   * @param packageName package name for the application (ex. com.whatsapp)
   * @return string command to execute directly into shell/cmd
   */
  public String uninstallApp(String packageName) {
    this.command.append(" ");
    this.command.append("uninstall");
    this.command.append(" ");
    this.command.append(packageName);
    return this.build();
  }

  /**
   * This is terminal operation. Nothing will be added to command after this option
   *
   * @return string command to execute directly into shell/cmd
   */
  public String stopEmulator() {
    command.append(" ");
    command.append("emu");
    command.append(" ");
    command.append("kill");
    return this.build();
  }

  /**
   * Set the name of emulator you want to perform actions on.
   *
   * @param emulator emulator name to perform the command on (ex. emulator-5554) [required]
   * @return this class
   */
  @SuppressWarnings("UnusedReturnValue")
  public AdbCmdBuilder setEmulator(String emulator) {
    command.append(" ");
    command.append("-s");
    command.append(" ");
    command.append(emulator);
    return this;
  }

  /**
   * Check the boot animation status.
   * @return status of boot animation
   */
  public String checkBootAnimation() {
    command.append(" ");
    command.append("shell");
    command.append(" ");
    command.append("getprop");
    command.append(" ");
    command.append("init.svc.bootanim");
    return command.toString();
  }

  /**
   * Build the command.
   *
   * @return string command to execute directly into shell/cmd
   */
  public String build() {
    return this.command.toString();
  }

  /**
   * This is terminal operation. Nothing will be added to command after this option.
   *
   * @param deviceName emulator port number to perform the command on (ex. emulator-5554) [required]
   * @return string command to execute directly into shell/cmd
   */
  public String takeScreenshot(String deviceName, String fileName) {
    command.append(" ");
    command.append("-s");
    command.append(" ");
    command.append(deviceName);
    command.append(" ");
    command.append("exec-out");
    command.append(" ");
    command.append("screencap");
    command.append(" ");
    command.append("-p");
    command.append(" ");
    command.append(">");
    command.append(" ");
    command.append(fileName);
    return this.build();
  }

}
