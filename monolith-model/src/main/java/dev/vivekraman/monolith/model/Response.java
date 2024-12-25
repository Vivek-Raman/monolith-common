package dev.vivekraman.monolith.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

  private T data;
  private String error;

  protected Response(T data) {
    this.data = data;
  }

  public static <T> Response<T> of(T data) {
    return new Response<>(data);
  }

  public static Response<Object> error(String error) {
    return Response.builder().error(error).build();
  }
}
