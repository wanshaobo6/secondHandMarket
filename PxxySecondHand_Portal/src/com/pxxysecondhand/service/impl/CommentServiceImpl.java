/**   
* @Title: ICommentServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��30�� ����5:06:28 
* @version V1.0   
*/
package com.pxxysecondhand.service.impl;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pxxysecondhand.component.JedisClient;
import com.pxxysecondhand.mapper.CommentMapper;
import com.pxxysecondhand.mapper.ItemMapper;
import com.pxxysecondhand.mapper.UserMapper;
import com.pxxysecondhand.pojo.Comment;
import com.pxxysecondhand.pojo.CommentExample;
import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.pojo.UserExample;
import com.pxxysecondhand.pojo.UserExample.Criteria;
import com.pxxysecondhand.service.ICommentService;
import com.pxxysecondhand.tempPojo.ItemDescComment;
import com.pxxysecondhand.tempPojo.MyPublic;
import com.pxxysecondhand.tempPojo.Mymessage;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CommonUtils;
import com.pxxysecondhand.utils.CookieUtils;
import com.pxxysecondhand.utils.JsonUtils;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

/**
 * @author  
 *
 */
@Service
public class CommentServiceImpl implements ICommentService {
	@Autowired
	private CommentMapper commentMapper; 
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	
	@Autowired
	private ItemMapper itemMapper;
	
	//#��¼�ɹ���洢�û���������ǰ׺
	@Value("${USER_ONLINE_TOKEN_PREFIX}")
	private String USER_ONLINE_TOKEN_PREFIX;
	
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#getCommentByItemId(java.lang.String)
	 */
	@Override
	public List<ItemDescComment> getCommentByItemId(String itemId) {
		// TODO Auto-generated method stub
		List<ItemDescComment> commentList = commentMapper.getCommentByItemId(itemId);
		doSomeOperationToComments(commentList);
		return commentList;
	}
	public void doSomeOperationToComments(List<ItemDescComment> commentList) {
		if(commentList==null)
			return;
		for (ItemDescComment itemDescComment : commentList) {
			itemDescComment.setCommentTime(CommonUtils.getTimeIntervalInImpreciseWay(itemDescComment.getCreatetime()));
			doSomeOperationToComments(itemDescComment.getItemDescComments());
		}
		
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#checkIsLogin(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	@Override
	public User checkIsLogin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//��ȡcookie
		String cookievalue = CookieUtils.getCookieValue(request, "Login_Token");
		if(StringUtils.isEmpty(cookievalue))
			return null;
		String userStr = jedisClient.get(USER_ONLINE_TOKEN_PREFIX+cookievalue);
		if(StringUtils.isEmpty(userStr))
			return null;
		User user = null; 
		try {
			 user = JsonUtils.jsonToPojo(userStr, User.class);
		}catch(Exception e) {
			return null;
		}
		return user;
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#publicMyComment(com.pxxysecondhand.pojo.User, java.lang.String, java.lang.String)
	 */
	@Override
	public CommonResult publicMyComment(User user, String itemId, String parentId,String Content) {
		// TODO Auto-generated method stub
		Comment comment = new Comment();
		//��ȫ����
		comment.setContent(Content);
		comment.setCreatetime(new Date());
		comment.setFromuserid(user.getId());
		comment.setFromusername(user.getUsername());
		comment.setHavaread(0);
		comment.setId(CommonUtils.uuidGenerator());
		comment.setIsparent(0);
		//�ı丸����ĸ���״̬
			if(!parentId.equals("0")) {
				Comment parentComment = new Comment();
				parentComment.setId(parentId);
				parentComment.setIsparent(1);
				commentMapper.updateByPrimaryKeySelective(parentComment);
			}
		comment.setItemid(itemId);
		comment.setParentid(parentId);
		//��ѯ��˭���۷�
		User toUser = userMapper.selectItemOwner(itemId);
		comment.setTouserid(toUser.getId());
		System.out.println(toUser.getId());//d1f7c69198504e60bc9ffad9e09c8bd2
		comment.setTousername(toUser.getUsername());
		//�����¼
		commentMapper.insert(comment);
		return CommonResult.ok(comment);
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#deleteMyComment(com.pxxysecondhand.pojo.User, java.lang.String)
	 */
	@Override
	public CommonResult deleteMyComment(User user, String commentId) {
		// TODO Auto-generated method stub
		//1.�鿴�û��Ƿ����ʸ�ɾ��
		Comment comment = commentMapper.selectByPrimaryKey(commentId);
		if(comment==null||(!comment.getFromuserid().equals(user.getId())))
			return CommonResult.build(500,"��ûȨ�޲���");
		//2.ɾ��������
		recursiveDelete(commentId);
		//3.�ж�commentId�ĸ��������Ƿ����Ӷ���
		CommentExample example = new CommentExample();
		com.pxxysecondhand.pojo.CommentExample.Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(comment.getParentid());
		int n = commentMapper.countByExample(example);
		if(n==0&&(comment.getParentid().equals("0"))) {
			//�޸ĸ����۵�isParent
			Comment record = new Comment();
			record.setId(comment.getParentid());
			record.setIsparent(0);
			commentMapper.updateByPrimaryKeySelective(record);
		}
		return CommonResult.ok();
	}
	
	/**
	 * �ݹ�ɾ��������
	* @Title: CommentServiceImpl.java 
	* @Package com.pxxysecondhand.service.impl 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��1�� ����4:06:59 
	* @version V1.0
	 */
	public void recursiveDelete(String CommentId) {
		//��ѯcommentid��Ӧ������
		Comment comment = commentMapper.selectByPrimaryKey(CommentId);
		//ɾ��commentid��Ӧ������
		commentMapper.deleteByPrimaryKey(CommentId);
		//��ѯcommentid�Ƿ����������
		CommentExample commentExample = new CommentExample();
		com.pxxysecondhand.pojo.CommentExample.Criteria criteria = commentExample.createCriteria();
		criteria.andParentidEqualTo(CommentId);
		List<Comment> list = commentMapper.selectByExample(commentExample);
		for (Comment comment2 : list) {
			recursiveDelete(comment2.getId());
		}
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#countCommentNumByItemId(java.lang.String)
	 */
	@Override
	public int countCommentNumByItemId(String itemid) {
		// TODO Auto-generated method stub
		CommentExample example = new CommentExample();
		com.pxxysecondhand.pojo.CommentExample.Criteria criteria = example.createCriteria();
		criteria.andItemidEqualTo(itemid);
		Integer num = commentMapper.countByExample(example);
		return num;
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#countCommentNotReadByItemId(java.lang.String)
	 */
	@Override
	public int countCommentNotReadByItemId(String itemId) {
		// TODO Auto-generated method stub
		CommentExample example = new CommentExample();
		com.pxxysecondhand.pojo.CommentExample.Criteria criteria = example.createCriteria();
		criteria.andItemidEqualTo(itemId);
		criteria.andHavareadEqualTo(0);
		Integer num = commentMapper.countByExample(example);
		return num;
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#deleteCommentsByItemId(java.lang.String)
	 */
	@Override
	public void deleteCommentsByItemId(String itemId) {
		// TODO Auto-generated method stub
		CommentExample example = new CommentExample();
		com.pxxysecondhand.pojo.CommentExample.Criteria criteria = example.createCriteria();
		criteria.andItemidEqualTo(itemId);
		commentMapper.deleteByExample(example);
	}
	@Override
	public SearchResult<Mymessage> showMyMessage(User user, int page, int rows, HttpServletRequest request) {
		/**
		 * �����Ñ���ԃ���Լ�����������
		 */
		PageHelper.startPage(page, rows);
		CommentExample example=new CommentExample();
		example.setOrderByClause("'createTime' DESC");
		com.pxxysecondhand.pojo.CommentExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andTouseridEqualTo(user.getId());
		List<Comment> list = this.commentMapper.selectByExample(example);
		List<Mymessage> mymessages=new ArrayList<>();
		list.stream().forEach(e->{
				Item item = itemMapper.selectByPrimaryKey(e.getItemid());
				Mymessage mymessage=new Mymessage();
				mymessage.setFromUsername(e.getFromusername());
				mymessage.setItemName(item.getItemtitle());
				mymessage.setMessage(e.getContent());
				String time = CommonUtils.getTimeIntervalInImpreciseWay(e.getCreatetime());
				mymessage.setMessageDate(time);
				mymessage.setCurrentPrice(item.getCurrprice());
				mymessage.setImage(CommonUtils.getLargeImage(item.getItemimages()));   
				mymessages.add(mymessage);
		});
		//��ҳ������
		SearchResult<Mymessage> result = new SearchResult();
		result.setItemList(mymessages);
		result.setCurrentPage(page);
		PageInfo<Comment> pageInfo = new PageInfo(list);
		int i = (int) pageInfo.getTotal();
		result.setTotalCount(i);
		int totalPage = i % rows == 0 ? i / rows : (i / rows + 1);
		result.setTotalPage(totalPage);
		result.setURL(CommonUtils.getURLByServletRequest(request));
		return result;
=======
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pxxysecondhand.component.JedisClient;
import com.pxxysecondhand.mapper.CommentMapper;
import com.pxxysecondhand.mapper.ItemMapper;
import com.pxxysecondhand.mapper.UserMapper;
import com.pxxysecondhand.pojo.Comment;
import com.pxxysecondhand.pojo.CommentExample;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.pojo.UserExample;
import com.pxxysecondhand.pojo.UserExample.Criteria;
import com.pxxysecondhand.service.ICommentService;
import com.pxxysecondhand.tempPojo.ItemDescComment;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CommonUtils;
import com.pxxysecondhand.utils.CookieUtils;
import com.pxxysecondhand.utils.JsonUtils;

/**
 * @author  
 *
 */
@Service
public class CommentServiceImpl implements ICommentService {
	@Autowired
	private CommentMapper commentMapper; 
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	//#��¼�ɹ���洢�û���������ǰ׺
	@Value("${USER_ONLINE_TOKEN_PREFIX}")
	private String USER_ONLINE_TOKEN_PREFIX;
	
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#getCommentByItemId(java.lang.String)
	 */
	@Override
	public List<ItemDescComment> getCommentByItemId(String itemId) {
		// TODO Auto-generated method stub
		List<ItemDescComment> commentList = commentMapper.getCommentByItemId(itemId);
		doSomeOperationToComments(commentList);
		return commentList;
	}
	public void doSomeOperationToComments(List<ItemDescComment> commentList) {
		if(commentList==null)
			return;
		for (ItemDescComment itemDescComment : commentList) {
			itemDescComment.setCommentTime(CommonUtils.getTimeIntervalInImpreciseWay(itemDescComment.getCreatetime()));
			doSomeOperationToComments(itemDescComment.getItemDescComments());
		}
		
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#checkIsLogin(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	@Override
	public User checkIsLogin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//��ȡcookie
		String cookievalue = CookieUtils.getCookieValue(request, "Login_Token");
		if(StringUtils.isEmpty(cookievalue))
			return null;
		String userStr = jedisClient.get(USER_ONLINE_TOKEN_PREFIX+cookievalue);
		if(StringUtils.isEmpty(userStr))
			return null;
		User user = null; 
		try {
			 user = JsonUtils.jsonToPojo(userStr, User.class);
		}catch(Exception e) {
			return null;
		}
		return user;
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#publicMyComment(com.pxxysecondhand.pojo.User, java.lang.String, java.lang.String)
	 */
	@Override
	public CommonResult publicMyComment(User user, String itemId, String parentId,String Content) {
		// TODO Auto-generated method stub
		Comment comment = new Comment();
		//��ȫ����
		comment.setContent(Content);
		comment.setCreatetime(new Date());
		comment.setFromuserid(user.getId());
		comment.setFromusername(user.getUsername());
		comment.setHavaread(0);
		comment.setId(CommonUtils.uuidGenerator());
		comment.setIsparent(0);
		//�ı丸����ĸ���״̬
			if(!parentId.equals("0")) {
				Comment parentComment = new Comment();
				parentComment.setId(parentId);
				parentComment.setIsparent(1);
				commentMapper.updateByPrimaryKeySelective(parentComment);
			}
		comment.setItemid(itemId);
		comment.setParentid(parentId);
		//��ѯ��˭���۷�
		Comment toComment = commentMapper.selectByPrimaryKey(parentId);
		comment.setTouserid(toComment.getFromuserid());
		comment.setTousername(toComment.getFromusername());
		//�����¼
		commentMapper.insert(comment);
		return CommonResult.ok(comment);
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#deleteMyComment(com.pxxysecondhand.pojo.User, java.lang.String)
	 */
	@Override
	public CommonResult deleteMyComment(User user, String commentId) {
		// TODO Auto-generated method stub
		//1.�鿴�û��Ƿ����ʸ�ɾ��
		Comment comment = commentMapper.selectByPrimaryKey(commentId);
		if(comment==null||(!comment.getFromuserid().equals(user.getId())))
			return CommonResult.build(500,"��ûȨ�޲���");
		//2.ɾ��������
		recursiveDelete(commentId);
		//3.�ж�commentId�ĸ��������Ƿ����Ӷ���
		CommentExample example = new CommentExample();
		com.pxxysecondhand.pojo.CommentExample.Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(comment.getParentid());
		int n = commentMapper.countByExample(example);
		if(n==0&&(comment.getParentid().equals("0"))) {
			//�޸ĸ����۵�isParent
			Comment record = new Comment();
			record.setId(comment.getParentid());
			record.setIsparent(0);
			commentMapper.updateByPrimaryKeySelective(record);
		}
		return CommonResult.ok();
	}
	
	/**
	 * �ݹ�ɾ��������
	* @Title: CommentServiceImpl.java 
	* @Package com.pxxysecondhand.service.impl 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��1�� ����4:06:59 
	* @version V1.0
	 */
	public void recursiveDelete(String CommentId) {
		//��ѯcommentid��Ӧ������
		Comment comment = commentMapper.selectByPrimaryKey(CommentId);
		//ɾ��commentid��Ӧ������
		commentMapper.deleteByPrimaryKey(CommentId);
		//��ѯcommentid�Ƿ����������
		CommentExample commentExample = new CommentExample();
		com.pxxysecondhand.pojo.CommentExample.Criteria criteria = commentExample.createCriteria();
		criteria.andParentidEqualTo(CommentId);
		List<Comment> list = commentMapper.selectByExample(commentExample);
		for (Comment comment2 : list) {
			recursiveDelete(comment2.getId());
		}
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#countCommentNumByItemId(java.lang.String)
	 */
	@Override
	public int countCommentNumByItemId(String itemid) {
		// TODO Auto-generated method stub
		CommentExample example = new CommentExample();
		com.pxxysecondhand.pojo.CommentExample.Criteria criteria = example.createCriteria();
		criteria.andItemidEqualTo(itemid);
		Integer num = commentMapper.countByExample(example);
		return num;
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#countCommentNotReadByItemId(java.lang.String)
	 */
	@Override
	public int countCommentNotReadByItemId(String itemId) {
		// TODO Auto-generated method stub
		CommentExample example = new CommentExample();
		com.pxxysecondhand.pojo.CommentExample.Criteria criteria = example.createCriteria();
		criteria.andItemidEqualTo(itemId);
		criteria.andHavareadEqualTo(0);
		Integer num = commentMapper.countByExample(example);
		return num;
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICommentService#deleteCommentsByItemId(java.lang.String)
	 */
	@Override
	public void deleteCommentsByItemId(String itemId) {
		// TODO Auto-generated method stub
		CommentExample example = new CommentExample();
		com.pxxysecondhand.pojo.CommentExample.Criteria criteria = example.createCriteria();
		criteria.andItemidEqualTo(itemId);
		commentMapper.deleteByExample(example);
>>>>>>> branch 'master' of https://github.com/wanshaobo6/secondHandMarket.git
	}

}
