package respring.dorering.rest.CustomerService.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import respring.dorering.rest.CustomerService.dto.CustomerServicePostDto;
import respring.dorering.rest.CustomerService.entity.CustomerServicePost;
import respring.dorering.rest.CustomerService.service.CustomerServicePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer-service-posts")
public class CustomerServicePostController {

    private final CustomerServicePostService customerServicePostService;

    @Autowired
    public CustomerServicePostController(CustomerServicePostService customerServicePostService) {
        this.customerServicePostService = customerServicePostService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<CustomerServicePost> postPage = customerServicePostService.findAllPosts(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("posts", postPage.getContent());
        response.put("currentPage", postPage.getNumber());
        response.put("totalItems", postPage.getTotalElements());
        response.put("totalPages", postPage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomerServicePostDto> createPost(@RequestBody CustomerServicePostDto postDto) {
        CustomerServicePostDto createdPost = customerServicePostService.createPost(postDto);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping("/{boardCode}")
    public ResponseEntity<CustomerServicePost> getPostByBoardCode(@PathVariable Integer boardCode) {
        return customerServicePostService.findPostById(boardCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{boardCode}")
    public ResponseEntity<CustomerServicePost> updatePost(
            @PathVariable Integer boardCode,
            @RequestBody CustomerServicePost postUpdateRequest) {
        CustomerServicePost updatedPost = customerServicePostService.updatePost(boardCode, postUpdateRequest);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{boardCode}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer boardCode) {
        customerServicePostService.deletePost(boardCode);
        return ResponseEntity.ok().build();
    }
}