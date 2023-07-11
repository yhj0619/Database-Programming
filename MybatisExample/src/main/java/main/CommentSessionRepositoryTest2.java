package main;


import java.util.*;

import model.Comment;
import model.Reply;
import repository.mybatis.CommentSessionRepository;

public class CommentSessionRepositoryTest2 {
	private static CommentSessionRepository commentDao = new CommentSessionRepository();
	
	public static void main(String[] args) {
		System.out.println("CommentSessionRepository2Test starts...");
		
		try {
			insertComment(20200001L, "user1", "comment #1");
			
			insertReply(20200101L, 20200001L, "user2", "1st reply");
			insertReply(20200102L, 20200001L, "user3", "2nd reply");
			insertReply(20200103L, 20200001L, "user2", "3rd reply");
			System.out.println();
			
			findCommentByPrimaryKey(20200001L);
			System.out.println();
			
			findCommentByPrimaryKeyAssociation(20200001L);
			System.out.println();
			
			findCommentByPrimaryKeyAssociation2(20200001L);
			System.out.println();
			
			findCommentByPrimaryKeyCollection(20200001L);
			System.out.println();
		} finally {
			deleteAllReplies();
			deleteAllComments();
		}
	}

	public static void findCommentByPrimaryKey(long commentNo) {		
		Comment comment = commentDao.findCommentByCommentNo(commentNo);		
		
		System.out.println("findCommentByPrimaryKey(" + commentNo + "): ");
		System.out.println(comment);
	}
	
	public static void findCommentByPrimaryKeyAssociation(long commentNo) {		
		Comment comment = commentDao.findCommentAndUserByCommentNo(commentNo);	
		
		System.out.println("findCommentByPrimaryKeyAssociation(" + commentNo + "): ");
		System.out.println(comment);
		System.out.println("user's name: " + comment.getUser().getUserName());
		System.out.println("user's phone: " + comment.getUser().getPhone());
	}

	public static void findCommentByPrimaryKeyAssociation2(long commentNo) {		
		Comment comment = commentDao.findCommentAndUserByCommentNo2(commentNo);		
		
		System.out.println("findCommentByPrimaryKeyAssociation2(" + commentNo + "): ");
		System.out.println(comment);
		System.out.println("user's name: " + comment.getUser().getUserName());
		System.out.println("user's phone: " + comment.getUser().getPhone());
	}
	
	public static void findCommentByPrimaryKeyCollection(long commentNo) {
		Comment comment = commentDao.findCommentAndRepliesByCommentNo(commentNo);	
		
		System.out.println("findCommentByPrimaryKeyCollection(" + commentNo + "): ");
		System.out.println(comment);
		List<Reply> replies = comment.getReplies();
		System.out.println("- number of replies: " + replies.size());
		System.out.print("- reply IDs: ");
		for (int i = 0; i < replies.size(); i++) {
			System.out.print(replies.get(i).getReplyId() + ", ");
		}
		System.out.println();
	}
	
	public static void insertComment(long commentNo, String userId, String commentContent) {
		Date regDate = Calendar.getInstance().getTime();		
		Comment comment = new Comment();
		comment.setCommentNo(commentNo);
		comment.setUserId(userId);
		comment.setCommentContent(commentContent);
		comment.setRegDate(regDate);
		
		int result = commentDao.insertComment(comment);		
		System.out.println("insertComment(" + commentNo + ", ...): " + result);
	}
	
	public static void insertReply(long replyId, long commentNo, String userId, String replyContent) {
		Reply reply = new Reply(replyId, commentNo, userId, replyContent, 
								Calendar.getInstance().getTime());
		int result = commentDao.insertReply(reply);		
		System.out.println("insertReply(" + replyId + ", " + commentNo + ",...): " + result);
	}
	
	public static void deleteAllComments() {		
		int result = commentDao.deleteAllComments();
		System.out.println("deleteAllComments(): " + result);
	}	
	
	public static void deleteAllReplies() {		
		int result = commentDao.deleteAllReplies();
		System.out.println("deleteAllReplys(): " + result);
	}	
}
