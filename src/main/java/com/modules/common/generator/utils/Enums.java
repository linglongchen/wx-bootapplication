package com.modules.common.generator.utils;


import java.io.Serializable;
import java.util.*;

/**
 *  枚举字典封装
 *
 *  @author: zxd
 *  @Date: 2019/12/5 14:23
 */
public class Enums implements Serializable {

    public String name(Object id) {
        if (id == null) {
            return "";
        }
        for (Iterator<item> i = list.iterator(); i.hasNext(); ) {
            item item = i.next();
            if (item.getId().equals(id)) {
                return item.getName();
            }
        }
        return "";
    }

    private List<item> list = new ArrayList<item>();

    public List<item> list() {
        return this.list;
    }

    public static Enums build() {
        return new Enums();
    }

    public Enums add(Object id, String name) {
        this.list.add(new item(id, name));
        return this;
    }

    public Enums addAll(Enums enums){
        this.list.addAll(enums.list);
        return this;
    }

    public Enums addAll(List<item> list){
        this.list.addAll(list);
        return this;
    }

    public List<item> getList() {
        return list;
    }

    public void setList(List<item> list) {
        this.list = list;
    }

    public Map<String, String> createMap(){
        Map<String, String> map = new HashMap<>();
        for(item item : list){
            map.put(item.getId() + "", item.getName());
        }
        return map;
    }

    public class item implements Serializable {
        public item(Object id, String name) {
            this.id = id;
            this.name = name;
        }

        private Object id;
        private String name;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
