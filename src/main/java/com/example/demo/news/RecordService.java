package com.example.demo.news;


import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    List<Record> recordList = null;
    @Autowired
    private RecordRepository repoR;
    public List<Record> listAllRecords(String keyword) {/*коллекция, отвечающая за поиск и фильтр*/
        if(keyword != null){
            return repoR.search(keyword);
        }
        return repoR.findAll();
    }
    public void save(Record record){
        repoR.save(record);
    }
    public Record get(Long id){/*редактирование*/
        return repoR.findById(id).get();
    }
    public void delete(Long id){
        repoR.deleteById(id);
    }
}
