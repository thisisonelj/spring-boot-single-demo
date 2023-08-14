package com.example.spring.boot.demo.account.service.power.impl;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import com.example.spring.boot.demo.account.entity.power.ExportPowerInfo;
import com.example.spring.boot.demo.account.utils.excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.boot.demo.account.dao.power.AccountPowerMapper;
import com.example.spring.boot.demo.account.dao.power.AccountRoleMapper;
import com.example.spring.boot.demo.account.dto.AccountPowerDTO;
import com.example.spring.boot.demo.account.entity.power.AccountPowerDO;
import com.example.spring.boot.demo.account.entity.power.PowerEnum;
import com.example.spring.boot.demo.account.service.AccountPowerService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author liujun
 * @Date 2023/8/13 10:08
 * @Description
 */
@Service
public class AccountPowerServiceImpl implements AccountPowerService {
    @Autowired
    private AccountPowerMapper accountPowerMapper;
    @Autowired
    private AccountRoleMapper accountRoleMapper;

    @Override
    public List<AccountPowerDTO> queryPowerAll() {
        List<AccountPowerDTO> accountPowerDTOS = new ArrayList<>();
        List<AccountPowerDO> accountPowerDOList = accountPowerMapper.selectAll();
        Map<String, List<AccountPowerDO>> powerMap = accountPowerDOList.stream()
            .collect(Collectors.groupingBy(AccountPowerDO::getUserRoleId, Collectors.toList()));
        powerMap.forEach((item, powerList) -> {
            AccountPowerDO accountPowerDO = powerList.stream().findFirst().get();
            AccountPowerDTO accountPowerDTO = new AccountPowerDTO();
            accountPowerDTO.setUserRoleId(item);
            accountPowerDTO.setUserRoleValue(accountPowerDO.getUserRoleValue());
            accountPowerDTO.setCreateTime(accountPowerDO.getCreateTime());
            powerList.forEach(e -> {
                if (e.getPowerId().equals(PowerEnum.USERMANAGE.toString())) {
                    accountPowerDTO.setUserManage(e.getPowerValue());
                }
                if (e.getPowerId().equals(PowerEnum.CMMANAGE.toString())) {
                    accountPowerDTO.setCmManage(e.getPowerValue());
                }
                if (e.getPowerId().equals(PowerEnum.SYSTEMSETTING.toString())) {
                    accountPowerDTO.setSystemSetting(e.getPowerValue());
                }
                if (e.getPowerId().equals(PowerEnum.VOUCHERMANAGE.toString())) {
                    accountPowerDTO.setVoucherManage(e.getPowerValue());
                }
                if (e.getPowerId().equals(PowerEnum.BOOKMANAGE.toString())) {
                    accountPowerDTO.setBookManage(e.getPowerValue());
                }
            });
            accountPowerDTOS.add(accountPowerDTO);
        });
        return accountPowerDTOS;
    }

    @Override
    public List<AccountPowerDTO> queryPowerList(List<AccountPowerDO> accountPowerDOList) {
        if (!accountPowerDOList.isEmpty()) {
            List<AccountPowerDTO> accountPowerDTOS = new ArrayList<>();
            accountPowerDOList.forEach(item -> {
                Date powerTime = new Date();
                if (accountPowerMapper.select(item).size() == 0) {
                    AccountPowerDTO accountPowerDTO = new AccountPowerDTO();
                    accountPowerDTO.setUserRoleId(item.getUserRoleId());
                    accountPowerDTO.setUserRoleValue(item.getUserRoleValue());
                    accountPowerDTO.setCreateTime(powerTime);
                    accountPowerDTO.setUserManage(false);
                    accountPowerDTO.setVoucherManage(false);
                    accountPowerDTO.setCmManage(false);
                    accountPowerDTO.setSystemSetting(false);
                    accountPowerDTO.setBookManage(false);
                    accountPowerDTOS.add(accountPowerDTO);
                    Arrays.stream(PowerEnum.values()).forEach(e -> {
                        AccountPowerDO accountPowerDO = new AccountPowerDO();
                        String id = UUID.randomUUID().toString();
                        accountPowerDO.setId(id);
                        accountPowerDO.setUserRoleId(item.getUserRoleId());
                        accountPowerDO.setUserRoleValue(item.getUserRoleValue());
                        accountPowerDO.setPowerId(e.toString());
                        accountPowerDO.setPowerValue(false);
                        accountPowerDO.setCreateTime(powerTime);
                        accountPowerMapper.addAccountPower(accountPowerDO);
                    });
                } else {
                    List<AccountPowerDO> powerList = accountPowerMapper.select(item);
                    AccountPowerDTO accountPowerDTO = new AccountPowerDTO();
                    accountPowerDTO.setUserRoleId(item.getUserRoleId());
                    accountPowerDTO.setUserRoleValue(item.getUserRoleValue());
                    accountPowerDTO.setCreateTime(powerList.stream().findFirst().get().getCreateTime());
                    for (AccountPowerDO e : powerList) {
                        if (e.getPowerId().equals(PowerEnum.USERMANAGE.toString())) {
                            accountPowerDTO.setUserManage(e.getPowerValue());
                        }
                        if (e.getPowerId().equals(PowerEnum.CMMANAGE.toString())) {
                            accountPowerDTO.setCmManage(e.getPowerValue());
                        }
                        if (e.getPowerId().equals(PowerEnum.SYSTEMSETTING.toString())) {
                            accountPowerDTO.setSystemSetting(e.getPowerValue());
                        }
                        if (e.getPowerId().equals(PowerEnum.VOUCHERMANAGE.toString())) {
                            accountPowerDTO.setVoucherManage(e.getPowerValue());
                        }
                        if (e.getPowerId().equals(PowerEnum.BOOKMANAGE.toString())) {
                            accountPowerDTO.setBookManage(e.getPowerValue());
                        }
                    } ;
                    accountPowerDTOS.add(accountPowerDTO);
                }
            });
            return accountPowerDTOS;
        }
        return queryPowerAll();
    }

    @Override
    public List<AccountPowerDTO> updateAccountPower(List<AccountPowerDTO> accountPowerDTOList) {
        accountPowerDTOList.forEach(item -> {
            AccountPowerDO accountPowerDO = new AccountPowerDO();
            accountPowerDO.setUserRoleId(item.getUserRoleId());
            accountPowerMapper.delete(accountPowerDO);
            HashMap<String, Boolean> powerMap = new HashMap<>();
            powerMap.put(PowerEnum.USERMANAGE.toString(), item.getUserManage());
            powerMap.put(PowerEnum.SYSTEMSETTING.toString(), item.getSystemSetting());
            powerMap.put(PowerEnum.VOUCHERMANAGE.toString(), item.getVoucherManage());
            powerMap.put(PowerEnum.CMMANAGE.toString(), item.getCmManage());
            powerMap.put(PowerEnum.BOOKMANAGE.toString(), item.getBookManage());
            powerMap.forEach((e, value) -> {
                String id = UUID.randomUUID().toString();
                AccountPowerDO accountPowerDO1 = new AccountPowerDO();
                accountPowerDO1.setId(id);
                accountPowerDO1.setCreateTime(item.getCreateTime());
                accountPowerDO1.setUserRoleId(item.getUserRoleId());
                accountPowerDO1.setUserRoleValue(item.getUserRoleValue());
                accountPowerDO1.setPowerId(e);
                accountPowerDO1.setPowerValue(value);
                accountPowerMapper.addAccountPower(accountPowerDO1);
            });
        });
        List<AccountPowerDO> powerList = accountPowerDTOList.stream().map(k -> {
            AccountPowerDO accountPowerDO = new AccountPowerDO();
            accountPowerDO.setUserRoleId(k.getUserRoleId());
            accountPowerDO.setUserRoleValue(k.getUserRoleValue());
            return accountPowerDO;
        }).collect(Collectors.toList());
        if (powerList.size() == 0) {
            return queryPowerAll();
        }
        return queryPowerList(powerList);
    }

    @Override
    public String deleteAccountPower(AccountPowerDTO accountPowerDTO) {
        AccountPowerDO accountPowerDO = new AccountPowerDO();
        accountPowerDO.setUserRoleId(accountPowerDTO.getUserRoleId());
        accountPowerMapper.delete(accountPowerDO);
        return accountPowerDTO.getUserRoleId();
    }

    @Override
    public List<AccountPowerDTO> importAccountPower(MultipartFile uploadFile) {
        List<ArrayList<String>> handleExcelData = new ArrayList<ArrayList<String>>();
        try {
            // 获得Workbook工作薄对象
            Workbook workbook = getWorkBook(uploadFile);
            // 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
            if (workbook != null) {
                for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                    // 获得当前sheet工作表
                    Sheet sheet = workbook.getSheetAt(sheetNum);
                    if (sheet == null) {
                        continue;
                    }
                    // 获得当前sheet的开始行
                    int firstRowNum = sheet.getFirstRowNum();
                    // 获得当前sheet的结束行
                    int lastRowNum = sheet.getLastRowNum();
                    // 循环除了第一行的所有行
                    for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                        // 获得当前行
                        Row row = sheet.getRow(rowNum);
                        if (row == null) {
                            continue;
                        }
                        // 获得当前行的开始列
                        int firstCellNum = row.getFirstCellNum();
                        // 获得当前行的列数
                        int lastCellNum = row.getPhysicalNumberOfCells();
                        ArrayList<String> cells = new ArrayList<>();
                        // 循环当前行
                        for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                            Cell cell = row.getCell(cellNum);
                            cells.add(getCellValue(cell));
                        }
                        handleExcelData.add(cells);
                    }
                }
            }
        } catch (Exception e) {

        }
        handleExcelData.forEach(item -> {
            AccountPowerDO accountPowerDO = new AccountPowerDO();
            accountPowerDO.setUserRoleId(item.stream().findFirst().get());
            Date localPowerDate = new Date();
            if (accountPowerMapper.select(accountPowerDO).isEmpty()) {
                Arrays.stream(PowerEnum.values()).forEach(k -> {
                    AccountPowerDO accountPowerDO1 = new AccountPowerDO();
                    String id = UUID.randomUUID().toString();
                    accountPowerDO1.setId(id);
                    accountPowerDO1.setUserRoleId(item.get(0));
                    accountPowerDO1.setUserRoleValue(item.get(1));
                    accountPowerDO1.setCreateTime(localPowerDate);
                    if(k.toString().equals("userManage")){
                        accountPowerDO1.setPowerId(k.toString());
                        accountPowerDO1.setPowerValue(Boolean.valueOf(item.get(2)));
                    }
                    if(k.toString().equals("systemSetting")){
                        accountPowerDO1.setPowerId(k.toString());
                        accountPowerDO1.setPowerValue(Boolean.valueOf(item.get(3)));
                    }
                    if(k.toString().equals("cmManage")){
                        accountPowerDO1.setPowerId(k.toString());
                        accountPowerDO1.setPowerValue(Boolean.valueOf(item.get(4)));
                    }
                    if(k.toString().equals("voucherManage")){
                        accountPowerDO1.setPowerId(k.toString());
                        accountPowerDO1.setPowerValue(Boolean.valueOf(item.get(5)));
                    }
                    if(k.toString().equals("bookManage")){
                        accountPowerDO1.setPowerId(k.toString());
                        accountPowerDO1.setPowerValue(Boolean.valueOf(item.get(6)));
                    }
                   accountPowerMapper.addAccountPower(accountPowerDO1);
                });
            }else{
                accountPowerMapper.delete(accountPowerDO);
                Arrays.stream(PowerEnum.values()).forEach(k -> {
                    AccountPowerDO accountPowerDO1 = new AccountPowerDO();
                    String id = UUID.randomUUID().toString();
                    accountPowerDO1.setId(id);
                    accountPowerDO1.setUserRoleId(item.get(0));
                    accountPowerDO1.setUserRoleValue(item.get(1));
                    accountPowerDO1.setCreateTime(localPowerDate);
                    if(k.toString().equals("userManage")){
                        accountPowerDO1.setPowerId(k.toString());
                        accountPowerDO1.setPowerValue(Boolean.valueOf(item.get(2)));
                    }
                    if(k.toString().equals("systemSetting")){
                        accountPowerDO1.setPowerId(k.toString());
                        accountPowerDO1.setPowerValue(Boolean.valueOf(item.get(3)));
                    }
                    if(k.toString().equals("cmManage")){
                        accountPowerDO1.setPowerId(k.toString());
                        accountPowerDO1.setPowerValue(Boolean.valueOf(item.get(4)));
                    }
                    if(k.toString().equals("voucherManage")){
                        accountPowerDO1.setPowerId(k.toString());
                        accountPowerDO1.setPowerValue(Boolean.valueOf(item.get(5)));
                    }
                    if(k.toString().equals("bookManage")){
                        accountPowerDO1.setPowerId(k.toString());
                        accountPowerDO1.setPowerValue(Boolean.valueOf(item.get(6)));
                    }
                    accountPowerMapper.addAccountPower(accountPowerDO1);
                });
            }

        });
        return queryPowerAll();

    }

    private Workbook getWorkBook(MultipartFile file) {
        // 获得文件名
        String fileName = file.getOriginalFilename();
        // 创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            // 获取excel文件的io流
            InputStream is = file.getInputStream();
            // 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith("xls")) {
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(is);
            }
        } catch (Exception e) {

        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        // 把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == CellType.NUMERIC) {
            cell.setCellType(CellType.STRING);
        }
        // 判断数据的类型
        if (cell.getCellType() == CellType.NUMERIC) {
            // 数字
            cellValue = String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.STRING) {
            // 字符串
            cellValue = String.valueOf(cell.getStringCellValue()).trim();
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            // Boolean
            cellValue = String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.FORMULA) {
            // 公式
            cellValue = String.valueOf(cell.getCellFormula());
        } else if (cell.getCellType() == CellType.BLANK) {
            // 空值
            cellValue = "";
        } else if (cell.getCellType() == CellType.ERROR) {
            // 故障
            cellValue = "非法字符";
        } else {
            cellValue = "未知类型";
        }
        return cellValue;
    }

    @Override
    public void exportAccountPower(List<AccountPowerDTO> accountPowerDTOS, HttpServletResponse response) {
        List<ExportPowerInfo> exportPowerList = accountPowerDTOS.stream().map(item -> {
            ExportPowerInfo exportPowerInfo = new ExportPowerInfo();
            exportPowerInfo.setUserRoleId(item.getUserRoleId());
            exportPowerInfo.setUserRoleName(item.getUserRoleValue());
            exportPowerInfo.setUserManage(item.getUserManage());
            exportPowerInfo.setSystemSetting(item.getSystemSetting());
            exportPowerInfo.setCmManage(item.getCmManage());
            exportPowerInfo.setVoucherManage(item.getVoucherManage());
            exportPowerInfo.setBookManage(item.getBookManage());
            return exportPowerInfo;
        }).collect(Collectors.toList());
        String fileName = "核算权限excel工作表";
        String sheetName = "权限表";
        response.setHeader("Access-Control-Expose-Headers", "content-disposition");
        ExcelUtil.writeExcel(response, exportPowerList, fileName, sheetName, new ExportPowerInfo());
    }
}
