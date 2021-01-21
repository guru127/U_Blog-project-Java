package com.upgrad.ublog.services;

import com.upgrad.ublog.dao.DAOFactory;
import com.upgrad.ublog.dao.PostDAO;
import com.upgrad.ublog.dao.PostDAOImpl;
import com.upgrad.ublog.dtos.Post;
import com.upgrad.ublog.exceptions.PostNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
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
        List<Post> postList=new ArrayList<>();
        try {
            postList = postDAO.findByTag(tag);
        } catch (SQLException e) {
            throw new Exception("Some unexpected exception occurred.");
        }
        return postList;
    }

    @Override
    public Set<String> getAllTags() throws Exception {
        Set<String> tagSet =new HashSet<>();
        List<String> tagList = postDAO.findAllTags();
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
        boolean flag=false;
        try {
            post = postDAO.findByPostId(postId);
        } catch (SQLException e) {
            throw new PostNotFoundException("No Post exist with the given Post Id ");
        }

        if (post != null && emailId.equals(post.getEmailId())) {
            try {
                postDAO.deleteByPostId(postId);
                flag=true;
            } catch (SQLException e) {
                System.out.println("Some unexpected error occurred!");
            }
        }

        return flag;
    }

}
