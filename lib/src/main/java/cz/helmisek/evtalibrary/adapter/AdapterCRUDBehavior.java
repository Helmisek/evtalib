package cz.helmisek.evtalibrary.adapter;

import java.util.List;


/**
 * Created by Jirka Helmich on 13.04.16.
 * <p/>
 * Interface which describes methods based on CRUD implementation which our {@link BaseAdapter} implements.
 */
public interface AdapterCRUDBehavior
{

	/**
	 * Create new item with provided entity and insert it into adapter.
	 *
	 * @param entity             - Any object which your viewholder or viewmodel can handle.
	 * @param shouldSmoothScroll - Flag to determine whether after insertion our recycler should scroll or not.
	 * @param viewTypeId         - ViewType ID to determine which type is going to handle your new item
	 */
	<T extends Object> void create(T entity, int viewTypeId, boolean shouldSmoothScroll);

	/**
	 * Create new item with provided entityList and insert it into adapter.
	 *
	 * @param entityList         - Any list of objects which your viewholder or viewmodel can handle.
	 * @param shouldSmoothScroll - Flag to determine whether after insertion our recycler should scroll or not.
	 * @param viewTypeId         - ViewType ID to determine which type is going to handle your new item
	 */
	<T extends Object> void create(List<T> entityList, int viewTypeId, boolean shouldSmoothScroll);

	/**
	 * Create new item with provided entity and insert it into adapter.
	 *
	 * @param entity     - Any object which your viewholder or viewmodel can handle.
	 * @param viewTypeId - ViewType ID to determine which type is going to handle your new item
	 */
	<T extends Object> void create(T entity, int viewTypeId);


	/**
	 * Create new item with provided entityList and insert it into adapter.
	 *
	 * @param entityList - Any list of objects which your viewholder or viewmodel can handle.
	 * @param viewTypeId - ViewType ID to determine which type is going to handle your new item
	 */
	<T extends Object> void create(List<T> entityList, int viewTypeId);

	/**
	 * Obtain specific item entity based provided position.
	 *
	 * @param position   - Specific position in list of specific view type.
	 * @param viewTypeId - ViewType ID to determine which type is going to handle your new item
	 */
	<T extends Object> T read(int position, int viewTypeId);

	/**
	 * Obtain specific item entity based provided position.
	 *
	 * @param position - Specific position in recycler
	 */
	<T extends Object> T read(int position);

	/**
	 * Update specific item's entity based on position and viewType Id. You need to provide new entity.
	 *
	 * @param position   - Specific position in list of specific view type.
	 * @param viewTypeId - ViewType ID to determine which type is going to handle your new item
	 */
	<T extends Object> void update(T entity, int position, int viewTypeId);

	/**
	 * Update specific item's entity based on position and viewType Id.
	 *
	 * @param position   - Specific position in list of specific view type.
	 * @param viewTypeId - ViewType ID to determine which type is going to handle your new item
	 */
	void update(int position, int viewTypeId);

	/**
	 * Update specific item's entity based on position and viewType Id. You need to provide new entity.
	 *
	 * @param entity   - Any object which your viewholder or viewmodel can handle.
	 * @param position -  Position of item in adapter.
	 */
	<T extends Object> void update(T entity, int position);

	/**
	 * Update specific item's entity based on position and viewType Id.
	 *
	 * @param position -  Position of item in adapter.
	 */
	void update(int position);

	/**
	 * Delete specific item based on specific list position and viewType ID.
	 *
	 * @param position   - Specific position in list of specific view type.
	 * @param viewTypeId - ViewType ID to determine which type is going to handle your new item
	 */
	void delete(int position, int viewTypeId);

	/**
	 * Delete specific item based on specific list position and viewType ID.
	 *
	 * @param position -  Position of item in adapter.
	 */
	void delete(int position);

}
