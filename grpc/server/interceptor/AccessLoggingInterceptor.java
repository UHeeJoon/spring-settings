@Component
@Slf4j
public class AccessLoggingInterceptor implements ServerInterceptor {
  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
    ServerCall<ReqT, RespT> call,
    Metadata headers,
    ServerCallHandler<ReqT, RespT> next) {

    Instant startTime = Instant.now();
    String clientIP = headers.get(Metadata.Key.of("X-Forwarded-For", Metadata.ASCII_STRING_MARSHALLER));

    log.info("{} - - [{}] \"{}\" {}", clientIP, startTime, call.getMethodDescriptor().getFullMethodName(), headers);

    ServerCall.Listener<ReqT> listener = next.startCall(call, headers);
    return new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(listener) {

      @Override
      public void onComplete() {
        Duration duration = Duration.between(startTime, Instant.now());
        String clientIP = headers.get(Metadata.Key.of("X-Forwarded-For", Metadata.ASCII_STRING_MARSHALLER));

        log.info("{} - - [{}] \"{}\" {} {}ms",
          clientIP != null ? clientIP : "-", Instant.now(), call.getMethodDescriptor().getFullMethodName(),
          headers, duration.toMillis());

        super.onComplete();
      }
    };
  }
}
