@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class MutableBaseEntity extends BaseEntity{

  @LastModifiedDate
  @Column(name = "MODIFIED_AT")
  private LocalDateTime modifiedAt;

  @LastModifiedBy
  @Column(name = "MODIFIED_BY")
  private String modifiedBy;
}