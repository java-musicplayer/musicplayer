/**
 * 
 */
package team.javaMusicPlayer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import team.javaMusicPlayer.dao.TablesBase;
import team.javaMusicPlayer.model.Music;
import team.javaMusicPlayer.util.db.JdbcUtil;

/** 
 * @author sky
 *
 */
public class MusicDaoImpl implements TablesBase<Music> {

	@Override
	public boolean insert(Music obj) {
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		String sql = "insert into music values(?,?,?,?)";
		try {
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, obj.getMd5value());
			preStatement.setString(2, obj.getName());
			preStatement.setString(3, obj.getSinger());
			preStatement.setString(4, obj.getMusicUrl());
			return preStatement.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JdbcUtil.close(null, preStatement, conn);
		}

	}

	@Override
	public boolean update(Music nobj) {

		return false;
	}

	@Override
	public boolean delete(int id) {
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		String sql = "delete from music where id=?";
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
	public Music getById(int id) {
		Music ans = new Music();
		ans.setId(id);
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		String sql = "select * from music where id=?";
		try {
			preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, id);
			rs = preStatement.executeQuery();
			while (rs.next()) {
				ans.setMd5value(rs.getString("md5value"));
				ans.setName(rs.getString("name"));
				ans.setSinger(rs.getString("singer"));
				ans.setMusicUrl(rs.getString("musicUrl"));
			}
			return ans;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(rs, preStatement, conn);
		}

	}

	public Music getByMd5Value(String md5Value) {
		Music ans = new Music();
		ans.setMd5value(md5Value);
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		String sql = "select * from music where md5value=?";
		try {
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, md5Value);
			rs = preStatement.executeQuery();
			while (rs.next()) {
				ans.setId(rs.getInt("id"));
				ans.setName(rs.getString("name"));
				ans.setSinger(rs.getString("singer"));
				ans.setMusicUrl(rs.getString("musicUrl"));
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
