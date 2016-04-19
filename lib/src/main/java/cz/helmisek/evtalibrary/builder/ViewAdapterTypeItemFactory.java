package cz.helmisek.evtalibrary.builder;

import java.util.ArrayList;
import java.util.List;

import cz.helmisek.evtalibrary.adapter.entity.AdapterViewType;
import cz.helmisek.evtalibrary.adapter.entity.AdapterViewTypeValue;


/**
 * Helper factory to utilize view type item adding system.
 */
public class ViewAdapterTypeItemFactory
{

	public static <T extends Object> List<AdapterViewType> addViewTypeItemList(List<AdapterViewType> viewTypes, int viewTypeId, List<T> content)
	{
		List<AdapterViewType> list = new ArrayList<>();
		for(AdapterViewType type : viewTypes)
		{
			if(type.getViewTypeId() == viewTypeId)
			{
				for(T entity : content)
				{
					list.add(new AdapterViewType(type, new AdapterViewTypeValue(entity)));
				}
				break;
			}
		}
		return list;
	}


	public static <T extends Object> List<AdapterViewType> addViewTypeItem(List<AdapterViewType> viewTypes, int viewTypeId, T singleContent)
	{
		List<T> list = new ArrayList<>();
		if(singleContent == null)
		{
			list.add((T) new Object());
		}
		else
		{
			list.add(singleContent);
		}
		return addViewTypeItemList(viewTypes, viewTypeId, list);
	}


	public static <T extends Object> List<AdapterViewType> addViewTypeItem(List<AdapterViewType> viewTypes, int viewTypeId)
	{
		return addViewTypeItem(viewTypes, viewTypeId, null);
	}
}
