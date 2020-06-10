package app.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface DAO<A> {
    List<A> getAll();
    List<A> getBy(int id);
    Optional<A> get(int id);
    void put(A a);
}
