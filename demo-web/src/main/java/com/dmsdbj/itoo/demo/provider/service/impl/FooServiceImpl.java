package com.dmsdbj.itoo.demo.provider.service.impl;

import com.dmsdbj.itoo.demo.entity.FooEntity;
import com.dmsdbj.itoo.demo.entity.FooGroupEntity;
import com.dmsdbj.itoo.demo.model.FooCascadeModel;
import com.dmsdbj.itoo.demo.model.FooInfoModel;
import com.dmsdbj.itoo.demo.model.FooModel;
import com.dmsdbj.itoo.demo.template.FooImportTemplate;
import com.dmsdbj.itoo.demo.template.FooTemplate;
import com.dmsdbj.itoo.demo.provider.dao.FooDao;
import com.dmsdbj.itoo.demo.provider.service.FooGroupService;
import com.dmsdbj.itoo.demo.provider.service.FooService;
import com.dmsdbj.itoo.tool.base.dao.BaseDaoSimplify;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServiceSimplifyImpl;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.dmsdbj.itoo.tool.uuid.BaseUuidUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.jplus.hyberbin.excel.utils.ExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.dmsdbj.itoo.demo.provider.until.DemoConstants.*;

/**
 * FooService接口实现类
 * foo人员表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-18 14:35:27
 */
@Service("fooService")
@Slf4j
public class FooServiceImpl extends BaseServiceSimplifyImpl<FooEntity> implements FooService {

    @Resource
    private FooDao fooDao;

    @Resource
    private FooGroupService fooGroupService;

    @Override
    public BaseDaoSimplify<FooEntity> getRealDao() {
        return this.fooDao;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ItooResult create(FooEntity entity) {
        //添加用户前判断用户名是否重复
        if (fooDao.queryByUserCode(entity.getUserCode()).size() > 0) {
            return ItooResult.build(ItooResult.FAIL, "用户名已存在!");
        }
        this.insert(entity);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功!");
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ItooResult modify(FooEntity entity) {
        //约束:userCode必须唯一,不能与数据库中已有的其他值相同
        List<FooEntity> fooEntities = fooDao.queryByUserCode(entity.getUserCode());
        for (FooEntity fooEntity : fooEntities) {
            if (fooEntity.getUserCode().equals(entity.getUserCode()) && !fooEntity.getId().equals(entity.getId())) {
                return ItooResult.build(ItooResult.FAIL, "用户名已存在!");
            }
        }
        this.updateById(entity);
        return ItooResult.build(ItooResult.SUCCESS, "编辑成功");
    }

    @Override
    public PageInfo<FooModel> queryAllFoo(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo<>(fooDao.queryAllModel());
    }

    @Override
    public PageInfo<FooModel> queryByLikeName(String fooName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(fooDao.queryLikeName(fooName));
    }

    @Override
    public List<FooModel> queryByGroupId(String fooGroupId) {
        return fooDao.queryByGroupId(fooGroupId);
    }

    @Override
    public FooModel queryFooModelById(String id) {
        return fooDao.findModelById(id);
    }

    @Override
    public FooInfoModel queryNewFoo() {
        return fooDao.queryNewFoo();
    }

    @Override
    public List<FooModel> queryByFooCascadeModel(FooCascadeModel fooCascadeModel) {
        System.out.print(fooCascadeModel.getFooGroupId());
        return fooDao.queryByGroupIdAndDate(fooCascadeModel.getFooGroupId(),fooCascadeModel.getStartTime(),fooCascadeModel.getEndTime());
    }

    @Override
    public ItooResult exportByGroupId(HttpServletResponse response, String groupId) {
        Map<Serializable,Serializable> map=new HashMap<>(16);
        //日期转换字符串的格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        map.put(SHEET_NAME,"Foo信息");
        //要导出的字段
        map.put(COLUMNS,new String[]{"userName","sex","telNum","startTime","fooGroupName"});
        //导出表格的标题
        map.put(TITLE,"Foo信息");
        map.put(NAME,"Foo信息表");
        //要导出的数据
        List<FooTemplate> fooTemplates=new ArrayList<>();
        List<FooModel> fooModels=this.queryByGroupId(groupId);
        for (FooModel fooModel:fooModels){
            FooTemplate fooTemplate=new FooTemplate();
            BeanUtils.copyProperties(fooModel,fooTemplate);
            fooTemplate.setFooGroupName(fooModel.getDeptName());
            fooTemplate.setStartTime(simpleDateFormat.format(fooModel.getStartTime()));
            fooTemplates.add(fooTemplate);
        }
        //为空不进行导出
        if (fooTemplates.size()==0){
            return ItooResult.build(ItooResult.FAIL, "没有要导出的数据！");
        }
        map.put(DATA_LIST,(Serializable) fooTemplates);

        //导出
        try {
            ExcelUtil.exportExcel(map,response);
        } catch (Exception e) {
            log.error("导出失败,未知的异常--"+e);
            return ItooResult.build(ItooResult.FAIL, "导出信息失败！");
        }
        return ItooResult.build(ItooResult.SUCCESS  , "导出信息成功！");
    }

    @Override
    public boolean downTemplate(HttpServletResponse response) {
        Map<Serializable, Serializable> map = new HashMap<>(16);
        List<FooTemplate> templateList = new ArrayList<>();
        FooTemplate fooTemplate = new FooTemplate();
        fooTemplate.setUserCode("130140141021");
        fooTemplate.setUserName("王贵香");
        fooTemplate.setTelNum("15732624913");
        fooTemplate.setSex("女");
        fooTemplate.setStartTime("2018/10/20");
        fooTemplate.setFooGroupName("财务部");
        templateList.add(fooTemplate);
        //excel设置
        map.put(SHEET_NAME, "用户信息");
        map.put(COLUMNS, new String[]{"userCode", "userName", "telNum", "sex", "startTime", "fooGroupName"});
        map.put(TITLE, "用户信息模板：入职日期格式需为日期格式");
        map.put(NAME, "用户信息模板");
        map.put(DATA_LIST, (Serializable) templateList);

        try {
            ExcelUtil.exportExcel(map, response);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ItooResult importTemplate(MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("UTF-8");
        //日期转换字符串的格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            //校验文件是否存在
            String fileName = multipartFile.getOriginalFilename();
            if (fileName == null) {
                log.error("导入失败,fileName is null!");
                return ItooResult.build(ItooResult.FAIL, "传入的文件为空!");
            }

            //Map<fooGroupName, FooGroupEntity>定义map,方便后续数据存取.
            Map<String, FooGroupEntity> fooGroupMap = new HashMap<>(16);

            //EXCEL解析成list
            Map<Serializable, Serializable> map = new HashMap<>(4);
            map.put(SHEET_NAME, "用户信息");
            map.put(CLASS, FooImportTemplate.class);
            List<FooImportTemplate> fooImportList = ExcelUtil.importExcel(Objects.requireNonNull(fileName), map, request, response);
            if (fooImportList.size()==0){
                return ItooResult.build(ItooResult.FAIL,"请检查导入的数据");
            }
            //定义导入错误数据集合
            List<FooTemplate> errorFooList = new ArrayList<>();

            //循环处理数据
            for (FooImportTemplate excelFoo : fooImportList) {
                //约束：userCode，userName必填，同时要导入userCode不能与数据库中的其他userCode相同
                if (!this.verify(excelFoo)) {
                    FooTemplate fooTemplate=new FooTemplate();
                    BeanUtils.copyProperties(excelFoo,fooTemplate);
                    fooTemplate.setStartTime(simpleDateFormat.format(excelFoo.getStartTime()));
                    errorFooList.add(fooTemplate);
                    continue;
                }
                //判断组是否存在,不存在则查询或创建
                String fooGroupName = excelFoo.getFooGroupName();
                if (fooGroupMap.get(fooGroupName) == null) {
                    //fooGroupName不存在于map中,则查询数据库,如果没有则创建.
                    FooGroupEntity fooGroupEntity = fooGroupService.findByDeptName(fooGroupName);

                    if (fooGroupEntity == null) {
                        // 创建fooGroup,保存到数据库
                        fooGroupEntity = new FooGroupEntity();
                        fooGroupEntity.setId(BaseUuidUtils.base58Uuid());
                        fooGroupEntity.setParentId("0");
                        fooGroupEntity.setDeptName(fooGroupName);
                        fooGroupService.insert(fooGroupEntity);
                    }
                    //将fooGroupEntity放入map中,下次就不用再查询数据库了.
                    fooGroupMap.put(fooGroupName, fooGroupEntity);
                }

                //new出FooEntity对象,通过BeanUtils把相同属性进行拷贝,然后处理不能拷贝的特殊属性.
                FooEntity fooEntity = new FooEntity();
                BeanUtils.copyProperties(excelFoo, fooEntity);
                fooEntity.setFooGroupId(fooGroupMap.get(fooGroupName).getId());

                //保存到数据库
                this.insert(fooEntity);
            }

            //不符合条件的信息返回
            if (errorFooList.size() > 0) {
                Map<Serializable,Serializable> errorMap=new HashMap<>(16);
                errorMap.put(NAME, "未导入的信息");
                errorMap.put(SHEET_NAME, "用户信息");
                errorMap.put(TITLE, "未导入的信息");
                errorMap.put(COLUMNS, new String[]{"userCode", "userName", "telNum", "sex", "startTime", "fooGroupName","failReason"});
                errorMap.put(DATA_LIST,(Serializable) errorFooList);
                ExcelUtil.exportExcel(errorMap,response);
            }
        } catch (Exception e) {
            return ItooResult.build(ItooResult.FAIL, "导入用户异常");
        }
        return ItooResult.build(ItooResult.SUCCESS, "导入用户成功");
    }

    /**
     * 批量导入前验证数据是否符合要求
     *
     * @param fooTemplate fooTemplate
     * @return true:符合导入要求，false：不符合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 16:43:03
     */
    private boolean verify(FooImportTemplate fooTemplate) {
        //判断非空字段是否有值.
        if (StringUtils.isEmpty(fooTemplate.getUserCode())) {
            fooTemplate.setFailReason("userCode未填写");
            log.warn("导入失败,userCode未填写,excelFooEntity-{}", fooTemplate);
            return false;
        }
        if (StringUtils.isEmpty(fooTemplate.getUserName())) {
            fooTemplate.setFailReason("userName未填写");
            log.warn("导入失败,userName未填写,excelFooEntity-{}", fooTemplate);
            return false;
        }
        //判断用户名是否重复
        if (fooDao.queryByUserCode(fooTemplate.getUserCode()).size() > 0) {
            fooTemplate.setFailReason("userCode已存在");
            log.warn("导入失败,userCode已存在,excelFooEntity-{}", fooTemplate);
            return false;
        }
        return true;
    }

}
