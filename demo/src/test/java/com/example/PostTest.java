package com.example;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostTest {
    private Post post;

    @BeforeEach
    void setUp() {
        post = new Post();
        post.setPostComments(new ArrayList<>());
    }

    // Test cases for addPost()

    @Test
    void testValidTitle() {
        // Test with a valid easy post
        post.setPostID(1);
        post.setPostTitle("Valid Title");
        post.setPostBody("A".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setEmergencyStatus("Ordinary");
        post.setPostType("Easy");
        assertTrue(post.addPost(), "Post should be added successfully");

        // Test with another valid post
        post.setPostID(2);
        post.setPostTitle("Another Valid Title");
        post.setPostBody("B".repeat(300));
        post.setPostTags(new String[]{"tag3", "tag4"});
        post.setEmergencyStatus("Ordinary");
        post.setPostType("Easy");
        assertTrue(post.addPost(), "Another post should be added successfully");
    }

    @Test
    void testInvalidTitle() {
        // Test with invalid title containing numbers
        post.setPostID(3);
        post.setPostTitle("12345Valid");
        post.setPostBody("A".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setEmergencyStatus("Ordinary");
        post.setPostType("Easy");
        assertFalse(post.addPost(), "Post with invalid title should not be added");

        // Test with null title
        post.setPostID(4);
        post.setPostTitle(null);
        post.setPostBody("B".repeat(300));
        post.setPostTags(new String[]{"tag3", "tag4"});
        post.setEmergencyStatus("Ordinary");
        post.setPostType("Easy");
        assertFalse(post.addPost(), "Post with null title should not be added");
    }

    @Test
    void testShortBody() {
        // Test with short body
        post.setPostID(5);
        post.setPostTitle("Valid Title");
        post.setPostBody("Short body");
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setEmergencyStatus("Ordinary");
        post.setPostType("Easy");
        assertFalse(post.addPost(), "Post with short body should not be added");

        // Test with another short body
        post.setPostID(6);
        post.setPostTitle("Valid Title");
        post.setPostBody("Short");
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setEmergencyStatus("Ordinary");
        post.setPostType("Easy");
        assertFalse(post.addPost(), "Post with short body should not be added");
    }

    @Test
    void testInsufficientTags() {
        // Test with only one tag
        post.setPostID(7);
        post.setPostTitle("Valid Title");
        post.setPostBody("A".repeat(300));
        post.setPostTags(new String[]{"tag1"});
        post.setEmergencyStatus("Ordinary");
        post.setPostType("Easy");
        assertFalse(post.addPost(), "Post with insufficient tags should not be added");

        // Test with null tags
        post.setPostID(8);
        post.setPostTitle("Valid Title");
        post.setPostBody("B".repeat(300));
        post.setPostTags(null);
        post.setEmergencyStatus("Ordinary");
        post.setPostType("Easy");
        assertFalse(post.addPost(), "Post with null tags should not be added");
    }

    @Test
    void testValidEmergencyStatus() {
        // Test with valid emergency status
        post.setPostID(9);
        post.setPostTitle("Valid Title");
        post.setPostBody("A".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setEmergencyStatus("Highly Needed");
        post.setPostType("Easy");
        assertTrue(post.addPost(), "Post should be added successfully");

        post.setPostID(10);
        post.setPostTitle("Valid Title");
        post.setPostBody("B".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setEmergencyStatus("Immediately Needed");
        post.setPostType("Easy");
        assertTrue(post.addPost(), "Post should be added successfully");
    }

    @Test
    void testValidDifficultyPost() {
        // Test with a valid Very Difficult post
        post.setPostID(12);
        post.setPostTitle("Valid Title");
        post.setPostBody("A".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2", "tag3", "tag4"});
        post.setEmergencyStatus("Immediately Needed");
        post.setPostType("Very Difficult");
        assertTrue(post.addPost(), "Post should be added successfully");
        
        // Test with a valid Difficult post
        post.setPostID(13);
        post.setPostTitle("Valid Title");
        post.setPostBody("B".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2", "tag3", "tag4"});
        post.setEmergencyStatus("Immediately Needed");
        post.setPostType("Easy");
        assertTrue(post.addPost(), "Post should be added successfully");
    }

    // Test cases for addComment()

    @Test
    void testValidComment() {
        // Test with a valid comment
        post.setPostID(7);
        post.setPostTitle("Valid Title");
        post.setPostBody("A".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setEmergencyStatus("Immediately Needed");
        post.setPostType("Difficult");
        post.addPost();
        assertTrue(post.addComment("This is a valid comment", post), "Valid comment should be added successfully");

        // Test with another valid comment
        assertTrue(post.addComment("This is also a valid comment", post), "Another valid comment should be added successfully");
    }

    @Test
    void testInvalidCommentText() {
        // Test with a too short comment
        post.setPostID(8);
        post.setPostTitle("Valid Title");
        post.setPostBody("A".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setEmergencyStatus("Immediately Needed");
        post.setPostType("Difficult");
        post.addPost();
        assertFalse(post.addComment("Too short", post), "Too short comment should not be added");

        // Test too long comment
        assertFalse(post.addComment("This comment is too long and therefore it is not valid", post), "Too long comment should not be added");
    }

    @Test
    void testExceedingMaxComments() {
        // Simulate exceeding max comments for an easy and ordinary post
        post.setPostID(9);
        post.setPostTitle("Valid Title");
        post.setPostBody("A".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setEmergencyStatus("Ordinary");
        post.setPostType("Easy");
        post.addPost();
        post.addComment("Example Comment Number 1", post);
        post.addComment("Example Comment Number 2", post);
        post.addComment("Example Comment Number 3", post);
        assertFalse(post.addComment("This is a valid comment", post), "Exceeding max comments should not be allowed");

        // Adding another comment after exceeding the limit
        assertFalse(post.addComment("This is also a valid comment", post), "Adding comment after exceeding limit should not be allowed");
    }

    @Test
    void testAddingMultipleComments() {
        // Test with valid comments for a Very Difficult post
        post.setPostID(10);
        post.setPostTitle("Valid Title");
        post.setPostBody("A".repeat(400));
        post.setPostTags(new String[]{"tag1", "tag2", "tag3", "tag4"});
        post.setEmergencyStatus("Immediately Needed");
        post.setPostType("Very Difficult");
        post.addPost();
        post.addComment("Example Comment Number 1", post);
        post.addComment("Example Comment Number 2", post);
        post.addComment("Example Comment Number 3", post);
        assertTrue(post.addComment("This is a valid comment", post), "Valid comment for Post should be added successfully");

        // Test with another valid comment for Very Difficult post
        assertTrue(post.addComment("This is also a valid comment", post), "Another valid comment for Post should be added successfully");
    }

    @Test
    void testInvalidFormatComment() {
        // Test with invalid format comment
        post.setPostID(11);
        post.setPostTitle("Valid Title");
        post.setPostBody("A".repeat(400));
        post.setPostTags(new String[]{"tag1", "tag2", "tag3", "tag4"});
        post.addPost();
        assertFalse(post.addComment("this is an invalid comment", post), "First word is not capitalised, comment should not be added");

        // Test with another invalid format comment
        assertFalse(post.addComment("123this is also an invalid comment", post), "First character is a number, comment should not be added");
    }

    @Test
    void testExceedingMaxCommentsForVeryDifficultPost() {
        // Simulate exceeding max comments for a Very Difficult post
        post.setPostID(12);
        post.setPostTitle("Valid Title");
        post.setPostBody("A".repeat(400));
        post.setPostTags(new String[]{"tag1", "tag2", "tag3", "tag4"});
        post.addPost();
        post.addComment("Example Comment Number 1", post);
        post.addComment("Example Comment Number 2", post);
        post.addComment("Example Comment Number 3", post);
        post.addComment("Example Comment Number 4", post);
        post.addComment("Example Comment Number 5", post);
        assertFalse(post.addComment("This is a valid comment", post), "Exceeding max comments for Very Difficult post should not be allowed");

        // Adding another comment after exceeding the limit for Very Difficult post
        assertFalse(post.addComment("this is an invalid comment", post), "Adding comment after exceeding limit for Very Difficult post should not be allowed");
    }
}
