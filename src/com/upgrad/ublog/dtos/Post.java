package com.upgrad.ublog.dtos;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Post {
  private int postId;
  private String emailId;
  private String tag;
  private String title;
  private String description;
  private String timestamp;

    public Post(int postId, String emailId, String tag, String title, String description,
                String timestamp) {
        this.postId = postId;
        this.emailId = emailId;
        this.tag = tag;
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;

    }

    public Post() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPostId() {this.postId=postId; }

    @Override
   public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", emailId='" + emailId + '\'' +
             ", tag='" + tag + '\'' +
              ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public static void main(String[] args) {
        Post post = new Post();
        post.setPostId();
        post.setEmailId("dummy@dummy.com");
        post.setTag("tag");
        post.setTitle("title");
        post.setDescription("Description");
        post.setTimestamp( LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        System.out.println(post);

    }


}
