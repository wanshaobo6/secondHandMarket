package com.pxxysecondhand.mapper;

import com.pxxysecondhand.pojo.Collection;
import com.pxxysecondhand.pojo.CollectionExample;
import com.pxxysecondhand.tempPojo.MyCollections;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectionMapper {
    int countByExample(CollectionExample example);

    int deleteByExample(CollectionExample example);

    int deleteByPrimaryKey(String id);

    int insert(Collection record);

    int insertSelective(Collection record);

    List<Collection> selectByExample(CollectionExample example);

    Collection selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Collection record, @Param("example") CollectionExample example);

    int updateByExample(@Param("record") Collection record, @Param("example") CollectionExample example);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);
    
    //
    List<MyCollections> getCollectionsByCollectorId(String collectorId);
}