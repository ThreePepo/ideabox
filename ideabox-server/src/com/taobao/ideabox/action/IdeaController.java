package com.taobao.ideabox.action;

import com.taobao.ideabox.dao.IdeaDAO;
import com.taobao.ideabox.entity.impl.IdeaDO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-4
 * Time: 下午3:50
 */
@Controller
public class IdeaController {
    @Resource
     IdeaDAO ideaDAO;

    /**
     * 以JSON方式返回查询结果
     * @param request
     * @param out
     */
    @RequestMapping(value="/idea/query",method = RequestMethod.GET)
    @ResponseBody
    public void  queryIdeas(HttpServletRequest request, HttpServletResponse response, PrintWriter out)throws Exception{
        List<IdeaDO> ideaList = ideaDAO.query("",0,20);
        //以JSON方式输出查询数据
        JSONObject ideas = new JSONObject();
        JSONArray array = new JSONArray();
        for(IdeaDO ideaDO: ideaList){
            JSONObject idea = new JSONObject();
            idea.put("description", ideaDO.getDescription());
            idea.put("video",ideaDO.getVideo());
            idea.put("photo",ideaDO.getPhoto());
            idea.put("clicks",ideaDO.getClicks());
            idea.put("owner",ideaDO.getOwner());
            idea.put("userId",ideaDO.getUserId());
            array.put(idea);
        }
        ideas.put("ideas",array);
        response.setContentType("application/json");
        out.print(ideas.toString());
    }

    @RequestMapping(value="/idea/add",method = RequestMethod.POST)
    public void add(){

    }
}
