package study.demo.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.demo.entity.User2;

public interface UserRepository extends JpaRepository<User2, Long> {
    @Query("select u from User2 u")
    Page<User2> findList(Pageable pageable);
    User2 findById(long id);
    User2 findByUserName(String username);
    void deleteById(Long id);
}
