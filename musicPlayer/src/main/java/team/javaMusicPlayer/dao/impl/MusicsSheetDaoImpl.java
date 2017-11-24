/**
 * 
 */
package team.javaMusicPlayer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.javaMusicPlayer.dao.TablesBase;
import team.javaMusicPlayer.model.MusicSheet;
import team.javaMusicPlayer.util.db.JdbcUtil;

/**
 * @author sky
 *
 */
public class MusicsSheetDaoImpl implements TablesBase<MusicSheet> {

	@Override
	public boolean insert(MusicSheet obj) {
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		String sql = "insert into musicsheet values(?,?,?,?,?,?)";
		try {
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, obj.getUuid());
			preStatement.setString(2, obj.getName());
			preStatement.setString(3, obj.getCreatorId());
			preStatement.setString(4, obj.getCreator());
			preStatement.setString(5, obj.getDateCreated());
			preStatement.setString(6, obj.getPicture());
			return preStatement.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JdbcUtil.close(null, preStatement, conn);
		}
	}

	@Override
	public boolean update(MusicSheet nobj) {
		return false;
	}

	@Override
	public boolean delete(int id) {
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		String sql = "delete from musicsheet where id=?";
		try {
			preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, id);
			return preStatement.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JdbcUtil.close(null, preStatement, conn);
		}
	}

	@Override
	public MusicSheet getById(int id) {
		MusicSheet ans = new MusicSheet();
		ans.setId(id);
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		String sql = "select * from musicsheet where id=?";
		try {
			preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, id);
			rs = preStatement.executeQuery();
			while (rs.next()) {
				ans.setUuid(rs.getString("uuid"));
				ans.setName(rs.getString("name"));
				ans.setCreatorId(rs.getString("creatorId"));
				ans.setCreator(rs.getString("creator"));
				ans.setDateCreated(rs.getString("dateCreated"));
				ans.setPicture(rs.getString("pictureUrl"));
			}
			return ans;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(rs, preStatement, conn);
		}
	}
	
	/**
	 * 获取某人的歌单 
	 * @param creatorId
	 * @return 某人歌单列表
	 */
	public List<MusicSheet> getByCreatorId(String creatorId) {
		List<MusicSheet> anSheets= new ArrayList<MusicSheet>();
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		String sql = "select * from musicsheet where creatorId=?";
		try {
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, creatorId);
			rs = preStatement.executeQuery();
			while (rs.next()) {
				MusicSheet ans=new MusicSheet();
				ans.setId(rs.getInt("id"));
				ans.setUuid(rs.getString("uuid"));
				ans.setName(rs.getString("name"));
				ans.setCreatorId(rs.getString("creatorId"));
				ans.setCreator(rs.getString("creator"));
				ans.setDateCreated(rs.getString("dateCreated"));
				ans.setPicture(rs.getString("pictureUrl"));
				anSheets.add(ans);
			}
			return anSheets;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(rs, preStatement, conn);
		}
	}
	
	/**
	 * 依据UUID获取歌单
	 * @param uuid
	 * @return
	 */
	public MusicSheet getByUuid(String uuid) {
		MusicSheet ans = new MusicSheet();
		ans.setUuid(uuid);
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		String sql = "select * from musicsheet where uuid=?";
		try {
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, uuid);
			rs = preStatement.executeQuery();
			while (rs.next()) {
				ans.setId(rs.getInt("id"));
				ans.setName(rs.getString("name"));
				ans.setCreatorId(rs.getString("creatorId"));
				ans.setCreator(rs.getString("creator"));
				ans.setDateCreated(rs.getString("dateCreated"));
				ans.setPicture(rs.getString("pictureUrl"));
			}
			return ans;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(rs, preStatement, conn);
		}
	}

}
