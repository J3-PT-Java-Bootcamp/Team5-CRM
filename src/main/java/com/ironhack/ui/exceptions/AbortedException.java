package com.ironhack.ui.exceptions;

import com.ironhack.domain.exceptions.Team5CrmException;

public class AbortedException extends Team5CrmException {

  public AbortedException() {
  }

  public AbortedException(String message) {
    super(message);
  }

  public AbortedException(String message, Throwable cause) {
    super(message, cause);
  }

  public AbortedException(Throwable cause) {
    super(cause);
  }

  public AbortedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
