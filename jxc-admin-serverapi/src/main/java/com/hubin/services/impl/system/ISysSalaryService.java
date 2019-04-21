package com.hubin.services.impl.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubin.domain.system.SysSalary;
import com.hubin.domain.system.SysUser;
import com.hubin.dto.system.SalaryExcelDTO;
import com.hubin.dto.system.SysSalaryDTO;
import com.hubin.factor.system.SysSalaryFactor;
import com.hubin.services.system.SysSalaryService;
import com.hubin.services.system.SysUserService;
import com.hubin.utils.pages.PageUtils;
import com.hubin.utils.ResultFormat;
import com.hubin.utils.pages.PageParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <br>
 *
 * @author hubin
 * @title:薪资单
 * @description:
 * @date: 2019/4/8 11:50
 */
@Service
public class ISysSalaryService extends ServiceImpl<SysSalaryFactor,SysSalary> implements SysSalaryService {

    @Autowired
    SysUserService sysUserService;

    /**
     * 创建工资单
     */
    @Override
    public void createSalary() {
        List<SysUser> userList = sysUserService.getUserByRole();
        userList.stream().forEach(u ->{
            SysSalary s = new SysSalary();
            s.setUid(u.getUid()).setRealName(u.getRealName()).setAttendanceDays("24").setOvertimeDays("0")
            .setOvertimePay(BigDecimal.valueOf(0)).setBasePay(BigDecimal.valueOf(2000)).setSeniorityPay(BigDecimal.valueOf(0))
            .setAllworkPay(BigDecimal.valueOf(100)).setTelephonePay(BigDecimal.valueOf(50)).setCarPay(BigDecimal.valueOf(0));
            if (u.getRoleId()==106) {
                s.setManagePay(BigDecimal.valueOf(500));
            }else{
                s.setManagePay(BigDecimal.valueOf(0));
            }
            s.setAssistantPay(BigDecimal.valueOf(0)).setExhibitPay(BigDecimal.valueOf(0)).setSalesTask(BigDecimal.valueOf(45000))
            .setSalesFinish(BigDecimal.valueOf(0)).setFinishRate(BigDecimal.valueOf(0)).setPerformancePay(BigDecimal.valueOf(0))
            .setPerformanceBonus(BigDecimal.valueOf(0)).setSalaryPay(s.salaryPaysPlus()).setDeductFund(BigDecimal.valueOf(0))
            .setPracticalPay(s.practicalPaysPlus(BigDecimal.valueOf(0))).setCreateTime(new Date()).setUpdateTime(new Date());
            boolean flag = ResultFormat.format(baseMapper.insert(s));
            if (flag) {
                System.err.printf("%s创建工资单成功 %n",u.getRealName());
            }else{
                System.err.printf("%s创建工资单失败 %n",u.getRealName());
            }
        });
    }

    /**
     * 查询本月是否创建工资单
     * @return
     */
    @Override
    public boolean getCountByMonth() {
        return baseMapper.getCountByMonth()>0?Boolean.TRUE:Boolean.FALSE;
    }

    /**
     * 当月工资单分页
     * @param pageParam
     * @return
     */
    @Override
    public PageUtils queryPage(PageParam pageParam,SysSalaryDTO salaryDTO) {
        if(Objects.isNull(salaryDTO.getCreateTime()))salaryDTO.setCreateTime(new Date());
        List<SysSalary> list = baseMapper.queryPageList(pageParam,salaryDTO);
        Long total = Long.valueOf(list.size());
        return PageUtils.build().rows(list).total(total);
    }

    /**
     * 获取导出Excel的数据
     * @param pageParam
     * @param salaryDTO
     * @return
     */
    @Override
    public List<SalaryExcelDTO> getListByMonth(PageParam pageParam, SysSalaryDTO salaryDTO) {
        if(Objects.isNull(salaryDTO.getCreateTime()))salaryDTO.setCreateTime(new Date());
        List<SysSalary> list = baseMapper.queryPageList(pageParam,salaryDTO);
        return this.toCopyProperties(list);
    }

    /**
     * 更新工资单
     * @param sysSalary
     * @return
     */
    @Override
    @Transactional
    public boolean updateSalary(SysSalary sysSalary) {
        sysSalary.setUpdateTime(new Date());
        if(ResultFormat.format(baseMapper.updateById(sysSalary))){
            return ResultFormat.format(baseMapper.salaryProcedure(sysSalary.getId()));
        }
        return Boolean.FALSE;
    }

    private List<SalaryExcelDTO> toCopyProperties(List<SysSalary> list) {
        List<SalaryExcelDTO> salaryExcelDTOs = new ArrayList<>();
        for (SysSalary s:list) {
            SalaryExcelDTO news = new SalaryExcelDTO();
            BeanUtils.copyProperties(s,news);
            news.setBaseSum(news.getBasePay()
                    .add(news.getSeniorityPay())
                    .add(news.getAllworkPay())
                    .add(news.getTelephonePay())
                    .add(news.getCarPay())
                    .add(news.getManagePay())
                    .add(news.getAssistantPay())
                    .add(news.getExhibitPay())
                    .add(news.getOvertimePay()));
            news.setBonusSum(news.getPerformancePay().add(news.getPerformanceBonus()));
            news.setFinishRateStr((news.getFinishRate().multiply(BigDecimal.valueOf(100L))).toString()+"%");
            salaryExcelDTOs.add(news);
        }
        return salaryExcelDTOs;
    }
}
