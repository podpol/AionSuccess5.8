package mysql5;

import com.aionemu.commons.database.DatabaseFactory;
import com.aionemu.gameserver.dao.BaseDAO;
import com.aionemu.gameserver.dao.MySQL5DAOUtils;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.base.BaseLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQL5BaseDAO extends BaseDAO
{
    private static final Logger log = LoggerFactory.getLogger(MySQL5BaseDAO.class);
	
    public static final String SELECT_QUERY = "SELECT * FROM `base_location`";
    public static final String UPDATE_QUERY = "UPDATE `base_location` SET `race` = ? WHERE `id` = ?";
    public static final String INSERT_QUERY = "INSERT INTO `base_location` (`id`, `race`) VALUES(?, ?)";
	
    @Override
    public boolean loadBaseLocations(final Map<Integer, BaseLocation> locations) {
        boolean success = true;
        Connection con = null;
        List<Integer> loaded = new ArrayList<Integer>();
        PreparedStatement stmt = null;
        try {
            con = DatabaseFactory.getConnection();
            stmt = con.prepareStatement(SELECT_QUERY);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                BaseLocation loc = locations.get(resultSet.getInt("id"));
                loc.setRace(Race.valueOf(resultSet.getString("race")));
                loaded.add(loc.getId());
            }
            resultSet.close();
        } catch (Exception e) {
            log.warn("Error loading Base informaiton from database: " + e.getMessage(), e);
            success = false;
        } finally {
            DatabaseFactory.close(stmt, con);
        } for (Map.Entry<Integer, BaseLocation> entry : locations.entrySet()) {
            BaseLocation sLoc = entry.getValue();
            if (!loaded.contains(sLoc.getId())) {
                insertBaseLocation(sLoc);
            }
        }
        return success;
    }
	
    @Override
    public boolean updateBaseLocation(final BaseLocation locations) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DatabaseFactory.getConnection();
            stmt = con.prepareStatement(UPDATE_QUERY);
            stmt.setString(1, locations.getRace().toString());
            stmt.setInt(2, locations.getId());
            stmt.execute();
        } catch (Exception e) {
            log.error("Error update Base Location: " + "id: " + locations.getId() );
            return false;
        } finally {
            DatabaseFactory.close(stmt, con);
        }
        return true;
    }
	
    private boolean insertBaseLocation(final BaseLocation locations) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DatabaseFactory.getConnection();
            stmt = con.prepareStatement(INSERT_QUERY);
            stmt.setInt(1, locations.getId());
            stmt.setString(2, Race.NPC.toString());
            stmt.execute();
        } catch (Exception e) {
            log.error("Error insert Base Location: " + locations.getId(), e);
            return false;
        } finally {
            DatabaseFactory.close(stmt, con);
        }
        return true;
    }
	
    @Override
    public boolean supports(String databaseName, int majorVersion, int minorVersion) {
        return MySQL5DAOUtils.supports(databaseName, majorVersion, minorVersion);
    }
}