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

import com.anytrek.ts3.dto.TrkInfoDetailDto;
import com.anytrek.ts3.model.TrkInfo;
import com.anytrek.ts3.util.MyMapper;

/**
 * 
 * @author John date 2018 M09 15
 */
public interface TrkInfoMapper extends MyMapper<TrkInfo> {

	public String getPushUrlByDeviceId(Long deviceId);

	public List<TrkInfoDetailDto> getDeviceListByParams(HashMap<String, Object> params);

	public List<TrkInfo> getDevicesByBatchId(int batchId) throws Exception;

	public int getDeviceCountByOrgId(Integer orgId);

	public int deleteDevice(Long deviceId) throws Exception;

	public List<Long> getDeviceIdByOrgId(Integer orgId);

	public List<TrkInfoDetailDto> getDeviceListByUserId(Integer userId);

	public TrkInfoDetailDto getDeviceByDeviceId(Long deviceId);
}
