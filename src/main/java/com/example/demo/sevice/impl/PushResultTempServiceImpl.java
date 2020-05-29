package com.example.demo.sevice.impl;

import com.example.demo.dao.mapper.PushResultMapper;
import com.example.demo.domain.bean.PushResultTemp;
import com.example.demo.sevice.PushResultTempService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PushResultTempServiceImpl implements PushResultTempService {

    @Autowired
    private PushResultMapper pushResultMapper;

    @Override
    public List<PushResultTemp> getResult(int index) {
        log.info("query params index:{}", index);
        return pushResultMapper.selectByLimit(index);
    }
}
