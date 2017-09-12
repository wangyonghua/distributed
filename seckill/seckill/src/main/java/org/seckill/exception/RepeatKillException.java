package org.seckill.exception;

public class RepeatKillException extends SecKillException{
  public RepeatKillException(String message, Throwable cause) {
      super(message, cause);
  }

  public RepeatKillException(String message) {
      super(message);
  }
}