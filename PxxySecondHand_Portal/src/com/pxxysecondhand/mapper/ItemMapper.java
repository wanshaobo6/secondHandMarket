package com.pxxysecondhand.mapper;

import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.ItemExample;
import com.pxxysecondhand.tempPojo.ItemDescResult;
import com.pxxysecondhand.tempPojo.MyPurchase;
import com.pxxysecondhand.tempPojo.NewestPublic;
import com.pxxysecondhand.tempPojo.SearchItem;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemMapper {
    int countByExample(ItemExample example);

    int deleteByExample(ItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByExample(ItemExample example);

    Item selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
    
    //
    ItemDescResult showItem(String id);

	
    List<SearchItem> searchByKeywordsOrderByorderNum(String keywords);

	List<SearchItem> searchByKeywordsOrderByPolishTime(String keywords);

	List<SearchItem> searchByKeywordsOrderByCollectionNum(String keywords);
	
	List<SearchItem> searchByKeywordsOrderByAuthorCredit(String keywords);

	List<SearchItem> searchByKeywordsOrderByPriceDesc(String keywords);

	List<SearchItem> searchByKeywordsOrderByPriceAesc(String keywords);

    
}