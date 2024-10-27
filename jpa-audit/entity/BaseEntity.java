@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  @CreatedDate
  @Column(name = "CREATED_AT", updatable = false)
  private LocalDateTime createdAt;

  @CreatedBy
  @Column(name = "CREATED_BY", updatable = false)
  private String createdBy;

}