//package com.Health.bmi.CacheConfig;
//
//import com.Health.bmi.model.Data;
//import com.Health.bmi.repository.DataRepository;
//import com.Health.bmi.service.InterfaceImplementation.DataService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//@EnableCaching
//public class CacheConfiguration {
//        @Autowired
//    private CacheManager cacheManager;
//        @Autowired
//        private DataService dataService;
//        public void preloadCache(){
//            Cache cache = cacheManager.getCache("MyCache");
//
//        }
//}
