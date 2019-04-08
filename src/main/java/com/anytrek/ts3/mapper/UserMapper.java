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

import com.anytrek.ts3.dto.UserDetailDto;
import com.anytrek.ts3.model.SysRole;
import com.anytrek.ts3.model.User;
import com.anytrek.ts3.util.MyMapper;

/**
 * 
 * @author John date 2018 M09 16
 */
public interface UserMapper extends MyMapper<User> {
	public User getRepeatUser(User user) throws Exception;

	public User getUserByKey(String key) throws Exception;

	public void updateUserKey(Integer userId, String key) throws Exception;

	public User getUserByEmail(String email) throws Exception;

	// public void updatePasswordByUserId(HashMap<String, Object> params) throws
	// Exception;

	public List<UserDetailDto> getUserListByParams(HashMap<String, Object> params) throws Exception;

	public List<UserDetailDto> getUserListByRoleParams(HashMap<String, Object> params) throws Exception;

	public List<UserDetailDto> getUserByOrgId(Integer orgId) throws Exception;

	public User getUserByUsername(String username);

	public List<SysRole> getValidRoleList(Object[] roleIds);

//	public List<User> getUserByRoleId(Integer roleId) throws Exception;

	public Integer getUserCountByRoleId(Integer roleId);

	public int deleteUser(Integer userId) throws Exception;

}
