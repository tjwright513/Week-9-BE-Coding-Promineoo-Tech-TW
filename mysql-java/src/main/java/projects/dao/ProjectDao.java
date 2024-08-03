package projects.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import projects.entity.Project;
import projects.exception.DbException;
import provided.util.DaoBase;

@SuppressWarnings("unused")

public class ProjectDao extends DaoBase{
	private static final String CATEGORY_TABLE = "category";
	private static final String MATERIAL_TABLE = "material";
    private static final String PROJECT_TABLE = "project";
	private static final String PROJECT_CATEGORY_TABLE = "project";
	private static final String STEP_TABLE = "step";
	


	public Project insertProject(Project project) {
      String sql = "INSERT INTO " + PROJECT_TABLE + " (project_name, estimated_hours, difficulty, notes) " + "VALUES " + "(?, ?, ?, ?, ?)";
      try (Connection conn = DbConnection.getConnection()){
    	  startTransaction(conn);
    	  try(PreparedStatement stmp = conn.prepareStatement(sql)) {
    		setParameter(stmp, 1, project.getProjectName(), String.class);
    		setParameter(stmp, 2, project.getEstimatedHours(), BigDecimal.class);
    		setParameter(stmp, 3, project.getActualHours(), BigDecimal.class);
    		setParameter(stmp, 4, project.getDifficulty(), Integer.class);
    		setParameter(stmp, 5, project.getNotes(), String.class);
    		
    		stmp.executeUpdate();
    		
    		Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
    		commitTransaction(conn);
    		
    		project.setProjectId(projectId);
    		return project;
    		
    	  }
    	  catch(Exception e) {
    		  rollbackTransaction(conn);
    		  throw new DbException(e);
    	  }  
      }
      catch(SQLException e) {
    	  throw new DbException(e);
      }
	}
}
