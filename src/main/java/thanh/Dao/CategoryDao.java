package thanh.Dao;

import java.util.List;

import thanh.entity.Category;

public interface CategoryDao {
    void insert(Category c);
    void update(Category c);
    void delete(int id);
    Category findById(int id);
    List<Category> findAll();
    List<Category> findByOwner(int ownerId);
    List<Category> search(String keyword);
}
