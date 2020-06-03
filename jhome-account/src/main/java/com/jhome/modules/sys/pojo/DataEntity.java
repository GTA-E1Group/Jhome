package com.jhome.modules.sys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
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
