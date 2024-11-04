@Configuration
public class GlobalClientInterceptorConfiguration {

  @GrpcGlobalClientInterceptor
  AuthenticationInterceptor authenticationInterceptor() {
    return new AuthenticationInterceptor();
  }

  @GrpcGlobalClientInterceptor
  LoggingGrpcInterceptor loggingGrpcInterceptor() {
    return new LoggingGrpcInterceptor();
  }
}