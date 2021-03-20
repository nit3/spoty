package com.spoty.app.domain.generic;

import lombok.Getter;

public class OperationResult<T> {

  @Getter
  private T value;

  @Getter
  private String message;

  @Getter
  private int code;

  public OperationResult<T> setValue(T value) {
    this.value = value;
    return this;
  }

  public OperationResult<T> setMessage(String msg) {
    this.message = msg;
    return this;
  }

  public OperationResult<T> setCode(OperationCode code) {
    this.code = code.value();
    return this;
  }

  public boolean isSuccessful() {
    return this.code == OperationCode.SUCCESS.value();
  }
}
