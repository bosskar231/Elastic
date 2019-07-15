//package com.auxo.Configuration;
//
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import io.dropwizard.Configuration;
//
//import javax.validation.constraints.NotNull;
//
//public class ConfigurationClass  extends Configuration {
//
//    @NotNull
//    private static final String host ;
//    @NotNull
//    private int port_one;
//    @JsonProperty
//    public String getHost() {
//        return host;
//    }
//
//    @JsonProperty
//    public int getPort_one() {
//        return port_one;
//    }
//    @JsonProperty
//    public void setPort_one(int port_one) {
//        this.port_one = port_one;
//    }
//    @JsonProperty
//    public int getPort_two() {
//        return port_two;
//    }
//    @JsonProperty
//    public void setPort_two(int port_two) {
//        this.port_two = port_two;
//    }
//    @JsonProperty
//    public String getScheme() {
//        return scheme;
//    }
//    @JsonProperty
//    public void setScheme(String scheme) {
//        this.scheme = scheme;
//    }
//
//    @NotNull
//    private  int port_two ;
//    @NotNull
//    private String scheme ;
//
//
//    public void print()
//    {
//        System.out.println("host is "+host);
//    }
//
//
//
//}
