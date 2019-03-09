package com.pxxysecondhand.mapper;

import com.pxxysecondhand.pojo.Trade;
import com.pxxysecondhand.pojo.TradeExample;
import com.pxxysecondhand.tempPojo.EvaluateModel;
import com.pxxysecondhand.tempPojo.MyPurchase;
import com.pxxysecondhand.tempPojo.MySelled;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeMapper {
  int countByExample(TradeExample example);

    int deleteByExample(TradeExample example);

    int deleteByPrimaryKey(String id);

    int insert(Trade record);

    int insertSelective(Trade record);

    List<Trade> selectByExample(TradeExample example);

    Trade selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Trade record, @Param("example") TradeExample example);

    int updateByExample(@Param("record") Trade record, @Param("example") TradeExample example);

    int updateByPrimaryKeySelective(Trade record);

    int updateByPrimaryKey(Trade record);
    //----------------
   	//获得用户评级模型
   	List<EvaluateModel> getEvaluateModels(String userId);
   	
   	List<MyPurchase> getAllOfMyPurchase(String userId);
   	
   	MyPurchase getMyPurchaseByTradeId(String tradeId);
   	
   	EvaluateModel getBuyerIdAndOwnerId(String tradeId);

   	Trade getNewestTradeByItemId(String id);
   	
   	List<MySelled> getAllOfMySelled(String ownerId);
}