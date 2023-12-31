package repository.mybatis;

import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import model.Comment;
import model.Reply;

public class CommentSessionRepository {
	private final String namespace = "repository.mybatis.mapper.CommentMapper";

	private SqlSessionFactory sqlSessionFactory = createSqlSessionFactory();
	
	private SqlSessionFactory createSqlSessionFactory() {
		String resource = "mybatis-config.xml"; //설정파일. src.resoures 밑에 있음. 
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return new SqlSessionFactoryBuilder().build(inputStream); //이걸 통해 build 하면 공장 생성완!!!!!!!!
	}
	/**
	 * mybatis를 이용햇기에 더욱 간결해짐.. 
	 * namescpace랑 primarykey를 잘 알아야함!!!
	 * Mapper.xml의 id 값 고대로~
	 * namespace 그대로~
	 */
	public Comment findCommentByCommentNo(long commentNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return (Comment)sqlSession.selectOne(			//하나의 comment객체만 return 이니까 selectOne
					namespace + ".selectCommentByPrimaryKey", commentNo);
		} finally {
			sqlSession.close();
		}
	}

	public List<Comment> findCommentByCondition(Map<String, Object> condition) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(			//여러 개의 레코드가 ㄱ머색되는건 selectList를 사용.
													//반환값도 List<Comment> 해주는거 필수!!
					namespace + ".selectCommentByCondition", condition);			
		} finally {
			sqlSession.close();
		}
	}

	public int insertComment(Comment comment) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.insert(namespace + ".insertComment", comment);
			if (result > 0) {
				sqlSession.commit();
			} 			
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int updateComment(Comment comment) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.update(namespace + ".updateComment", comment);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int deleteComment(long commentNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.delete(namespace + ".deleteComment", commentNo);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	public int deleteAllComments() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.delete(namespace + ".deleteAllComments");
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	public Comment findCommentAndUserByCommentNo(long commentNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return (Comment)sqlSession.selectOne(
					namespace + ".selectCommentByPrimaryKeyAssociation", commentNo);
		} finally {
			sqlSession.close();
		}
	}
	
	public Comment findCommentAndUserByCommentNo2(long commentNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return (Comment)sqlSession.selectOne(
					namespace + ".selectCommentByPrimaryKeyAssociation2", commentNo);
		} finally {
			sqlSession.close();
		}
	}
	
	public Comment findCommentAndRepliesByCommentNo(long commentNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return (Comment)sqlSession.selectOne(
					namespace + ".selectCommentByPrimaryKeyCollection", commentNo);
		} finally {
			sqlSession.close();
		}
	}

	public int insertReply(Reply reply) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.insert(namespace + ".insertReply", reply);
			if (result > 0) {
				sqlSession.commit();
			} 			
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int deleteAllReplies() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.delete(namespace + ".deleteAllReplies");
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
}
