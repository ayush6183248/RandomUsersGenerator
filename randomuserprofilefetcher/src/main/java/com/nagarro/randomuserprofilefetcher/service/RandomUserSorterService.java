package com.nagarro.randomuserprofilefetcher.service;

import java.util.Comparator;
import java.util.List;

import javax.swing.SortOrder;

import com.nagarro.randomuserprofilefetcher.constants.ValidationConstants;
import com.nagarro.randomuserprofilefetcher.entities.RandomUsersInfo;

public class RandomUserSorterService {
	
    public static List<RandomUsersInfo> sortRandomUsersInfos(List<RandomUsersInfo> RandomUsersInfoList, String sortType, String sortOrder) {
//        Comparator<RandomUsersInfo> comparator = (RandomUsersInfo1, RandomUsersInfo2) -> {
//            int comparison;
//
//            switch (sortType.toLowerCase()) {
//                case ValidationConstants.AGE:
//                    comparison = Integer.compare(RandomUsersInfo1.getAge() % 2, RandomUsersInfo2.getAge() % 2);
//                    break;
//                case ValidationConstants.NAME:
//                    comparison = Integer.compare(RandomUsersInfo1.getName().length() % 2, RandomUsersInfo2.getName().length() % 2);
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unsupported sort type: " + sortType);
//            }
//
//            return sortOrder.equalsIgnoreCase(ValidationConstants.EVEN) ? comparison : -comparison;
//        };
//
//        RandomUsersInfoList.sort(comparator);
        return RandomUsersInfoList;
    }
}
