public interface AuthenticationHolder {
  Optional<Authentication> getAuthentication();
  void setAuthentication(Authentication authentication);
}