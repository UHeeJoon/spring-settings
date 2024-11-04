package api.grpc.global.utils;

import com.google.protobuf.Timestamp;

import java.util.Date;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @since :2024-11-04 오전 12:25
 */
public class TimestampConverter {
  public static Date fromProto(Timestamp timestamp) {
    long milliseconds = timestamp.getSeconds() * 1000 + timestamp.getNanos() / 1000000;
    return new Date(milliseconds);
  }

  public static Timestamp toProto(Date date) {
    Timestamp.Builder timestampBuilder = Timestamp.newBuilder();
    timestampBuilder.setSeconds(date.getTime() / 1000); // 초 단위로 변환
    timestampBuilder.setNanos((int) ((date.getTime() % 1000) * 1000000)); // 나노초 단위로 변환

    return timestampBuilder.build();
  }
}