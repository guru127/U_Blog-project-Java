package com.upgrad.ublog.services;

import com.upgrad.ublog.dao.DAOFactory;
import com.upgrad.ublog.dao.PostDAO;
import com.upgrad.ublog.dao.PostDAOImpl;
import com.upgrad.ublog.dtos.Post;
import com.upgrad.ublog.exceptions.PostNotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class PostServiceImpl implements PostService {
    private static PostServiceImpl instance;
    private DAOFactory daoFactory;
    private PostDAO postDAO;

    private PostServiceImpl() {
        daoFactory = new DAOFactory();
        postDAO = daoFactory.getPostDAO();

    }

    public static PostServiceImpl getInstance() {
        if (instance == null) {
            instance = new PostServiceImpl();
        }
        return instance;
    }

    @Override
    public Post create(Post post) throws Exception {
        //Post post = null;
        try {
            if (post != null)
                post = postDAO.create(post);

        } catch (SQLException e) {
            System.out.println("Some unexpected error occurred!");
        }
        return post;
    }

    @Override
    public List<Post> getPostsByEmailId(String emailId) throws Exception {
        List<Post> postList;
        try {
            postList = postDAO.findByEmailId(emailId);
        } catch (SQLException e) {
            throw new Exception("Some unexpected exception occurred.");
        }

        return postList;
    }

    @Override
    public List<Post> getPostsByTag(String tag) throws Exception {
        List<Post> postList;
        try {
            postList = postDAO.findByTag(tag);
        } catch (SQLException e) {
            throw new Exception("Some unexpected exception occurred.");
        }
        return postList;
    }

    @Override
    public Set<String> getAllTags() throws Exception {
        Set<String> tagSet = null;
        List<String> tagList = postDAO.findAllTags();
        //tagList;
        try {
            tagList = postDAO.findAllTags();
        } catch (SQLException e) {
            System.out.println("Some unexpected error occurred!");
        }
        for (String s : tagList) {
            tagSet.add(s);
        }

        return tagSet;
    }

    @Override
    public boolean deletePost(int postId, String emailId) throws Exception {
        Post post = null;
        try {
            post = postDAO.findByPostId(postId);
        } catch (SQLException e) {
            throw new PostNotFoundException("No Post exist with the given Post Id ");
        }

        if (post != null && emailId.equals(post.getEmailId())) {
            try {
                postDAO.deleteByPostId(postId);
                return true;
            } catch (SQLException e) {
                System.out.println("Some unexpected error occurred!");
            }
        }

        return false;
    }

}

/**
 * TODO: 4.6. Implement deletePost() method which takes post id and email id as an input parameter.
 *  1. Get the post corresponding to the post id using the findByPostId() method of the PostDAO interface.
 *  2. If no post exists corresponding the post id, then throw a new PostNotFoundException with
 *   "No Post exist with the given Post Id" message.
 *  3. If the post was created by the same user whose email id is passed as an input argument, then delete
 *   the post using deleteByPostId() method of PostDAO and return the same boolean value returned by the
 *   deleteByPostId() method.
 *  4. If the post was not created by the same user whose email id is passed as an input argument, don't delete
 *   the post, and return false.
 *  Note: The exception passed by DAO layer should not be passed to the presentation layer. Print the stack
 *  trace corresponding to the exception passed by DAO layer and throw a new exception of type Exception
 *  with a message "Some unexpected error occurred!"
 */

/**
 * TODO: 4.2. Implement getPostsByEmailId() method which takes email id as an input parameter and
 *  returns all the posts corresponding to the email id using the findByEmailId() method of PostDAO interface.
 *  Note: The exception passed by DAO layer should not be passed to the presentation layer. Print the stack
 *  trace corresponding to the exception passed by DAO layer and throw a new exception of type Exception
 *  with a message "Some unexpected error occurred!"
 */

/**
 * TODO: 4.10. Implement the getAllTags() method which retrieves the list of all the tags from the
 *  database using the findAllTags() method of PostDAO interface and return a set of unique tags.
 *  Note: The exception passed by DAO layer should not be passed to the presentation layer. Print the stack
 *  trace corresponding to the exception passed by DAO layer and throw a new exception of type Exception
 *  with a message "Some unexpected error occurred!"
 */

/**
 * TODO: 4.11. Implement getPostsByTag() method which takes tag as an input parameter and
 *  returns all the posts corresponding to the tag using the findByTag() method of PostDAO interface.
 *  Note: The exception passed by DAO layer should not be passed to the presentation layer. Print the stack
 *  trace corresponding to the exception passed by DAO layer and throw a new exception of type Exception
 *  with a message "Some unexpected error occurred!"
 */

