package com.yj.web.controller;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.model.*;
import com.yj.service.service.impl.FrCustomerCourseProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * by Kylan
 * 课程项目/产品 类
 * @author YongL
 * @date 2018/12/17
 */
@RestController
@RequestMapping("/frCustomerCourse")
public class FrCustomerCourseProjectController {


    /**
     * 课程项目/产品实现类
     */
    @Autowired
    private FrCustomerCourseProjectServiceImpl frCustomerCourseProjectService;


    /**
     * 根据businessTypeId 查询技师
     *
     * @param request
     * @param businessTypeId
     * @return
     */
    @GetMapping("getMarketUserList")
    public JsonResult getTechnicianBySdaduimId(HttpServletRequest request, String businessTypeId, String userType) {
        String code = CookieUtils.getCookieValue(request, "code", true);
        List<PersonnelInfo> personnelInfos = frCustomerCourseProjectService.getTechnicianBySdaduimId(userType, businessTypeId, code);
        return JsonResult.success(personnelInfos);

    }

    /**
     * 新建项目
     *
     * @param map
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("addSaveCustomer")
    public JsonResult addSaveCustomer(@RequestBody Map<String, String> map, HttpServletRequest request) throws YJException {
        if (map == null) {
            throw new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        String id = CookieUtils.getCookieValue(request, "id", true);
        String name = CookieUtils.getCookieValue(request, "name", true);
        map.put("createUserId", id);
        map.put("createUserName", name);
        JsonResult jsonResult = null;
        frCustomerCourseProjectService.addSaveCustomer(map);
        return JsonResult.success(map);

    }

    /**
     * 用户id查询全部
     * @param request
     * @return
     */
    @GetMapping("fetchOrderListByUserId")
    public JsonResult fetchOrderListByUserId( HttpServletRequest request){
        String cid = request.getParameter("cid");
        String code = CookieUtils.getCookieValue(request, "code", true);
        String shopId = CookieUtils.getCookieValue(request, "shopid", true);
        List list = frCustomerCourseProjectService.getOrderListByCid(shopId, code, cid);
        return JsonResult.success(list);
    }

    /**
     * 启用
     * @param request
     * @return
     */
    @PostMapping("customerStar")
    public JsonResult starCustomer(@RequestBody Map<String, String> map,HttpServletRequest request){
        String orderId = map.get("orderId");
        String cid = map.get("cid");
        String name = CookieUtils.getCookieValue(request, "name", true);
        String code = CookieUtils.getCookieValue(request, "code", true);
        AddProject addProject = frCustomerCourseProjectService.starCustomer(orderId, cid, name, code);
        return JsonResult.success(addProject);
    }


    /**
     * 补余
     * @param request
     * @return
     */
    @PostMapping("customerRemnant")
    public JsonResult customerRemnant(@RequestBody Map<String, String> map, HttpServletRequest request){
        String orderId = map.get("orderId");
        String cid = map.get("cid");
        String name = CookieUtils.getCookieValue(request, "name", true);
        String code = CookieUtils.getCookieValue(request, "code", true);
        ProjectOrder projectOrder = frCustomerCourseProjectService.customerRemnant(orderId, cid, name, code);
        return JsonResult.success(projectOrder);
    }

    /**
     * 延期
     * @param request
     * @return
     */
    @PostMapping("customerExtension")
    public JsonResult customerExtension(@RequestBody Map<String, String> map, HttpServletRequest request){
        String orderId = map.get("orderId");
        String useful = map.get("useful");
        String flag = map.get("flag");
        String cid = map.get("cid");
        String name = CookieUtils.getCookieValue(request, "name", true);
        String code = CookieUtils.getCookieValue(request, "code", true);
        AddProject projectOrder = frCustomerCourseProjectService.customerExtension(orderId, useful, flag, cid, name, code);
        return JsonResult.success(projectOrder);
    }

    /**
     * 转让表
     * @param request
     * @param map
     * @return
     */
    @PostMapping("customerTransfer")
    public JsonResult setReturnAddProject(@RequestBody Map<String, String> map, HttpServletRequest request){
        //String cid = map.get("cid");
        String name = CookieUtils.getCookieValue(request, "name", true);
        String shopid = CookieUtils.getCookieValue(request, "shopid", true);
       // String code = CookieUtils.getCookieValue(request, "code", true);
        frCustomerCourseProjectService.setTurnProject(map, name,shopid);
        return JsonResult.success(map);
    }

    /**
     * 退费表
     * @param request
     * @param map
     * @return
     */
    @PostMapping("customerReturnMoney")
    public JsonResult customerReturnMoney(@RequestBody Map<String, String> map, HttpServletRequest request){
       // String cid = map.get("cid");
        String name = CookieUtils.getCookieValue(request, "name", true);

        frCustomerCourseProjectService.setReturnAddProject(map, name);
        return JsonResult.success(map);
    }

    /**
     * 查询退费
     * @param request
     * @return
     */
    @GetMapping("returnAddProjects")
    public JsonResult getListReturnAddProject( HttpServletRequest request){
        String cid = request.getParameter("cid");
       // String cid = CookieUtils.getCookieValue(request, "cid", true);
        List<ReturnAddProject> returnAddProjects = frCustomerCourseProjectService.getListReturnAddProject(cid);
        return JsonResult.success(returnAddProjects);
    }


    /**
     * 查询转让记录
     * @param request
     * @return
     */
    @GetMapping("turnProjects")
    public JsonResult getListTurnProject( HttpServletRequest request){
        String cid = request.getParameter("cid");
        List<TurnProject> turnProjects = frCustomerCourseProjectService.getListTurnProject(cid);
        return JsonResult.success(turnProjects);
    }

    /**
     * 冲销
     * @param request
     * @return
     */
    @PostMapping("turnProjectsDelete")
    public JsonResult turnProjectsDelete(@RequestBody Map<String, String> map, HttpServletRequest request){
        String id = map.get("id");
        frCustomerCourseProjectService.deleteTurnProject(id);
        return JsonResult.success(new HashMap<>());
    }


    /**
     * 查询数组
     * @param request
     * @return
     */
    @GetMapping("list")
    public JsonResult getCourseList(HttpServletRequest request){

        String cid = request.getParameter("cid");
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        String shopName = request.getParameter("shopName");
        String timeType = request.getParameter("timeType");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String name = request.getParameter("name");
        String orderType = request.getParameter("orderType");
        String code = CookieUtils.getCookieValue(request, "code", true);
        List list = frCustomerCourseProjectService.getCourseList(type,status,shopName,timeType,startDate,endDate,name,code,orderType,cid);
        return JsonResult.success(list);
    }




}