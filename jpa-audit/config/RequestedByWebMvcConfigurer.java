@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class RequestedByWebMvcConfigurer implements WebMvcConfigurer {
  private final RequestedByInterceptor interceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addWebRequestInterceptor(interceptor);
  }
}