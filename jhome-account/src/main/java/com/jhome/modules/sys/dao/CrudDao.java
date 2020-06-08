package com.jhome.modules.sys.dao;

import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CrudDao {
    @Select( "select * from xx")
    void select();
}
