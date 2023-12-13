package model.database;

public interface DAO<T> {
	public T select(String id);
	public void insert(T obj);
	public void update(T obj);
	public void delete(T obj);
}
