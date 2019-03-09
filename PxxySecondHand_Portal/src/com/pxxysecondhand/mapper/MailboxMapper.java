package com.pxxysecondhand.mapper;

import com.pxxysecondhand.pojo.Mailbox;
import com.pxxysecondhand.pojo.MailboxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MailboxMapper {
    int countByExample(MailboxExample example);

    int deleteByExample(MailboxExample example);

    int deleteByPrimaryKey(String id);

    int insert(Mailbox record);

    int insertSelective(Mailbox record);

    List<Mailbox> selectByExample(MailboxExample example);

    Mailbox selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Mailbox record, @Param("example") MailboxExample example);

    int updateByExample(@Param("record") Mailbox record, @Param("example") MailboxExample example);

    int updateByPrimaryKeySelective(Mailbox record);

    int updateByPrimaryKey(Mailbox record);
}