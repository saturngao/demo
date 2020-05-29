package com.example.demo.dao.mapper;

import com.example.demo.domain.bean.PushResultTemp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PushResultMapper {
    PushResultTemp selectByPrimaryKey(String id);

    Integer selectCount();

    List<PushResultTemp> selectByLimit(int index);
}
