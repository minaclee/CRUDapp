package minmin.collabera.Crudservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import minmin.collabera.Crudservice.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
