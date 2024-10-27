@Configuration
@EnableJpaAuditing(
  auditorAwareRef = "requestedByAuditorAware",
  dateTimeProviderRef = "requestedAtAuditorAware"
)
public class JpaAuditConfig {

}