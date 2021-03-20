package com.spoty.app.cmd;

public class AvdManagerCmdBuilder {
  private final StringBuilder command;
  //For linux should be "'no'" for windows should be "no"
  private final String createProfileAnswer = "no";

  /**
   * Command build for avdmanager.
   *
   * @param action type of action available options [create,delete]
   */
  public AvdManagerCmdBuilder(String action) {
    this.command = new StringBuilder();
    if ("create".equals(action)) {
      createAction();
    } else if ("delete".equals(action)) {
      deleteAction();
    }
  }

  private void createAction() {
    this.command.append("echo "+createProfileAnswer+" |");
    this.command.append(" ");
    this.command.append("avdmanager create avd");
  }

  private void deleteAction() {
    this.command.append("avdmanager delete avd");
  }

  /**
   * Forces creation (overwrites an existing AVD).
   *
   * @return this class
   */
  @SuppressWarnings("UnusedReturnValue")
  public AvdManagerCmdBuilder setForce() {
    this.command.append(" ");
    this.command.append("--force");
    return this;
  }

  /**
   * Set the name of AVD you want to perform actions on.
   *
   * @param name Name of the AVD. [required]
   * @return this class
   */
  public AvdManagerCmdBuilder setName(String name) {
    this.command.append(" ");
    this.command.append("-n");
    this.command.append(" ");
    this.command.append(name);
    return this;
  }

  /**
   * Set SD card for AVD.
   *
   * @param sdcard Path to a shared SD card image, or size of a new sdcard for
   *               the new AVD.
   * @return this class
   */
  @SuppressWarnings("UnusedReturnValue")
  public AvdManagerCmdBuilder setSdcard(String sdcard) {
    this.command.append(" ");
    this.command.append("-c");
    this.command.append(" ");
    this.command.append(sdcard);
    return this;
  }

  /**
   * Set system image for AVD.
   *
   * @param image Package path of the system image for this AVD (e.g.
   *              'system-images;android-19;google_apis;x86') [required].
   * @return this class
   */
  @SuppressWarnings("UnusedReturnValue")
  public AvdManagerCmdBuilder setImage(String image) {
    this.command.append(" ");
    this.command.append("-k");
    this.command.append(" ");
    this.command.append("\"");
    this.command.append(image);
    this.command.append("\"");
    return this;
  }

  /**
   * This is different from Device model class.
   *
   * @param device The optional device definition to use. Can be a device index
   *               or id.
   * @return this class
   */
  public AvdManagerCmdBuilder setDevice(String device) {
    this.command.append(" ");
    this.command.append("--device");
    this.command.append(" ");
    this.command.append("\"");
    this.command.append(device);
    this.command.append("\"");
    return this;
  }

  /**
   * Build the command.
   *
   * @return string command to execute directly into shell/cmd
   */
  public String build() {
    return command.toString();
  }
}
