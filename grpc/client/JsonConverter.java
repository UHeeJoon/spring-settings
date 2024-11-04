public class JsonConverter {
  public static String toJson(MessageOrBuilder messageOrBuilder) throws IOException {
    return JsonFormat.printer().print(messageOrBuilder);
  }

  public static <T extends Message> String toJsonList(List<T> responseList) throws InvalidProtocolBufferException {
    List<String> jsonList = new ArrayList<>();

    for (MessageOrBuilder response : responseList) {
      String json = JsonFormat.printer().print(response);
      jsonList.add(json);
    }

    return "[" + String.join(",", jsonList) + "]";
  }

  public static Message fromJson(String json) throws IOException {
    Message.Builder structBuilder = Struct.newBuilder();
    JsonFormat.parser().ignoringUnknownFields().merge(json, structBuilder);
    return structBuilder.build();
  }
}
