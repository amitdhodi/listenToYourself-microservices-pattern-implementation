package com.example.listenToYourself.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {
    @Value("${couchbase.cluster.bucketName}")
    private String bucketName;

    @Value("${couchbase.cluster.ip}")
    private String ip;


    @Value("${couchbase.cluster.password}")
    private String password;


    @Override
    protected String getBucketName() {
        return bucketName;
    }

    @Override
    protected String getBucketPassword() {
        return this.password;
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList(ip);
    }

}
