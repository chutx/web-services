package my.training.ws.service.impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import my.training.ws.domain.Team;
import my.training.ws.util.TeamsUtility;

@WebService
public class Teams {

	private TeamsUtility utils;

	public Teams() {
		utils = new TeamsUtility();
		utils.make_test_teams();
	}
	
	@WebMethod
	public Team getTeam(String name){
		return utils.getTeam(name);
	}
	
	@WebMethod
	public List<Team> getTeams(){
		return utils.getTeams();
	}

}
