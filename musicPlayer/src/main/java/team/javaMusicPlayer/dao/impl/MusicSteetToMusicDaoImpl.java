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
import team.javaMusicPlayer.model.MusicSheetToMusic;
import team.javaMusicPlayer.util.db.JdbcUtil;

/**
 * @author sky
 *
 */
public class MusicSteetToMusicDaoImpl implements TablesBase<MusicSheetToMusic> {

	@Override
	public boolean insert(MusicSheetToMusic obj) {
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		String sql = "insert into musicsheet_music values(null,?,?)";
		try {
			preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, obj.getMusicId());
			preStatement.setInt(2, obj.getMusicSheetId());
			return preStatement.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JdbcUtil.close(null, preStatement, conn);
		}
	}

	@Override
	public boolean update(MusicSheetToMusic nobj) {
		return false;
	}

	@Override
	public boolean delete(int id) {
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		String sql = "delete from musicsheet_music where id=?";
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
	public MusicSheetToMusic getById(int id) {
		MusicSheetToMusic ans=null;
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		String sql = "select * from musicsheet_music where id=?";
		try {
			preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, id);
			rs = preStatement.executeQuery();
			if (rs.next()) {
				ans = new MusicSheetToMusic();
				ans.setId(id);
				ans.setMusicId(rs.getInt("musicId"));
				ans.setMusicSheetId(rs.getInt("musicsheetId"));
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
	 * 查询歌单中的歌曲
	 * 
	 * @param musicsheetId
	 * @return
	 */
	public List<MusicSheetToMusic> getByMusicSheetId(int musicsheetId) {
		List<MusicSheetToMusic> musicSheetToMusics = new ArrayList<MusicSheetToMusic>();

		Connection conn = JdbcUtil.getConnect();
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		String sql = "select * from musicsheet_music where musicsheetId=?";
		try {
			preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, musicsheetId);
			rs = preStatement.executeQuery();
			while (rs.next()) {
				MusicSheetToMusic ans = new MusicSheetToMusic();
				ans.setId(rs.getInt("id"));
				ans.setMusicId(rs.getInt("musicId"));
				ans.setMusicSheetId(musicsheetId);
				musicSheetToMusics.add(ans);
			}
			return musicSheetToMusics;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(rs, preStatement, conn);
		}
	}
	
	/**
	 * 本首歌曲时候已经加入到本歌单
	 * @param msid
	 * @param misd
	 * @return
	 */
	public boolean musicIsExist(int msid,int mid) {
		Connection conn=JdbcUtil.getConnect();
		PreparedStatement preState=null;
		ResultSet rs=null;
		String sql="select id from musicsheet_music where musicId=? and musicsheetId=?";
		try {
			preState=conn.prepareStatement(sql);
			preState.setInt(1, mid);
			preState.setInt(2, msid);
			rs=preState.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JdbcUtil.close(rs, preState, conn);
		}
		
	}

}
