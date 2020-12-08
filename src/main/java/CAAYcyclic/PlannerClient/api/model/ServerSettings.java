/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.api.model;

/**
 *
 * @author Youssef
 */
public class ServerSettings {
    private final String host = "127.0.0.1";
    private final Integer port = 8080;
    private final String scheme = "http";
    private final String path = "";

    public String getUrl() {
        return scheme + "://" + host + ":" + port +path;
    }
}
