package com.tmb.ms.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.tmb.ms.entity.Activity;
import com.tmb.ms.entity.Item;

@Service
public class LoanUtil {
	public Set<Item> prepareItems(Set<Item> exstItems, Set<Item> reqItems, long id) throws Exception {
		if (exstItems == null)
			if (reqItems == null)
				return null;
			else
				return prepareItems(reqItems, id);
		else {
			if (reqItems == null)
				return exstItems;
			else {
				Set<Item> resItems = new HashSet<Item>();
				Set<Item> selectedItems = new HashSet<Item>();
				for (Item exstItem : exstItems) {
					if (reqItems.contains(exstItem)) {
						resItems.add(exstItem);
						selectedItems.add(exstItem);
					}
				}
				prepareItems(reqItems, id);
				exstItems.removeAll(selectedItems);
				resItems.addAll(reqItems);
				return resItems;
			}
		}
	}

	public Set<Item> prepareItems(Set<Item> items, long id) throws Exception {
		for (Item item : items)
			item.setLoanId(id);
		return items;
	}

	public Set<Activity> prepareActivities(Set<Activity> exstActivities, Set<Activity> reqActivities, long id) {
		if (exstActivities == null)
			if (reqActivities == null)
				return null;
			else
				return prepareActivities(reqActivities, id);
		else if (reqActivities == null)
			return exstActivities;
		else {
			Set<Activity> resActivities = new HashSet<Activity>();
			Set<Activity> selectedActivities = new HashSet<Activity>();
			for (Activity exstActivity : exstActivities) {
				if (reqActivities.contains(exstActivity)) {
					resActivities.add(exstActivity);
					selectedActivities.add(exstActivity);
				}
			}
			prepareActivities(reqActivities, id);
			exstActivities.removeAll(selectedActivities);
			resActivities.addAll(reqActivities);
			return resActivities;
		}
	}

	public Set<Activity> prepareActivities(Set<Activity> activities, long id) {
		for (Activity activity : activities)
			activity.setLoanId(id);
		return activities;
	}
}