package com.fzb.blog.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import com.fzb.blog.model.CollectLink;

public class CollectLinkController extends ManageController {

	 public void delete() {
	        CollectLink.dao.deleteById(getPara("id"));
	    }

	    public void update() {
	        CollectLink.dao.set("collectId", getPara("collectId"))
	                .set("name", getPara("name"))
	                .set("sort", getParaToInt("sort", 100))
	                .set("url", getPara("url")).set("remark", getPara("remark")).update();
	    }

	    public void index() {
	        render("/admin/collect_link.jsp");
	    }

	    public void queryAll() {
	        renderJson(CollectLink.dao.queryAll(getParaToInt("page"), getParaToInt("rows")));
	    }

	    public void add() {
	        new CollectLink().set("name", getPara("name"))
	                .set("sort", getParaToInt("sort", 100))
	                .set("url", getPara("url")).set("remark", getPara("remark")).save();
	    }
	    
	    public void add2() throws UnsupportedEncodingException {
	    	boolean fals=true;
	    	if(getPara("name")==null || getPara("name").trim().equals("")){
	    		fals=false;
	    	}
           if(getPara("url")==null || getPara("url").trim().equals("")){
        	   fals=false;
        	   
	    	}
	    	
           if(fals){
        	   new CollectLink().set("name",new String(getPara("name").getBytes("ISO-8859-1"),"UTF-8"))
               .set("sort", getParaToInt("sort", 100))
               .set("url", getPara("url")).set("remark", new String(getPara("remark").getBytes("ISO-8859-1"),"UTF-8"))
               .set("state",1).set("typeId", getParaToInt("typeId", 100))
               .set("createTime", new Date()).save();
               renderJson("{'result':'success','status':'200'}");
           }else{
        	   renderJson("{'result':'faile','status':'500'}");
           }
	       
	    }

}
