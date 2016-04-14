package cz.helmisek.evtalibrary.adapter;

/**
 * Created by Jirka Helmich on 13.04.16.
 */
public interface AdapterCRUDBehavior
{
	<T extends Object> void create(T entity, int viewTypeId, boolean shouldSmoothScroll);

	<T extends Object> void create(T entity, int viewTypeId);

	<T extends Object> T read(int position, int viewTypeId);

	<T extends Object> void update(T entity, int position, int viewTypeId);

	<T extends Object> void update(int position, int viewTypeId);

	<T extends Object> void delete(int position, int viewTypeId);

}
