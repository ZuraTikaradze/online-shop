package ge.tikaradze.shop.repository;

import ge.tikaradze.shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findFirstByTitle(String title);
}
