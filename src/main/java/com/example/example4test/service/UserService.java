package com.example.example4test.service;

import com.example.example4test.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
  User saveUser (User user);
  User getUserById(Long id);

  void deleteUserById(Long id);
  List<User> getAllUser(String keyword);
  Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
