package dev.vivekraman.monolith.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ResponseList<T extends Serializable> extends Response<List<T>> implements Serializable {
  @Serial
  private static final long serialVersionUID = 3989783766243646883L;

  private Integer page;
  private Integer size;
  private Long total;

  public ResponseList(Page<T> data) {
    super(data.getContent());
    this.page = data.getNumber();
    this.size = data.getSize();
    this.total = data.getTotalElements();
  }

  public ResponseList(List<T> data) {
    super(data);
    this.page = 0;
    this.size = data.size();
    this.total = Integer.toUnsignedLong(data.size());
  }
}
