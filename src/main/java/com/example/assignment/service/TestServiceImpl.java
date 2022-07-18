package com.example.assignment.service;

import com.example.assignment.dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    @Override
    public void testService(){
        testMapper.testInsert();
        System.out.println("obj test done");
    }

}
