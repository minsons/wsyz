package com.fzb.blog.model;

import com.fzb.common.util.ParseTools;
import com.jfinal.plugin.activerecord.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Link extends Model<Link> {
    public static final Link dao = new Link();

    public List<Link> queryAll() {
        return find("select linkName,linkId as id,sort,url from link order by sort");
    }

    public Map<String, Object> queryAll(Integer page, Integer pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(
                "rows",
                find("select linkName,linkId as id,sort,url,alt from link order by sort limit ?,?",
                        ParseTools.getFirstRecord(page,
                                pageSize), pageSize));
        fillData(page, pageSize, "from link", data, new Object[0]);
        return data;
    }

    private void fillData(int page, int pageSize, String where, Map<String, Object> data, Object[] obj) {
        if (((List) data.get("rows")).size() > 0) {
            data.put("page", page);
            long count = findFirst("select count(1) cnt " + where, obj).getLong("cnt");
            data.put("total", ParseTools.getTotalPate(count, pageSize));
            data.put("records", count);
        } else {
            data.clear();
        }
    }
}
