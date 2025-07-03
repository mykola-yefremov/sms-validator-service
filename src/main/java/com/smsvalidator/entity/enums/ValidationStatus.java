package com.smsvalidator.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ValidationStatus {
  VALID("Message is valid"),
  INVALID("Message contains invalid content or format"),
  SPAM("Message identified as spam"),
  PENDING("Message is being processed"),
  FAILED("Validation failed due to system error");

  private final String description;
}
