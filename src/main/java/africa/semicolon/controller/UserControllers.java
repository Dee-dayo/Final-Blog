package africa.semicolon.controller;

import africa.semicolon.dto.requests.*;
import africa.semicolon.dto.responses.*;
import africa.semicolon.exceptions.FinalBlogExceptions;
import africa.semicolon.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/Blogspot")
public class UserControllers {

    @Autowired
    private UserServices userServices;

    @PostMapping("/sign_up")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest){
        try {
            UserRegisterResponse response = userServices.register(userRegisterRequest);
            return new ResponseEntity<>(new UserApiResponse(true, response), CREATED);
        } catch(FinalBlogExceptions e){
            return new ResponseEntity<>(new UserApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/sign_in")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest){
        try {
            UserLoginResponse response = userServices.login(userLoginRequest);
            return new ResponseEntity<>(new UserApiResponse(true, response), ACCEPTED);
        } catch (FinalBlogExceptions e){
            return new ResponseEntity<>(new UserApiResponse(false, e.getMessage()), FORBIDDEN);
        }
    }

    @PostMapping("/create_post")
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequest createPostRequest){
        try {
            CreatePostResponse response = userServices.createPost(createPostRequest);
            return new ResponseEntity<>(new UserApiResponse(true, response), CREATED);
        } catch (FinalBlogExceptions e){
            return new ResponseEntity<>(new UserApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete_post")
    public ResponseEntity<?> deletePost(@RequestBody DeletePostRequest deletePostRequest){
        try {
            DeletePostResponse response = userServices.deletePost(deletePostRequest);
            return new ResponseEntity<>(new UserApiResponse(true, response), ACCEPTED);
        }catch (FinalBlogExceptions e) {
            return new ResponseEntity<>(new UserApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/sign_out")
    public ResponseEntity<?> logout(@RequestBody UserLogoutRequest userLogoutRequest){
        try {
            UserLogoutResponse response = userServices.logout(userLogoutRequest);
            return new ResponseEntity<>(new UserApiResponse(true, response), ACCEPTED);
        } catch (FinalBlogExceptions e){
            return new ResponseEntity<>(new UserApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/view_post")
    public ResponseEntity<?> viewPost(@RequestBody ViewPostRequest viewPostRequest){
        try {
            ViewPostResponse response = userServices.viewPost(viewPostRequest);
            return new ResponseEntity<>(new UserApiResponse(true, response), OK);
        } catch (FinalBlogExceptions e){
            return new ResponseEntity<>(new UserApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/add_comment")
    public ResponseEntity<?> addComment(@RequestBody CommentPostRequest commentPostRequest){
        try {
            CommentPostResponse response = userServices.addComment(commentPostRequest);
            return new ResponseEntity<>(new UserApiResponse(true, response), CREATED);
        } catch (FinalBlogExceptions e){
            return new ResponseEntity<>(new UserApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete_comment")
    public ResponseEntity<?> deleteComment(@RequestBody DeleteCommentRequest deleteCommentRequest){
        try {
            CommentPostResponse response = userServices.deleteComment(deleteCommentRequest);
            return new ResponseEntity<>(new UserApiResponse(true, response), ACCEPTED);
        } catch (FinalBlogExceptions e){
            return new ResponseEntity<>(new UserApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @GetMapping("/all_posts")
    public ResponseEntity<?> viewAllPosts(@RequestBody String username){
        try {
            UserPostsResponse response = userServices.getUserPosts(username);
            return new ResponseEntity<>(new UserApiResponse(true, response), ACCEPTED);
        } catch (FinalBlogExceptions e){
            return new ResponseEntity<>(new UserApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}
