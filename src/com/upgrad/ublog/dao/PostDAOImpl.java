package com.upgrad.ublog.dao;

import com.upgrad.ublog.db.Database;
import com.upgrad.ublog.dtos.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PostDAOImpl implements PostDAO {
   private static PostDAOImpl instance;
   private PostDAOImpl(){}

   public static PostDAOImpl getInstance(){
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
        preparedStatement.setString(6, String.valueOf(post.getTimestamp()));

        preparedStatement.executeUpdate();
            }
        catch (SQLException e) {
        e.printStackTrace();
    }
        return post;
}


    @Override
    public List<Post> findByEmailId(String emailId) throws SQLException {
       List<Post> postList= new ArrayList<>();
       Connection connection =Database.getInstance();
        String sql ="SELECT * FROM post WHERE emailId='"+emailId+"'";
        ResultSet resultSet=null;
        try {
            Statement statement= connection.createStatement();
            resultSet=statement.executeQuery(sql);

            while (resultSet.next()) {
                Post post = new Post();
                post.setPostId(resultSet.getInt("postId"));
                post.setEmailId(resultSet.getString("emailId"));
                post.setTag(resultSet.getString("tag"));
                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));
                post.setTimestamp((resultSet.getString("timeStamp")));

                postList.add(post);
            }
        } catch (SQLException e) {
            e.getMessage();

       }
        return postList;
    }

    @Override
    public List<Post> findByTag(String tag) throws SQLException {
        List<Post> postList= new ArrayList<>();
        Connection connection =Database.getInstance();
        String sql ="SELECT * FROM post WHERE tag='"+tag+"'";
        ResultSet resultSet;
        try {
            Statement statement= connection.createStatement();
            resultSet=statement.executeQuery(sql);

            while (resultSet.next()) {
                Post post = new Post();
                post.setPostId(resultSet.getInt("postId"));
                post.setEmailId(resultSet.getString("emailId"));
                post.setTag(resultSet.getString("tag"));
                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));
                post.setTimestamp((resultSet.getString("timeStamp")));

                postList.add(post);
            }
        } catch (SQLException e) {
            e.getMessage();

        }
        return postList;
    }


    @Override
    public Post findByPostId(int postId) throws SQLException {
        Post post=new Post();
        Connection connection =Database.getInstance();
        Statement statement= connection.createStatement();
        String sql ="SELECT * FROM post WHERE postId='"+postId+"'";
        ResultSet resultSet=null;
        try{
            resultSet=statement.executeQuery(sql);
            while(resultSet.next()) {
                post.setPostId(resultSet.getInt("postId"));
                post.setEmailId(resultSet.getString("emailId"));
                post.setTag(resultSet.getString("tag"));
                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));
                post.setDescription(resultSet.getString("timeStamp"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return post;
    }

    @Override
    public List<String> findAllTags() throws SQLException {
        List<String> tagList= new ArrayList<>();
        Connection connection =Database.getInstance();
        Statement statement= connection.createStatement();
        String sql ="SELECT * FROM POST";
        ResultSet resultSet=statement.executeQuery(sql);

        while (resultSet.next()){
            tagList.add(resultSet.getString("tag"));
        }
        return tagList;
    }

    @Override
    public boolean deleteByPostId(int postId) throws SQLException {
        boolean flag=false;
        Connection connection =Database.getInstance();
        Statement statement= connection.createStatement();
        String sql ="DELETE FROM post WHERE postId ='"+postId+"'";
        try {
            statement.executeUpdate(sql);
            flag=true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

}


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

