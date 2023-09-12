package dev.vivekraman.monolith.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
  @Serial
  private static final long serialVersionUID = 8480397197149093465L;

  private T data;
  private String error;

  protected Response(T data) {
    this.data = data;
  }

  public static <T extends Serializable> Response<T> of(T data) {
    return new Response<>(data);
  }

  public static <T extends Serializable> Response<List<T>> of(Page<T> data) {
    return new ResponseList<>(data);
  }

  public static <T extends Serializable> Response<List<T>> of(List<T> data) {
    return new ResponseList<>(data);
  }

  public static Response<Object> error(String error) {
    return Response.builder().error(error).build();
  }
}
