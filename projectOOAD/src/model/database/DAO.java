package model.database;

public interface DAO<T> {
	public T selectById(int id);
	public T selectByName(String name);
	public void insert(T obj);
	public void update(T obj);
	public void delete(T obj);
}
