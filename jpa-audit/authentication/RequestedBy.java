@Getter
public class RequestedBy implements Authentication {
    private final String requestedBy;

    public RequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
}
