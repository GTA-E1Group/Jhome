package com.bracket.common.Bus.AbstractModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DataEntity<T extends DataEntity<T>> extends BaseEntity<T> {

    protected String id;
    protected String createByName;
    protected String status;
//    @JsonFormat(
//            pattern = "yyyy-MM-dd HH:mm:ss"
//    )
//   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    protected Date createDate;
//    @JsonFormat(
//            pattern = "yyyy-MM-dd HH:mm:ss"
//    )
//    protected Date updateDate;
    protected String updateBy;
    protected String createBy;


}
