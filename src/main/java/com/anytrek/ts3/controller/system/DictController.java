package com.anytrek.ts3.controller.system;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.common.FlagConstants;
import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;
import com.anytrek.ts3.mapper.SysDictMapper;
import com.anytrek.ts3.model.SysDict;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 菜单控制器
 * 
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("/dictManager")
public class DictController extends ControllerBase {

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(RoleController.class);

	@Autowired
	private SysDictMapper dictMapper;

	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = { "/getAllDictList" }, method = RequestMethod.GET)
	public HashMap<String, Object> getAllDictList(@RequestParam(value = "dictName", required = false) String dictName,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "pageindex") int pageindex, @RequestParam(value = "pagesize") int pagesize,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// where条件参数
		HashMap<String, Object> params = new HashMap<>();
		if (!StringUtils.isEmpty(dictName)) {
			params.put("dictName", dictName);
		}
		if (!StringUtils.isEmpty(type)) {
			params.put("type", type);
		}
		// 默认页码为20，页数为第一页
		int psize = 20;
		int pindex = 1;
		if (pagesize > 0)
			psize = pagesize;
		if (pageindex > 0)
			pindex = pageindex;
		// 获取当前分页设备列表
		PageHelper.startPage(pindex, psize);
		List<SysDict> viewList = dictMapper.getDictListByParams(params);
		// 获取分页类数据
		PageInfo<SysDict> appsPageInfo = new PageInfo<>(viewList);
		// 前端应急处理
		HashMap<String, Object> list = new HashMap<>();
		list.put("list", viewList);
		list.put("totalRows", appsPageInfo.getTotal());
		return list;

	}

	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = { "/getDictById" }, method = RequestMethod.GET)
	public SysDict getDictById(@RequestParam(value = "dictId", required = true) Integer dictId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return dictMapper.getDictById(dictId);
	}

	@RequiresPermissions("sys:dict:add")
	@RequestMapping(value = { "/addDict" }, method = RequestMethod.POST)
	public @ResponseBody SysDict addDict(@RequestBody SysDict dict) {
		if (StringUtils.isEmpty(dict.getDictName()) || StringUtils.isEmpty(dict.getDictValue())) {
			throw new WebException(ErrorCode.DICT_PARAMETER_ERROR);
		}
		dict.setFlag(FlagConstants.FLAG_UNDELETED);
		dict.setCreateTime(new Timestamp(new Date().getTime()));
		int row = dictMapper.insertSelective(dict);
		if (row == 0) {
			throw new WebException(ErrorCode.SERVER_ERROR, "Error Data!");
		}
		return dict;
	}

	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = { "/editDict" }, method = RequestMethod.POST)
	public @ResponseBody SysDict editDict(@RequestBody SysDict dict) {
		if (StringUtils.isEmpty(dict.getDictName()) || StringUtils.isEmpty(dict.getDictValue())) {
			throw new WebException(ErrorCode.DICT_PARAMETER_ERROR);
		}
		int row = dictMapper.updateByPrimaryKeySelective(dict);
		if (row == 0) {
			throw new WebException(ErrorCode.SERVER_ERROR, "Error Data!");
		}
		return dict;
	}

	@RequiresPermissions("sys:dict:delete")
	@RequestMapping(value = { "/deleteDict" }, method = RequestMethod.GET)
	public void delete(@RequestParam(value = "dictId", required = true) Integer dictId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (dictId == null || dictId == 0) {
			throw new WebException(ErrorCode.DICT_PARAMETER_ERROR);
		}
		int row = dictMapper.deleteDict(dictId);
		if (row == 0) {
			throw new WebException(ErrorCode.DICT_NOT_FOUND);
		}
		sendOk();
	}

	@RequiresPermissions("sys:dict:add")
	@RequestMapping(value = { "/batchImportDict" }, method = RequestMethod.POST)
	public void batchImportDict(@RequestParam("file") MultipartFile file) throws Exception {
		if (StringUtils.isEmpty(file)) {
			throw new WebException(ErrorCode.DICT_PARAMETER_ERROR);
		}
		List<SysDict> list = this.dictImport(file);
		dictMapper.insertList(list);
	}

	private List<SysDict> dictImport(MultipartFile file) throws Exception {

		String fileName = file.getOriginalFilename();
		if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
			throw new WebException(ErrorCode.ERROR_EXCEL_FORMAT);
		}
		boolean isExcel2003 = true;
		if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			isExcel2003 = false;
		}
		InputStream is = file.getInputStream();
		Workbook wb = null;
		if (isExcel2003) {
			wb = new HSSFWorkbook(is);
		} else {
			wb = new XSSFWorkbook(is);
		}
		Sheet sheet = wb.getSheetAt(0);
		List<SysDict> list = new ArrayList<SysDict>();
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			SysDict dict = new SysDict();
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			dict.setDictName(row.getCell(0).getStringCellValue());
			dict.setDictValue(row.getCell(1).getStringCellValue());
			dict.setType(row.getCell(2).getStringCellValue());
			dict.setDescription(row.getCell(3).getStringCellValue());
			dict.setOrderNum((int) row.getCell(4).getNumericCellValue());
			dict.setRemarks(row.getCell(5).getStringCellValue());
			dict.setFlag(FlagConstants.FLAG_UNDELETED);
			dict.setCreateTime(new Timestamp(new Date().getTime()));
			list.add(dict);
		}
		return list;
	}
}
