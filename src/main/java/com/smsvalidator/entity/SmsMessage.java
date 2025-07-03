package com.smsvalidator.entity;

import com.smsvalidator.entity.enums.ValidationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "sms_messages",
    indexes = {
      @Index(name = "idx_sms_phone_number", columnList = "phoneNumber"),
      @Index(name = "idx_sms_status", columnList = "status"),
      @Index(name = "idx_sms_created_at", columnList = "createdAt"),
      @Index(name = "idx_sms_status_created_at", columnList = "status, createdAt")
    })
@EntityListeners(AuditingEntityListener.class)
public class SmsMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Phone number cannot be blank")
  @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "Phone number must be in international format")
  @Column(name = "phone_number", nullable = false, length = 20)
  private String phoneNumber;

  @NotBlank(message = "SMS text cannot be blank")
  @Size(max = 1600, message = "SMS text cannot exceed 1600 characters")
  @Column(name = "text", nullable = false, columnDefinition = "TEXT")
  private String text;

  @NotNull(message = "Validation status cannot be null")
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  private ValidationStatus status;

  @Size(max = 1000, message = "Validation details cannot exceed 1000 characters")
  @Column(name = "validation_details", columnDefinition = "TEXT")
  private String validationDetails;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  @Version
  @Column(name = "version", nullable = false)
  private Long version;
}
