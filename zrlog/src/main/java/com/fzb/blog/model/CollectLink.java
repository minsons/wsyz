package com.fzb.blog.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fzb.common.util.ParseTools;
import com.jfinal.plugin.activerecord.Model;

public class CollectLink extends Model<CollectLink> {
	
	 public static final CollectLink dao = new CollectLink();

	/**
	 * 
	 */
	private static final long serialVersionUID = -3085621410195871712L;

	  /**
	   * 查找所有的收藏链接
	   * @return
	   */
	   public List<CollectLink> queryAll() {
	        return find("select collectId,name ,url,remark,typeId  from collectlink order by sort");
	    }
	
	   /**
	    * 分页查询所有的收藏链接
	    * @param page
	    * @param pageSize
	    * @return
	    */
	   public Map<String, Object> queryAll(Integer page, Integer pageSize) {
	        Map<String, Object> data = new HashMap<String, Object>();
	        data.put(
	                "rows",
	                find("select collectId,name ,url,remark,typeId  from collectlink order by sort limit ?,?",
	                        ParseTools.getFirstRecord(page,
	                                pageSize), pageSize));
	        fillData(page, pageSize, "from collectlink", data, new Object[0]);
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
	
	  //获取某个类型的链接
	  public List<CollectLink> queryLinkByTypeId(int typeId){
		  return find("select collectId,name,url,remark  from collectlink where typeId=? and state=1 limit 0,50 " ,typeId);
	  }
	
}
