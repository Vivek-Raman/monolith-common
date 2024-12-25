package dev.vivekraman.monolith.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class ResponseList<T>  {
  private List<T> data;
  private Integer page;
  private Integer size;
  private Long total;

  public static <T> ResponseList<T> of(Page<T> data) {
    return new ResponseList<>(data);
  }

  public static <T> ResponseList<T> of(List<T> data) {
    return new ResponseList<>(data);
  }

  public ResponseList(Page<T> data) {
    this.page = data.getNumber();
    this.size = data.getSize();
    this.total = data.getTotalElements();
  }

  public ResponseList(List<T> data) {
    this.page = 0;
    this.size = data.size();
    this.total = Integer.toUnsignedLong(data.size());
  }
}
