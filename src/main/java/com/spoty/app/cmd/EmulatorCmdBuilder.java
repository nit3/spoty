package com.spoty.app.cmd;

public class EmulatorCmdBuilder {
  private final StringBuilder command;
  //For linux it should be "shopt -u huponexit". For windows it should be "start"
  private final String detachCmd = "start /min";
  //TODO: add required options validation
  public EmulatorCmdBuilder() {
    this.command = new StringBuilder();
    this.command.append(detachCmd);
    // for linux environment only
    //    this.command.append(";");
    this.command.append(" ");
    this.command.append("emulator");
  }

  /**
   * reset the user data image (copy it from initdata) [not recommended].
   *
   * @return this class
   */
  public EmulatorCmdBuilder setWipe() {
    this.command.append(" ");
    this.command.append("-wipe-data");
    return this;
  }

  /**
   * Set the name virtual device (ex. Pixel)
   *
   * @param avd use a specific android virtual device [required]
   * @return this class
   */
  @SuppressWarnings("UnusedReturnValue")
  public EmulatorCmdBuilder setAvd(String avd) {
    this.command.append(" ");
    this.command.append("@");
    this.command.append(avd);
    return this;
  }

  /**
   * Recommended range any consecutive ports between [5555,5586] (ex. 5555,5556) [required]
   *
   * @param start start of the ports range (ex. 5555)
   * @param end   end of the ports range (ex. 5556)
   * @return this class
   */
  @SuppressWarnings("UnusedReturnValue")
  public EmulatorCmdBuilder setPortRange(int start, int end) {
    this.command.append(" ");
    this.command.append("-ports");
    this.command.append(" ");
    this.command.append(start).append(",").append(end);
    return this;
  }

  /**
   * whether to show boot animation or not.
   *
   * @return this class
   */
  @SuppressWarnings("UnusedReturnValue")
  public EmulatorCmdBuilder disableBootAnimation() {
    this.command.append(" ");
    this.command.append("-no-boot-anim");
    return this;
  }

  public EmulatorCmdBuilder swiftShader()
  {
      this.command.append(" ");
      this.command.append("-gpu");
      this.command.append(" ");
      this.command.append("swiftshader_indirect");
      return this;
  }

  /**
   * Set the skin for emulator (only cosmetic).
   *
   * @param skin select a given skin
   * @return this class
   */
  public EmulatorCmdBuilder setSkin(String skin) {
    this.command.append(" ");
    this.command.append("-skin");
    this.command.append(" ");
    this.command.append(skin);
    return this;
  }

  /**
   * Set http proxy for emulator to use.
   *
   * @param proxy make TCP connections through a HTTP/HTTPS proxy
   * @return this class
   */
  public EmulatorCmdBuilder setProxy(String proxy) {
    this.command.append(" ");
    this.command.append("-http-proxy");
    this.command.append(" ");
    this.command.append(proxy);
    return this;
  }

  /**
   * using host gpu. [required]
   *
   * @return this class
   */
  @SuppressWarnings("UnusedReturnValue")
  public EmulatorCmdBuilder useHostGpu() {
    this.command.append(" ");
    this.command.append("-gpu host");
    return this;
  }

  /**
   * Build the command.
   *
   * @return string command to execute directly into shell/cmd
   */
  public String build() {
    return this.command.toString();
  }
}
