package com.fzb.blog.controller;

import java.util.List;

import com.fzb.blog.model.Type;
import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class TypeController extends ManageController {
    public void delete() {
        Type.dao.deleteById(getPara("id"));
    }

    public void queryAll() {
        renderJson(Type.dao
                .queryAll(getParaToInt("page"), getParaToInt("rows")));
    }

    @Override
    public void add() {
        new Type().set("typeName", getPara("typeName"))
                .set("alias", getPara("alias"))
                .set("remark", getPara("remark")).save();
    }

    @Override
    public void update() {
        new Type().set("typeId", getPara("id"))
                .set("typeName", getPara("typeName"))
                .set("alias", getPara("alias"))
                .set("remark", getPara("remark")).update();
    }
    
    
    //获取类型的下拉列表
    //@ActionKey("/links/getTypesId")
    public void getTypeIds(){
    	List<Type> types=new Type().find("select typeId,typeName from type");
    	renderJson("types",types);
    }


}
