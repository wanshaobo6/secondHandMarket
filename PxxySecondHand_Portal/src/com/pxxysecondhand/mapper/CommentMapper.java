package com.pxxysecondhand.mapper;

import com.pxxysecondhand.pojo.Comment;
import com.pxxysecondhand.pojo.CommentExample;
import com.pxxysecondhand.tempPojo.ItemDescComment;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper {
    int countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    //递归查询所有的评论
    List<ItemDescComment> getCommentByItemId(String itemId);
}