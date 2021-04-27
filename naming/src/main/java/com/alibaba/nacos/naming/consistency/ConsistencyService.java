/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.nacos.naming.consistency;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.naming.pojo.Record;

/**
 * Consistence service for all implementations to derive.
 *
 * 所有要派生的实现的一致性服务。
 * <p>
 * We announce this consistency service to decouple the specific consistency implementation with business logic.
 * User should not be aware of what consistency protocol is being used.
 *
 * 我们发布这个一致性服务是为了将特定的一致性实现与业务逻辑解耦。
 * 用户不应该知道正在使用什么一致性协议。
 * <p>
 * In this way, we also provide space for user to extend the underlying consistency protocols, as long as they
 * obey our consistency baseline.
 * 这样，我们还为用户提供了扩展底层一致性协议的空间，只要它们
 *
 * 遵守我们的一致性基线。
 *
 * @author nkorange
 * @since 1.0.0
 */
public interface ConsistencyService {

    /**
     * Put a data related to a key to Nacos cluster
     * 将与密钥相关的数据放入Nacos集群
     *
     * @param key   key of data, this key should be globally unique
     * @param value value of data
     * @throws NacosException
     * @see
     */
    void put(String key, Record value) throws NacosException;

    /**
     * Remove a data from Nacos cluster
     * 从Nacos群集删除数据
     *
     * @param key key of data
     * @throws NacosException
     */
    void remove(String key) throws NacosException;

    /**
     * Get a data from Nacos cluster
     *
     * 从Nacos群集获取数据
     *
     * @param key key of data 数据密钥
     * @return data related to the key  与密钥相关的数据
     * @throws NacosException
     */
    Datum get(String key) throws NacosException;

    /**
     * Listen for changes of a data
     *
     * @param key      key of data
     * @param listener callback of data change
     * @throws NacosException
     */
    void listen(String key, RecordListener listener) throws NacosException;

    /**
     * Cancel listening of a data
     *
     * @param key      key of data
     * @param listener callback of data change
     * @throws NacosException
     */
    void unlisten(String key, RecordListener listener) throws NacosException;

    /**
     * Tell the status of this consistency service
     *
     * @return true if available
     */
    boolean isAvailable();
}
