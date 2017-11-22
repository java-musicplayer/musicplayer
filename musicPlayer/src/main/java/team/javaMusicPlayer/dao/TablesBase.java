package team.javaMusicPlayer.dao;

public interface TablesBase <T>{
	public boolean insert(T obj);
	public boolean update(T nobj);
	public boolean delete(int id);
	public T getById(int id);
}
