package com.example;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String[] postTypes = {"Very Difficult", "Difficult", "Easy"};
    private String[] postEmergency = {"Immediately Needed", "Highly Needed", "Ordinary"};
    private ArrayList<String> postComments = new ArrayList<>();
    private String emergencyStatus;
    private String postType;

    // Getters and Setters
    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String[] getPostTags() {
        return postTags;
    }

    public void setPostTags(String[] postTags) {
        this.postTags = postTags;
    }

    public ArrayList<String> getPostComments() {
        return postComments;
    }

    public void setPostComments(ArrayList<String> postComments) {
        this.postComments = postComments;
    }

    public String getEmergencyStatus() {
        return emergencyStatus;
    }

    public void setEmergencyStatus(String emergencyStatus) {
        this.emergencyStatus = emergencyStatus;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    private boolean isValidEmergencyStatus(String status) {
        for (String validStatus : postEmergency) {
            if (validStatus.equals(status)) {
                return true;
            }
        }
        return false;
    }

    public boolean addPost() {
        // Validate post information
        if (postTitle == null || postTitle.isEmpty() || !postTitle.matches("[A-Za-z ]+")) {
            return false;
        }
        if (postBody == null || postBody.length() < 300) {
            return false;
        }
        if (postTags == null || postTags.length < 2) {
            return false;
        }
        if (!isValidEmergencyStatus(emergencyStatus)) {
            return false;
        }

        if (emergencyStatus.equals("Easy") && (postType.equals("Highly Needed") || postType.equals("Immediately Needed"))) {
            return false;
        }

        // Append post information to post.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("post.txt", true))) {
            writer.write("Post ID: " + postID);
            writer.newLine();
            writer.write("Title: " + postTitle);
            writer.newLine();
            writer.write("Body: " + postBody);
            writer.newLine();
            writer.write("Tags: " + String.join(", ", postTags));
            writer.newLine();
            writer.write("Emergency: " + emergencyStatus);
            writer.newLine();
            writer.write("Type: " + postType);
            writer.newLine();
            writer.write("Comments: " + String.join(", ", postComments));
            writer.newLine();
            writer.write("----");
            writer.newLine();
            System.out.println("Post added to file successfully");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addComment(String comment, Post Post) {
        // Validate comment size
        if (comment == null || comment.isEmpty())  {
            return false;
        }

        // Validate first letter is uppercase
        if (!Character.isUpperCase(comment.charAt(0))) {
            return false;
        }
        
        //Validate comment limit
        if ("Ordinary".equals(Post.getEmergencyStatus()) && postComments.size() >2) {
            return false;
        }

        if ("Easy".equals(Post.getPostType()) && postComments.size() >2) {
            return false;
        }

        if (postComments.size() > 4) {
            return false;
        }

        //Validate word count
        String[] words = comment.trim().split("\\s+");
        if (words.length < 4 || words.length > 10) {
            return false;
        }


        // Append comment to comment.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("comment.txt", true))) {
            writer.write("Post ID: " + postID);
            writer.newLine();
            writer.write("Comment: " + comment);
            writer.newLine();
            writer.write("----");
            writer.newLine();
            postComments.add(comment);
            System.out.println("Comment added to file successfully");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
