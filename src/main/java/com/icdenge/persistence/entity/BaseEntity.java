package com.icdenge.persistence.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Audited
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @CreatedDate
  @Column(columnDefinition = "TIMESTAMP")
  protected Instant createDate;

  @CreatedBy
  protected String createdBy;

  @LastModifiedDate
  @Column(columnDefinition = "TIMESTAMP")
  protected Instant lastModifiedDate;

  @LastModifiedBy
  protected String lastModifiedBy;

  @Version protected Integer version;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BaseEntity that)) return false;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
