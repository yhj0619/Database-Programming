package main;


import java.util.*;

import model.Comment;
import repository.jdbc.CommentJdbcRepository;

public class CommentJdbcRepositoryTest {
	private static CommentJdbcRepository commentDao = new CommentJdbcRepository(); //Repository 바뀜.

	public static void main(String[] args) {	
		System.out.println("CommentJdbcRepositoryTest starts...");
		
		try {
			insertComment(20200010L, "user1", "comment #10"); //comment를 저장. reg_date는 생략. insetComment에서 현재시각으로 저장함. 그래서 insertComment로 안주어져있음.
			insertComment(20200020L, "user1", "comment #20");
			insertComment(20200030L, "user2", "comment #30");
			System.out.println();
			
			findCommentByPrimaryKey(20200010L);
			findCommentByPrimaryKey(20200020L);
			findCommentByCondition("user1"); //
			System.out.println();
	
			updateComment(20200010L, "comment #11");
			findCommentByCondition("user1");
			System.out.println();
			
			deleteComment(20200010L);
			findCommentByCondition("user1");
			System.out.println();			
		} finally {
			deleteAllComments();
			findCommentByCondition("user1"); //전체 delete가 잘됐는지 확인한거야
		}
	}
	
	public static void findCommentByPrimaryKey(long commentNo) {		
		Comment comment = commentDao.findCommentByCommentNo(commentNo); //comment 객체 return !!
		System.out.println("findCommentByPrimaryKey(" + commentNo + "): ");
		System.out.println(comment);
	}

	public static void findCommentByCondition(String userId) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userId", userId);
		List<Comment> list = commentDao.findCommentByCondition(condition); 	//여러개의 comment객체가 return되니까 list로 만들어주기
		System.out.println("findCommentByCondition(" + userId + "): ");
		System.out.println(list);
	}
	
	public static void insertComment(long commentNo, String userId, String commentContent) {
		Date regDate = Calendar.getInstance().getTime(); //자바로 reg_date넣어주기
		
		Comment comment = new Comment();
		comment.setCommentNo(commentNo);
		comment.setUserId(userId);
		comment.setCommentContent(commentContent);
		comment.setRegDate(regDate);
		int result = commentDao.insertComment(comment); //완전한 comment 객체로 넣어주기.
		System.out.println("insertComment(" + commentNo + ", ...): " + result);
	}

	public static void updateComment(long commentNo, String commentContent) {
		Comment comment = new Comment();
		comment.setCommentNo(commentNo);
		comment.setCommentContent(commentContent);
		int result = commentDao.updateComment(comment);
		System.out.println("updateComment(" + commentNo + ", " + commentContent + "): " + result);
	}

	public static void deleteComment(long commentNo) {
		int result = commentDao.deleteComment(commentNo);
		System.out.println("deleteComment(" + commentNo + "): " + result);
	}		
	
	public static void deleteAllComments() {		
		int result = commentDao.deleteAllComments();
		System.out.println("deleteAllComments(): " + result);
	}	
}
