package com.fzb.blog.model;

import com.fzb.common.util.ParseTools;
import com.jfinal.plugin.activerecord.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Type extends Model<Type> {
    public static final Type dao = new Type();

    public List<Type> queryAll() {
        return dao
                .find("select t.typeId as id,t.alias,t.typeName,t.remark,(select count(logId) from log where rubbish=? and private=? and typeid=t.typeid) as typeamount from type t",
                        false, false);
    }

    public Map<String, Object> queryAll(Integer page, Integer pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(
                "rows",
                find("select t.typeId as id,t.alias,t.typeName,t.remark,(select count(logId) from log where typeid=t.typeid) as typeamount from type t limit ?,?",
                        ParseTools.getFirstRecord(page,
                                pageSize), pageSize));
        fillData(page, pageSize, "from type", data, new Object[0]);
        return data;
    }

    private void fillData(int page, int pageSize, String where,
                          Map<String, Object> data, Object[] obj) {
        if (((List) data.get("rows")).size() > 0) {
            data.put("page", page);
            long count = findFirst("select count(1) cnt " + where, obj).getLong("cnt");
            data.put("total", ParseTools.getTotalPate(count, pageSize));
            data.put("records", count);
        } else {
            data.clear();
        }
    }

    public Type findByAlias(String alias) {
        return dao.findFirst("select * from type where alias=?", alias);
    }
    
    //查找前几条记录
    public List<Type> getFirstRecords() {
    	return dao
                .find("select typeId,alias,remark,sort,typeName from type order by sort limit ?,? ",
                        0, 8);
    }
}
