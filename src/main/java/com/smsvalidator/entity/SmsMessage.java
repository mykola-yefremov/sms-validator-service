package com.smsvalidator.entity;

import com.smsvalidator.entity.enums.ValidationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sms_message")
public class SmsMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long smsId;

  @NotNull private String phoneNumber;
  @NotNull private String text;

  @NotNull
  @Enumerated(EnumType.STRING)
  private ValidationStatus status;

  @NotNull private Instant createdAt;
}
