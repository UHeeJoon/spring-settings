@Component
@RequiredArgsConstructor
public class RequestedByAuditorAware implements AuditorAware<String> {

  private final ApplicationContext ctx;

  @Override
  public Optional<String> getCurrentAuditor() {
    try {
      return Optional.of(ctx.getBean(RequestedByProvider.class)).flatMap(RequestedByProvider::getRequestBy);
    } catch (Exception e) {
      return Optional.of("system");
    }
  }
}