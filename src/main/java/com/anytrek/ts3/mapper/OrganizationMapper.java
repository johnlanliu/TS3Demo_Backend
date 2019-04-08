/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.anytrek.ts3.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.anytrek.ts3.dto.OrganizationDetailDto;
import com.anytrek.ts3.model.Organization;
import com.anytrek.ts3.util.MyMapper;

/**
 * 
 * @author John date 2018 M09 16
 */
public interface OrganizationMapper extends MyMapper<Organization> {
	// public String getOrgId(String activationKey) throws Exception;

	public List<OrganizationDetailDto> getChildrenList(@Param(value = "orgId") Integer orgId) throws Exception;

	// public List<Organization> getOrgIdAndParentIdByOrgids(String[] orgIds) throws
	// Exception;

	public Organization getOrgByActivationKey(String activationKey) throws Exception;

	public void updateOrgActivationKey(Integer orgId, String activationKey) throws Exception;

	public Organization getOrgByEmail(String email) throws Exception;

	public void updateOrganizationByParams(HashMap<String, Object> params) throws Exception;

	// 遍历修改子节点的祖先节点树
	public void changeChildrenOrgParents(HashMap<String, Object> params) throws Exception;

	public int deleteOrg(Integer orgId) throws Exception;;

	public Integer getOrgIdByOrgName(String orgName) throws Exception;
}
