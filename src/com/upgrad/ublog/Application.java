package com.upgrad.ublog;

import com.upgrad.ublog.dtos.Post;
import com.upgrad.ublog.dtos.User;
import com.upgrad.ublog.exceptions.PostNotFoundException;
import com.upgrad.ublog.services.PostService;
import com.upgrad.ublog.services.ServiceFactory;
import com.upgrad.ublog.services.UserService;
import com.upgrad.ublog.utils.LogWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Application {
    private Scanner scanner;

    private PostService postService;
    private UserService userService;

    private boolean isLoggedIn;//flog to check whether user logged in or not
    private String loggedInEmailId;// an attribute to save emailId of user when he logged

    public Application(PostService postService, UserService userService) {
        scanner = new Scanner(System.in);
        this.postService = postService;
        this.userService = userService;
        isLoggedIn = false;
        loggedInEmailId = null;
    }

    private void start() {
        boolean flag = true;

        System.out.println("*********************");
        System.out.println("********U-Blog*******");
        System.out.println("*********************");

        do {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Create Post");
            System.out.println("4. Search Post");
            System.out.println("5. Delete Post");
            System.out.println("6. Filter Post");
            System.out.println("7. Logout");
            System.out.println("8. Exit");

            System.out.print("\nPlease select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": login(); break;
                case "2": register(); break;
                case "3": createPost(); break;
                case "4": searchPost(); break;
                case "5": deletePost(); break;
                case "6": filterPost(); break;
                case "7": logout();  break;
                case "8": flag = false; break;
                default: System.out.println("Error"); break;
            }
        } while (flag);
    }
    //This method is used to perform login function for the user.
    //If the user is already logged in, then he won't be able to login again.
    //Also a user can only login, if the emailId and password matches to the those given while registering.
    private void login() {
        if (isLoggedIn) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("********Login********");
        System.out.println("*********************");

        User user = new User();
        System.out.println("Enter Email Id: ");
        String emailId=scanner.nextLine();
        user.setEmailId(emailId);
        System.out.println("enter password: ");
        user.setPassword(scanner.nextLine());
        try{
            userService.login(user);
            System.out.println("you are logged in");
            isLoggedIn=true;
            loggedInEmailId= emailId;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //This method is used to perform register function for the user.
    //If the user is already logged in, then he won't be able to register.
    //Also a user can only register, if the no user already registered with given email Id.
    private void register() {
        if (isLoggedIn) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("******Register*******");
        System.out.println("*********************");

        User user = new User();
        System.out.println("Enter Email Id: ");
        String emailId=scanner.nextLine();
        user.setEmailId(emailId);
        System.out.println("enter password: ");
        user.setPassword(scanner.nextLine());
        try{
            userService.register(user);
            System.out.println("You are logged in.");
            isLoggedIn=true;
            loggedInEmailId= emailId;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void createPost() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*****Create Post*****");
        System.out.println("*********************");

        Post post= new Post();
        post.setPostId();
        post.setEmailId(loggedInEmailId);
        System.out.println("enter post tag: ");
        post.setTag(scanner.nextLine());
        System.out.println("enter post title: ");
        post.setTitle(scanner.nextLine());
        System.out.println("enter post description");
        post.setDescription(scanner.nextLine());
        post.setTimestamp( LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        try{
            postService.create(post);
            System.out.println("post is created");

            String messege=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))+" "
                    +"New post with title ["+post.getTitle()+"] created by [ "+post.getEmailId()+"]";
            String directory=System.getProperty("user.dir");//to fetch file path
            LogWriter.writeLog(messege, directory);

            return;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private void searchPost() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*****Search Post*****");
        System.out.println("*********************");

        System.out.println("enter emailId : ");
        List<Post> post=null;
        try{
            String emailId=scanner.nextLine();
            post=postService.getPostsByEmailId(emailId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(post.size()!=0){
            for (Post eachPost: post){
                System.out.println(eachPost);
            }
            }
        else {
            System.out.println("Sorry no posts exists for this email id");
            return;
        }
    }

    private void deletePost() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*****Delete Post*****");
        System.out.println("*********************");

        System.out.println("enter postId: ");

        try{
            int postId=scanner.nextInt();
            String  s=scanner.nextLine();
            if(postService.deletePost(postId,loggedInEmailId)){
            System.out.println("post deleted successfully ");
            return;
            }
            else {
                System.out.println(" You are not authorised to delete this post");
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterPost() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*****Filter Post*****");
        System.out.println("*********************");

        Set<String> tagSet=new HashSet<>();

        try {
            tagSet = postService.getAllTags();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }if(tagSet!=null){
            System.out.println("available tags are : ");
        for (String tag: tagSet) {
            System.out.println(tag);
        }
        }  System.out.println("select from above tags : ");
        String tag =scanner.nextLine();
        List<Post> post=new ArrayList<>();
        try{
            post=postService.getPostsByTag(tag);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(post!=null){
            for (Post eachPost: post){
                System.out.println(eachPost);
            }
        }
    }

    private void logout() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }
        System.out.println("Logged out successfully");
        isLoggedIn = false;
        loggedInEmailId = null;
    }

    public static void main(String[] args) {
        ServiceFactory serviceFactory = new ServiceFactory();
        UserService userService = serviceFactory.getUserService();
        PostService postService = serviceFactory.getPostService();
        Thread logWriter =new Thread();
        logWriter.start();
        Application application = new Application(postService, userService);
        application.start();

    }
}
