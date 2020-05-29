package com.example.demo.sevice;

import com.example.demo.domain.bean.PushResultTemp;

import java.util.List;

public interface PushResultTempService {

    /**
     *
     * @param index
     * @return
     */
    List<PushResultTemp> getResult(int index);
}
