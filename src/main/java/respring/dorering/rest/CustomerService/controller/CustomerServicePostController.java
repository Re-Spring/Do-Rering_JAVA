package respring.dorering.rest.CustomerService.controller;

import respring.dorering.rest.CustomerService.dto.CustomerServicePostDto;
import respring.dorering.rest.CustomerService.entity.CustomerServicePost;
import respring.dorering.rest.CustomerService.service.CustomerServicePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-service-posts")
public class CustomerServicePostController {

    private final CustomerServicePostService customerServicePostService;

    @Autowired
    public CustomerServicePostController(CustomerServicePostService customerServicePostService) {
        this.customerServicePostService = customerServicePostService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerServicePost>> getAllPosts() {
        List<CustomerServicePost> posts = customerServicePostService.findAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<CustomerServicePostDto> createPost(@RequestBody CustomerServicePostDto postDto) {
        CustomerServicePostDto createdPost = customerServicePostService.createPost(postDto);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping("/{user_code}")
    public ResponseEntity<CustomerServicePostDto> getUserCodePost(@PathVariable Integer user_code) {
        return customerServicePostService.findPostByUserCode(user_code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{user_code}")
    public ResponseEntity<CustomerServicePost> updatePost(
            @PathVariable Integer user_code,
            @RequestBody CustomerServicePost postUpdateRequest) {
        CustomerServicePost updatedPost = customerServicePostService.updatePost(user_code, postUpdateRequest);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{user_code}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer user_code) {
        customerServicePostService.deletePost(user_code);
        return ResponseEntity.ok().build();
    }
}
