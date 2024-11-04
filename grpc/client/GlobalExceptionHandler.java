@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(StatusRuntimeException.class)
  public ResponseEntity<String> handleGrpcException(StatusRuntimeException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body("gRPC error occurred: " + ex.getMessage());
  }
}
