package com.taobao.ideabox.action;

import com.taobao.ideabox.dao.IdeaDAO;
import com.taobao.ideabox.entity.impl.IdeaDO;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
        for(IdeaDO ideaDO:ideaList ){
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

    @RequestMapping(value="/idea/upload",method = RequestMethod.POST)
    @ResponseBody
    public void upload(HttpServletRequest request, HttpServletResponse response, PrintWriter out)throws Exception{
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
//        IdeaDO ideaDO = new IdeaDO();
        int ideaId = Integer.parseInt(request.getParameter("id"));
        IdeaDO ideaDO = ideaDAO.selectById(ideaId);
        if(ideaDO == null){
            request.setAttribute("upload.message", "点子参数错误！");//
            return;
        }
        try{
            List items = upload.parseRequest(request);
            for (Object item : items) {
                FileItem fileItem = (FileItem) item;
                if (fileItem.isFormField()) {
                    //表单信息
                } else {
                    if (fileItem.getName() != null && !fileItem.getName().equals("")) {
                        File tempFile = new File(fileItem.getName());// 构造临时对象
                        String url = System.getProperty("user.dir") + "/resource";
                        File file1 = new File(url);
                        if (!file1.exists()) {
                            file1.mkdirs();
                        }
                        File file = new File(url + "/", tempFile.getName());
                        fileItem.write(file);// 保存文件在服务器的物理磁盘中
                        InputStream in = new FileInputStream(file);
                        ideaDO.setPhoto(file.getAbsolutePath());
                        request.setAttribute("upload.message", "上传文件成功！");//
                    }
                }
            }
            ideaDAO.update(ideaDO);
        } catch (Exception e){
            request.setAttribute("upload.message", "上传文件失败！");
        }
    }

    @RequestMapping(value="/idea/add",method = RequestMethod.POST)
    @ResponseBody
    public void add(HttpServletRequest request, HttpServletResponse response, PrintWriter out)throws Exception{
        IdeaDO ideaDO = new IdeaDO();
        String tags = request.getParameter("tags");
        Integer status = Integer.getInteger( request.getParameter("status"));
        if(status == null){
            status = 0;
        }
        JSONObject idea = new JSONObject();
        if("".equals(tags)){
            idea.put("ideaId", 0);
        } else {
            ideaDO.setDescription(tags);
            ideaDO.setStatus(status);
            int ideaId = ideaDAO.insert(ideaDO);
            idea.put("ideaId", ideaId);
        }
        response.setContentType("application/json");
        out.print(idea.toString());
    }
}
