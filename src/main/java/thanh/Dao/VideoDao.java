package thanh.Dao;

import java.util.List;

import thanh.entity.Video;

public interface VideoDao {
    void insert(Video v);
    void update(Video v);
    void delete(int id);
    Video findById(int id);
    List<Video> findAll();
    List<Video> findByCategory(int categoryId);
    List<Video> search(String keyword);
}
