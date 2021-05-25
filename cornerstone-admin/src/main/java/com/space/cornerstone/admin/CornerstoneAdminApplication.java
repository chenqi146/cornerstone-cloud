/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.space.cornerstone.admin;

import cn.hutool.core.util.StrUtil;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Spring Boot Admin 启动类
 **/
@Slf4j
@Configuration
@EnableAdminServer
@SpringBootApplication
public class CornerstoneAdminApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CornerstoneAdminApplication.class, args);

        Environment environment = context.getBean(Environment.class);
        String serverPort = environment.getProperty("server.port");
        InetAddress localHost = null;
        try {
            localHost = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        }
        String ip = localHost == null ? "http://localhost" : localHost.getHostAddress();
        log.info("Spring Boot Admin: " + ip + StrUtil.COLON + serverPort);
    }

}
