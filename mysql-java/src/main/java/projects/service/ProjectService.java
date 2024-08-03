package projects.service;

import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;



public class ProjectService {
   private ProjectDao projectDao = new ProjectDao();

public ProjectDao getProjectDao() {
	return projectDao;
}

public void setProjectDao(ProjectDao projectDao) {
	this.projectDao = projectDao;
}

public Project addProject(Project project) throws DbException {
	;
	try {
		return projectDao.insertProject(project);
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return project;
}
		
}
   
   
