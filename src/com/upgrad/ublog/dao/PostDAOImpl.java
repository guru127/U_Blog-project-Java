package com.upgrad.ublog.dao;

import com.upgrad.ublog.db.Database;
import com.upgrad.ublog.dtos.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

public class PostDAOImpl implements PostDAO {
   private static PostDAOImpl instance;
   private PostDAOImpl(){}

   public static PostDAO getInstance(){
       if (instance ==null){
           instance =new PostDAOImpl();
       }
       return instance;
   }

    @Override
    public Post create(Post post) throws SQLException {
        Connection connection = Database.getInstance();

        String sql="INSERT INTO POST (postId, emailId, tag, title, description, timestamp) VALUES(?,?,?,?,?,?)";
        try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, post.getPostId());
        preparedStatement.setString(2, post.getEmailId());
        preparedStatement.setString(3, post.getTag());
        preparedStatement.setString(4, post.getTitle());
        preparedStatement.setString(5, post.getDescription());
        preparedStatement.setString(6, String.valueOf(LocalDateTime.now()));

        preparedStatement.executeUpdate();
    }
        catch (SQLException e) {
        e.printStackTrace();
    }
        return post;
}

    @Override
    public List<Post> findByEmailId(String emailId) throws SQLException {
        return null;
    }

    @Override
    public List<Post> findByTag(String tag) throws SQLException {
        return null;
    }

    @Override
    public Post findByPostId(int postId) throws SQLException {
        return null;
    }

    @Override
    public List<String> findAllTags() throws SQLException {
        return null;
    }

    @Override
    public boolean deleteByPostId(int postId) throws SQLException {
        return false;
    }
}
/**
 * TODO: 4.1. Implement findByEmailId() method which takes email id as an input parameter and
 *  returns all the posts corresponding to the email id from the Post table defined
 *  in the database.
 */

/**
 * TODO: 4.4. Implement the deleteByPostId() method which takes post id as an input argument and delete
 *  the corresponding post from the database. If the post was deleted successfully, then return true,
 *  otherwise, return false. (Hint: The executeUpdate() method returns the count of rows affected by the
 *  query.)
 * TODO: 4.5. Implement the findByPostId() method which takes post id as an input argument and return the
 *  post details from the database. If no post exists for the given id, then return a Post object
 *  without setting any of its attributes.
 */

/**
 * TODO: 4.8. Implement findAllTags() method which returns a list of all tags in the POST table.
 * TODO: 4.9. Implement findByTag() method which takes "tag" as an input argument and returns all the
 *  posts corresponding to the input "tag" from the POST table defined in the database.
 */

