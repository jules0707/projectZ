package house.my.model.web;

import java.util.List;

import lombok.Data;

@Data
public class PaginatedList<P> {

  private List<P> partialList;
  private Integer fullListZize;
}
