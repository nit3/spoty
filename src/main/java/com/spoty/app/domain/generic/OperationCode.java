package com.spoty.app.domain.generic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

public enum OperationCode {
  GENERIC_ERROR(100),
  SUCCESS(0),
  INVALID_MODEL(101),
  STORAGE_FULL(102),
  GENERIC_HARDWARE_ERROR(103),
  GENERIC_NETWORK_ERROR(104),
  STORAGE_ERROR(105);

  private final int code;

  private OperationCode(int code) {
    this.code = code;
  }

  /**
   * factory method for jackson.
   * @param value enum code
   * @return enum object
   */
  @JsonCreator
  public static OperationCode fromValue(int value) {
    Optional<OperationCode> result = Arrays
        .stream(OperationCode.values())
        .filter(x -> x.code == value).findFirst();
    return result.orElse(OperationCode.GENERIC_ERROR);
  }

  @JsonValue
  public int value() {
    return this.code;
  }

}
