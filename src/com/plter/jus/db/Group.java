package com.plter.jus.db;

import com.plter.jus.db.entities.GroupsEntity;
import org.hibernate.Session;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * Created by plter on 6/23/15.
 */
public class Group {

    public static final String KEY_GROUPS = "groups";

    public static void retrieveGroups(Session session,ModelMap map){
        List<GroupsEntity> list = session.createCriteria(GroupsEntity.class).list();
        map.addAttribute(KEY_GROUPS,list);
    }
}
