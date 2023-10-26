package services;

import entities.User;
import exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public User update(Long id, User user) {
        try {
            if (userRepository.existsById(id)) {
                user.setId(id);
                return userRepository.save(user);
            } else {
                throw new ResourceNotFoundException("User not found with id: " + id);
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }
}
