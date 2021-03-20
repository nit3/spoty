package com.spoty.app.cmd;

import com.spoty.app.service.UserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CommandLine {
    private static final Logger log = LoggerFactory.getLogger(CommandLine.class);
    // for linux should be "/bin/bash". For windows should be "cmd.exe"
    private static final String cmd = "cmd.exe";

    // for linux should be "-ic". For windows should be "/c"
    private static final String interactive = "/c";

  /**
   * Execute the specified command using /bin/bash shell.
   *
   * @param command command to run in interactive shell
   * @return {true} if the command finished. {false} if the command got intercepted
   */
  public static boolean run(String command) {
    log.debug("executing command: " + command);
    try {
      ProcessBuilder builder = new ProcessBuilder(cmd, interactive, command);
      builder.redirectErrorStream(true);
      Process process = builder.start();
      // allow time for sub process to detach. Might need to adjust for slower servers
      process.waitFor(5, TimeUnit.SECONDS);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Execute the specified command using /bin/bash shell and return the output.
   * @param command command to run in interactive shell
   * @return output of the command
   */
  public static List<String> runWithResult(String command) {
    log.debug("executing command: " + command);
    try {
      ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-ic", command);
      builder.redirectErrorStream(true);
      Process process = builder.start();
      // allow time for sub process to detach. Might need to adjust for slower servers
      process.waitFor(5, TimeUnit.SECONDS);
      return IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8)
          .lines().skip(2).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}
